## spring-rest-webflux
## Programação Reativa com Spring Boot, WebFlux e MongoDB

### O que é o Spring WebFlux?
Para suportar a programação reativa e a criação de sistemas reativos, o Spring Boot criou uma nova stack web chamada Spring WebFlux. 
Esta nova stack da web suporta Controller anotados, endpoints funcionais, WebClient (análogo ao RestTemplate no Spring Web MVC), WebSockets e muito mais.

### @Document
Classe Model é anotada com @Document porque o MongoDB é um banco de dados de documentos.

### Lombok
O Lombok é uma biblioteca Java focada em produtividade e redução de código boilerplate que por meio de anotações adicionadas ao nosso código ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código Java.
Desta forma não é necessário escrever métodos getter, setter, equals, builder e etc.

### ReactiveMongoRepository
Repository para consultar seu banco de dados MongoDB de forma reativa. 
O Spring suporta repositórios reativos e possui as operações básicas de CRUD, reativas, sob uma entidade.

### As classes Flux e o Mono
As classes Flux e o Mono são expostas nas APIs reativas do Spring Framework:
* Flux é um stream reativo formado por 0 ou N elementos.
* Mono é um stream reativo formado por 0 ou 1 elemento.

### Netty
O projeto roda com o servidor web reativo, o Netty, na porta 8080 como default. 
O Netty é um framework que oferece a infraestrutura client server non-blocking I/O.

## Autor: Diego Gomes Borges
