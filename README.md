# Bloomberg Deals Data Warehouse

### Description:
This project represents the data warehouse for every deal was done on Bloomberg project. \
It has been built and developed with a MySQL database and Spring boot on a Ubuntu OS. and deployed on a docker image. 
---
**NOTE**
The test cases was build on MySQL database, so you might need to create a MySQL database first before building and running the application.
---
### Install and run:
As mentioned above, this project was built on MySQL database and running on a docker image, so the  following steps are the steps to run the application:

- Navigate to /repository/download/path/bloombergdatawarehouse directory and run the command to build up the project with maven:
```
./mvnw clean install
```
- Install docker on your machine, for more information please follow the [link](https://docs.docker.com/engine/install/).
- After installing the docker, and the same directory, run the following command:
```
sudo docker-compose build
sudo docker-compose up
```
The '**sudo docker-compose build**' command is  to build the docker image for this project, and '**sudo docker-compose up**' to run it.

- Once the application is running, open your browser on http://localhost:8081 and you will get something like this:
- Now, you can add, view a deal, and view all deals that have been submitted sorted by their creation time.
---
### Author:
Monther Nedal Al-Husaini \
Software Development Engineer