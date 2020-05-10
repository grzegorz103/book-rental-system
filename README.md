# Book Rental System 
[![CircleCI](https://circleci.com/gh/grzegorz103/BookRentalSystem.svg?style=svg)](https://circleci.com/gh/grzegorz103/BookRentalSystem)
<img alt="GitHub top language" src="https://img.shields.io/github/languages/top/grzegorz103/book-rental-system">
## Table of contents
* [General info](#general-info)
* [Demo](#demo)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)

## General info
Book rental system created with Spring and Angular 5.

## Demo

**[Click here](https://rent-book.herokuapp.com/)**

### Example users
| __Login__ | __Password__ | Role |
| -------------- | ------------ | --- |
| admin | admin1 | Admin |
| user1 | usertest | User |
| user2 | usertest2 | User | 

## Technologies
* Spring (Boot, MVC, Security, Data JPA)
* Hibernate
* PostgreSQL
* Lombok, Swagger
* JUnit, Mockito
* CircleCI
* Angular 5

## Setup
### Prerequisites

- Angular 8 or greater is required
```$xslt
$ npm install -g @angular/cli
``` 
- Java 8 or greater

### Deployment

```
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
$ cd front
$ npm install
$ ng serve
```
Run browser and head to ```http://localhost:4200```    
Swagger doc is available at ```http://localhost:8080/swagger-ui.html ```
