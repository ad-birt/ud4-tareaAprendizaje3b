package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Address;
import entidades.Student;

public class Ud4TareaAprendizaje3bEjercicio1 {

	/**
	 * 1. Crea un nuevo objeto Student con su direcci�n
	 * y tel�fonos y gu�rdalo en la base de datos
	 */
	public static void main(String[] args) {

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Student.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		
		try {			
			// crea un objeto Student
			System.out.println("Creando un nuevo objeto Student con su direcci�n...");
			Student student = createStudent();
			
			// comienza la transacci�n
			session.beginTransaction();
			
			// guarda el objeto Student
			System.out.println("Guardando el estudiante...");
			session.persist(student);
			
			// hace commit de la transaccion
			session.getTransaction().commit();
			
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci�n
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	private static Student createStudent() {
		Student tempStudent = new Student();
		Address tempAddress = new Address();
		tempStudent.setFirstName("Mikel");
		tempStudent.setLastName("Unamuno");
		tempStudent.setEmail("junamuno@birt.eus");
		tempStudent.getPhones().add("712123456");
		tempStudent.getPhones().add("712123457");
		tempAddress.setAddressLine1("Kale Nagusia 10");
		tempAddress.setAddressLine2("3B");
		tempAddress.setCity("Donostia");
		tempAddress.setZipCode("20003");
		tempStudent.setAddress(tempAddress);
		return tempStudent;		
	}
}





