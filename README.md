

# 🚀 Mottu Mottion: Sistema Inteligente de Gestão de Pátios para Frota de Motos de Entregadores

> Plataforma moderna com API RESTful para controle operacional e logístico das motos da Mottu.

**Mottu Mottion** é um sistema de gestão de pátios voltado para as motos utilizadas por entregadores parceiros da Mottu. Ele organiza o fluxo de entrada e saída, alocação de vagas, tempo de permanência e envia notificações automatizadas. A aplicação se comunica via API REST e pode ser acessada por interfaces web ou mobile.

---

## 📜 **Visão Geral do Projeto**

O Mottu Mottion surgiu para solucionar um dos principais gargalos operacionais da Mottu: a ausência de um controle padronizado e rastreável nos pátios das mais de 100 filiais. Dificuldades em localizar motos, monitorar manutenções e prevenir furtos impactavam diretamente a produtividade e a segurança da operação.

O sistema automatiza e otimiza a movimentação física das motos, proporcionando visibilidade em tempo real, rastreabilidade total e ações corretivas proativas — com o apoio de sensores IoT, visão computacional e uma plataforma responsiva.

---

## 🎯 **Objetivo**

Modelar um sistema de informação que atenda às principais necessidades operacionais da Mottu, com foco em:

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

## 🧱 **Arquitetura e Componentes Principais**

### 📦 **Pacotes e suas Responsabilidades**

| Pacote        | Descrição                                                                                                      |
|---------------|---------------------------------------------------------------------------------------------------------------|
| **Model**     | Contém as classes de entidade que representam os dados da aplicação (ex: `Cliente`, `Moto`). Mapeiam tabelas do banco. |
| **DTO**       | *Data Transfer Objects*: Transferem dados entre camadas, expondo apenas informações necessárias e protegendo a estrutura interna das entidades. |
| **Repository**| Interfaces que estendem `JpaRepository`. Responsáveis pela comunicação com o banco de dados (operações CRUD). |
| **Service**   | Camada de lógica de negócio. Processa regras, validações e coordena operações entre Repository e Controller.  |
| **Controller**| Camada que expõe endpoints da API. Recebe requisições HTTP, delega para Service e retorna respostas adequadas. |
| **Exception** | Classes personalizadas para tratamento de erros (ex: `ResourceNotFoundException`) e manipulação global de exceções. |
| **CORS**      | Configuração de *Cross-Origin Resource Sharing* para permitir que a API seja acessada por domínios diferentes do seu. |

---

## 🌐 **O que é API? REST vs RESTful**

### 🔹 **API (Application Programming Interface)**
Conjunto de definições e protocolos que permite a comunicação entre sistemas. Funciona como um contrato entre cliente e servidor, definindo como os recursos podem ser acessados e manipulados.

### 🔄 **REST (Representational State Transfer)**
Estilo arquitetural que define restrições para criação de web services:
- **Client-Server**: Separação de responsabilidades
- **Stateless**: Cada requisição contém todas informações necessárias
- **Cacheable**: Respostas podem ser cacheadas
- **Uniform Interface**: Interface consistente (URIs, métodos HTTP)
- **Layered System**: Arquitetura em camadas
- **Code-on-Demand (opcional)**: Execução de código no cliente

### ✅ **API RESTful**
Uma API é considerada **RESTful** quando adere estritamente aos princípios REST. O termo "REST" é frequentemente usado de forma genérica, enquanto "RESTful" denota conformidade completa com as restrições REST.

| Característica       | REST API          | RESTful API       |
|----------------------|-------------------|-------------------|
| **Adesão ao REST**   | Parcial           | Total             |
| **Métodos HTTP**     | Uso variado       | Semântica precisa |
| **HATEOAS**          | Opcional          | Obrigatório       |
| **URI Structure**    | Às vezes rígida   | Recursos claros   |


---

## 📦 **Dependências (`pom.xml`)**

