# PROGRAMA GEN IA COPILOT
# CALIFICACIONES DE UN ESTUDIANTES

## FUNCIONALIDAD
Proyecto backend construido en acompañamiento con GitHub Copilot.

Este proyecto consiste en el procesamiento de calificaciones de alumnos, el cual nos indica si el alumno aprobó o reprobó.

Este proceso batch consiste en lo siguiente:  
1.- Lee el archivo csv en el cual vienen el registro de las calificaciones de los alumnos.  
2.- Se procesan los datos leídos, y se obtiene el promedio.  
3.- Se escriben estos datos procesados, es decir, las calificaciones y el promedio, en la base de datos MySQL.   
4.- Ya teniendo estos datos con el promedio, se proceden a ser leídos.  
5.- Se procesan estos datos con el promedio, ahora para establecer el estado de si el alumno aprobó o reprobó.  
6.- Se escribe el resultado de los datos procesados, es decir, los datos del alumno con el estado de aprobado o reprobado,en una colección de mongo db. 

Adicional, se crearon controladores para exponer los endpoints de eta información y pueda ser consumida por un frontend, por medio de los mismos.

## REQUISITOS
- Java 17
- Spring Batch
- Maven 
- Docker
- MySQL
- MongoDB

## INSTALACIÓN 
1.- Clonar el repositorio o descargar el proyecto y descomprimir para abrirlo en tu IDE.

2.- Crear una base de datos en MySQL con el nombre de academia y una tabla llamada estudiantes procesados. 
Asegurarse que la base de datos tenga la siguiere configuración:
Puerto: 3306
User: root
Contraseña: admin 

3.-Crear y levantar un contenedor docker con la imagen de mongodb con las siguientes características:
Puerto: 27018
User: root 
Password: root123

- Comando descargar imagen de mongo db y creación del contenedor 
#### docker run -d -p 27018:27017 --name academia -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root123 -e MONGO_INITDB_DATABASE=academia mongo:latest

- Comando verificar conexión 
#### mongosh "mongodb://root:root123@localhost:27018/academia?authSource=admin"

4.- En el archivo application.properties habilitamos el proceso batch, cambiando a true el siguiente campo:
#### spring.batch.job.enabled=true

## EJECUCIÓN
1.- Nos vamos a la terminal del proyecto y ejecutamos el siguiente comando: 

#### mvn spring-bot: run

Con esto estaríamos ejecutando el proceso batch. 

2.- Para validar las pruebas unitarias, ejecutáramos el siguiente comando:

#### mvn tests

3.- Los pasos anteriores también pueden ejecutarse desde sus respectivas clases y el botón run del IDE.

4.- Para corroborar la funcionalidad de los controllers, se pueden realizar de tres formas: 

4.1. En gitbash por medio de los siguientes comandos:
- curl http://localhost:8080/api/estudiantes
- curl -i http://localhost:8080/api/estudiantes/1
- curl -i http://localhost:8080/api/estudiantes/9999 # debe dar 404
- curl -i -X POST http://localhost:8080/api/estudiantes -H "Content-Type: application/json" -d '{"nombre":"Nuevo Alumno","grupo":"A","nota1":80,"nota2":85,"nota3":90,"promedio":85}'
- curl http://localhost:8080/api/estudiantes/aprobados/total
- curl http://localhost:8080/api/reportes/estado/REPROBADO

4.2. En navegador por medio de la url:
- http://localhost:8080/api/estudiantes
- http://localhost:8080/api/estudiantes/1
- http://localhost:8080/api/estudiantes/9999 # debe dar 404
- http://localhost:8080/api/estudiantes/aprobados/total
- http://localhost:8080/api/reportes/estado/REPROBADO

4.3. En herramientas como postman seleccionamos el método que se desea ejecutar (GET,POST,PUT,DELETE,PATCH) , se coloca la url http://localhost:8080/api/estudiantes... con sus respectivos parámetros de ser necesario y se ejecuta.
