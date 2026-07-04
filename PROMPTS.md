La IA Clásica es como un crítico de arte que analiza y clasifica obras existentes.
La IA Generativa es como un artista que crea obras nuevas a partir de lo que ya ha aprendido estudiando millones de obras previas.
# Evidencia de uso de GitHub Copilot — <Paola Esquivel>
## Prompt 1 — pom.xml
- **Prompt:** Genera un pom.xml para un proyecto Spring Boot 3.2.2 con Java 17 y estas dependencias:
  spring-boot-starter-batch, mysql-connector-j (scope runtime), spring-boot-starter-data-mongodb, springboot-
  starter-web, spring-boot-starter-data-jpa y spring-boot-starter-test (scope test). groupId
  com.academia, artifactId spring-batch-final-calificaciones, versión 1.0.0. Incluye el spring-boot-mavenplugin.
- **Modalidad:** Chat
- **Resultado:** aceptado, en este paso me di cuenta que por default esta en modo agente, por lo que procedi a modificarlo a modo ask, sin embargo, si valide que haya agregado correctamente las dependencias y versiones solicitadas.
y posterior, en modo ask(chat) le pedi nuevamente generar el pom.xml, y me genero correctamente el pom.xml con el mismo prompt,y lo compare con el que habia generado y cambiado el agente.
y solo hubo un par de diferencias en la parte de los datos del proyecto no estaba el campo de descpción y en properties el de project.build.sourceEncoding.
## Prompt 2 — Modelo Estudiante
- **Prompt:** // Clase modelo Estudiante con los campos: nombre (String), grupo (String),
  // nota1, nota2, nota3 y promedio (todos double).
  // Incluye constructor vacio, getters y setters de todos los campos,
  // y un toString que muestre nombre, grupo y promedio.
- **Modalidad:** Autocompletado
- **Resultado:** Aceptado, el prompt fue suficiente para que me generara la clase modelo Estudiante con los campos solicitados, constructor vacio, getters y setters de todos los campos y un toString 
que muestra nombre, grupo y promedio. Solo que si escribi los dos primeros comentarios para que me saliera el autocompletado.
Adicional, pregunte si era necesario instanciar la clase estudiante para compilarla, a lo que me respondio que no,que con tener la clase Estudiante bien 
Maven la compila aunque aun no se use. Que solo requiero una clase adicional si se desea ejecutar, lo cual es correcto, 
mas bien yo habia confundido el termino compilar con ejecutar.

## Prompt 2.1 - Processor Estudiante 
- **Prompt:** /// Processor de Spring Batch que implementa ItemProcessor<Estudiante, Estudiante>.
  // En el metodo process: calcula el promedio como (nota1 + nota2 + nota3) / 3,
  // asigna el promedio al estudiante con setPromedio, registra un log con SLF4J
  // "Step 1 - Procesando: {estudiante}" y devuelve el estudiante.
- **Modalidad:** Autocompletado
- **Resultado:** Aceptado, pero primero tuve que agregar el prompt arriba de la clase Estudiante,
de lo contrario, me generaba de nuevo la clase EstudianteProcessor,
asi como tuve que empezar a escribir la definicion de la clase EstudianteProcessor para que
implementara de la interfaz ItemProcessor<Estudiante, Estudiante> y me generara el metodo process, y luego de eso me genero el resto del prompt correctamente.

## Prompt 3 — ReporteEstudianteProcessor (umbral)
- **Prompt:** // Processor que implementa ItemProcessor<Estudiante, EstudianteReporte>.
  // Convierte un Estudiante en un EstudianteReporte copiando nombre, grupo y promedio,
  // y asigna estado "APROBADO" si el promedio es >= 70, o "REPROBADO" si es menor.
  // Loguea "Step 2 - Reporte: {reporte}" y devuelve el reporte.
- **Resultado:** Aceptado, no tuvo umbral, porque si lo hizo con el signo igual o mayor que, ademas uso el operador ternario para asignar el estado.


## Comparación código de Config Batch con y sin Copilot
En sí, pude identificar que realmente los cambios que realice en esta clase de configuración fueron mas de como se estaban ordenando los pasos, esto me ayudo
a comprender la logica del programa e identificar cada una de las partes, de igual manera la forma en la que la IA puso los nombres de los metodos no eran
tan claros o evidentes como los que se colocaron en el proyecto de la clase, finalmente, aqui inyecte ambos processors en lugar de utilizar la anotacion de
@Component en cada uno de ellos, de igual manera considero que esto es para hacer más explicito el proceso.

## Prompt 4 — /tests del Processor
- Genera pruebas unitarias con JUnit 5 para EstudianteProcessor y
  ReporteEstudianteProcessor: que verifiquen que el promedio se calcula bien, y que el estado es APROBADO con
  promedio 70 y REPROBADO con 69.9.
- **Resultado: Copie el del promedio, sin embargo, el del reporte estaba colocando los valores tal cual 70 aprobado y 69.9
- reprobado por lo que le pedi uno donde tomara los bordes para la validación.

## /fix
Se corrigieron dos problemas detectados: el promedio se estaba calculando con divisor incorrecto y el mensaje de procesamiento no usaba SLF4J.
<!-- replace lines 8 to 10 -->
import com.academia.batch.model.Estudiante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
<!-- replace lines 13 to 14 -->
public class EstudianteProcessor implements ItemProcessor<Estudiante, Estudiante> {
private static final Logger log = LoggerFactory.getLogger(EstudianteProcessor.class);
<!-- replace lines 15 to 21 -->
@Override
public Estudiante process(Estudiante estudiante) throws Exception {
double promedio = (estudiante.getNota1() + estudiante.getNota2() + estudiante.getNota3()) / 3.0;
estudiante.setPromedio(promedio);
log.info("Step 1 - Procesando: {}", estudiante);
return estudiante;
}
## Prompt 5 — Prompt malo vs. bueno (Bloque 3)
- **Malo:** haz un metodo de reprobados
- **Bueno:** (el prompt completo con contexto/objetivo/restricciones)
- **Diferencia observada:** En mi caso, solo cuando le di el prompt malo me coloco comentarios especificando como funcionaba
- el metodo pero de ahí en fuera ambos fueron iguales, pero considero que esto fue así ya por 
- todo lo que se ha trabajado o he estado revisado en el chat, con eso es que ha tenido mas contexto. 
## Prompt 6 — Refactor sin romper
- **Prompt:** Refactoriza este método para que sea más legible sin cambiar su
  comportamiento.
- **¿Tests siguieron en verde?:** sí todos pasaron exitosamente