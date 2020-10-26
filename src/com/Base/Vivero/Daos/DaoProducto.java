package com.Base.Vivero.Daos;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.Base.Vivero.Entity.Producto;
import com.Base.Vivero.Model.ConexionHibernate;


public class DaoProducto {
	
	 private static SessionFactory factory;
	 
	public DaoProducto() {
		ConexionHibernate.generarConexion();
		factory=ConexionHibernate.getFactory();
	}
	
	public List getProducto() {
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Producto.class);
        List productos = crit.list();
        session.close();
        return productos;
    }
	
	public void addProducto(Producto prod) {
		 Session session = factory.openSession();
	     session.beginTransaction();

	     session.save(prod);
	     
	     session.getTransaction().commit();
	     session.close();
	}
	
	public void deleteProducto(Producto prod) {
		 Session session = factory.openSession();
		 session.beginTransaction();
	     session.delete(prod);
	     session.getTransaction().commit();
	     session.close();
	       
	}
	
	public void updatePoducto(Producto prod) {
		Session session = factory.openSession();
		 session.beginTransaction();
	     session.update(prod);
	     session.getTransaction().commit();
	     session.close();
	}
	public List getTipos() {
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Producto.class);
        crit.setProjection(Projections.groupProperty("tipo"));
        List listaTipos = crit.list();
        session.close();
        return listaTipos;

    }
	
	public List getCondicon() {
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Producto.class);
        crit.setProjection(Projections.groupProperty("condicionActual"));
        List listCondicion = crit.list();
        session.close();
        return listCondicion;

    }
	
	
	
}
