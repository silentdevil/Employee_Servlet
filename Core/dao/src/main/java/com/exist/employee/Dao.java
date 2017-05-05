
package com.exist.employee;

import java.util.List;
import java.io.*;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.stat.*;
import org.slf4j.*;

public class Dao {
  private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
  private final Statistics statistics = sessionFactory.getStatistics();
   


    public Session beginTransaction() {
       statistics.setStatisticsEnabled(true);
      Session session = sessionFactory.openSession();
        if(!session.getTransaction().isActive())
          session.beginTransaction();
        return session;
    }
    

    public <T> void save(final T o){
      Session session = beginTransaction();
      session.save(o);
	    session.getTransaction().commit();
      session.close();
    }

    public <T> void saveOrUpdate(final T o){
      Session session = beginTransaction();
      session.saveOrUpdate(o);
      session.getTransaction().commit();
      session.close();
    }

    public void delete(final Object object){
      Session session = beginTransaction();
      session.delete(object);
	    session.getTransaction().commit();
      session.close();
    }

    public <T> T get(final Class<T> type, final Long id){
      Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      T t = null;
      try {
	       t = (T) session.get(type, id);
         
          session.getTransaction().commit();
          session.close();
      } catch(Exception ex) {
        ex.printStackTrace();
      }
      printStatistics(statistics);
      return t;
    }

    public <T> T get(T t) {
      Session session = beginTransaction();
      List<T> list = session.createCriteria(t.getClass()).list();
  	  session.getTransaction().commit();
      session.close();
      return (T) list.get(list.indexOf((T)t));
    }

    public <T> void update(final T o){
      Session session = beginTransaction();
      session.update(o);
	     session.getTransaction().commit();
      session.close();
    }

    public <T> List<T> getAll(final Class<T> type) {
      Session session = beginTransaction();
	    Criteria criteria = session.createCriteria(type)   
                                 .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
      List<T> list = criteria.list();
      printStatistics(statistics);
      session.close();
      return list;
    }

    public <T> List getAll(final Class<T> type, String order) {
      Session session = beginTransaction();
	  Criteria criteria = session.createCriteria(type);
	  criteria.setCacheable(true);
    criteria.setProjection(Projections.projectionList()
                                      .add(Projections.property("employeeId"))
                                      .add(Projections.property("employeeName"))
                                      .add(Projections.property(order)))
            .addOrder(Property.forName(order).asc());
      List list = criteria.list();
      printStatistics(statistics);
      session.close();
      
      return list;
    }

    public static void printStatistics(Statistics statistics)
    {
        System.out.println("***************");
        System.out.println("Entity fetch count :" + statistics.getEntityFetchCount());
        System.out.println("Second level cache hit count : "+ statistics.getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + statistics.getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + statistics.getSecondLevelCacheMissCount());
        System.out.println("***************");
    }

  }