| Dependência                      | Finalidade                             |
| -------------------------------- | -------------------------------------- |
| `spring-boot-starter-data-jpa`   | Persistência de dados com JPA.         |
| `spring-boot-starter-web`        | Criação de APIs REST.                  |
| `spring-boot-starter-validation` | Validação de dados.                    |
| `spring-boot-starter-hateoas`    | Suporte a HATEOAS.                     |
| `spring-boot-devtools`           | Recarga automática no desenvolvimento. |
| `lombok`                         | Redução de código boilerplate.         |
| `spring-boot-starter-test`       | Testes automatizados.                  |
| `spring-boot-starter-cache`      | Implementação de cache.                |
| `ojdbc11`                        | Integração com banco de dados Oracle.  |

---

## 🛠 **Configuração do Banco de Dados**

A aplicação utiliza Oracle como banco de dados. Exemplo de configuração em `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.cache.type=simple
logging.level.org.springframework.cache=DEBUG
```

---

## 🚀 **Guia de Execução**

### 📌 Pré-requisitos

* **Java 17+**
* **Maven**
* **IDE** (IntelliJ, Eclipse ou VSCode)

### 1️⃣ Clone o Repositório

```bash
git clone https://github.com/giovannarevitoroz/mottu-mottion-java-advanced.git
```

### 2️⃣ Configure o Banco de Dados

Atualize o `application.properties` com suas credenciais Oracle.

### 3️⃣ Importe o Projeto na IDE

Abra o projeto em sua IDE. O Maven cuidará das dependências automaticamente.

### 4️⃣ Instale as Dependências

```bash
mvn install
```

### 5️⃣ Execute a Aplicação

**Via IDE**: Clique em **Run**
**Via terminal**:

```bash
mvn spring-boot:run
```

### 6️⃣ Teste a API

Acesse via navegador ou ferramentas como **Postman** ou **Insomnia**:

```
http://localhost:8080
```

---

## 📡 Endpoints da API Mottu Mottion

### 🔹 Cargos (`/api/cargo`)

| Método | Endpoint                             | Descrição                                     |
|--------|--------------------------------------|-----------------------------------------------|
| POST   | `/api/cargo`                         | Cadastrar novo cargo                          |
| GET    | `/api/cargo`                         | Listar todos os cargos (paginado)             |
| GET    | `/api/cargo/{id}`                    | Buscar cargo por ID                           |
| GET    | `/api/cargo/nome/{nome}`             | Buscar cargo por nome exato                   |
| GET    | `/api/cargo/busca?nome=...`          | Buscar cargos por parte do nome (paginado)    |
| GET    | `/api/cargo/funcionario/{idFuncionario}` | Buscar cargo pelo ID do funcionário       |
| PUT    | `/api/cargo/{id}`                    | Atualizar cargo                               |
| DELETE | `/api/cargo/{id}`                    | Deletar cargo                                 |
| GET    | `/api/cargo/prefixo/{prefixo}`       | Buscar cargos por prefixo                     |

---

### 🔹 Clientes (`/api/clientes`)

| Método | Endpoint                                         | Descrição                                      |
|--------|--------------------------------------------------|------------------------------------------------|
| POST   | `/api/clientes`                                  | Cadastrar novo cliente                         |
| GET    | `/api/clientes`                                  | Listar todos os clientes (paginado)            |
| GET    | `/api/clientes/{id}`                             | Buscar cliente por ID                          |
| GET    | `/api/clientes/cpf/{cpf}`                        | Buscar cliente por CPF                         |
| GET    | `/api/clientes/email/{email}`                    | Buscar cliente por email                       |
| GET    | `/api/clientes/telefone/{telefone}`              | Buscar cliente por telefone                    |
| GET    | `/api/clientes/placa-moto/{placa}`               | Buscar cliente por placa da moto               |
| GET    | `/api/clientes/{id}/motos`                       | Listar motos por ID do cliente                 |
| GET    | `/api/clientes/buscar-moto-por-cpf/{cpf}/motos`  | Listar motos por CPF do cliente                |
| PUT    | `/api/clientes/{id}`                             | Atualizar cliente                              |
| DELETE | `/api/clientes/{id}`                             | Deletar cliente                                |

---

