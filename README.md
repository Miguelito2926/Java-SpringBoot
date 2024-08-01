# E-commerce API

![E-commerce API](https://img.shields.io/badge/API-Spring%20Boot-brightgreen)
![Swagger](https://img.shields.io/badge/Swagger-Enabled-orange)
![Spring Security](https://img.shields.io/badge/Security-Spring%20Security%20JWT-blue)

## Descrição

A E-commerce API é uma aplicação desenvolvida para gerenciar clientes, produtos, pedidos e usuários em uma plataforma de vendas online. Esta API oferece uma interface RESTful e é documentada usando Swagger, facilitando a integração e o consumo por outras aplicações.

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security com JWT**
- **Lombok**
- **Swagger**
- **MySQL**

## Funcionalidades

### Clientes

- **Listar Clientes**: Obter todos os clientes cadastrados.
- **Filtrar Clientes**: Buscar clientes com base em critérios específicos.
- **Obter Cliente por ID**: Buscar informações detalhadas de um cliente específico.
- **Criar Cliente**: Adicionar um novo cliente.
- **Atualizar Cliente**: Atualizar as informações de um cliente existente.
- **Deletar Cliente**: Remover um cliente do sistema.

### Produtos

- **Listar Produtos**: Obter todos os produtos disponíveis.
- **Filtrar Produtos**: Buscar produtos com base em critérios específicos.
- **Obter Produto por ID**: Buscar informações detalhadas de um produto específico.
- **Criar Produto**: Adicionar um novo produto.
- **Atualizar Produto**: Atualizar as informações de um produto existente.
- **Deletar Produto**: Remover um produto do sistema.

### Pedidos

- **Criar Pedido**: Adicionar um novo pedido.
- **Obter Pedido por ID**: Buscar informações detalhadas de um pedido específico.
- **Atualizar Status do Pedido**: Modificar o status de um pedido existente.

### Usuários

- **Criar Usuário**: Adicionar um novo usuário ao sistema.
- **Autenticar Usuário**: Gerar um token de autenticação para o usuário.

## Documentação da API

A documentação completa da API está disponível no Swagger, que pode ser acessada através do seguinte endpoint após iniciar o aplicativo:

http://localhost:8080/swagger-ui.html

markdown
Copiar código

## Segurança

A API utiliza **Spring Security** com **JWT** para autenticação e autorização. Para acessar determinados endpoints, é necessário fornecer um token JWT válido. Para obter um token, utilize o endpoint de autenticação:

POST /api/v1/usuarios/auth

markdown
Copiar código

**Configuração de Beans:** A configuração de segurança e outras configurações de beans são gerenciadas no pacote `com.ednaldo.ecommerce.config`.

## Configuração e Execução

### Pré-requisitos

- **Java 11+**
- **Maven**
- **MySQL**

### Passos para rodar a aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/ecommerce-api.git
   cd ecommerce-api
## Configure o banco de dados:

Crie um banco de dados MySQL e atualize as configurações no arquivo application.yml ou application.properties com as credenciais do banco.

## Compile e execute a aplicação:

bash
Copiar código
mvn spring-boot:run
Acesse o Swagger para explorar os endpoints:

Abra o navegador e vá para http://localhost:8080/swagger-ui.html.

## Estrutura do Projeto

src
├── main
│   ├── java
│   │   └── com
│   │       └── ednaldo
│   │           └── ecommerce
│   │               ├── api
│   │               │   └── controller
│   │               ├── domain
│   │               │   ├── dto
│   │               │   ├── entity
│   │               │   ├── enums
│   │               │   └── repository
│   │               ├── exception
│   │               ├── service
│   │               └── config
│   └── resources
│       └── application.yml
└── test
    └── java


## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues para melhorias e novas funcionalidades.

## Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter mais detalhes.

## Contato
Para dúvidas ou sugestões, entre em contato:

- Ednaldo Tavares
- LinkedIn
- juniorprol@hotmail.com
