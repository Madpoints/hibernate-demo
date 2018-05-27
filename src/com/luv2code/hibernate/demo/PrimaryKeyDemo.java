package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to save Java object
		try {
			
			// create 3 student objects
			System.out.println("Creating new student objects...");
			Student tempStudent1 = new Student("Sam", "Martin", "cool@email.com");
			Student tempStudent2 = new Student("Jen", "Vale", "run@email.com");
			Student tempStudent3 = new Student("Lin", "Wall", "code@email.com");

			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving student ojects...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done");	
			
		} finally {
			factory.close();
		}
		

	}

}
