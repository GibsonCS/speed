# API - Speed service

## Functional Requirements (FR)

- Must allow user creation
- Must allow user deletion
- Must allow retrieving a user
- Must allow elevating user privileges
- Must allow updating a user

---

## Endpoints:

### Create User

**Request**  
POST /users

**Body**
{
"name": "string",
"lastname": "string",
"email": "string",
"password": "string"
}

**Response**
{
"id": "uuid"
}

---

### Get User

**Request**  
GET /users/{id}

**Response**
{
"id": "uuid",
"name": "string",
"lastname": "string",
"email": "string",
"roles": ["string"]
}

---

### Update User

**Request**  
PUT /users/{id}

**Body**
{
"name": "string",
"lastname": "string",
"email": "string"
}

**Response**
{
"id": "uuid"
}

---

### Delete User

**Request**  
DELETE /users/{id}

**Response**  
204 No Content

---

### Add Role to User

**Request**  
PUT /users/{id}/roles

**Body**
{
"roleId": "uuid"
}

**Response**  
204 No Content

---
