package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();	
		
		//create session
		Session session = factory.getCurrentSession();
		
		//use the session object to save Java Object
		try {
			//create a student object
			System.out.println("Creating new student object...");
			Student theStudent = new Student("Jeremy", "Vamps", "jeremy.vamps@xyz.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student object..");
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
