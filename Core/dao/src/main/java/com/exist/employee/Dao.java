
package com.exist.employee;

import java.util.List;
import java.io.*;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;

public class Dao {
  //private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Session beginTransaction() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      if(!session.getTransaction().isActive())
        session.beginTransaction();
      return session;
    }
    

    public <T> void save(final T o){
      Session session = beginTransaction();
      session.save(o);
	  session.getTransaction().commit();
    }


    public void delete(final Object object){

      Session session = beginTransaction();
      session.delete(object);
	  session.getTransaction().commit();
    }

    /***/
    public <T> T get(final Class<T> type, final Long id){
      Session session = beginTransaction();
	  T t = (T) session.get(type, id);
	  session.getTransaction().commit();
      return t;
    }

    public <T> T get(T t) {
      Session session = beginTransaction();
      List<T> list = session.createCriteria(t.getClass()).list();
	  session.getTransaction().commit();
      return (T) list.get(list.indexOf((T)t));
    }

    /***/
    public <T> T merge(final T o)   {
      Session session = beginTransaction();
      return (T) session.merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o){
      Session session = beginTransaction();
      session.saveOrUpdate(o);
	  session.getTransaction().commit();
    }

    public <T> List<T> getAll(final Class<T> type) {
      Session session = beginTransaction();
      final Criteria crit = session.createCriteria(type);
      return crit.list();
    }
  }