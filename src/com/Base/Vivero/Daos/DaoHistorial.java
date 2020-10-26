package com.Base.Vivero.Daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.Base.Vivero.Entity.Historial;
import com.Base.Vivero.Model.ConexionHibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DaoHistorial {

    private static SessionFactory factory;

    public DaoHistorial() {
        ConexionHibernate.generarConexion();
        factory = ConexionHibernate.getFactory();
    }

    public List getHistorial() {

        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Historial.class);
        List Historial = crit.list();
        session.close();
        System.out.println(Historial);
        return Historial;
    }

    public boolean deleteHistorial(){
        Session session = factory.openSession();
        session.beginTransaction();
        return true;
    }

    public void addHistorial(Historial hist) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(hist);

        session.getTransaction().commit();
        session.close();
    }

}
