package com.Base.Vivero.Daos;

import java.util.Date;
import java.util.List;

import com.Base.Vivero.Entity.Historial;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.Base.Vivero.Entity.Fotografia;
import com.Base.Vivero.Model.ConexionHibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;


public class DaoFotografia {

    private static SessionFactory factory;


    public DaoFotografia() {
        ConexionHibernate.generarConexion();
        factory = ConexionHibernate.getFactory();

    }

    public List<Fotografia> getFotografia() {
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Fotografia.class);
        List<Fotografia> fotografia = crit.list();
        return fotografia;
    }

    public void  modifiedFotografia(Fotografia fotografia){
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(fotografia);
        session.getTransaction().commit();
        session.close();
    }



    public boolean deleteFotografia(Fotografia fotografia) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(fotografia);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    public void addFotografia(Fotografia fot) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(fot);

        session.getTransaction().commit();
        session.close();
    }
}
