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

1. Localizate en cada proyecto y el siguiente comando.
   
  ```sh
  sudo mvn clean package -DskipTests
  ```
  or
  
  ```sh
  ./mvnw install -DskipTests
  ```
   
2. Para cada proyecto crear el Dockerfile con la informacion del snapshot en  mi caso es target/api-0.0.1-SNAPSHOT.jar y con diferente nombre java-app.
```dockerfile
FROM openjdk:17-jdk-alpine

COPY target/api-0.0.1-SNAPSHOT.jar java-app-create.jar

ENTRYPOINT ["java", "-jar", "java-app-create.jar"]
```

3. Crear el docker compose con todos los proyectos, cada uno es un servicio.
```docker-compose.yml
version: '3.9'
services:
  java_app_create:
    container_name: java_app_create
    image: pee-java-app:1.0.0-create
    build:
      context: ./springBoot-create
    ports:
      - 8080:8080
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgresdocker
    depends_on:
      - java_db
  java_app_delete:
    container_name: java_app_delete
    image: pee-java-app:1.0.0-delete
    build:
      context: ./springBoot-delete
    ports:
      - 8081:8081
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgresdocker
    depends_on:
      - java_db
  java_app_read:
    container_name: java_app_read
    image: pee-java-app:1.0.0-read
    build:
      context: ./springBoot-read
    ports:
      - 8082:8082
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgresdocker
    depends_on:
      - java_db
  java_app_update:
    container_name: java_app_update
    image: pee-java-app:1.0.0-update
    build:
      context: ./springBoot-update
    ports:
      - 8083:8083
    environment:
      - DATABASE_URL=jdbc:postgresql://java_db:5432/postgres
      - DATABASE_USERNAME=postgres
      - DATABASE_PASSWORD=postgresdocker
    depends_on:
      - java_db
  java_db:
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
  
8. Eliminar contenedores e imagenes
  - `sudo docker-compose down --rmi all`
