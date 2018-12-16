/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logica.Lote;
import Logica.TipoSuelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author fdominguez
 */
public class TiposSueloDaoHibernate {
    
    private final SessionFactory sessionFactory;

    public TiposSueloDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
    
    public void guardar(TipoSuelo unTipoSuelo) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(unTipoSuelo);
        session.getTransaction().commit();
        session.close();
    }
}
