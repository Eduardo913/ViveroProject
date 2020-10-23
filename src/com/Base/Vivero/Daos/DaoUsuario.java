package com.Base.Vivero.Daos;

import com.Base.Vivero.Entity.Usuario;
import com.Base.Vivero.Model.ConexionHibernate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DaoUsuario {

    private static SessionFactory factory;

    public DaoUsuario() {
        ConexionHibernate.generarConexion();
        factory=ConexionHibernate.getFactory();
    }

    public void addUser(Usuario user){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<Usuario> getIUsers(){
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Usuario.class);
        List<Usuario> listUsers = crit.list();
        session.close();
        return listUsers;
    }

    public boolean authUser(String usuario, String password){
        Session session = factory.openSession();
        Criteria crit = session.createCriteria(Usuario.class);
        crit.add(Restrictions.eq("userName",usuario));
        crit.add(Restrictions.eq("password",password));
        List lista = crit.list();
        session.close();
        if (lista.isEmpty()){
            return false;
        }else {
            return true;
        }

    }

}
