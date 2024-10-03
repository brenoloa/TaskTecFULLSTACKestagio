# Projeto Full Stack - Aplicação com Angular, Spring Boot e PostgreSQL

## Descrição

Este projeto é uma aplicação full stack que implementa um sistema de gerenciamento de tarefas, com autenticação de usuários via token JWT. Ele é composto por um front-end em Angular e um back-end em Spring Boot, utilizando PostgreSQL como banco de dados para persistência dos dados.


**Caso queria ver a aplicação funcionando sem preecisar ter o projeto na sua máquina veja o vídeo -->** https://www.youtube.com/watch?v=23hk6rpxJ7c 

### Funcionalidades:

- Autenticação de usuários com JWT.
- CRUD de tarefas, permitindo criar, visualizar, editar e excluir tarefas.
- Filtros de tarefas por prioridade, status e data.
- Autenticação para proteger os endpoints.
- Sistema de login e cadastro de usuários.
- Persistência de dados no PostgreSQL.

---

(![/user](https://i.imgur.com/EQ5qP2x.png))
## Tecnologias Utilizadas

### Front-end:
- **Angular** (versão mais recente)

### Back-end:
- **Java 11**
- **Spring Boot**
- **JWT** para autenticação
- **JPA** para persistência
- **PostgreSQL** como banco de dados.

---

## Implementações

### Itens obrigatórios:

- ✅ a) **Front-end em Angular**: O front-end foi desenvolvido utilizando Angular na versão mais recente.
- ✅ b) **Back-end com Java 11 e Spring Boot**: O back-end foi implementado utilizando Java 11 com o framework Spring Boot.
- ✅ c) **Endpoints REST**: Todos os endpoints foram implementados seguindo o padrão REST e testados com o **Postman.**
  
### Itens Opcionais:

- ✅ d) **Autenticação JWT**: A aplicação possui controle de login por usuário e senha, com autenticação via token JWT.
- ✅ e) **Persistência com PostgreSQL e JPA**: O banco de dados PostgreSQL foi utilizado, e a persistência foi gerenciada com JPA.
- ✅ f) **Testes de unidade**: Foram implementados testes de unidade utilizando JUnit e Spring Test.
- ✅ g) **Documentação da API com Swagger**: A API foi documentada utilizando Swagger e um arquivo PDF da documentação está disponível no repositório.

---

## Documentação

A documentação completa da API também está disponível em formato PDF dentro do repositório. Esse arquivo contém a descrição detalhada dos endpoints, parâmetros e exemplos de uso.

---

## Backup do Banco de Dados

Um arquivo de backup do banco de dados PostgreSQL (`meu_banco.dump`) está incluído no repositório. Para restaurar o banco de dados no seu **pgAdmin**, siga os seguintes passos:

1. Abra o **pgAdmin** e conecte-se ao seu servidor PostgreSQL.
2. Crie um novo banco de dados (ou utilize um existente) com o nome que desejar.
3. Clique com o botão direito no banco de dados criado e selecione a opção **Restore**.
4. Na janela de restauração, vá até a seção **Filename**, selecione o arquivo `backup.dump` incluído no repositório.
5. Clique em **Restore** para iniciar o processo.

O banco de dados será restaurado com todas as tabelas e dados necessários para rodar a aplicação.

Lembre-se de conferir o **application.properties** caso seja necessário mude alguma variavél compátivel com o banco criado.
---

## Como Executar o Projeto Localmente

### Pré-requisitos:
- **Node.js** e **npm**
- **Angular CLI**
- **Java 11**
- **Maven**
- **PostgreSQL**
- **pgAdmin** para restaurar o backup do banco de dados

### Passos para executar:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/brenoloa/TaskTecFULLSTACKestagio.git
   ```

2. **Configurar o banco de dados PostgreSQL**:
   - Restaure o banco de dados seguindo as instruções acima.
   - Atualize o arquivo `application.properties` com as credenciais do seu banco de dados:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/meu_banco
     spring.datasource.username=meu_usuario
     spring.datasource.password=minha_senha
     ```

3. **Executar o back-end**:
   - Navegue até o diretório do back-end:
     ```bash
     cd login-auth-api
     ```
   - Execute o comando Maven para rodar a aplicação Spring Boot:
     ```bash
     mvn spring-boot:run
     ```
     Ou apenas execute o arquivo **LoginAuthApiApplication.java**

4. **Executar o front-end**:
   - Navegue até o diretório do front-end:
     ```bash
     cd brenoesigprojeto
     ```
   - Instale as dependências:
     ```bash
     npm install
     ```
   - Execute a aplicação Angular:
     ```bash
     ng serve
     ```

5. **Acessar a aplicação**:
   - Acesse o front-end em: `http://localhost:4200`
   - A API estará disponível em: `http://localhost:8080`

---

## Documentação da API

A documentação da API pode ser acessada via Swagger em: `http://localhost:8080/swagger-ui.html`, ou você pode consultar o arquivo PDF incluído no repositório para mais detalhes.

---

## Testes

Para rodar os testes de unidade, navegue até o diretório do back-end e execute:
```bash
mvn test
```
---
Caso prefira utilizar uma conta com algumas tarefas ja criadas use:
email:      breno@esig.com
password:   breno@breno

Porém fique a vontade para criar sua própia conta e testar a aplicação.

---

## Caso tenha alguma dúvida entre em contato.

- **Breno Andrade**
- **GitHub**: [github.com/brenoloa](https://github.com/brenoloa)
- **Contato**: contatobrenoloa@gmail.com
