---
title: Location
sidebar_position: 352
---

# Location

Details and position information for a physical place where services are provided and resources and participants may be
  stored, found, contained, or accommodated.

## Properties

| Name | Card | Type | Description |
| --- | --- | --- | --- |
| id | 0..1 | string | Logical id of this artifact
| meta | 0..1 | Meta | Metadata about the resource
| implicitRules | 0..1 | uri | A set of rules under which this content was created
| language | 0..1 | code | Language of the resource content
| text | 0..1 | Narrative | Text summary of the resource, for human interpretation
| contained | 0..* | Resource | Contained, inline Resources
| extension | 0..* | Extension | Additional content defined by implementations
| modifierExtension | 0..* | Extension | Extensions that cannot be ignored
| identifier | 0..* | Identifier | Unique code or number identifying the location to its users
| status | 0..1 | code | active \| suspended \| inactive
| operationalStatus | 0..1 | Coding | The operational status of the location (typically only for a bed/room)
| name | 0..1 | string | Name of the location as used by humans
| alias | 0..* | string | A list of alternate names that the location is known as, or was known as, in the past
| description | 0..1 | string | Additional details about the location that could be displayed as further information to identify the location beyond its name
| mode | 0..1 | code | instance \| kind
| type | 0..* | CodeableConcept | Type of function performed
| telecom | 0..* | ContactPoint | Contact details of the location
| address | 0..1 | Address | Physical location
| physicalType | 0..1 | CodeableConcept | Physical form of the location
| position | 0..1 | BackboneElement | The absolute geographic location
| managingOrganization | 0..1 | Reference | Organization responsible for provisioning and upkeep
| partOf | 0..1 | Reference | Another Location this one is physically a part of
| hoursOfOperation | 0..* | BackboneElement | What days/times during a week is this location usually open
| availabilityExceptions | 0..1 | string | Description of availability exceptions
| endpoint | 0..* | Reference | Technical endpoints providing access to services operated for the location

## Search Parameters

| Name | Type | Description | Expression
| --- | --- | --- | --- |
| address | string | A (part of the) address of the location | Location.address
| address-city | string | A city specified in an address | Location.address.city
| address-country | string | A country specified in an address | Location.address.country
| address-postalcode | string | A postal code specified in an address | Location.address.postalCode
| address-state | string | A state specified in an address | Location.address.state
| address-use | token | A use code specified in an address | Location.address.use
| endpoint | reference | Technical endpoints providing access to services operated for the location | Location.endpoint
| identifier | token | An identifier for the location | Location.identifier
| name | string | A portion of the location's name or alias | Location.name
| near | special | Search for locations where the location.position is near to, or within a specified distance of, the provided coordinates expressed as [latitude]\|[longitude]\|[distance]\|[units] (using the WGS84 datum, see notes). If the units are omitted, then kms should be assumed. If the distance is omitted, then the server can use its own discretion as to what distances should be considered near (and units are irrelevant)

Servers may search using various techniques that might have differing accuracies, depending on implementation efficiency.

Requires the near-distance parameter to be provided also | Location.position
| operational-status | token | Searches for locations (typically bed/room) that have an operational status (e.g. contaminated, housekeeping) | Location.operationalStatus
| organization | reference | Searches for locations that are managed by the provided organization | Location.managingOrganization
| partof | reference | A location of which this location is a part | Location.partOf
| status | token | Searches for locations with a specific kind of status | Location.status
| type | token | A code for the type of location | Location.type
