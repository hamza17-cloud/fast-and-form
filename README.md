# fast-and-form-core

Core module for Fast & Form Backend

## before starting

You should use your ``application.properties`` in order to connect to the database:
```
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

spring.data.mongodb.host=<ip_address_or_host>
spring.data.mongodb.port=<port>
spring.data.mongodb.database=<db_name>
spring.data.mongodb.username=<user>
spring.data.mongodb.password=<password>
allowed.origin=http://<app_host>:<app_port>

ff.entities_location=src/main/resources/user_entity.json,src/main/resources/user_entity.json
```
allowed.origin is a sequence of URLs allowing browsers to do CORS request to the backend
## ajouter des valeur pour notre Users 
dans applaction.properties
app.<nomDuChamp>
exemple : app.firstName=toto
## How to install

Install maven and setup project:
```
mvn install
```

## How to package as a jar
```
mvn -B package --file pom.xml
```

### Start the jar from dev environment
```
java -jar target/fast-and-form-0.0.1-SNAPSHOT.jar  --spring.config.location=src\main\resources\application.properties
```

## How to start the jar

The JAR does not include a application.properties file, so will need one to start it with your configuration.

To start the jar use:
```
java -jar fast-and-form-core-<version>.jar --spring.config.location=/opt/somewhere/override.properties
```