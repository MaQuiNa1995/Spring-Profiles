package maquina1995.spring.profiles;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import maquina1995.spring.profiles.component.AbstractDatasource;
import maquina1995.spring.profiles.configuration.SpringConfiguration;

/**
 * Tendremos que ejecutar la aplicación con este argumento
 * <p>
 * -Dspring.profiles.active=nombrePerfil
 * <p>
 * Para elegir el perfil a usar En esta aplicación hay 2:
 * <li>desarrollo</li>
 * <li>integracion</li>
 * <p>
 * Esto hará un filtro para que se carguen ciertos beans a traves del
 * {@link SpringConfiguration}
 * 
 * @author MaQuiNa1995
 *
 */
public class Main {

	public static void main(String[] args) {
		// Try con recursos para cerrar automaticamente al final del try el contexto
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
		        SpringConfiguration.class)) {
			verPerfilActivo(context);
			cargarDatasource(context);
		}
	}

	private static void cargarDatasource(AnnotationConfigApplicationContext context) {
		AbstractDatasource datasource = context.getBean(AbstractDatasource.class);
		System.out.println("En este caso se carga " + datasource.getClass().getSimpleName());

	}

	private static void verPerfilActivo(AnnotationConfigApplicationContext context) {
		Environment environment = context.getBean(Environment.class);

		System.out.println("Nombre de perfil activo/s:");
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println(profileName);
		}

	}

}
