package hibernate.demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;


public class CreateStudentDemo {
	public static void main(String args[]) {
		//create session factory
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class).buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			Student obj1 = new Student("Abdul", "Kalam", "ak@xyz.com");
			session.beginTransaction();
			
			session.save(obj1);
			System.out.println("Saving student");
			
			session.getTransaction().commit();
			System.out.println("done!");
			
			
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			factory.close();
		}
		
	}
}
