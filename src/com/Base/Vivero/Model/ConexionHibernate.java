package com.Base.Vivero.Model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ConexionHibernate {
	
	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;
	private static Configuration configuration;
	private static String driver;
	
	private ConexionHibernate() {
	
	}
	
	public static void generarConexion() {
		if(factory == null) {
				System.err.println("Iniciando");
		       try {
		           System.err.println("Leyendo configuracion.");
		           generarConfiguracion();
		           serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		           factory = configuration.buildSessionFactory(serviceRegistry);
		       } catch (Throwable ex) {
		           System.err.println("No se puede crear la Sesion" + ex);
		           throw new ExceptionInInitializerError(ex);
		       }
		}
		
	}

	public static SessionFactory getFactory() {
		return factory;
	}
	
	private static void generarConfiguracion() {
		configuration = new Configuration();
		configuration.configure(driver+".cfg.xml");
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		ConexionHibernate.driver = driver;
	}
	
	


	
	
}
