import { allOk, createReference, indexSearchParameterBundle, indexStructureDefinitionBundle } from '@medplum/core';
import { readJson } from '@medplum/definitions';
import { Bundle, Encounter, Patient, SearchParameter } from '@medplum/fhirtypes';
import { FhirRequest } from './fhirrouter';
import { getRootSchema, graphqlHandler } from './graphql';
import { MemoryRepository } from './repo';

describe('GraphQL', () => {
  beforeAll(() => {
    indexStructureDefinitionBundle(readJson('fhir/r4/profiles-types.json') as Bundle);
    indexStructureDefinitionBundle(readJson('fhir/r4/profiles-resources.json') as Bundle);
    indexSearchParameterBundle(readJson('fhir/r4/search-parameters.json') as Bundle<SearchParameter>);
    getRootSchema();
  });

  test('Read by ID', async () => {
    const repo = new MemoryRepository();

    const patient = await repo.createResource<Patient>({
      resourceType: 'Patient',
      name: [{ given: ['John'], family: 'Doe' }],
    });

    const request: FhirRequest = {
      method: 'POST',
      url: '/fhir/R4/$graphql',
      query: {},
      params: {},
      body: {
        query: `{ Patient(id: "${patient.id}") { resourceType id meta { versionId lastUpdated } name { given family } } }`,
      },
    };

    const result = await graphqlHandler(request, repo);
    expect(result).toBeDefined();
    expect(result.length).toBe(2);
    expect(result[0]).toMatchObject(allOk);

    const data = (result?.[1] as any).data;
    expect(data).toMatchObject({ Patient: patient });
  });

  // test('Read by ID not found', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       Patient(id: "${randomUUID()}") {
  //         id
  //         name { given }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.Patient).toBeNull();
  //   expect(res.body.errors[0].message).toEqual('Not found');
  // });

  // test('Search', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       PatientList(name: "Smith") {
  //         id
  //         name { given }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.PatientList).toBeDefined();
  // });

  // test('Search with _id', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       PatientList(_id: "${patient.id}") {
  //         id
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.PatientList).toBeDefined();
  //   expect(res.body.data.PatientList.length).toBe(1);
  // });

  // test('Search with _count', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       EncounterList(_count: 1) {
  //         id
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.EncounterList).toBeDefined();
  //   expect(res.body.data.EncounterList.length).toBe(1);
  // });

  // test('Search with based-on', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       EncounterList(based_on: "${getReferenceString(serviceRequest)}") {
  //         id
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.EncounterList).toBeDefined();
  //   expect(res.body.data.EncounterList.length).toBe(1);
  // });

  // test('Sort by _lastUpdated asc', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       EncounterList(_sort: "_lastUpdated") {
  //         id
  //         meta { lastUpdated }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.EncounterList).toBeDefined();
  //   expect(res.body.data.EncounterList.length >= 2).toBe(true);

  //   const e1 = res.body.data.EncounterList[0];
  //   const e2 = res.body.data.EncounterList[1];
  //   expect(e1.meta.lastUpdated.localeCompare(e2.meta.lastUpdated)).toBeLessThanOrEqual(0);
  // });

  // test('Sort by _lastUpdated desc', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       EncounterList(_sort: "-_lastUpdated") {
  //         id
  //         meta { lastUpdated }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.EncounterList).toBeDefined();
  //   expect(res.body.data.EncounterList.length >= 2).toBe(true);

  //   const e1 = res.body.data.EncounterList[0];
  //   const e2 = res.body.data.EncounterList[1];
  //   expect(e1.meta.lastUpdated.localeCompare(e2.meta.lastUpdated)).toBeGreaterThanOrEqual(0);
  // });

  test('Read resource by reference', async () => {
    const repo = new MemoryRepository();

    const patient = await repo.createResource<Patient>({
      resourceType: 'Patient',
      name: [{ given: ['John'], family: 'Doe' }],
    });

    const encounter = await repo.createResource<Encounter>({
      resourceType: 'Encounter',
      subject: createReference(patient),
    });

    const request: FhirRequest = {
      method: 'POST',
      url: '/fhir/R4/$graphql',
      query: {},
      params: {},
      body: {
        query: `
        {
          Encounter(id: "${encounter.id}") {
            id
            meta {
              lastUpdated
            }
            subject {
              reference
              resource {
                ... on Patient {
                  id
                  name {
                    given
                    family
                  }
                }
              }
            }
          }
        }
    `,
      },
    };

    const result = await graphqlHandler(request, repo);
    expect(result).toBeDefined();
    expect(result.length).toBe(2);
    expect(result[0]).toMatchObject(allOk);

    const data = (result?.[1] as any).data;
    expect(data.Encounter.id).toEqual(encounter.id);
    expect(data.Encounter.subject.resource).toBeDefined();
    expect(data.Encounter.subject.resource.id).toEqual(patient.id);
    expect(data.Encounter.subject.resource.name[0].given[0]).toEqual('John');
  });

  // test('Read resource by reference not found', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //       {
  //         Encounter(id: "${encounter2.id}") {
  //           id
  //           meta {
  //             lastUpdated
  //           }
  //           subject {
  //             id
  //             reference
  //             resource {
  //               __typename
  //               ... on Patient {
  //                 name {
  //                   given
  //                   family
  //                 }
  //               }
  //             }
  //           }
  //         }
  //       }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.Encounter).toBeDefined();
  //   expect(res.body.data.Encounter.subject.resource).toBeNull();
  // });

  // test('Reverse lookup with _reference', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       PatientList(_count: 1) {
  //         id
  //         ObservationList(_reference: subject) {
  //           id
  //           status
  //           code {
  //             text
  //           }
  //         }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(200);
  //   expect(res.body.data.PatientList).toBeDefined();
  //   expect(res.body.data.PatientList[0].ObservationList).toBeDefined();
  // });

  // test('Reverse lookup without _reference', async () => {
  //   const res = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //     {
  //       PatientList(_count: 1) {
  //         id
  //         ObservationList(subject: "xyz") {
  //           id
  //           status
  //           code {
  //             text
  //           }
  //         }
  //       }
  //     }
  //   `,
  //     });
  //   expect(res.status).toBe(400);
  // });

  // test('Max depth', async () => {
  //   // The definition of "depth" is a little abstract in GraphQL
  //   // We use "selection", which, in a well formatted query, is the level of indentation

  //   // 8 levels of depth is ok
  //   const res1 = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //       {
  //         ServiceRequestList {
  //           id
  //           basedOn {
  //             resource {
  //               ...on ServiceRequest {
  //                 id
  //                 basedOn {
  //                   resource {
  //                     ...on ServiceRequest {
  //                       id
  //                     }
  //                   }
  //                 }
  //               }
  //             }
  //           }
  //         }
  //       }
  //   `,
  //     });
  //   expect(res1.status).toBe(200);

  //   // 10 levels of nesting is too much
  //   const res2 = await request(app)
  //     .post('/fhir/R4/$graphql')
  //     .set('Authorization', 'Bearer ' + accessToken)
  //     .set('Content-Type', 'application/json')
  //     .send({
  //       query: `
  //       {
  //         ServiceRequestList {
  //           id
  //           basedOn {
  //             resource {
  //               ...on ServiceRequest {
  //                 id
  //                 basedOn {
  //                   resource {
  //                     ...on ServiceRequest {
  //                       id
  //                       basedOn {
  //                         resource {
  //                           ...on ServiceRequest {
  //                             id
  //                           }
  //                         }
  //                       }
  //                     }
  //                   }
  //                 }
  //               }
  //             }
  //           }
  //         }
  //       }
  //   `,
  //     });
  //   expect(res2.status).toBe(400);
  //   expect(res2.body.issue[0].details.text).toEqual('Field "resource" exceeds max depth (depth=9, max=8)');
  // });
});
