package com.tutorial.jdbc;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class).buildSessionFactory();
	Session session = factory.getCurrentSession();
	
	try {
		session.beginTransaction();
		List<Student> theStudents = session.createQuery("from Student").getResultList();
		
		displayStudent(theStudents);
		
		session.getTransaction().commit();
		System.out.println("Done");
	}catch(HibernateException e) {
		
	}finally {
		factory.close();
	}
	}

	private static void displayStudent(List<Student> theStudents) {
		for(Student std:theStudents) {
			System.out.println(std);
		}
	}

}
