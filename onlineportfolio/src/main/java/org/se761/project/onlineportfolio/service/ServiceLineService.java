package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.ServiceLineDatabase;
import org.se761.project.onlineportfolio.model.ServiceLine;

public class ServiceLineService {

	private ServiceLineDatabase serviceDatabase = new ServiceLineDatabase();
	
	/**
	 * Get all service lines from the database
	 */
	public List<ServiceLine> getAllServices(){
		return serviceDatabase.getAllServices();
	}
	
	/**
	 * Add a service line to the database
	 */
	public ServiceLine addService(ServiceLine service){
		return serviceDatabase.addService(service);
	}
	
}
