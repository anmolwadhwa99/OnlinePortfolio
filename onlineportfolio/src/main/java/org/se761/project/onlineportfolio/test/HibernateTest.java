package org.se761.project.onlineportfolio.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Qualification;

public class HibernateTest {

	public static void main(String[] args){
		
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		Session session = factory.openSession();

		
		session.beginTransaction();
		
		Account account = new Account();
		
		account.setUserName("awad");
		account.setPin("6666");
		
		
		session.getTransaction().commit();
		session.close();
		
		factory.close();
		
		
		
	}
	
	
}
