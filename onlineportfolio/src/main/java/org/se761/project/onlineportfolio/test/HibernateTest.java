package org.se761.project.onlineportfolio.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.service.AccountService;
import org.se761.project.onlineportfolio.service.QualificationService;

public class HibernateTest {

	public static void main(String[] args){
		
//		Configuration c = new Configuration().configure();
//		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
//				applySettings(c.getProperties());
//
//		SessionFactory sessionFactory = c.buildSessionFactory(builder.build());
//		
//		Session session = sessionFactory.openSession();
		
		
		AccountService accountService = new AccountService();
		QualificationService qualService = new QualificationService();
		
		Qualification qual = new Qualification();
		qual.setClientName("Deloitte 2 ");
		qual.setProblemStatement("This is a problem 2 ");
		qual.setProjectName("This is a project 2");
		qual.setRelevanceToClient("This is relevant 2");
		
		
		List<Qualification> quals = accountService.getAllQualifications(1);
		
		for(Qualification q : quals){
			System.out.println(q.getClientName());
		}
	
		
//		accountService.addQualification(1, qual);
		
//		Account account = new Account();
//		account.setAdmin(false);
//		account.setPin("4444");
//		account.setUserName("dgop");
		
//		session.beginTransaction();
//		
//		Account a = (Account) session.get(Account.class, 3);
//		
//		a.getQuals().add(qual);
//		
//		qual.getAccounts().add(a);
//		
//		session.save(qual);
//		session.save(a);
		
		
		
		
		
		
		
//		session.save(account);
		
//		session.getTransaction().commit();
//		
//		session.close();
//		
//		sessionFactory.close();
//		
		
		
		
		
		
		
		//		AdminGroup adminGroup = new AdminGroup();

//		

		
		
//		accountService.addQualification(1, qual);
		
//		List<Qualification> quals = accountService.getAllQualifications(1);
		
	
		
		
//		
//
//		Account account = new Account();
//		account.setUserName("awad");
//		account.setPin("1111");
//		account.setAdmin(true);
//		accountService.addAccountDetails(account);
		
//		
//		adminGroup.setAdminGroupName("This is an admin group");
		
		
		
		
		
		
		
//		System.out.println(a.getUserName());
		

//		
//		accountService.addQualification(4, qual);
		
		
		
		
	}
	
	
}
