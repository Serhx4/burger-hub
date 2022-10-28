<br />
<div align="center">
  <a href="https://github.com/Serhx4/burger-hub">
    <img src="https://github.com/Serhx4/burger-hub/blob/master/src/main/resources/static/image/logo/burger.png" alt="Logo" width="80" height="80">
  </a>
  
  <h1 align="center">Burger Hub</h1>

  <p align="center">
    E-commerce burger delivery platform
    <br />
    <a href="https://github.com/Serhx4/burger-hub"><strong>Explore repository »</strong></a>
  </p>
</div>

## About the project

E-commerce platform built with Spring Boot framework.
Has functionality for ordering already existing or creating your own burger designs, adding them to the cart, creating and validating orders, using promo codes to get a discounts.
Spring Security is used to authorize existing users to the system or create new accounts.

## Built with
Spring:
* Spring Boot
* Spring Boot Web
* Spring Boot Validation
* Spring Boot Security
* Spring Data Jpa

Database:
* H2  (for development purposes)
* PostgreSQL

Web interface:
* Thymeleaf
* Bootstrap

## Getting Started

### Installing
Easy start:

1. Clone the repo
   ```sh
   git clone https://github.com/Serhx4/burger-hub.git
   ```
2. Change active profile to `dev` in `application.properties` to run with pre-loaded in-memory H2 database
   ```js
   spring.profiles.active=dev
   ```
Or:

2. Change active profile to `test` in `application.properties` to run with PostgreSQL database
   ```js
   spring.profiles.active=test
   ```
3. Set up your database credentials in 'application-test.properties'
    ```js
    spring.datasource.username=your_username_here
    spring.datasource.password=your_password_here
   ```
   
### Executing
* On the start CommandLineRunner in `DevelopmentConfig.java` will preload in-memory `jdbc:h2:mem:burger-hub` H2 database (`dev` profile)
* Or empty `burger-hub-test` PostgreSQL database (`test` profile) with all necessary data to run application
* After app was started, go to `http://localhost:8080/`
* Use username `chef` and password `p` to log in as Burger Hub Chef. Chef can create new burger designs that will be available for all users
* Or register as new user through registration form
* You can check in-memory database at `localhost:8080/h2-console`. Use `admin` username and `p` password to log in into `jdbc:h2:mem:burger-hub` H2 database
