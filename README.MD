# Amigo Fiel
**Amigo Fiel** é um site dedicado à adoção de animais, ajudando a conectar cães e gatos que precisam de um lar com pessoas dispostas a adotar.

## Funcionalidades
- Autentificação de usuário
- Visualize perfis de animais disponíveis para adoção
- Informações detalhadas sobre cada cão/gato
- Formulário de adoção online

## Tecnologias Utilizadas

### Frontend
- Angular
- HTML5 & CSS3
- TypeScript

### Backend
- Java
- Spring Framework (Security, Data, etc)
- OAuth 2.0
- CORS
- Swagger
- Java Mail Sender
- OpenFeign
- JUnit5, Mockito, WebMVC, Surefire, JaCoCo e Pytest

### Infra
- PostgreSQL
- Docker

## Requisitos
O Docker Compose está configurado para criar e gerenciar os seguintes serviços:
- **PostgreSQL**: Banco de dados utilizado pelo backend.
- **Backend**: A aplicação Java que fornece a API.

Certifique-se de que o Docker e o Docker Compose estão instalados e configurados corretamente no seu sistema. Além disso, é importante que as credenciais de email para a recuperação de senha estejam corretas.

## Como Executar o Projeto

### Frontend
1. Clone este repositório:
    ```bash
    git clone https://github.com/gabrafo/AmigoFiel
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd frontend/amigofiel
    ```
3. Instale as dependências:
    ```bash
    npm install
    ```
4. Execute a aplicação:
    ```bash
    ng serve
    ```
5. Acesse a aplicação em seu navegador:
    ```
    http://localhost:4200
    ```

### Backend
1. Clone este repositório:
    ```bash
    git clone https://github.com/gabrafo/AmigoFiel
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd backend
    ```
3. Suba o contâiner usando o Docker Compose:
    ```bash
    docker-compose build
    ```
4. Execute a aplicação:
    ```bash
    docker-compose up
    ```
5. Faça as requisições utilizando seu cliente HTTP favorito (ou rode o Frontend e teste o projeto integralmente)

### Documentação da API
A documentação da API está disponível via Swagger. Para acessá-la, após a execução do Backend, abra o navegador e vá para:
http://localhost:8080/swagger-ui.html

## Contribuição
Este projeto foi desenvolvido em conjunto por uma equipe de 3 pessoas:
- Douglas
- Gabriel Fagundes
- Thyale MD

Contribuições adicionais são bem-vindas! Para contribuir, abra uma issue ou envie um pull request. Além disso, é bem possível que, quando você acesse esse repositório, ele ainda esteja em processo de construção.
Portanto, atenção às frequentes mudanças que o projeto está passando.
