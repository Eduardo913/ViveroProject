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
        List historial = crit.list();
        System.out.println(historial);
        session.close();
        return historial;
    }

    public boolean deleteHistorial(Historial historial) {
        System.out.println(historial);
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(historial);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    public void addHistorial(Historial hist) {
        Session session = factory.openSession();

        session.beginTransaction();

        session.save(hist);

        session.getTransaction().commit();
        session.close();
    }

}
