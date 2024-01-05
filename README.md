Banco_Api

Projeto desenvolvido em java que gerência contas correntes, utilizando o framework Quarkus. Veja como executar o projeto e as rotas disponíveis.

Se você deseja aprender mais sobre o Quarkus, visite o site oficial: https://quarkus.io/ .

Você pode executar sua aplicação em modo de desenvolvimento, que permite codificação ao vivo, utilizando:

./mvnw compile quarkus:dev

Depois de iniciado o servidor, você pode acessar os seguintes Endpoint:

Clientes 
(localhost:8080/api/v1/cliente): GET

Criar Conta
(localhost:8080/api/v1/cadastrarconta): POST

Contas
(localhost:8080/api/v1/conta): GET

Ver Saldo
(localhost:8080/api/v1/contacorrente/saldo/{id}): GET

Depositar
(localhost:8080/api/v1/depositar): POST

Sacar
(localhost:8080/api/v1/sacar): POST

Transferir
(localhost:8080/api/v1/transferir): PATCH

Deletar
(localhost:8080/api/v1/deletarconta/{id}): DELETE
