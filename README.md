# livraria-api
REST API developed during Alura Bootcamp Java. 

REST API to control a digital library.

# Technologies used
* Spring
  - [Spring Initializr](https://start.spring.io/)
  - [Spring Web](https://spring.io/guides/gs/serving-web-content/)
  - [Spring Dev Tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
  - [Spring Validation](https://www.baeldung.com/spring-boot-bean-validation)
* [ModelMapper](http://modelmapper.org/)
* [Project Lombok](https://projectlombok.org/)
* Java 11

# Executing project
Execute the class `LivrariaApiApplication.java` as a normal Java Application.

# API Test
* [Postman](https://www.postman.com/)

# end-points

| METHOD        | END-POINT                     | DESCRIPTION                    | FIELDS                                         |
| ------------- |:-----------------------------:| :-----------------------------:|:----------------------------------------------:|
| POST          | http://localhost:8080/autores |  Set a new author.             | nome, dataNascimento, nacionalidade, curriculo |
| GET           | http://localhost:8080/autores |  Get a list of authors.        |    |


