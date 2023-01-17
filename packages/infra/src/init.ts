/* eslint-disable no-constant-condition */
import { ACMClient, CertificateSummary, ListCertificatesCommand, RequestCertificateCommand } from '@aws-sdk/client-acm';
import { PutParameterCommand, SSMClient } from '@aws-sdk/client-ssm';
import { GetCallerIdentityCommand, STSClient } from '@aws-sdk/client-sts';
import { generateKeyPairSync, randomUUID } from 'crypto';
import { existsSync, writeFileSync } from 'fs';
import { resolve } from 'path';
import readline from 'readline';
import { MedplumInfraConfig } from './config';

main()
  .then(() => process.exit(0))
  .catch((err) => {
    console.error(err);
    process.exit(1);
  });

/**
 * Main entry point.
 * This is the Medplum infra config initialization script.
 */
async function main(): Promise<void> {
  const config = { apiPort: 8103 } as MedplumInfraConfig;

  const terminal = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  const print = (text: string): void => console.log(text);

  const header = (text: string): void => console.log('\n' + text + '\n');

  const ask = (text: string, defaultValue?: string): Promise<string> =>
    new Promise((resolve) => {
      terminal.question(text + (defaultValue ? ' [' + defaultValue + ']' : '') + ' ', (answer: string) => {
        resolve(answer || defaultValue || '');
      });
    });

  const yesOrNo = async (text: string): Promise<boolean> => {
    while (true) {
      const answer = (await ask(text + ' [y/n] ')).toLowerCase();
      if (answer === 'y') {
        return true;
      }
      if (answer === 'n') {
        return false;
      }
      print('Please answer "y" or "n".');
    }
  };

  print('Welcome to Medplum Infrastructure!');
  print('This script will help you configure your AWS account for Medplum.');
  print('We will ask a series of questions to generate your infra config file.');
  print('Some questions have default values in [square brackets], which you can accept by pressing Enter.');

  header('ENVIRONMENT NAME');
  print('Medplum deployments have a short environment name such as "prod", "staging", "alice", or "demo".');
  print('The environment name is used in multiple places:');
  print('  1. As part of config file names (i.e., medplum.demo.config.json)');
  print('  2. As the base of CloudFormation stack names (i.e., MedplumDemo)');
  print('  3. AWS Parameter Store keys (i.e., /medplum/demo/...)');
  config.name = await ask('What is your environment name?', 'demo');
  print('Using environment name "' + config.name + '"...');

  header('CONFIG FILE');
  print('Medplum Infrastructure will create a config file in the current directory.');
  const configFileName = await ask('What is the config file name?', `medplum.${config.name}.config.json`);
  if (existsSync(configFileName)) {
    print('Config file already exists.');
    if (!(await yesOrNo('Do you want to overwrite the config file?'))) {
      print('Exiting...');
      return;
    }
  }
  print('Using config file "' + configFileName + '"...');
  writeConfig(configFileName, config);

  header('AWS REGION');
  print('Most Medplum resources will be created in a single AWS region.');
  config.region = await ask('Enter your AWS region:', 'us-east-1');
  writeConfig(configFileName, config);

  header('AWS ACCOUNT NUMBER');
  print('Medplum Infrastructure will use your AWS account number to create AWS resources.');
  const currentAccountId = await getAccountId(config.region);
  print('Using the AWS CLI, your current account ID is: ' + currentAccountId);
  config.accountNumber = await ask('What is your AWS account number?', currentAccountId);
  writeConfig(configFileName, config);

  header('STACK NAME');
  print('Medplum will create a CloudFormation stack to manage AWS resources.');
  const defaultStackName = 'Medplum' + config.name.charAt(0).toUpperCase() + config.name.slice(1);
  config.stackName = await ask('Enter your CloudFormation stack name?', defaultStackName);
  writeConfig(configFileName, config);

  header('BASE DOMAIN NAME');
  print('Medplum deploys multiple subdomains for various services.');
  print('');
  print('For example, "api." for the REST API and "app." for the web application.');
  print('The base domain name is the common suffix for all subdomains.');
  print('');
  print('For example, if your base domain name is "example.com",');
  print('then the REST API will be "api.example.com".');
  print('');
  print('Note that you must own the base domain, and it must use Route53 DNS.');
  print('Medplum will create subdomains for you, but you must configure the base domain.');
  while (!config.domainName) {
    config.domainName = await ask('Enter your base domain name:');
  }
  writeConfig(configFileName, config);

  header('SUPPORT EMAIL');
  print('Medplum sends transactional emails to users.');
  print('For example, emails to new users or for password reset.');
  print('Medplum will use the support email address to send these emails.');
  print('Note that you must verify the support email address in SES.');
  const supportEmail = await ask('Enter your support email address:');

  header('API DOMAIN NAME');
  print('Medplum deploys a REST API for the backend services.');
  config.apiDomainName = await ask('Enter your REST API domain name:', 'api.' + config.domainName);
  writeConfig(configFileName, config);

  header('APP DOMAIN NAME');
  print('Medplum deploys a web application for the user interface.');
  config.appDomainName = await ask('Enter your web application domain name:', 'app.' + config.domainName);
  writeConfig(configFileName, config);

  header('STORAGE DOMAIN NAME');
  print('Medplum deploys a storage service for file uploads.');
  config.storageDomainName = await ask('Enter your storage domain name:', 'storage.' + config.domainName);
  writeConfig(configFileName, config);

  header('SSL CERTIFICATES');
  print(`Medplum will now check for existing SSL certificates for the subdomains in "${config.region}"...`);
  const allCerts = await listCertificates(config.region);
  print('Found ' + allCerts.length + ' certificate(s).');

  const doCert = async (certName: 'api' | 'app' | 'storage'): Promise<boolean> => {
    const subdomain = config[(certName + 'DomainName') as 'apiDomainName' | 'appDomainName' | 'storageDomainName'];
    const setting = (certName + 'SslCertArn') as 'apiSslCertArn' | 'appSslCertArn' | 'storageSslCertArn';
    const existingCert = allCerts.find((cert) => cert.DomainName === subdomain);
    let arn = undefined;
    print('');
    if (existingCert) {
      print(`Found existing certificate for "${subdomain}".`);
      arn = existingCert.CertificateArn as string;
    } else {
      print(`No existing certificate found for "${subdomain}".`);
      if (!(await yesOrNo('Do you want to request a new certificate?'))) {
        print('Exiting...');
        return false;
      }
      arn = await requestCert(config.region, subdomain);
      if (!arn) {
        print('Failed to request SSL certificate.');
        return false;
      }
    }
    print('Certificate ARN: ' + arn);
    config[setting] = arn;
    writeConfig(configFileName, config);
    return true;
  };

  for (const certName of ['api', 'app', 'storage'] as const) {
    if (!(await doCert(certName))) {
      return;
    }
  }

  header('SIGNING KEY');
  print('Medplum uses AWS CloudFront Presigned URLs for binary content such as file uploads.');
  if (!(await yesOrNo('Generate a signing key for the "storage" domain?'))) {
    print('Exiting...');
    return;
  }

  // Generate a signing key
  const passphrase = randomUUID();
  const signingKey = generateKeyPairSync('rsa', {
    modulusLength: 2048,
    publicKeyEncoding: {
      type: 'spki',
      format: 'pem',
    },
    privateKeyEncoding: {
      type: 'pkcs1',
      format: 'pem',
      cipher: 'aes-256-cbc',
      passphrase,
    },
  });
  config.storagePublicKey = signingKey.publicKey;
  writeConfig(configFileName, config);

  header('AWS PARAMETER STORE');
  print('Medplum uses AWS Parameter Store to store sensitive configuration values.');
  print('These values will be encrypted at rest.');
  print(`The values will be stored in the "/medplum/${config.name}" path.`);

  const serverParams = [
    ['port', config.apiPort],
    ['baseUrl', `https://${config.apiDomainName}/`],
    ['appBaseUrl', `https://${config.appDomainName}/`],
    ['storageBaseUrl', `https://${config.storageDomainName}/binary/`],
    ['signingKey', signingKey.privateKey],
    ['signingKeyPassphrase', passphrase],
    ['supportEmail', supportEmail],
  ];

  if (!(await yesOrNo('Write values to AWS Parameter Store?'))) {
    print('Exiting...');
    return;
  }

  for (const param of serverParams) {
    await setParameter(config.region, `/medplum/${config.name}/${param[0]}`, param[1].toString());
  }

  header('DONE!');
  print('Medplum configuration complete.');
  print('You can now proceed to deploying the Medplum infrastructure with CDK.');
  print('Run:');
  print('');
  print(`    npx cdk synth -c config=${configFileName}`);
  print('');
  print('See Medplum documentation for more information:');
  print('');
  print('    https://www.medplum.com/docs/self-hosting/install-on-aws');
  print('');
}

