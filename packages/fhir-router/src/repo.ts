import { badRequest, deepClone, matchesSearchRequest, notFound, SearchRequest } from '@medplum/core';
import { Bundle, BundleEntry, Reference, Resource } from '@medplum/fhirtypes';
import { applyPatch, JsonPatchError, Operation } from 'fast-json-patch';

export interface FhirRepository {
  // Server
  createResource<T extends Resource>(resource: T): Promise<T>;

  readResource<T extends Resource>(resourceType: string, id: string): Promise<T>;

  readReference<T extends Resource>(reference: Reference<T>): Promise<T>;

  /**
   * Returns resource history.
   *
   * Results are sorted with oldest versions last
   *
   * See: https://www.hl7.org/fhir/http.html#history
   *
   * @param resourceType The FHIR resource type.
   * @param id The FHIR resource ID.
   * @returns Operation outcome and a history bundle.
   */
  readHistory<T extends Resource>(resourceType: string, id: string): Promise<Bundle<T>>;

  readVersion<T extends Resource>(resourceType: string, id: string, vid: string): Promise<T>;

  updateResource<T extends Resource>(resource: T): Promise<T>;

  deleteResource(resourceType: string, id: string): Promise<void>;

  patchResource(resourceType: string, id: string, patch: Operation[]): Promise<Resource>;

  search<T extends Resource>(searchRequest: SearchRequest): Promise<Bundle<T>>;
}

export class MemoryRepository implements FhirRepository {
  readonly #resources: Record<string, Record<string, Resource>>;
  readonly #history: Record<string, Record<string, Resource[]>>;

  constructor() {
    this.#resources = {};
    this.#history = {};
  }

  createResource<T extends Resource>(resource: T): Promise<T> {
    const result = deepClone(resource);

    if (!result.id) {
      result.id = this.generateId();
    }

    if (!result.meta) {
      result.meta = {};
    }

    if (!result.meta?.versionId) {
      result.meta.versionId = this.generateId();
    }

    if (!result.meta?.lastUpdated) {
      result.meta.lastUpdated = new Date().toISOString();
    }

    const { resourceType, id } = result as { resourceType: string; id: string };

    if (!(resourceType in this.#resources)) {
      this.#resources[resourceType] = {};
    }

    if (!(resourceType in this.#history)) {
      this.#history[resourceType] = {};
    }

    if (!(id in this.#history[resourceType])) {
      this.#history[resourceType][id] = [];
    }

    this.#resources[resourceType][id] = result;
    this.#history[resourceType][id].push(result);
    return Promise.resolve(deepClone(result));
  }

  updateResource<T extends Resource>(resource: T): Promise<T> {
    const result = deepClone(resource);
    if (result.meta) {
      if (result.meta.versionId) {
        delete result.meta.versionId;
      }
      if (result.meta.lastUpdated) {
        delete result.meta.lastUpdated;
      }
    }
    return this.createResource(result);
  }

  async patchResource(resourceType: string, id: string, patch: Operation[]): Promise<Resource> {
    const resource = await this.readResource(resourceType, id);

    let patchResult;
    try {
      patchResult = applyPatch(resource, patch, true);
    } catch (err) {
      const patchError = err as JsonPatchError;
      const message = patchError.message?.split('\n')?.[0] || 'JSONPatch error';
      throw badRequest(message);
    }

    const patchedResource = patchResult.newDocument;
    return this.updateResource(patchedResource);
  }

  async readResource<T extends Resource>(resourceType: string, id: string): Promise<T> {
    const resource = this.#resources?.[resourceType]?.[id] as T | undefined;
    if (!resource) {
      throw notFound;
    }
    return deepClone(resource);
  }

  async readReference<T extends Resource>(reference: Reference<T>): Promise<T> {
    const parts = reference.reference?.split('/');
    if (!parts || parts.length !== 2) {
      throw badRequest('Invalid reference');
    }
    return this.readResource(parts[0], parts[1]);
  }

  async readHistory<T extends Resource>(resourceType: string, id: string): Promise<Bundle<T>> {
    await this.readResource(resourceType, id);
    return {
      resourceType: 'Bundle',
      type: 'history',
      entry: ((this.#history?.[resourceType]?.[id] ?? []) as T[])
        .reverse()
        .map((version) => ({ resource: deepClone(version) })),
    };
  }

  async readVersion<T extends Resource>(resourceType: string, id: string, versionId: string): Promise<T> {
    await this.readResource(resourceType, id);
    const version = this.#history?.[resourceType]?.[id]?.find((v) => v.meta?.versionId === versionId) as T | undefined;
    if (!version) {
      throw notFound;
    }
    return deepClone(version);
  }

  async search<T extends Resource>(searchRequest: SearchRequest): Promise<Bundle<T>> {
    const { resourceType } = searchRequest;
    const resources = this.#resources[resourceType] ?? {};
    const result = Object.values(resources).filter((resource) => matchesSearchRequest(resource, searchRequest));
    return {
      resourceType: 'Bundle',
      type: 'searchset',
      entry: result.map((resource) => ({ resource: deepClone(resource) })) as BundleEntry<T>[],
      total: result.length,
    };
  }

  async deleteResource(resourceType: string, id: string): Promise<void> {
    if (!this.#resources?.[resourceType]?.[id]) {
      throw notFound;
    }
    delete this.#resources[resourceType][id];
  }

  private generateId(): string {
    // Cross platform random UUID generator
    // Note that this is not intended for production use, but rather for testing
    // This should be replaced when crypto.randomUUID is fully supported
    // https://stackoverflow.com/revisions/2117523/28
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
      const r = (Math.random() * 16) | 0;
      const v = c === 'x' ? r : (r & 0x3) | 0x8;
      return v.toString(16);
    });
  }
}
