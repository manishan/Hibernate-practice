package com.tutorial.jdbc;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Iterator;

public class ManageEmployee {

	private static SessionFactory factory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			factory = new Configuration().configure().buildSessionFactory();
					
		}catch(Throwable ex) {
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
		}
		
		ManageEmployee me = new ManageEmployee();
		
		HashSet set1 = new HashSet();
		set1.add(new Certificate("MBA"));
		set1.add(new Certificate("CA"));
		
		Integer emp1 = me.addEmployee("Manisha","Naik",8888,set1);
		
		HashSet set2 =  new HashSet();
		set2.add(new Certificate("BTech"));
		set2.add(new Certificate("MCA"));
		Integer emp2 = me.addEmployee("Sanil","Shinde",12334,set2);
		
		
	me.listEmployees();
	//me.updateEmployee(3,2334);
		//me.deleteEmployee(3);
	}

	public int addEmployee(String firstName,String lastName,int salary,Set certificates) {
		Session session = factory.openSession();
		
		Transaction tx = null;
		Integer employeeId=0;
		
		try {
			tx= session.beginTransaction();
			Employee emp= new Employee(firstName,lastName,salary);
			emp.setCertificates(certificates);
			employeeId = (Integer)session.save(emp);
			tx.commit();
	      } catch (HibernateException e) {
	          if (tx!=null) tx.rollback();
	          e.printStackTrace(); 
	       } finally {
	          session.close(); 
	       }
	       return employeeId;
		
	}
	
	//method to read all the employees
	public void listEmployees() {
		Session session= factory.openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			List employees =  session.createQuery("FROM Employee").list();
			for(Iterator itertor= employees.iterator(); itertor.hasNext();) {
				Employee employee = (Employee)itertor.next();
				 System.out.print("First Name: " + employee.getFirstName()); 
		         System.out.print("  Last Name: " + employee.getLastName()); 
		         System.out.println("  Salary: " + employee.getSalary()); 
			}
			
		}catch(HibernateException he) {
			System.err.println("error in reading data");
			if(tx!=null) {
				tx.rollback();
			}
			he.printStackTrace();
		}	finally {
			session.close();
}
		}
	
	public void updateEmployee(Integer EmployeeId,int salary) {
		Session session = factory.openSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, EmployeeId);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
			
		}catch(HibernateException he) {
			System.err.println("error while updating records");
			he.printStackTrace();
			if(tx!=null)tx.rollback();
		}finally {
			session.close();
		}
	}
	
	public void deleteEmployee(Integer EmployeeId) {
		Session session = factory.openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, EmployeeId);
			session.delete(employee);
			tx.commit();
		}catch(HibernateException e) {
			System.err.print("error while deleting record");
			if(tx !=null) {
				tx.rollback();
			}e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
