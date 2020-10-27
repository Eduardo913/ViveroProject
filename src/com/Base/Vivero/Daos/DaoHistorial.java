package com.Base.Vivero.Daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.Base.Vivero.Entity.Historial;
import com.Base.Vivero.Entity.Producto;
import com.Base.Vivero.Model.ConexionHibernate;

public class DaoHistorial {
	
	private static SessionFactory factory;

	public DaoHistorial() {
		ConexionHibernate.generarConexion();
		factory=ConexionHibernate.getFactory();
	}
	
	public List getHistorial() {
	       Session session = factory.openSession();
	       Criteria crit = session.createCriteria(Historial.class);
	       List Historial = crit.list();
	       crit.list().toString();
	       session.close();
	       return Historial;
	}
	
	public void addHistorial(Historial hist) {
		 Session session = factory.openSession();
	     session.beginTransaction();

	     session.save(hist);
	     
	     session.getTransaction().commit();
	     session.close();
	}
	
	public void deleteHistorial(Historial hist) {
		 Session session = factory.openSession();
		 session.beginTransaction();
	     session.delete(hist);
	     session.getTransaction().commit();
	     session.close();
	       
	}
	
	public void updateHistorial(Historial hist) {
		Session session = factory.openSession();
		 session.beginTransaction();
	     session.update(hist);
	     session.getTransaction().commit();
	     session.close();
	}
	
	public List getProductsHistorial() {
			Session session = factory.openSession();
	       Criteria crit = session.createCriteria(Historial.class);
	       crit.setProjection(Projections.groupProperty("Producto"));
	       List Historial = (List<Historial>)crit.list();
	       
	       return Historial;

    }
	 
}
