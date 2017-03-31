package com.exist.employee;

import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
public class EmployeeService {

	public void addEmployee(String lastname, String firstname, 
								String middlename, Address address, float gwa,
								boolean currentlyHired, Contact contact){
			
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Employee emp = new Employee();
		emp.setFirstname(firstname);
		emp.setLastname(lastname);
		emp.setMiddlename(middlename);
		emp.setAddress(address);
		emp.setGwa(gwa);
		emp.setCurrentlyHired(currentlyHired);
		emp.setContact(contact);
		


		session.save(emp);
		session.getTransaction().commit();
	}

	public Address addAddress(String brgy, String city){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Address add = new Address();
		add.setBrgy(brgy);
		add.setCity(city);

		session.save(add);
		session.getTransaction().commit();
		return add;
	}
	
	public Contact addContact(String landline){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Contact contact = new Contact();
		contact.setLandline(landline);

		session.save(contact);
		session.getTransaction().commit();
		return contact;
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