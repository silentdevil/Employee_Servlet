package com.exist.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;

public class EmployeeService {

	public <E> void saveElement (E e) {
		Session session = beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
	}

	public Session beginTransaction() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		return session;
	}

	public <E> void deleteElement (E e) throws Exception {
		try {
			Session session = beginTransaction();
			if(e.getClass() == Employee.class)
				System.out.println(((Employee) e).getLastname());
			session.delete(e);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			InputManager.output("");
			//throw new Exception("Can't find " + e.getClass().getName());
		}
	}

	public <E> E getData(long id, E e) throws Exception {
		
		Session session = beginTransaction();
		try {
			e = (E) session.get(e.getClass(),id);
			session.getTransaction().commit();
		} catch(Exception ex) {
			InputManager.output("Data not found");
		}
		
		return e;
		
	}

	public <E> E getData(E e) throws Exception {
		try {
			Session session = beginTransaction();
			List<E> list = session.createCriteria(e.getClass()).list();
			return (E) list.get(list.indexOf((E)e));
		} catch(Exception ex) {
			throw new Exception();
		}
	}

	public boolean isRoleDeletable(Role role) {
		Session session = beginTransaction();	
	 	List<Role> list = session.createSQLQuery("SELECT * FROM roles INNER JOIN employee_role On employee_role.roleid = roles.roleid AND employee_role.roleid =" + role.getRoleId()).addEntity(Role.class).list();
		session.getTransaction().commit();
		return list.size() == 0;
	}

	public void listEmployees(String order){
		Session session = beginTransaction();
		List<Employee> list = session.createSQLQuery("select * from employees " + order).addEntity(Employee.class).list();
		switch(order) {
			case "ORDER BY gwa":
				list = session.createCriteria(Employee.class).list();
				list.stream().sorted((e1,e2) -> Float.compare(e1.getGwa(), e2.getGwa()))
					.collect(java.util.stream.Collectors.toList()).
					forEach(emp -> System.out.printf("%d\t%s, %s %s %.2f\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getGwa()));
				break;
			case "ORDER BY datehired":
				list = session.createQuery("FROM Employee e ORDER BY e.datehired").list();
				list.forEach(emp -> System.out.printf("%d\t%s, %s %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getDatehired()));
				break;
			case "ORDER BY lastname":
				list.forEach(emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename()));
				break;
			default:
				list.forEach(emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename()));
		}
		session.getTransaction().commit();
	}
	
	public void listRoles() {
		Session session = beginTransaction();	
		List<Role> list = session.createCriteria(Role.class).list();
		list.forEach(System.out::println);
		System.out.println();
		session.getTransaction().commit();
	}
	
	public Set<Role> listEmployeeRoles(Employee employee) {
		Session session = beginTransaction();	
		List<Role> list = session.createSQLQuery("SELECT * FROM roles JOIN employee_role ON employee_role.roleid = roles.roleid JOIN employees ON employees.employeeid = employee_role.employeeid WHERE employees.employeeid = " + employee.getEmployeeId()).addEntity(Role.class).list();		//list.forEach(emp -> System.out.printf("%s\t",emp.getRole()));
		session.getTransaction().commit();
		return new HashSet<Role>(list);
	}

}