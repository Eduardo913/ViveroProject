package com.Base.Vivero.Daos;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.Base.Vivero.Entity.Fotografia;
import com.Base.Vivero.Entity.Producto;
import com.Base.Vivero.Model.ConexionHibernate;


public class DaoFotografia {

	private static SessionFactory factory;
	
	
	public DaoFotografia() {
		ConexionHibernate.generarConexion();
		factory=ConexionHibernate.getFactory();
		
	}
	
	public List getFotografia() {
	       Session session = factory.openSession();
	       Criteria crit = session.createCriteria(Fotografia.class);
	       List fotografia = crit.list();
	       crit.list().toString();
	       session.close();
	       return fotografia;
	   }
	
	public void addFotografia(Fotografia fot) {
		 Session session = factory.openSession();
	     session.beginTransaction();
	     
	     session.save(fot);
	     
	     session.getTransaction().commit();
	     session.close();
	}
	
	public void deleteFotografia(Fotografia fot) {
		 Session session = factory.openSession();
		 session.beginTransaction();
	     session.delete(fot);
	     session.getTransaction().commit();
	     session.close();
	       
	}
	
	public void updateFotografia(Fotografia fot) {
		Session session = factory.openSession();
		 session.beginTransaction();
	     session.update(fot);
	     session.getTransaction().commit();
	     session.close();
	}
}
