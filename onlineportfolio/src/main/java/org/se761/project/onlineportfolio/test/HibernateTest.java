package org.se761.project.onlineportfolio.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.Qualification;

public class HibernateTest {

	public static void main(String[] args){
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();

		
		
		Qualification qual = new Qualification();
		qual.setProblemStatement("This is a problem");
		qual.setClientName("Deloitte");
		
		session.save(qual);
		
		session.close();
		
		factory.close();
		
		
		
	}
	
	
}
