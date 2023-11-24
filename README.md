# Desafio Idwall

Desafio proposto pela empresa <a href="https://idwall.co/">Idwall</a> durante o último semestre na FIAP, onde seria necessário criar uma API capaz de trazer dados de criminosos que pertencem a lista do FBI e da Interpol.

# Definições técnicas

Implementação utilizando o Java 17 e a versão 3.0.6 do Spring Boot, além do MongoDB para armazenamento e leitura dos dados. A aplicação conta com o uso do <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/">Feign Client</a> para consumo das apis do FBI e Interpol.

# Sobre a API 

A API possui implementado três endpoints responsáveis por retornar os dados consumidos das APIs do <a href="https://api.fbi.gov/docs#/">FBI</a> e da <a href="https://interpol.api.bund.dev">Interpol</a>, inicialmente falando sobre o endpoint para os dados do FBI, temos:

<ul>
  <li>/busca-nome</li>
  Endpoint responsável por retornar os dados do criminoso através da busca por nome, caso exista. 

  
</ul>




