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

	public <E> void deleteElement (E e) {
		Session session = beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
	}

	public <E> E getData(long id, E e) throws Exception {
		
		Session session = beginTransaction();
		/*Criteria cri = session.createCriteria(e.getClass());
		String s = e.getClass().getName().toLowerCase() + "Id".trim();
		s = s.replace("com.exist.employee.", "");
		try {
			cri = cri.add(Restrictions.eq(s, Long.valueOf(id)));
			E obj = (E) cri.uniqueResult();
			session.getTransaction().commit();
			return obj;
		} catch(Exception ex) {
			System.err.println(s + " not found. Please enter the correct id");
			throw new Exception("Data not found");
		}*/
		try {
		e = (E) session.get(e.getClass(),id);
		session.getTransaction().commit();
		} catch(Exception ex) {
			throw new Exception("Data not found");
		}
		
		return e;
		
	}

	public <E> E getData(E e) throws Exception {
		try {
			Session session = beginTransaction();
			List<E> list = session.createCriteria(e.getClass()).list();
			return (E) list.get(list.indexOf((E)e));
		} catch(Exception ex) {
			//ex.printStackTrace();
			return null;
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
				list.forEach(emp -> System.out.printf("%d\t%s, %s %s %f\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename(),emp.getGwa()));
				break;
			case "ORDER BY datehired":
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
		List<Role> list = session.createSQLQuery("select * from roles").addEntity(Role.class).list();
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