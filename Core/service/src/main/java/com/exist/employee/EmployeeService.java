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
		session.beginTransaction();
		return session;
	}

	public <E> void deleteElement (E e) {
		Session session = beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
	}

	public <E> E getData(long id, E e) {
		Session session = beginTransaction();
		Criteria cri = session.createCriteria(e.getClass());
		String s = e.getClass().getName().toLowerCase() + "Id".trim();
		s = s.replace("com.exist.employee.", "");
		cri = cri.add(Restrictions.eq(s, Long.valueOf(id)));
		E obj = (E) cri.list().get(0);
		session.getTransaction().commit();
		return obj;
	}

	public boolean isRoleDeletable(Role role) {
		Session session = beginTransaction();	
	 	List<Role> list = session.createSQLQuery("SELECT * FROM roles INNER JOIN employee_role On employee_role.roleid = roles.roleid AND employee_role.roleid =" + role.getRoleId()).addEntity(Role.class).list();
		session.getTransaction().commit();
		return list.size() == 0;
	}

	public void listEmployees(String order){
		Session session = beginTransaction();
		List<Employee> list = session.createSQLQuery("select * from employees" + order).addEntity(Employee.class).list();
		list.forEach(emp -> System.out.printf("%d\t%s, %s %s\n",emp.getEmployeeId(),emp.getLastname(),emp.getFirstname(),emp.getMiddlename()));
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