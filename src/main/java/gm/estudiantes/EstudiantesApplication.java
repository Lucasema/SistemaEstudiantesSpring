package gm.estudiantes;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {// Esta intefaz es para ejecutar la app por consola

	@Autowired // inyeccion de dependencia de manera automatica
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class); //obtener un logger para mandar a imprimir informacion a consola utilizando el logger que hemos configurado (se configura en el archivo logback-spring.xml)

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion...");
		//Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada!");
	}

	@Override
	public void run(String... args) throws Exception { // Este metodo se ejecuta por el psvm
		logger.info(nl + "Ejecutando metodo run de spring...");
		Scanner s = new Scanner(System.in);
		var continuar = true;
		while(continuar){
			mostrarMenu();
			continuar = ejecutarOperacion(s);
			logger.info(nl);
		}
	}
	private boolean ejecutarOperacion(Scanner s) {
		logger.info("Elije una opcion: ");
		int opcion = Integer.parseInt(s.nextLine());
		var continuar = true;
		logger.info(nl);
		switch(opcion){
			case 1 -> { // Listar estudiantes
				logger.info("Listado de Estudiantes: " + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + nl));
			}
			case 2 -> { // Buscar estudiante por id
				logger.info("Ingrese el id del estudiante a buscar: ");
				Integer estudianteId = Integer.parseInt(s.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(estudianteId);
				if(estudiante != null)
					logger.info("Estudiante encontrado: " + estudiante + nl);
				else
					logger.info("No se encontro el estudiante de id: " + estudianteId + nl);
			}
			case 3 -> { // Agregar estudiante
				logger.info("Agregar Estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = s.nextLine();
				logger.info("Apellido: ");
				var apellido = s.nextLine();
				logger.info("Telefono: ");
				var telefono = s.nextLine();
				logger.info("Email: ");
				var email = s.nextLine();

				//Crear el objeto estudiante sin el id
				var nuevoEstudiante = new Estudiante();
				nuevoEstudiante.setNombre(nombre);
				nuevoEstudiante.setApellido(apellido);
				nuevoEstudiante.setTelefono(telefono);
				nuevoEstudiante.setEmail(email);

				estudianteServicio.guardarEstudiante(nuevoEstudiante);
				logger.info("Estudiante guardado: " + nuevoEstudiante + nl);
			}
			case 4 -> { // Actualizar estudiante
				logger.info("Modificar Estudiante: " + nl);
				logger.info("Id del Estudiante: ");
				var idEstudiante = Integer.parseInt(s.nextLine());
				// Buscamos el estudiante a modificar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null){
					logger.info("Nombre: ");
					var nombre = s.nextLine();
					logger.info("Apellido: ");
					var apellido = s.nextLine();
					logger.info("Telefono: ");
					var telefono = s.nextLine();
					logger.info("Email: ");
					var email = s.nextLine();

					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);

					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: " + estudiante + nl);
				}
				else
					logger.info("No se encontro el estudiante de id: " + idEstudiante + nl);

			}
			case 5 -> { // Eliminar Estudiante
				logger.info("Eliminar estudiante:  " + nl);
				logger.info("Ingrese el id del estudiante a eliminar: ");
				var idEstudiante = Integer.parseInt(s.nextLine());
				// Buscamos el estudiante a eliminar
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if (estudiante != null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: " + estudiante + nl);
				}
				else{
					logger.info("No se encontro el estudiante de id: " + idEstudiante + nl);
				}
			}
			case 6 -> { // Salir
				 logger.info("Hasta pronto..." + nl + nl);
				 continuar = false;
			}
			default -> logger.info("Opcion erronea: " + opcion + nl);
		}
		return continuar;
	}
	private void mostrarMenu(){
		logger.info(nl);
		logger.info( "**** Sistema de Estudiantes ****" + nl
						+ "1. Listar Estudiantes" + nl
						+ "2. Buscar Estudiantes" + nl
						+ "3. Agregar Estudiantes" + nl
						+ "4. Modificar Estudiantes" + nl
						+ "5. Eliminar Estudiantes" + nl
						+ "6. Salir" + nl);
		logger.info(nl);

	}
}




