## Informações gerais do projeto

Aplicação desenvolvida com SpringBoot v3.3.1 utilizando as seguintes dependências:

- Spring Boot Starter Web
- Spring Boot Starter Data Jpa
- Spring Boot Starter Security
- Spring Boot Starter Oauth2 Resource Server
- Springdoc Openapi Starter Webmvc Ui
- H2 Database
- JDK Java17

## Executando o projeto

1. Realizar o clone da aplicação
2. Importar o projeto no Eclipse/STS4.
3. No diretorio do projeto clicar com o botão direito, ir até 'Run As' e selecionar a opção **Maven clean** e **Maven install** para atualizar as dependências.
4. Executar o projeto a partir do Spring Boot Dashboard e aguardar a aplicação inicializar. Após isso poderemos acessar a aplicação via SwaggerUI ou diretamente pelo postman conforme descrito abaixo:

## Acessando o Swagger UI

Iremos acessar a documentação do projeto pelo Swagger UI previamente configurado no projeto pelo link abaixo.

- [swagger-ui](http://localhost:8080/swagger-ui/index.html#/)

  ### Acessando a API
    Para esse proejto utilizamos autenticação via JWT, para acessarmos os endpoints da aplicação, precisamos passar um token para o SwaggerUI. Abaixo está descrito como deve ser feito.
    
    - Acessando o Swagger UI, deveremos ir até o [authenticate-controller](http://localhost:8080/swagger-ui/index.html#/authenticate-controller), e realizar o seguinte POST:
      - Pelo Front-End do Swagger
        ```
        {
          "username": "admin",
          "password": "123456"
        }
        ```
      - Request via PostMan
        ```
        curl -X 'POST' \
          'http://localhost:8080/authenticate' \
          -H 'accept: */*' \
          -H 'Content-Type: application/json' \
          -d '{
          "username": "admin",
          "password": "123456"
        }'
        ```

      - Devemos receber um response parecido com esse abaixo:
        ```
        {
          "token": "eyJraWQiOiJhZTNjMjQxNS1lNTA0LTQwNDgtYjg5Mi1lOGEyZGRjODAyYWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2NjI1NzQsImlhdCI6MTcyMTY2MTM3NCwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.cYqSq2RhnnJz3-mu-31SBjkfl7QYTOSq_b9Dwn_h4QmX3T08GSkvUHj5sS97BxGVMyPiHZEKPxpZA_9-A_TqbIrHPPiXWWLvNfVtm4HwRA5JhftobKk1toChp4FJn1DCiUcRo4GAFPvcnWQKmE5rTlIzJsCC2Kw84rp_oYZoxPJWjR-8MV9Qk9L_U-ZLtjzuv0P5SdOaQXyYmcFvusZE4_iKD5Dbr-OfUJuTxhgxyK6r6cD7tHkp5HwmdCOVxjbRSv_MX9ghr7gTYfZ8-fVtgRm9Z4WojDEcBuEZZmYtWWuqnxSTRAEWjg-lEnnaJAirXGBPeWorhG97jV7GUVZmvg"
        }
        ```
        
      - Com o token gerado, devemos passa-lo para o SwaggerUI clicando no botão 'Authorize' e preenchendo o Input com o token e clicando em 'Authorize' novamente. Pronto, dessa forma conseguiremos chamar todos os endpoints da aplicação.
      
## Endpoints da Aplicação
A aplicação medical-care possui os seguintes principais endpoints:
  - [authenticate-controller](http://localhost:8080/swagger-ui/index.html#/authenticate-controller) endpoint responsavel por gerar o token JWT que será utilizado no 'Authorization' da aplicação.
    - Request example:
    ```
    curl -X 'POST' \
      'http://localhost:8080/authenticate' \
      -H 'accept: */*' \
      -H 'Content-Type: application/json' \
      -d '{
      "username": "admin",
      "password": "123456"
    }'
    ```
    - Response:
    ```
    {
      "token": "eyJraWQiOiJhZTNjMjQxNS1lNTA0LTQwNDgtYjg5Mi1lOGEyZGRjODAyYWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2NjI1NzQsImlhdCI6MTcyMTY2MTM3NCwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.cYqSq2RhnnJz3-mu-31SBjkfl7QYTOSq_b9Dwn_h4QmX3T08GSkvUHj5sS97BxGVMyPiHZEKPxpZA_9-A_TqbIrHPPiXWWLvNfVtm4HwRA5JhftobKk1toChp4FJn1DCiUcRo4GAFPvcnWQKmE5rTlIzJsCC2Kw84rp_oYZoxPJWjR-8MV9Qk9L_U-ZLtjzuv0P5SdOaQXyYmcFvusZE4_iKD5Dbr-OfUJuTxhgxyK6r6cD7tHkp5HwmdCOVxjbRSv_MX9ghr7gTYfZ8-fVtgRm9Z4WojDEcBuEZZmYtWWuqnxSTRAEWjg-lEnnaJAirXGBPeWorhG97jV7GUVZmvg"
    }
    ```
- [beneficiary-controller](http://localhost:8080/swagger-ui/index.html#/beneficiary-controller) principal endpoint da aplicação, onde poderemos realizar o CRUD de um beneficiario
  - Buscando todos os beneficiarios:
    - Request example:
    ```
    curl --location 'http://localhost:8080/api/beneficiary/findAll' \
    --header 'Authorization: Bearer eyJraWQiOiJhZTNjMjQxNS1lNTA0LTQwNDgtYjg5Mi1lOGEyZGRjODAyYWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2NjM2NzgsImlhdCI6MTcyMTY2MjQ3OCwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.diLa5LOao9XgDnfJRvRy6i3kjiA0g3FFo_26yBndOqLni6-2plYzSSctIkgDeilvlOdKHTbXyD6F66UDVirDHce7CNrlRpX1sf2d4bWRpyIdTtyCdJThIBmHhRozHOkHGaZ85iPyp0p8sIdi6zYKxE5QxFxPs0D0truXjtupkSRNcUKVuNoDjPv6cP8vXp79xNdmoyQVyht4EtvdnKvSWR5mr-RZLQSd5lFBK5FHeSU0bQOeu-I9bZsvqkOTXWlpM5BU604d8rgOpLtQRwm-EgqSroaz-GWXC9TIxEmbBg9qlwZs0-7i2UCVsb1U35qa4ysKVXStKzzHMHgzqcGZrQ'
    ```
    - Response:
    ```
    [
        {
            "id": 1,
            "name": "Teste da Silva",
            "phoneNumber": "11970707070",
            "birthDate": "1999-10-21",
            "inclusionDate": "2024-07-22-03.00.00",
            "updateDate": "2024-07-22-03.00.00",
            "documents": [
                {
                    "id": 1,
                    "documentType": "CPF",
                    "documentNumber": "16235446020",
                    "description": "Cadastro de Pessoa Física",
                    "inclusionDate": "2024-07-22-03.00.00",
                    "updateDate": "2024-07-22-03.00.00"
                }
            ]
        }
    ]
    ```
  - Buscando um beneficiario pelo Id:
    - Request example:
    ```
    curl -X 'GET' \
    'http://localhost:8080/api/beneficiary/1' \
    -H 'accept: */*' \
    -H 'Authorization: Bearer eyJraWQiOiJhZTNjMjQxNS1lNTA0LTQwNDgtYjg5Mi1lOGEyZGRjODAyYWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2NjM2NzgsImlhdCI6MTcyMTY2MjQ3OCwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.diLa5LOao9XgDnfJRvRy6i3kjiA0g3FFo_26yBndOqLni6-2plYzSSctIkgDeilvlOdKHTbXyD6F66UDVirDHce7CNrlRpX1sf2d4bWRpyIdTtyCdJThIBmHhRozHOkHGaZ85iPyp0p8sIdi6zYKxE5QxFxPs0D0truXjtupkSRNcUKVuNoDjPv6cP8vXp79xNdmoyQVyht4EtvdnKvSWR5mr-RZLQSd5lFBK5FHeSU0bQOeu-I9bZsvqkOTXWlpM5BU604d8rgOpLtQRwm-EgqSroaz-GWXC9TIxEmbBg9qlwZs0-7i2UCVsb1U35qa4ysKVXStKzzHMHgzqcGZrQ'
    ```
    - Response:
    ```
    {
      "id": 1,
      "name": "Teste da Silva",
      "phoneNumber": "11970707070",
      "birthDate": "1999-10-21",
      "inclusionDate": "2024-07-22-03.00.00",
      "updateDate": "2024-07-22-03.00.00",
      "documents": [
        {
          "id": 1,
          "documentType": "CPF",
          "documentNumber": "16235446020",
          "description": "Cadastro de Pessoa Física",
          "inclusionDate": "2024-07-22-03.00.00",
          "updateDate": "2024-07-22-03.00.00"
        }
      ]
    }
    ```
    - Inserindo um beneficiario:
      - Request example:
    ```
    curl -X 'POST' \
      'http://localhost:8080/api/beneficiary' \
      -H 'accept: */*' \
      -H 'Authorization: Bearer eyJraWQiOiJhMTYxODVlYy1lMmVmLTQ1ZGItYmIwNC05MDFlYmE0NWEwYjkiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2OTU0MTIsImlhdCI6MTcyMTY5NDIxMiwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.Gfd3TfrrSIXj2Vd29CsfsXuXL9iH1c0mQ7ndGyDzZiysq-30Yt9CkDJxTFgfSXTCDkWY-pk_Z8OUCZKahWbF-ixBRSiROwB6q-ETcwDR4kCYDtNq3pxgioesefLW9xHnNKU3KQs2RYlKS5JHe6nOhGOl_ce0QSmsZJkLJ8Rcmbq32IXt7bGyP1op0KghacOt5RzbLIZYQBfngZMCSchaYZhWkCfpNe-_8x6tXRPx4fkqqEvYlRrjlfL1NkLNpI7SRglihpHNPwKRSeBd9KTp8IjnNbTh5pre-CbYx88eX2z2WW8dTY79Qfov1wnU8dUagASbN2HmBCbaUUqb0fa2Dw' \
      -H 'Content-Type: application/json' \
      -d '{
      "name": "Teste de Souza",
      "phoneNumber": "string",
      "birthDate": "2024-07-23",
      "inclusionDate": "2024-07-23T00:24:01.601Z",
      "updateDate": "2024-07-23T00:24:01.601Z",
      "documents": [
        {
          "documentType": "string",
          "documentNumber": "string",
          "description": "string",
          "inclusionDate": "2024-07-23T00:24:01.601Z",
          "updateDate": "2024-07-23T00:24:01.601Z"
        }
      ]
    }'
    ```
    - Response:
    ```
    {
      "id": 2,
      "name": "Teste de Souza",
      "phoneNumber": "string",
      "birthDate": "2024-07-23",
      "inclusionDate": "2024-07-23T00:24:01.601",
      "updateDate": "2024-07-23T00:24:01.601",
      "documents": [
        {
          "id": 2,
          "documentType": "string",
          "documentNumber": "string",
          "description": "string",
          "inclusionDate": "2024-07-23T00:24:01.601",
          "updateDate": "2024-07-23T00:24:01.601"
        }
      ]
    }
    ```
    - Atualizando um beneficiario:
      - Request example:
    ```
    curl -X 'PUT' \
      'http://localhost:8080/api/beneficiary/update/2' \
      -H 'accept: */*' \
      -H 'Authorization: Bearer eyJraWQiOiJhMTYxODVlYy1lMmVmLTQ1ZGItYmIwNC05MDFlYmE0NWEwYjkiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2OTU0MTIsImlhdCI6MTcyMTY5NDIxMiwic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.Gfd3TfrrSIXj2Vd29CsfsXuXL9iH1c0mQ7ndGyDzZiysq-30Yt9CkDJxTFgfSXTCDkWY-pk_Z8OUCZKahWbF-ixBRSiROwB6q-ETcwDR4kCYDtNq3pxgioesefLW9xHnNKU3KQs2RYlKS5JHe6nOhGOl_ce0QSmsZJkLJ8Rcmbq32IXt7bGyP1op0KghacOt5RzbLIZYQBfngZMCSchaYZhWkCfpNe-_8x6tXRPx4fkqqEvYlRrjlfL1NkLNpI7SRglihpHNPwKRSeBd9KTp8IjnNbTh5pre-CbYx88eX2z2WW8dTY79Qfov1wnU8dUagASbN2HmBCbaUUqb0fa2Dw' \
      -H 'Content-Type: application/json' \
      -d '{
      "id": 2,
      "name": "Teste de Souza",
      "phoneNumber": "11910203040",
      "birthDate": "1984-07-23",
      "inclusionDate": "2024-07-23T00:24:01.601",
      "updateDate": "2024-07-23T00:24:01.601",
      "documents": [
        {
          "id": 2,
          "documentType": "RG",
          "documentNumber": "487441989",
          "description": "Registro geral",
          "inclusionDate": "2024-07-23T00:24:01.601",
          "updateDate": "2024-07-23T00:24:01.601"
        }
      ]
    }'
    ```
    - Response:
    ```
    HTTP STATUS_CODE 204
    ```
    - Deletando um beneficiario:
      - Request example:
    ```
    curl -X 'DELETE' \
    'http://localhost:8080/api/beneficiary/delete/2' \
    -H 'accept: */*' \
    -H 'Authorization: Bearer eyJraWQiOiIxMmZiZTdkOC0xOWI1LTRiZWItODQ3Ni00MTc5YWEwMjYxOWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2OTYwMzMsImlhdCI6MTcyMTY5NDgzMywic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.HRE8UYQPrzVCDfJ4UKhC2bHuAZg96AbJiM9Ctch0Is6Pf4iL_QpG_rvEmOU4mABJ11QEX2FN78l5F3z7kiMSw5fsYK-I9Gqp_q56oYWMeORV5CQs-7XuSekgTxB_lCeKk-ScSSYKt33o0WF-TGF4N0RKUnCZ7308rZnw60O9pla9VubR8bD5WZ9AiiCzqmyPMxThS4nGE62peQZHL38zPwrszI1_xBgpc-cCs2qZg17HuQXeIaNWP4UeqiMAHWvgrYPQaUzSlmn_Z9FlLUS8a8rB8EBMDuT5wh7QFnIsRN32EHURh4FgKOHZ7cFsKOgSOI0mJTiO6kOjdBo3fD2wCA'
    ```
     - Response:
    ```
    HTTP STATUS_CODE 204
    ```
     - Buscando os documentos de um beneficiario pelo id:
      - Request example:
    ```
    curl -X 'GET' \
    'http://localhost:8080/api/beneficiary/1/documents' \
    -H 'accept: */*' \
    -H 'Authorization: Bearer eyJraWQiOiIxMmZiZTdkOC0xOWI1LTRiZWItODQ3Ni00MTc5YWEwMjYxOWMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWRtaW4iLCJleHAiOjE3MjE2OTYwMzMsImlhdCI6MTcyMTY5NDgzMywic2NvcGUiOiJST0xFX0FETUlOIFJPTEVfVVNFUiJ9.HRE8UYQPrzVCDfJ4UKhC2bHuAZg96AbJiM9Ctch0Is6Pf4iL_QpG_rvEmOU4mABJ11QEX2FN78l5F3z7kiMSw5fsYK-I9Gqp_q56oYWMeORV5CQs-7XuSekgTxB_lCeKk-ScSSYKt33o0WF-TGF4N0RKUnCZ7308rZnw60O9pla9VubR8bD5WZ9AiiCzqmyPMxThS4nGE62peQZHL38zPwrszI1_xBgpc-cCs2qZg17HuQXeIaNWP4UeqiMAHWvgrYPQaUzSlmn_Z9FlLUS8a8rB8EBMDuT5wh7QFnIsRN32EHURh4FgKOHZ7cFsKOgSOI0mJTiO6kOjdBo3fD2wCA'
    ```
     - Response:
    ```
    [
      {
        "id": 1,
        "documentType": "CPF",
        "documentNumber": "16235446020",
        "description": "Cadastro de Pessoa Física",
        "inclusionDate": "2024-07-22T21:33:20.666",
        "updateDate": "2024-07-22T21:33:20.666"
      }
    ]
    ```

## Acesso ao Banco de Dados H2

Este projeto utiliza o banco de dados H2 e podemos acessar o seu console no link abaixo.

- Painel de controle H2 [h2-console](http://localhost:8080/h2-console).
  - **username: admin**
  - **password: 123456**

