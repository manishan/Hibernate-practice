package hibernate.demo;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create session factory
		
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class).buildSessionFactory();
				//create session
				Session session = factory.getCurrentSession();
				
				try {
					Student obj1 = new Student("Madhura", "kale", "mkale@xyz.com");
					Student obj2 = new Student("manasi", "mahadik", "manmah@xyz.com");
					Student obj3 = new Student("poonam", "waghchoure", "pony@xyz.com");
					Student obj4 = new Student("madhuri", "thambe", "duriT@xyz.com");
					session.beginTransaction();
					
					session.save(obj1);
					session.save(obj2);
					session.save(obj3);
					session.save(obj4);
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
