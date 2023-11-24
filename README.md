## Desafio Idwall

Desafio proposto pela empresa <a href="https://idwall.co/">Idwall</a> durante o último semestre na FIAP, onde seria necessário criar uma API capaz de trazer dados de criminosos que pertencem a lista do FBI e da Interpol.

# Definições técnicas

Implementação utilizando o Java 17 e a versão 3.0.6 do Spring Boot, além do MongoDB para armazenamento e leitura dos dados. A aplicação conta com o uso do <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/">Feign Client</a> para consumo das APIs do FBI e Interpol.

# Sobre a API 

A API possui implementado três endpoints responsáveis por retornar os dados consumidos das APIs do <a href="https://api.fbi.gov/docs#/">FBI</a> e da <a href="https://interpol.api.bund.dev">Interpol</a>, inicialmente falando sobre os endpoints para os dados do FBI, temos:

<ul>
  <li><strong>fbi/busca-nome</strong></li>
  Endpoint responsável por receber via parâmetro o nome do criminoso e retornar os dados do criminoso através da busca por nome, caso exista. 
  <li><strong>fbi/busca-lista-categoria</strong></li>
  Endpoint responsável por receber via parâmetro uma categoria, qual realizará um de-para com as categorias descritas em inglês disponíveis na API do FBI e alterado para o português, retornando os dados dos criminosos na lista.
</ul>

Para o consumo dos dados da API do Interpol, temos o endpoint <strong><i>interpol/busca-nome</i></strong>, assim como o endpoint responsável por buscar através do nome no endpoint do FBI, neste endpoint ele retornará os dados do criminoso conforme disponibilizado pela API da Interpol. 

Todos os dados estarão sendo armazenados conforme as requisições nos endpoints e poderão ser visualizados através dos endpoints:

<ul>
  <li><strong>/storage/busca-nome</strong></li>
  Endpoint responsável por retornar uma lista contendo todos os dados através do nome procurado.
  <li><strong>/storage/busca-ano</strong></li>
  Endpoint responsável por retornar uma lista contendo todos os dados através do ano de nascimento procurado.
</ul>

<br>
<br>
A API está configurada com as anotações do OpenApi para visualização do contrato e no pacote de recursos da estrutura do projeto foi inserido collections do postman usadas para testes durante a criação da aplicação.


Swagger: [contrato-swagger.json](https://github.com/qbleonardo/desafio-idwall/files/13462138/contrato-swagger.json)
