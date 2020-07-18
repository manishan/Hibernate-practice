package com.tutorial.jdbc;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
							.addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			int studentId=1;
			
			Student studentToBeUpdated = session.get(Student.class,studentId );
			studentToBeUpdated.setFirstName("Mrs Abdul");
			session.getTransaction().commit();
			
			//new code
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='abc@gmail.com' where id=5")
			.executeUpdate();
			
			session.getTransaction().commit();
			
		}catch(HibernateException e) { 
			
		}finally {
			factory.close();
		}
	}

}
