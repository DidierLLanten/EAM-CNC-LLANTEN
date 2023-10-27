# Parcial 2

### *Requirements*

**jdk17**
```sh
sudo apt install openjdk-17-jdk
```
**maven**
```sh
sudo apt install maven
```

***

### Steps
**These steps were executed on a VMware Ubuntu**

1. Por cada proyecto ejecutar el siguiente comando, Locate the path to the application and compile it with either of the two commands.
   
  ```sh
  sudo mvn clean package -DskipTests
  ```
  or
  
  ```sh
  ./mvnw install -DskipTests
  ```
   
2. Cada proyecto creaer el dockerfile con diferente nombre java-app, Create the Dockerfile with the snapshot information in my case is target/api-0.0.1-SNAPSHOT.jar.
```dockerfile
FROM openjdk:17-jdk-alpine

COPY target/api-0.0.1-SNAPSHOT.jar java-app.jar

ENTRYPOINT ["java", "-jar", "java-app.jar"]
```

3. Crear el docker compose con todos los proyectos, cada uno es un servicio, Create the docker-compose.yml with the two services we need, make the java_app service depend on java_db and add a volume to the java_db to persist the information, and set the environment variables written in the docker-compose.yml in the application.properties file.
```docker-compose.yml
version: '3.9'
services:
  java_app:
    container_name: java_app
    image: pee-java-app:1.0.0
    build: .
    ports:
      - 8080:8080
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgresdocker
    depends_on:
      - java_db
  java_db:
    container_name: java_db
    image: postgres:15.4
    ports:
      - 5432:5432
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgresdocker
      - POSTGRES_DB=postgres
    volumes:
      - ./data_bd:/var/lib/postgresql/data
```

4. In the root of the project create the folder where the volume will be stored, data_bd.

5. Execute the following command to build and run the service named java_app, for the first time.
```sh
sudo docker-compose up --build
```

6. Now that the containers are up, you can test the application in postman through these routes.
   - Read records GET
        `http://localhost:8082/api/usuario/list`
   - Create records POST 
        `http://localhost:8080/api/usuario/create`
     - Body raw (json)
     ```json
      {
        "nombre": "Didier",
        "apellido": "LLanten",
        "cedula": "1054967123",
        "edad": 26,
        "direccion": "Calle 9",
        "email": "didierdocker@gmail.com",
        "telefono": "3138872431"
      }
     ```
     - Update record PUT 
        `http://localhost:8083/api/usuario/update/1`
     - Body raw (json)
     ```json
      {
        "nombre": "Didier",
        "apellido": "LLanten",
        "cedula": "1054967123",
        "edad": 26,
        "direccion": "Calle 9",
        "email": "didierdocker@gmail.com",
        "telefono": "3138872431"
      }
     ```
      Delete record DELETE
        `http://localhost:8081/api/usuario/delete/4`

7. Finally, To test the persistence in the database, you have to download the containers, upload them again and list the records, it should bring all the previously saved records.
   - Containers down `sudo docker-compose down`
   - Containers up `sudo docker-compose up`
