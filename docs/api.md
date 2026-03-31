# 📚 API - Speed Service

## 📌 Requisitos Funcionais (RF)

- Deve permitir a criação de usuário  
- Deve permitir a exclusão de usuário  
- Deve permitir a busca de um usuário  
- Deve permitir elevar o privilégio do usuário  
- Deve permitir a atualização de um usuário  

---

## 🌐 Endpoints:

### ➕ Criar Usuário

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

### 🔍 Buscar Usuário

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

### ✏️ Atualizar Usuário

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

### ❌ Deletar Usuário

**Request**  
DELETE /users/{id}

**Response**  
204 No Content

---

### 🔐 Adicionar Role ao Usuário

**Request**  
PUT /users/{id}/roles

**Body**
{
"roleId": "uuid"
}

**Response**  
204 No Content

---
