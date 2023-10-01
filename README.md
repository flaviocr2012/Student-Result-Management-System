# Student-Result-Management-System

# How To run

* Start the application
* Open H2 Data Base in: http://localhost:8080/h2-console
* Put the username and password (in application.properties file) to connect. 

# How To test

* You must have Postman installed. Tomcat is running in localhost:8080.
* Put the URLs and body and test the functionalities.

# Architecture

* API Rest. Controller, Service and Repository. 
* I still separated the classes and methods in some layers like: dtos, exceptions, helpers
and configs, for instance. 

# About Development

Tech stack used in this project: 
* Java 17
* Spring Boot
* Spring Data JPA
* H2 Data Base

Some Libs:
* Json-Path
* Lombok
* Jackson
* ModelMapper

The solution developed for this project attends to the demanded requirements like:

* Add new students;
* Add new courses;
* Add new results;
* Return a list of students;
* Return a list of courses;
* Return a list of results;
* Delete a student;
* Delete a course;
* Delete a result;
* The list of results is updated, after a student or a course is deleted.

Developed the back end side. Thanks!
