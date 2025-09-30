# Em parceria com a Mottu, apresentamos: 
# Mottu Mottion um Sistema de Monitoramento e Gestão de Pátios para a Mottu

# Vídeo demonstracao do projeto 
## [https://youtu.be/TK6COIAQVmg](https://youtu.be/yVxPjS8rAtA)

## Visão Geral

O **Mottu Mottion** é uma aplicação desenvolvida para apoiar a empresa de aluguel de motos **Mottu** na gestão e monitoramento de sua frota.
O sistema permite acompanhar em tempo real a movimentação das motos entre os setores dos pátios, utilizando sensores de **Bluetooth** e **GPS** embarcados em microcontroladores **ESP32**.

A plataforma registra a entrada e saída das motos em cada setor, informando automaticamente a quantidade de veículos em cada local, além de possibilitar o gerenciamento de clientes, funcionários, pátios e vagas.

O projeto foi implementado em **Java com Spring Boot**, utilizando o padrão **API RESTful** para disponibilizar dados e serviços de forma padronizada e escalável, podendo ser consumido por interfaces web, mobile ou até mesmo sistemas de monitoramento em tempo real.

---

## Migração de Banco com Flyway

O projeto utiliza **Flyway** para versionamento e migração automática do banco de dados.

### Como funciona:

* Todos os scripts SQL devem ser adicionados na pasta:

```
src/main/resources/db/migration
```

* O Flyway aplicará automaticamente os scripts ao iniciar a aplicação.
  Isso garante que o banco esteja sempre atualizado com a versão correta das tabelas e dados iniciais.

* Exemplo de script de inserção de usuários:

```sql
INSERT INTO USUARIO (username, password, role)
VALUES 
('kgonascimento', '$2a$10$9j7OWhYxUJS3lzehf9tbyuBCTsPJmZYIXRuH.z0.a8Iw5wDvtJkPC', 'ROLE_FUNCIONARIO'),
('admin', '$2a$10$GDnclieRR2G76y06Em4PoexO4xm.08cRMB0TJe1jIFgXLIS3xiO8.', 'ROLE_GERENTE');
```

> Senhas estão criptografadas com **BCrypt**.

---

## Rotas Protegidas

A aplicação possui **controle de acesso baseado em perfis de usuário**:

### Perfis

| Perfil           | Descrição                                         |
| ---------------- | ------------------------------------------------- |
| ROLE_FUNCIONARIO | Acesso a operações básicas de registro e consulta |
| ROLE_GERENTE     | Todos os privilégios, incluindo gerenciamento     |

### Endpoints públicos

* `/login` – página de login
* `/error` – página de erro
* `/css/**`, `/js/**`, `/webjars/**` – recursos estáticos

### Endpoints para **FUNCIONÁRIO e GERENTE**

| Endpoint            | Operação                           |
| ------------------- | ---------------------------------- |
| `/clientes/**`      | Consultar e cadastrar clientes     |
| `/motos/**`         | Consultar e cadastrar motos        |
| `/movimentacoes/**` | Registrar entradas/saídas de motos |

### Endpoints apenas para **GERENTE**

| Endpoint           | Operação                                 |
| ------------------ | ---------------------------------------- |
| `/funcionarios/**` | Gerenciar funcionários                   |
| `/cargos/**`       | Gerenciar cargos                         |
| `/patios/**`       | Gerenciar pátios                         |
| `/setores/**`      | Gerenciar setores dos pátios             |
| `/vagas/**`        | Gerenciar vagas                          |
| `/gerentes/**`     | Gerenciar usuários com perfil de gerente |

> Qualquer outra requisição exige autenticação.

---
## Arquitetura da Solução

A solução foi estruturada em **camadas bem definidas**, seguindo princípios de **separação de responsabilidades**:

```
Controller → Service → Repository → Model → Database
```

* **Controller**: disponibiliza endpoints REST para comunicação com o frontend e integrações externas.
* **Service**: concentra as regras de negócio, validações e orquestração das operações.
* **Repository**: gerencia a persistência dos dados com **Spring Data JPA**.
* **Model**: representa as entidades de domínio persistidas no banco de dados.
* **DTO**: transporte de dados entre camadas, facilitando validações e reduzindo o acoplamento.
* **Security**: camada de segurança responsável por autenticação, autorização e uso de tokens JWT.
* **Exception**: centraliza tratamento de erros e respostas padronizadas para falhas.
* **View**: camada de apresentação com **Thymeleaf**, responsável pelas páginas do sistema.
* **CORS**: configuração de acesso para permitir que clientes hospedados em diferentes domínios possam consumir a API.

