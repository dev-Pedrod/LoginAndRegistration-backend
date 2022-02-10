# Overview
Back-end Developed in Java and Spring Boot for User Registration and Login with email verification using the Gmail service.

*(Developed in test profile)*

## Technologies

Here are the technologies used in this project:

- Java 11
- Spring Boot
- Spring Security
- H2 Database
- Java Mail

## Run

- In [application.properties](https://github.com/dev-Pedrod/LoginAndRegistration-backend/blob/master/src/main/resources/application.properties) put your email in `spring.mail.username` and your token in `spring.mail.password` to use the email service.

Example:

  ```
spring.mail.username=YourGmailHere@gmail.com
spring.mail.password=YourTokenHere
  ```
*Guide to generate 16 digits token to configure Gmail smtp:  [Sign in with app passwords](https://support.google.com/accounts/answer/185833)*

- Access the H2 database at: [H2 Database](http://localhost:8080/h2-console/)
  
 *(Remember to run the project before accessing)*
  
 ## Prints
 
  ### Login Example
  ![login](https://github.com/dev-Pedrod/LoginAndRegistration-backend/blob/readme/readmeIMG/Example%20Login.PNG)
  
  ### Request Example
  ![request](https://github.com/dev-Pedrod/LoginAndRegistration-backend/blob/readme/readmeIMG/Example%20request.PNG)
  
  ### Email Example
  ![email](https://github.com/dev-Pedrod/LoginAndRegistration-backend/blob/readme/readmeIMG/Example%20email.PNG)
  
---
By dev-Pedrod  [*See my Linkedin*](https://www.linkedin.com/in/pedrooliveiradev/)
