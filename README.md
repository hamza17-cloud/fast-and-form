# fast-and-form-core

Core module for Fast & Form Backend

## before starting

You should use your ``application.properties`` in order to connect to the database:
```
spring.data.mongodb.host=<ip_address_or_host>
spring.data.mongodb.port=<port>
spring.data.mongodb.database=<db_name>
spring.data.mongodb.username=<user>
spring.data.mongodb.password=<password>
allowed.origin=http://<app_host>:<app_port>
```
allowed.origin is a sequence of URLs allowing browsers to do CORS request to the backend

## How to install

Install maven and setup project:
```
mvn install
```