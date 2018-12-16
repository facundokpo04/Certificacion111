/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logica.Campo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author fdominguez
 */
public class CamposDaoHibernateImpl {

    private final SessionFactory sessionFactory;

    public CamposDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Campo campo) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(campo);
        session.getTransaction().commit();
        session.close();
    }
    
      public void Eliminar(Campo campo) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(campo);
        session.getTransaction().commit();
        session.close();
    }
     public void actualizar(Campo campo) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(campo);
        session.getTransaction().commit();
        session.close();
    }
    
}