---

## Estrutura do Projeto

```
src/main/java/br/com/fiap/sprint1
 ├── controller/             # Controladores REST
 ├── cors/                   # Configurações de CORS
 ├── dto/                    # Data Transfer Objects
 ├── exception/              # Tratamento de exceções
 ├── model/                  # Entidades JPA
 ├── repository/             # Repositórios Spring Data
 ├── security/               # Autenticação e autorização (JWT)
 ├── service/                # Regras de negócio
 ├── view/                   # Páginas Thymeleaf
 └── Sprint2Application.java # Classe principal do Spring Boot

src/main/resources
 ├── db/migration/           # Scripts Flyway para versionamento do banco
 ├── static.css/             # Arquivos de estilo CSS
 ├── templates/              # Templates Thymeleaf
 └── application.properties  # Configurações do sistema

src/test/java                # Testes automatizados (JUnit)
pom.xml                      # Gerenciamento de dependências (Maven)
```

---

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **Spring Security + JWT**
* **Flyway** (migração de banco de dados)
* **MySQL 8**
* **Thymeleaf**
* **Maven**
* **Lombok**

---

## API REST vs RESTful

* **API REST**: aplica parcialmente os princípios da arquitetura REST, podendo não seguir todas as restrições.
* **API RESTful**: segue de forma estrita os princípios do REST, como:

    * Uso correto dos verbos HTTP (`GET`, `POST`, `PUT`, `DELETE`)
    * Comunicação sem estado (stateless)
    * Recursos acessados por URIs
    * Hypermedia (HATEOAS)

O projeto **Mottu Mottion** foi implementado como uma **API RESTful**.

---

## Segurança e Perfis de Acesso

A segurança é baseada em **Spring Security** com autenticação via **JWT**.

Dois perfis de usuário foram definidos:

* **ROLE\_FUNCIONARIO**

    * Registrar entrada/saída de motos
    * Consultar motos, clientes e pátios

* **ROLE\_GERENTE**

    * Todos os privilégios do funcionário
    * Cadastro e gerenciamento de motos, pátios e vagas
    * Gestão de funcionários e usuários

---

## Exemplos de Endpoints

### Clientes

```http
GET /api/clientes
```

```http
POST /api/clientes
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "12345678900",
  "telefone": "11999999999"
}
```

### Motos

```http
GET /api/motos
```

```http
POST /api/motos
Content-Type: application/json

{
  "placa": "ABC1234",
  "modelo": "Honda Biz",
  "status": "DISPONIVEL"
}
```

### Pátios

```http
GET /api/patios
```

---

## Configuração do Banco de Dados

Arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mottu_mottion?useSSL=false&serverTimezone=UTC
spring.datasource.username=seuuser
spring.datasource.password=suasenha

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

---

## Como Executar o Projeto

### Requisitos

* Java 17+
* Maven 3+
* MySQL 8+

### Passos

1. Clonar o repositório:

   ```bash
   git clone https://github.com/giovannarevitoroz/Sprint01-Mottu-Motion-Java-Adavanced.git
   ```
2. Criar o banco de dados:

   ```sql
   CREATE DATABASE mottu_mottion;
   ```
3. Ajustar credenciais no `application.properties`.
4. Instalar dependências:

   ```bash
   mvn install
   ```
5. Executar aplicação:

   ```bash
   mvn spring-boot:run
   ```

A aplicação ficará disponível em:
`http://localhost:8080/login`

---

## Testes

Para executar os testes automatizados:

```bash
mvn test
```

---

## Roadmap Futuro

* Integração com aplicação mobile para clientes e funcionários
* Dashboard em tempo real com Node-RED
* Relatórios detalhados em PDF e Excel
* Expansão para monitoramento via IoT em escala

---

## Authors

* Giovanna Revito Roz – RM558981
* Kaian Gustavo de Oliveira Nascimento – RM558986
* Lucas Kenji Kikuchi – RM554424






