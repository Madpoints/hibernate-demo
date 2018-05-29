package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			int studentId = 1;
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id (primary key)
			System.out.println("Getting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// update student
			System.out.println("Updating student...");
			
			myStudent.setFirstName("Shaggy");
			
			// commit transaction
			session.getTransaction().commit();
			
			// new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("Updating all students email...");
			
			session.createQuery("update Student set email='student@email.com'")
				   .executeUpdate();

			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
		
	}

}
