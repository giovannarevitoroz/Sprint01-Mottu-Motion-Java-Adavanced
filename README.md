Perfeito 🚀
Aqui está a **documentação reorganizada e completa**, já com **MySQL, Flyway, JWT, pacotes, dependências e endpoints**. Assim você já pode usar no seu repositório como um **README.md** bem estruturado.

---

# 🚀 Mottu Mottion: Sistema Inteligente de Gestão de Pátios para Frota de Motos de Entregadores

> Plataforma moderna com API RESTful para controle operacional e logístico das motos da Mottu.

**Mottu Mottion** é um sistema de gestão de pátios voltado para as motos utilizadas por entregadores parceiros da Mottu.
Ele organiza o fluxo de entrada e saída, alocação de vagas, tempo de permanência e envia notificações automatizadas.
A aplicação se comunica via API REST e pode ser acessada por interfaces web ou mobile.

---

## 📜 **Visão Geral do Projeto**

O Mottu Mottion surgiu para solucionar um dos principais gargalos operacionais da Mottu: a ausência de um controle padronizado e rastreável nos pátios das mais de 100 filiais.
Dificuldades em localizar motos, monitorar manutenções e prevenir furtos impactavam diretamente a produtividade e a segurança da operação.

O sistema automatiza e otimiza a movimentação física das motos, proporcionando **visibilidade em tempo real, rastreabilidade total e ações corretivas proativas** — com o apoio de sensores IoT, visão computacional e uma plataforma responsiva.

---

## 🎯 **Objetivo**

| Requisito                  | Descrição                                                   |
| -------------------------- | ----------------------------------------------------------- |
| 🚚 Cadastro de clientes    | Registro de motociclistas com identificadores únicos.       |
| 🏍 Cadastro de motos       | Inclusão de dados como placa, chassi, modelo e status.      |
| 🅿 Gerenciamento de pátios | Controle de setores, vagas e movimentações das motos.       |
| 👨‍💻 Gestão de equipe     | Atribuição de cargos e controle de funcionários e gerentes. |

---

## 👨‍💻 **Equipe**

* **Giovanna Revito Roz** – RM558981
* **Kaian Gustavo de Oliveira Nascimento** – RM558986
* **Lucas Kenji Kikuchi** – RM554424

---

## 🧱 **Arquitetura e Estrutura do Projeto**

| Pacote                 | Descrição                                                                                                  |
| ---------------------- | ---------------------------------------------------------------------------------------------------------- |
| **controller**         | Controllers REST principais da API (CRUD de clientes, motos, funcionários, etc.).                          |
| **cors**               | Configurações de **CORS** para permitir acesso entre frontend e backend.                                   |
| **dto**                | Objetos de Transferência de Dados (DTOs) usados para comunicação entre camadas e validação de entrada.     |
| **exception**          | Classes de tratamento de exceções customizadas e handlers globais.                                         |
| **model**              | Entidades de domínio mapeadas com JPA/Hibernate.                                                           |
| **repository**         | Interfaces `JpaRepository` responsáveis pela persistência e consultas ao banco de dados.                   |
| **security**           | Configuração de autenticação/autorização com **Spring Security e JWT**.                                    |
| **service**            | Camada de serviços contendo a lógica de negócio, validações e integrações entre controller e repository.   |
| **view**               | Controllers voltados para a camada de visualização com **Thymeleaf** (rotas para renderizar páginas HTML). |
| **Sprint2Application** | Classe principal que inicializa o Spring Boot.                                                             |

---
---

## 📦 **Dependências (`pom.xml`)**

| Dependência                      | Finalidade                         |
| -------------------------------- | ---------------------------------- |
| `spring-boot-starter-data-jpa`   | Persistência de dados com JPA.     |
| `spring-boot-starter-web`        | Criação de APIs REST.              |
| `spring-boot-starter-validation` | Validação de dados.                |
| `spring-boot-starter-thymeleaf`  | Templates HTML com Thymeleaf.      |
| `spring-boot-starter-security`   | Segurança com Spring Security.     |
| `jjwt` / `spring-security-jwt`   | Geração e validação de tokens JWT. |
| `spring-boot-starter-cache`      | Implementação de cache.            |
| `mysql-connector-j`              | Integração com banco MySQL.        |
| `flyway-core`                    | Versionamento do banco.            |
| `lombok`                         | Redução de código boilerplate.     |
| `spring-boot-starter-test`       | Testes automatizados.              |

---

## 🛠 **Configuração do Banco de Dados (MySQL)**

Arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mottu_mottion?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=suasenha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

