package maquina1995.spring.profiles.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import maquina1995.spring.profiles.component.AbstractDatasource;
import maquina1995.spring.profiles.component.DefaultDatasource;
import maquina1995.spring.profiles.component.DesarrolloDatasource;
import maquina1995.spring.profiles.component.IntegradoDatasource;
import maquina1995.spring.profiles.component.UtilidadesDebug;

/**
 * Con la anotacion {@link Profile} le podemos indicar en que perfil se deben
 * cargar los beans
 * 
 * <li>Perfil - Beans Cargado</li>
 * <li>default - {@link SpringConfiguration#datasourceDefault()}</li>
 * <li>desarrollo - {@link SpringConfiguration#datasourceDesarrollo()}</li>
 * <li>integrado - {@link SpringConfiguration#datasourceIntegrado()}</td>
 * 
 * @return
 */
@Configuration
public class SpringConfiguration {

	public SpringConfiguration() {
		System.out.println("Se Empiezan a cargar los beans de spring...");
	}

	/**
	 * Solo se cargara si estamos en el eprfil de desarrollo
	 * 
	 * @return
	 */
	@Bean
	@Profile("desarrollo")
	public AbstractDatasource datasourceDesarrollo() {
		System.out.println("Se crea el DesarrolloDatasource porque estamos en el profile de desarrollo");
		return new DesarrolloDatasource();
	}

	@Bean
	@Profile("integracion")
	public AbstractDatasource datasourceIntegrado() {
		System.out.println("Se crea el IntegradoDatasource porque estamos en el profile de integrado");
		return new IntegradoDatasource();
	}

	/**
	 * Si no se define ningún perfil se cargara este bean
	 * 
	 * @return
	 */
	@Bean
	@Profile("default")
	public AbstractDatasource datasourceDefault() {
		System.out.println("Se crea el DefaultDatasource porque no se ha definido ningún profile");
		return new DefaultDatasource();
	}

	/**
	 * Este bean se cargara solo si no estamos en un perfil de integrado
	 * 
	 * @return
	 */
	@Bean
	@Profile("!integracion")
	public UtilidadesDebug utilidadesDebug() {
		System.out.println("Se crea el UtilidadesDebug porque no estamos en el profile de integrado");
		return new UtilidadesDebug();
	}

}
