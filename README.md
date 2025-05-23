# Pedido Service

Microserviço responsável pelo cadastro de **pedidos**, que se comunicam com o `cliente-service` para validar a existência do cliente antes de salvar o pedido.

---

## ✅ Tecnologias

- Java 19
- Spring Boot 3.2.5
- Spring Data JPA
- MySQL
- Docker (para banco)
- Maven

---

## 🧾 Pré-requisitos

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://www.docker.com/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Maven](https://maven.apache.org/)

---

## 🐳 Subindo MySQL com Docker

Você pode rodar um container com MySQL usando o comando abaixo:

```bash
docker run --name mysql-pedidos -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=pedidosdb -p 3306:3306 -d mysql:8.0
