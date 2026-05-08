# Simple Example Microservices

Projeto com 3 microservicos Spring Boot modernizados com Java 21, PostgreSQL, validacao Jakarta, Swagger/OpenAPI, logs estruturados e auditoria persistida em banco.

## Servicos e portas

- `cliente-service`: `http://localhost:8080`
- `pedido-service`: `http://localhost:8081`
- `produto-service`: `http://localhost:8082`
- PostgreSQL: `localhost:5433` (container `postgres` interno na porta `5432`)

## Tecnologias

- Java 21
- Spring Boot 3.3.x
- Spring Data JPA
- Jakarta Bean Validation
- Lombok
- SpringDoc OpenAPI
- PostgreSQL
- SLF4J + Logback
- Docker Compose

## Como subir com Docker Compose

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Para derrubar:

```bash
docker compose down
```

## Swagger / OpenAPI

- Cliente: `http://localhost:8080/swagger-ui.html`
- Pedido: `http://localhost:8081/swagger-ui.html`
- Produto: `http://localhost:8082/swagger-ui.html`

Endpoints JSON:

- Cliente: `http://localhost:8080/api-docs`
- Pedido: `http://localhost:8081/api-docs`
- Produto: `http://localhost:8082/api-docs`

## Exemplo rapido com cURL

Criar cliente:

```bash
curl -X POST "http://localhost:8080/clientes" -H "Content-Type: application/json" -d "{\"nome\":\"Maria\",\"email\":\"maria@exemplo.com\"}"
```

Criar produto:

```bash
curl -X POST "http://localhost:8082/produtos" -H "Content-Type: application/json" -d "{\"nome\":\"Notebook\",\"preco\":4500.00}"
```

Criar pedido:

```bash
curl -X POST "http://localhost:8081/pedidos" -H "Content-Type: application/json" -d "{\"descricao\":\"Pedido inicial\",\"clienteId\":1,\"produtoIds\":[1]}"
```

## Auditoria em banco (`audit_log`)

Cada servico possui entidade/tabela `audit_log` para registrar requisicoes REST via AOP.

Consulta de auditoria:

```sql
SELECT id, timestamp, usuario, acao, endpoint, metodo, status_code, descricao, tempo_execucao
FROM audit_log
ORDER BY timestamp DESC;
```

## Logs estruturados

- Arquivo de configuracao por servico: `src/main/resources/logback-spring.xml`
- Formato JSON no console e arquivo.
- Em `dev/default`: nivel `DEBUG` no console.
- Em `prod`: nivel `INFO` com saida em arquivo rotativo em `logs/`.

## Estrutura de pacotes por servico

Cada microservico segue organizacao por responsabilidade:

- `config`
- `controller`
- `service`
- `repository`
- `dto`
- `entity`
- `mapper`
- `exception`
- `audit`

## Troubleshooting

- Porta em uso: altere `server.port` no `application.properties` do servico.
- Falha de conexao com banco: confirme se o container PostgreSQL esta ativo e usando credenciais `postgres/spt1234`.
- Erro entre servicos: valide se o `cliente-service` esta acessivel em `http://localhost:8080` antes de criar pedidos.
- Tabelas nao persistem apos reinicio local: `ddl-auto=create-drop` esta ativo para ambiente de desenvolvimento.