/**
 * Writes a config file to disk.
 * @param configFileName The config file name.
 * @param config The config file contents.
 */
function writeConfig(configFileName: string, config: MedplumInfraConfig): void {
  writeFileSync(resolve(configFileName), JSON.stringify(config, undefined, 2), 'utf-8');
}

/**
 * Returns the current AWS account ID.
 * This is used as the default value for the "accountNumber" config setting.
 * @param region The AWS region.
 * @returns The AWS account ID.
 */
async function getAccountId(region: string): Promise<string> {
  const client = new STSClient({ region });
  const command = new GetCallerIdentityCommand({});
  const response = await client.send(command);
  return response.Account as string;
}

/**
 * Returns a list of AWS Certificates.
 * This is used to find existing certificates for the subdomains.
 * @param region The AWS region.
 * @returns The list of AWS Certificates.
 */
async function listCertificates(region: string): Promise<CertificateSummary[]> {
  const client = new ACMClient({ region });
  const command = new ListCertificatesCommand({ MaxItems: 1000 });
  const response = await client.send(command);
  return response.CertificateSummaryList as CertificateSummary[];
}

/**
 * Requests an AWS Certificate.
 * @param region The AWS region.
 * @param domain The domain name.
 * @returns The AWS Certificate ARN on success, or undefined on failure.
 */
async function requestCert(region: string, domain: string): Promise<string | undefined> {
  const client = new ACMClient({ region });
  const command = new RequestCertificateCommand({
    DomainName: domain,
    ValidationMethod: 'DNS',
  });
  const response = await client.send(command);
  return response.CertificateArn;
}

/**
 * Sets a parameter in AWS Parameter Store.
 * @param region The AWS region.
 * @param key The parameter key.
 * @param value The parameter value.
 */
async function setParameter(region: string, key: string, value: string): Promise<void> {
  const client = new SSMClient({ region });
  const command = new PutParameterCommand({
    Name: key,
    Value: value,
    Type: 'SecureString',
  });
  await client.send(command);
}