### 🔹 Movimentações (`/api/movimentacoes`)

| Método | Endpoint                                     | Descrição                                        |
|--------|----------------------------------------------|--------------------------------------------------|
| GET    | `/api/movimentacoes`                         | Listar todas as movimentações (paginado)         |
| GET    | `/api/movimentacoes/{id}`                    | Buscar movimentação por ID                       |
| GET    | `/api/movimentacoes/por-entrada?dataEntrada=...` | Buscar por data de entrada                 |
| GET    | `/api/movimentacoes/por-saida?dataSaida=...`     | Buscar por data de saída                   |
| GET    | `/api/movimentacoes/por-moto?idMoto=...`         | Buscar por ID da moto                      |
| GET    | `/api/movimentacoes/por-vaga?idVaga=...`         | Buscar por ID da vaga                      |
| GET    | `/api/movimentacoes/por-descricao?descricao=...` | Buscar por descrição da movimentação       |
| GET    | `/api/movimentacoes/por-periodo?dataInicio=...&dataFim=...` | Buscar por período de entrada e saída |

---

### 🔹 Pátios (`/api/patios`)

| Método | Endpoint                                         | Descrição                                      |
|--------|--------------------------------------------------|------------------------------------------------|
| GET    | `/api/patios`                                    | Listar todos os pátios                         |
| GET    | `/api/patios/buscar-por-nome?nome=...`           | Buscar pátios por nome                         |
| GET    | `/api/patios/buscar-por-localizacao?localizacao=...` | Buscar pátios por localização             |
| GET    | `/api/patios/{id}/funcionarios`                  | Listar funcionários do pátio                   |
| GET    | `/api/patios/{id}/gerentes`                      | Listar gerentes do pátio                       |
| POST   | `/api/patios`                                    | Cadastrar novo pátio                           |
| PUT    | `/api/patios/{id}`                               | Atualizar pátio                                |
| DELETE | `/api/patios/{id}`                               | Deletar pátio                                  |

---

### 🔹 Setores (`/api/setores`)

| Método | Endpoint             | Descrição              |
|--------|----------------------|------------------------|
| GET    | `/api/setores`       | Listar todos os setores|
| GET    | `/api/setores/{id}`  | Buscar setor por ID    |
| POST   | `/api/setores`       | Cadastrar novo setor   |
| PUT    | `/api/setores/{id}`  | Atualizar setor        |
| DELETE | `/api/setores/{id}`  | Deletar setor          |

---

### 🔹 Vagas (`/api/vagas`)

| Método | Endpoint                                             | Descrição                                 |
|--------|------------------------------------------------------|-------------------------------------------|
| GET    | `/api/vagas`                                         | Listar todas as vagas                     |
| GET    | `/api/vagas/{id}`                                    | Buscar vaga por ID                        |
| POST   | `/api/vagas`                                         | Cadastrar nova vaga                       |
| PUT    | `/api/vagas/{id}`                                    | Atualizar vaga                            |
| DELETE | `/api/vagas/{id}`                                    | Deletar vaga                              |
| GET    | `/api/vagas/status?status=...`                       | Buscar vagas por status                   |
| GET    | `/api/vagas/setor/{setorId}`                         | Buscar vagas por setor                    |
| GET    | `/api/vagas/numero?numero=...`                       | Buscar vaga por número                    |
| GET    | `/api/vagas/disponivel/{id}`                         | Verificar se vaga está disponível         |
| GET    | `/api/vagas/contagem?status=...&setorId=...`         | Contar vagas por status e setor           |

---

✅ *Todos os endpoints podem exigir autenticação e validação conforme configuração da aplicação.*


## ⚠️ **Soluções para Problemas Comuns**

* **Banco não conecta**: Verifique usuário, senha e URL no `application.properties`.
* **Erros de dependência**: Execute `mvn clean install`.
* **Versão do Java incompatível**: Certifique-se de estar usando Java 17 ou superior.

---

## 📚 **Documentação Adicional**

A documentação da API será expandida conforme o desenvolvimento evolui.

---

🚀 **Bom desenvolvimento!**

---
