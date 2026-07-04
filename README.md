# PROGRAMA GEN IA COPILOT
# CALIFICACIONES FINALES

## FUNCIONALIDAD
Proyecto backend construido con Copilot.

Este proyecto consiste en el procesamiento de calificaciones de alumnos, el cual nos indica si el alumno aprobó o reprobó.

Este proceso batch consiste en lo siguiente:
1.- Lee el archivo csv en el cual vienen el registro de las calificaciones de los alumnos.
2.- Se procesan los datos leídos, y se obtiene el promedio.
3.- Se escriben estos datos procesados, es decir, las calificaciones y el promedio, en la base de datos MySQL. 
4.- Ya teniendo estos datos con el promedio, se proceden a ser leídos. 
5.- Se procesan estos datos con el promedio, ahora para establecer el estado de si el alumno aprobó o reprobó. 
6.- Se escribe el resultado de los datos procesados, es decir, los datos del alumno con el estado de aprobado o reprobado,en una colección de mongo db. 

Adicional, se crearon controladores para exponer endpoints y ver esta información en el navegador. 

## REQUISITOS
Java 17
Spring Batch
Maven 
Docker
MySQL
MongoDB

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

Comando creación 
Comando verificar conexión 

4.- En el archivo application.properties habilitamos el proceso batch, cambiando a true el siguiente campo:

## EJECUCIÓN
1.- Nos vamos a la terminal del proyecto y ejecutamos el siguiente comando: 

mvn spring-bot: run

Con esto estaríamos ejecutando el proceso batch. 

2.- Para validar las pruebas unitarias, ejecutáramos el siguiente comando:

mvn tests

3.- Los pasos anteriores también pueden ejecutarse desde sus respectivas clases y el botón run del IDE.

4.- Para corroborar la funcionalidad de los controllers, se pueden realizar de tres formas: 

    * En gitbash
    * En navegador
    * En herramientas como postman