# Cache
spring.cache.type=simple
logging.level.org.springframework.cache=DEBUG
```

---

## 📂 **Scripts Flyway**

Local: `resources/db/migration`

* `V1__create_users.sql` → Criação da tabela de usuários
* `V2__insert_initial_users.sql` → Inserção de dados iniciais
* `V3__insert_new_users.sql` → Novos registros de usuários
* `V4__update_role.sql` → Alteração de papéis (roles)

---

## 🚀 **Guia de Execução**

### 📌 Pré-requisitos

* **Java 17+**
* **Maven**
* **MySQL 8+**
* **IDE** (IntelliJ, Eclipse ou VSCode)

### 🔧 Passos

1️⃣ Clone o repositório:

```bash
git clone https://github.com/giovannarevitoroz/Sprint01-Mottu-Motion-Java-Adavanced.git
```

2️⃣ Crie o banco no MySQL:

```sql
CREATE DATABASE mottu_mottion;
```

3️⃣ Ajuste o `application.properties` com suas credenciais.

4️⃣ Execute o Flyway (migrações rodam automaticamente na inicialização).

5️⃣ Instale as dependências:

```bash
mvn install
```

6️⃣ Rode a aplicação:

```bash
mvn spring-boot:run
```

7️⃣ Acesse a API em:

```
http://localhost:8080
```

---

# 🌐 **Endpoints da API**

### 🔐 Autenticação e Segurança

| Método | Rota        | Descrição                            | Autenticação |
| ------ | ----------- | ------------------------------------ | ------------ |
| POST   | `/login`    | Autentica usuário e retorna **JWT**  | ❌            |
| GET    | `/logout`   | Invalida o token JWT                 | ✅            |

---

### 👤 Clientes

| Método | Rota             | Descrição                         | Autenticação    |
| ------ | ---------------- | --------------------------------- | --------------- |
| GET    | `/clientes`      | Lista clientes (paginado)         | ✅               |
| GET    | `/clientes/{id}` | Detalhes de um cliente específico | ✅               |
| POST   | `/clientes`      | Cria novo cliente                 | ✅ (ROLE\_ADMIN) |
| PUT    | `/clientes/{id}` | Atualiza cliente                  | ✅ (ROLE\_ADMIN) |
| DELETE | `/clientes/{id}` | Remove cliente                    | ✅ (ROLE\_ADMIN) |

---

### 🏍 Motos

| Método | Rota          | Descrição              | Autenticação    |
| ------ | ------------- | ---------------------- | --------------- |
| GET    | `/motos`      | Lista motos (paginado) | ✅               |
| GET    | `/motos/{id}` | Detalhes de uma moto   | ✅               |
| POST   | `/motos`      | Cadastra moto          | ✅ (ROLE\_ADMIN) |
| PUT    | `/motos/{id}` | Atualiza moto          | ✅ (ROLE\_ADMIN) |
| DELETE | `/motos/{id}` | Remove moto            | ✅ (ROLE\_ADMIN) |

---

### 🅿 Pátios / Vagas / Movimentação

| Método | Rota                     | Descrição                                | Autenticação    |
| ------ | ------------------------ | ---------------------------------------- | --------------- |
| GET    | `/patios`                | Lista pátios                             | ✅               |
| GET    | `/patios/{id}`           | Detalhes de um pátio                     | ✅               |
| POST   | `/patios`                | Cria pátio                               | ✅ (ROLE\_ADMIN) |
| GET    | `/vagas`                 | Lista vagas                              | ✅               |
| POST   | `/vagas`                 | Cria vaga                                | ✅ (ROLE\_ADMIN) |
| PUT    | `/vagas/{id}/ocupar`     | Marca vaga como ocupada                  | ✅               |
| PUT    | `/vagas/{id}/liberar`    | Libera vaga                              | ✅               |
| GET    | `/movimentacoes`         | Lista movimentações (entrada/saída moto) | ✅               |
| POST   | `/movimentacoes/entrada` | Registra entrada de moto no pátio        | ✅               |
| POST   | `/movimentacoes/saida`   | Registra saída de moto                   | ✅               |

---

### 👨‍💻 Funcionários / Gerentes / Cargos

| Método | Rota                 | Descrição            | Autenticação    |
| ------ | -------------------- | -------------------- | --------------- |
| GET    | `/funcionarios`      | Lista funcionários   | ✅ (ROLE\_ADMIN) |
| POST   | `/funcionarios`      | Cria funcionário     | ✅ (ROLE\_ADMIN) |
| PUT    | `/funcionarios/{id}` | Atualiza funcionário | ✅ (ROLE\_ADMIN) |
| DELETE | `/funcionarios/{id}` | Remove funcionário   | ✅ (ROLE\_ADMIN) |
| GET    | `/cargos`            | Lista cargos         | ✅               |
| POST   | `/cargos`            | Cria cargo           | ✅ (ROLE\_ADMIN) |
| GET    | `/gerentes`          | Lista gerentes       | ✅ (ROLE\_ADMIN) |
| POST   | `/gerentes`          | Cria gerente         | ✅ (ROLE\_ADMIN) |

---

### 🌍 Rotas de Navegação (Thymeleaf)

| Rota            | Template            | Descrição              |
|-----------------| ------------------- | ---------------------- |
| `/home`         | `home.html`         | Página inicial         |
| `/login`        | `login.html`        | Tela de login          |
| `/clientes`     | `clientes.html`     | Listagem de clientes   |
| `/motos`        | `motos.html`        | Listagem de motos      |
| `/patios`       | `patios.html`       | Gestão de pátios       |
| `/vagas`        | `vagas.html`        | Gestão de vagas        |
| `/funcionarios` | `funcionarios.html` | Gestão de funcionários |

---
