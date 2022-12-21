import { indexSearchParameterBundle, indexStructureDefinitionBundle } from '@medplum/core';
import { readJson } from '@medplum/definitions';
import { Bundle, SearchParameter } from '@medplum/fhirtypes';
import { FhirRequest, FhirRouter } from './fhirrouter';
import { FhirRepository, MemoryRepository } from './repo';

const router: FhirRouter = new FhirRouter();
const repo: FhirRepository = new MemoryRepository();

describe('FHIR Router', () => {
  beforeAll(() => {
    indexStructureDefinitionBundle(readJson('fhir/r4/profiles-types.json') as Bundle);
    indexStructureDefinitionBundle(readJson('fhir/r4/profiles-resources.json') as Bundle);
    indexSearchParameterBundle(readJson('fhir/r4/search-parameters.json') as Bundle<SearchParameter>);
  });

  test('Search invalid search parameter', async () => {
    const request: FhirRequest = {
      method: 'GET',
      url: '/ServiceRequest',
      body: {},
      params: {},
      query: {
        basedOn: 'ServiceRequest/123',
      },
    };
    const [outcome, resource] = await router.handleRequest(request, repo);
    console.log('outcome', outcome);
    console.log('resource', resource);
  });
});
