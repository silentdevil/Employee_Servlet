package com.exist.employee;

import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
public class EmployeeService {

	public void addEmployee(String lastname, String firstname, String middlename){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Employee emp = new Employee();
		emp.setFirstname(firstname);
		emp.setLastname(lastname);
		emp.setMiddlename(middlename);


		session.save(emp);
		session.getTransaction().commit();
	}

	public void addAddress(String brgy, String city){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Address add = new Address();
		add.setBrgy(brgy);
		add.setCity(city);

		session.save(add);
		session.getTransaction().commit();
	}



	public void deleteEmployee(String employeeName){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List list = session.createSQLQuery("select * from employees").addEntity(Employee.class).list();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Employee emp = (Employee)itr.next();
			System.out.println("delete : "+ emp);
			session.delete(emp);
		}
		session.getTransaction().commit();
	}
	
	public void updateEmployee(String lastname, String middlename){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Employee.class);
		cri = cri.add(Restrictions.eq("lastname", lastname));
		List list = cri.list();
		Employee emp = (Employee)list.iterator().next();
		emp.setMiddlename(middlename);
		session.update(emp);
		session.getTransaction().commit();
	}

	public void listEmployees(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List list = session.createSQLQuery("select * from employees").addEntity(Employee.class).list();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Employee emp = (Employee)itr.next();
			System.out.println(emp);
		}
		session.getTransaction().commit();

	}
}