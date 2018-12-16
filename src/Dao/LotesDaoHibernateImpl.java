/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logica.Campo;
import Logica.Lote;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author fdominguez
 */
public class LotesDaoHibernateImpl {

    private final SessionFactory sessionFactory;

    public LotesDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void guardar(Lote lote) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(lote);
        session.getTransaction().commit();
        session.close();
    }
    
       public void eliminar(Lote lote) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(lote);
        session.getTransaction().commit();
        session.close();
    }

    public void actualizar(Lote unLote) {
          Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(unLote);
        session.getTransaction().commit();
        session.close();
    }
}
