package com.Base.Vivero.Daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.Base.Vivero.Entity.Calendario;
import com.Base.Vivero.Model.ConexionHibernate;

public class DaoCalendario {
	private static SessionFactory factory;

	public DaoCalendario() {
		ConexionHibernate.generarConexion();
		factory=ConexionHibernate.getFactory();
	}
	
	public List getCalendario() {
       Session session = factory.openSession();
       Criteria crit = session.createCriteria(Calendario.class);
       List calendario = crit.list();
       crit.list().toString();
       session.close();
       return calendario;
   }
	
	public void addCalendario(Calendario cal) {
		 Session session = factory.openSession();
	     session.beginTransaction();

	     session.save(cal);
	     
	     session.getTransaction().commit();
	     session.close();
	}
	
	public void deleteCalendario(Calendario cal) {
		 Session session = factory.openSession();
		 session.beginTransaction();
	     session.delete(cal);
	     session.getTransaction().commit();
	     session.close();
	       
	}
	
	public void updateCalendario(Calendario cal) {
		Session session = factory.openSession();
		 session.beginTransaction();
	     session.update(cal);
	     session.getTransaction().commit();
	     session.close();
	}
}
