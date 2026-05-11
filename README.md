# Exemplo de microserviços (Spring Boot)

Conjunto de serviços independentes com **API Gateway** (WebFlux), autenticação JWT no **usuario-service**, e integração HTTP do **pedido-service** com **cliente** e **produto**. Todos os serviços com JPA usam **PostgreSQL** no mesmo host (ex.: `localhost:5433`) e o banco **`sptech-db-mcs`**, com `spring.jpa.hibernate.ddl-auto=update` para desenvolvimento com várias aplicações ao mesmo tempo.

## Tecnologias

- Java **21**
- Spring Boot **3.2.5**
- Spring Data JPA + **PostgreSQL**
- Spring WebFlux (gateway e WebClient no pedido)
- Maven

## Pré-requisitos

- JDK 21
- Maven 3.9+
- PostgreSQL acessível em `localhost:5433` (ou ajuste as URLs em cada `application.properties`)
- Banco criado antes de subir os serviços:

```sql
CREATE DATABASE "sptech-db-mcs";
```

(Credenciais padrão do exemplo: usuário `postgres`, senha `spt1234` — altere em produção.)

## Módulos e portas

| Módulo           | Porta | Função |
|------------------|-------|--------|
| cliente-service  | 8080  | CRUD de clientes (`/clientes`) |
| pedido-service   | 8081  | Pedidos; valida cliente e enriquece com produtos via HTTP |
| produto-service  | 8082  | CRUD de produtos (`/produtos`) |
| usuario-service  | 8084  | Registro/login JWT (`/auth/*`) |
| api-gateway      | 8088  | Proxy `/api/{serviço}/...` para os backends |

## Postman

Importe a coleção em [postman/Simple-Microservices.postman_collection.json](postman/Simple-Microservices.postman_collection.json) (Postman: **Import** → arquivo). Ela inclui chamadas **diretas** por porta e rotas **via gateway**; variáveis `baseCliente`, `basePedido`, etc. ficam na própria coleção. Para o gateway, faça antes o **Login** em `04-Direto - Usuario` (porta 8084) para gravar o `token` (o filtro JWT do gateway exige `Authorization: Bearer` em todas as rotas).

Pacotes seguem **MVC por domínio**, por exemplo `com.exemplo.cliente.controller`, `com.exemplo.produto.service`, `com.exemplo.pedido.entity`.

## Ordem recomendada para subir

1. PostgreSQL (porta **5433**) com o banco `sptech-db-mcs` criado.
2. **cliente-service** e **produto-service** (o pedido chama esses dois).
3. **pedido-service**.
4. **usuario-service** (se for testar autenticação).
5. **api-gateway** (opcional para rotear tudo pela porta 8088).

Em cada pasta do serviço:

```bash
mvn spring-boot:run
```

Ou instale na ordem dos artefatos compartilhados e rode o pedido:

```bash
mvn clean install
cd cliente-service && mvn spring-boot:run
```

Na primeira vez, instale **cliente-service** e **produto-service** no repositório local Maven antes do **pedido-service**, pois o `pedido-service` declara dependência de compilação nesses dois módulos (reutilização dos DTOs para deserialização JSON).

## Testes manuais (integração)

Não há suíte `*Test.java` no repositório; o fluxo típico é manual (curl, Postman ou HTTP do IDE).

1. **Cliente** — `POST http://localhost:8080/clientes` com corpo JSON `{"nome":"...","email":"..."}` e anote o `id`.
2. **Produto** — `POST http://localhost:8082/produtos` com `{"nome":"...","preco":10.0}` e anote o `id`.
3. **Pedido** — `POST http://localhost:8081/pedidos` com um corpo compatível com a entidade Pedido, por exemplo:

```json
{
  "descricao": "Pedido teste",
  "clienteId": 1,
  "produtoIds": [1]
}
```

O serviço confere se o cliente existe chamando o cliente-service antes de persistir.

4. **Listar / detalhe** — `GET http://localhost:8081/pedidos` e `GET http://localhost:8081/pedidos/{id}` (o GET por id busca os produtos no produto-service e monta o DTO de resposta).

### Via API Gateway

Exemplos (o gateway remove o prefixo `/api/{serviço}` e encaminha o restante ao backend):

- `http://localhost:8088/api/clientes/...`
- `http://localhost:8088/api/pedidos/...`
- `http://localhost:8088/api/produtos/...`
- `http://localhost:8088/api/usuarios/...` (mapeado para o usuario-service na porta 8084)

## Docker (PostgreSQL de exemplo)

```bash
docker run --name postgres-sptech -e POSTGRES_PASSWORD=spt1234 -e "POSTGRES_DB=sptech-db-mcs" -p 5433:5432 -d postgres:16
```

Ajuste `spring.datasource.url` se usar outro host, porta ou nome de banco.
