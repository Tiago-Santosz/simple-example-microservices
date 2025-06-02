# 🛡️ Sistema de Microserviços com Autenticação JWT

**Branch:** `feat/auth-getway`

Este repositório contém um sistema de microserviços com autenticação baseada em JWT, utilizando Spring Boot e um gateway de API.

---

## 📋 Sumário

- [Pré-requisitos](#pré-requisitos)
- [Iniciando os Microserviços](#iniciando-os-microserviços)
- [Fluxo de Uso](#fluxo-de-uso)
  - [1. Criar um Usuário](#1-criar-um-usuário)
  - [2. Fazer Login e Obter o Token JWT](#2-fazer-login-e-obter-o-token-jwt)
  - [3. Usar o Token nas Requisições](#3-usar-o-token-nas-requisições)
- [Exemplos de Requisição](#exemplos-de-requisição)
  - [Buscar um Pedido por ID](#buscar-um-pedido-por-id)
  - [Criar um Produto](#criar-um-produto)
- [Observações](#observações)
- [Logs e Depuração](#logs-e-depuração)

---

## ✅ Pré-requisitos

- Java 19 (ou Java 17/21 recomendado)
- Maven
- MySQL rodando localmente
- cURL ou Postman para testar as requisições

---

## ▶️ Iniciando os Microserviços

Execute os comandos abaixo em terminais separados ou em sequência para iniciar os serviços:

```bash
cd usuario-service
mvn spring-boot:run
```

```bash
cd produto-service
mvn spring-boot:run
```

```bash
cd pedido-service
mvn spring-boot:run
```

```bash
cd cliente-service
mvn spring-boot:run
```

```bash
cd gateway
mvn spring-boot:run
```

---

## 🔄 Fluxo de Uso

### 1. Criar um Usuário

**Endpoint:**

```
POST http://localhost:8084/api/usuarios
```

**Corpo da Requisição:**

```json
{
  "username": "teste",
  "password": "senha123"
}
```

**Exemplo cURL:**

```bash
curl -X POST http://localhost:8084/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"username":"teste","password":"senha123"}'
```

**Resposta Esperada:**

```json
{
  "id": 1,
  "username": "teste"
}
```

---

### 2. Fazer Login e Obter o Token JWT

**Endpoint:**

```
POST http://localhost:8084/api/login
```

**Corpo da Requisição:**

```json
{
  "username": "teste",
  "password": "senha123"
}
```

**Exemplo cURL:**

```bash
curl -X POST http://localhost:8084/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"teste","password":"senha123"}'
```

**Resposta Esperada:**

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

> ⚠️ **Importante:** Copie o token para usar nas requisições autenticadas com Bearer Token.

---

### 3. Usar o Token nas Requisições

Inclua o token JWT no cabeçalho da requisição:

```
Authorization: Bearer <seu_token_jwt>
```

**Formato de requisição via Gateway:**

```
http://localhost:8088/api/<nome-serviço>/<endpoint>
```

---

## 📦 Exemplos de Requisição

### Buscar um Pedido por ID

**Endpoint:**

```
GET http://localhost:8088/api/pedidos/pedidos/5
```

**Exemplo cURL:**

```bash
curl -X GET http://localhost:8088/api/pedidos/pedidos/5 \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..." \
  -H "Content-Type: application/json"
```

**Resposta Esperada:**

```json
{
  "id": 5,
  "clienteId": 1,
  "valorTotal": 100.0
}
```

---

### Criar um Produto

**Endpoint:**

```
POST http://localhost:8088/api/produtos/produtos
```

**Corpo da Requisição:**

```json
{
  "nome": "Produto Teste",
  "preco": 10.0
}
```

**Exemplo cURL:**

```bash
curl -X POST http://localhost:8088/api/produtos/produtos \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9..." \
  -H "Content-Type: application/json" \
  -d '{"nome":"Produto Teste","preco":10.0}'
```

**Resposta Esperada:**

```json
{
  "id": 1,
  "nome": "Produto Teste",
  "preco": 10.0
}
```

---

## 📝 Observações

- **Token JWT:** Expira em 1 hora. Refaça o login quando necessário.
- **Códigos de Erro Comuns:**
  - `401 Unauthorized`: Token ausente, inválido ou expirado.
  - `400 Bad Request`: Problemas com o corpo da requisição (JSON mal formatado).

---

## 🐞 Logs e Depuração

---