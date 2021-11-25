package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemo {

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
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students : lastName='Vamps'
			theStudents = session.createQuery("from Student s where s.lastName='Vamps'").getResultList();

			//display the students
			System.out.println("Students who have lastname of 'Vamps'");
			displayStudents(theStudents);

			//commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
