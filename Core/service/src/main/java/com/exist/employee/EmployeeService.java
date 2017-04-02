package com.exist.employee;

import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
import java.util.Set;
import java.util.Date;
public class EmployeeService {
	//private UpdateEmployeeService updateEmployeeService;
	public void addEmployee(Employee employee){
			
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(employee);
		session.getTransaction().commit();
	}

	public void addAddress(Address address){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(address);
		session.getTransaction().commit();

	}
	
	public void addContact(Contact contact){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		

		session.save(contact);
		session.getTransaction().commit();
	}
	
	public void addRole(Role role) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.save(role);
		session.getTransaction().commit();
	}




	public void deleteEmployee(int employeeId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Employee> list = session.createSQLQuery("select * from employees where employeeid=" +employeeId).addEntity(Employee.class).list();
		
		session.delete(list.get(0));
		session.getTransaction().commit();
	}
	
	public void updateEmployee(Employee employee){
		//Employee emp = searchEmployee(id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		session.update(employee);
		session.getTransaction().commit();
	}
	
	public Employee searchEmployee(int id){
		Employee emp = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Employee.class);
		cri = cri.add(Restrictions.eq("employeeId", id));
		emp = (Employee)cri.list().get(0);
		session.getTransaction().commit();
		return emp;
	}
	
	public Role searchRole(String str) {
		Role role = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Role.class);
		cri = cri.add(Restrictions.eq("role", str));
		role = (Role)cri.list().get(0);
		session.getTransaction().commit();
		
		System.out.println("Commit");
		return role;
	}
	
	

	public void listEmployees(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Employee> list = session.createSQLQuery("select * from employees").addEntity(Employee.class).list();
		list.forEach(emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename()));
		session.getTransaction().commit();
	}
	
	public void listRoles() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		List<Role> list = session.createSQLQuery("select * from roles").addEntity(Role.class).list();
		list.forEach(emp -> System.out.printf("%s\t",emp.getRole()));
		System.out.println();
		session.getTransaction().commit();
	}
	
	public List<Role> listEmployeeRoles(Employee employee) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		List<Role> list = session.createSQLQuery("SELECT * FROM roles JOIN employee_role ON employee_role.roleid = roles.roleid JOIN employees ON employees.employeeid = employee_role.employeeid WHERE employees.employeeid = " + employee.getEmployeeId()).addEntity(Role.class).list();
		//list.forEach(emp -> System.out.printf("%s\t",emp.getRole()));
		session.getTransaction().commit();
		return list;
	}
}