package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			
			// create student object
			System.out.println("Creating new student object...");
			
			Student tempStudent = new Student("Alice", "Day", "sun@email.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving student oject...");
			System.out.println(tempStudent);
			
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// find student's primary key
			System.out.println("Studet Id: " + tempStudent.getId());
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id (primary key)
			System.out.println("Getting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Got: " + myStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
		
	}

}
