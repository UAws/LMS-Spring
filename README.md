# LMS-Spring



Learning Management System

Spring-boot + Vue reimplementation of OOP-Project https://github.com/UAws/OOP-Project

front / back-end separated rest web application

# Api Documentation
Api Documentation

## Version: 0.0.1-snapshot

### Terms of service
urn:tos


**License:** GNU General Public License v3.0

### /people/

#### GET
##### Summary:

getAllPeople

##### Parameters

| Name          | Located in | Description   | Required | Schema  |
| ------------- | ---------- | ------------- | -------- | ------- |
| isShowSubject | query      | isShowSubject | No       | boolean |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

#### POST
##### Summary:

createOrUpdatePeople

##### Parameters

| Name      | Located in | Description | Required | Schema                  |
| --------- | ---------- | ----------- | -------- | ----------------------- |
| peopleDTO | body       | peopleDTO   | Yes      | [PeopleDTO](#PeopleDTO) |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 201  | Created      |                                 |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

### /people/id/{id}

#### DELETE
##### Summary:

deletePerson

##### Parameters

| Name | Located in | Description | Required | Schema  |
| ---- | ---------- | ----------- | -------- | ------- |
| id   | path       | id          | Yes      | integer |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 204  | No Content   |                                 |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |

### /people/id/{id}/

#### GET
##### Summary:

getAllPeopleByID

##### Parameters

| Name          | Located in | Description   | Required | Schema  |
| ------------- | ---------- | ------------- | -------- | ------- |
| id            | path       | id            | Yes      | integer |
| isShowSubject | query      | isShowSubject | No       | boolean |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

### /people/role/{roles}

#### GET
##### Summary:

getAllPeopleByRoleID

##### Parameters

| Name          | Located in | Description   | Required | Schema  |
| ------------- | ---------- | ------------- | -------- | ------- |
| isShowSubject | query      | isShowSubject | No       | boolean |
| roles         | path       | roles         | Yes      | integer |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

### /subject/

#### GET
##### Summary:

getAllSubject

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

#### POST
##### Summary:

createOrUpdateSubject

##### Parameters

| Name    | Located in | Description | Required | Schema                    |
| ------- | ---------- | ----------- | -------- | ------------------------- |
| subject | body       | subject     | Yes      | [SubjectDTO](#SubjectDTO) |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 201  | Created      |                                 |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

### /subject/id/{id}

#### GET
##### Summary:

getAllSubjectByID

##### Parameters

| Name | Located in | Description | Required | Schema  |
| ---- | ---------- | ----------- | -------- | ------- |
| id   | path       | id          | Yes      | integer |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |
| 404  | Not Found    |                                 |

#### DELETE
##### Summary:

deleteSubject

##### Parameters

| Name | Located in | Description | Required | Schema  |
| ---- | ---------- | ----------- | -------- | ------- |
| id   | path       | id          | Yes      | integer |

##### Responses

| Code | Description  | Schema                          |
| ---- | ------------ | ------------------------------- |
| 200  | OK           | [ApiResultBean](#ApiResultBean) |
| 204  | No Content   |                                 |
| 401  | Unauthorized |                                 |
| 403  | Forbidden    |                                 |

### Models


#### ApiResultBean

| Name | Type    | Description | Required |
| ---- | ------- | ----------- | -------- |
| code | integer |             | No       |
| data | object  |             | No       |
| msg  | string  |             | No       |

#### PeopleDTO

| Name             | Type                                    | Description | Required |
| ---------------- | --------------------------------------- | ----------- | -------- |
| belongedSubjects | [ [SubjectEmbedDTO](#SubjectEmbedDTO) ] |             | No       |
| isActive         | boolean                                 |             | Yes      |
| name             | string                                  |             | Yes      |
| password         | string                                  |             | Yes      |
| title            | string                                  |             | Yes      |
| userId           | integer                                 |             | No       |
| userLevel        | integer                                 |             | Yes      |

#### PeopleEmbedDTO

| Name      | Type    | Description | Required |
| --------- | ------- | ----------- | -------- |
| isActive  | boolean |             | No       |
| name      | string  |             | No       |
| password  | string  |             | No       |
| title     | string  |             | No       |
| userId    | integer |             | No       |
| userLevel | integer |             | No       |

#### SubjectDTO

| Name            | Type                                  | Description | Required |
| --------------- | ------------------------------------- | ----------- | -------- |
| containedPeople | [ [PeopleEmbedDTO](#PeopleEmbedDTO) ] |             | No       |
| name            | string                                |             | Yes      |
| subjectId       | integer                               |             | No       |

#### SubjectEmbedDTO

| Name      | Type    | Description | Required |
| --------- | ------- | ----------- | -------- |
| name      | string  |             | No       |
| subjectId | integer |             | No       |
