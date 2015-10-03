package org.se761.project.onlineportfolio.heroku;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.model.ServiceLine.DeloitteServiceLine;


public class QualData extends Server{
	
	private static Map<String, Integer> ids = new HashMap<>();  
	
	private static void addQual(String name, Qualification qual){
		JSONObject jsonQual = new JSONObject();
		try {
			jsonQual.put("projectName", qual.getProjectName());
			jsonQual.put("clientName", qual.getClientName());
			jsonQual.put("problemStatement", qual.getProblemStatement());
			jsonQual.put("challengesFaced", qual.getChallengesFaced());
			jsonQual.put("solutionStatement", qual.getSolutionStatement());
			jsonQual.put("relevanceToClient", qual.getRelevanceToClient());
			jsonQual.put("outcomeStatement", qual.getOutcomeStatement());
			jsonQual.put("subtitle", qual.getSubtitle());
			jsonQual.put("isAnonymous", qual.isAnonymous());
			jsonQual.put("anonymousName", qual.getAnonymousName());
			jsonQual.put("isActive", qual.isActive());
			jsonQual.put("emailButton", qual.getEmailButton());
			jsonQual.put("websiteButton", qual.getWebsiteButton());
			jsonQual.put("industry", qual.getIndustry());
			jsonQual.put("tags", qual.getTags());
			jsonQual.put("status", qual.getStatus());
			jsonQual.put("serviceLine", qual.getServiceLine());
			jsonQual.put("clientImage", qual.getClientImage());
			jsonQual.put("projectImage", qual.getProjectImage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + QUALS_URL;
		String response = Server.HTTPPostMethod(inputURL, jsonQual);
		try {
			JSONObject json = new JSONObject(response);
			ids.put(name, json.getInt("qualId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> createQuals(){
		
		//EA Sports 
		Qualification eaSports = new Qualification();
		eaSports.setProjectName("Capability and Strategy Assessment and Sales Analytics");
		eaSports.setClientName("EA Sports");
		eaSports.setProblemStatement("EA Sports, a gaming software developer and distributor, sought to align the entire organization on CRM vision, goals and establishment of high-priority CRM capabilities to build consistency across multiple brands and groups.");
		eaSports.setSolutionStatement("We performed a capability assessment of current state and identified gaps between existing processes and other leading industry practices in 50+ best-in-class CRM organizations. The assessment identified CRM capability gaps in insights and analytics, customer experience, marketing effectiveness, sales effectiveness, and service effectiveness. Using this assessment, we developed a multi-year roadmap that outlined initiatives to achieve the end vision.");
		eaSports.setRelevanceToClient("We used our assessment to develop a comprehensive 3-year CRM roadmap and incorporate it into the annual operating plan. The roadmap created internal alignment on CRM definition, vision, and priorities and extended vision to the technical environment required to enable and support the priorities and gained CIO support and sponsorship.");
		eaSports.setServiceLine(DeloitteServiceLine.technology);
		eaSports.setStatus("open");
		eaSports.setEmailButton("dgop507@aucklanduni.ac.nz");
		eaSports.setWebsiteButton("http://www.google.co.nz");
		eaSports.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831885/EASports.png");
		eaSports.setProjectImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831885/EASports.png");
		eaSports.setWebsiteButton("https://www.easports.com/");
		eaSports.setEmailButton(dummyEmail);
		addQual("qual_EA", eaSports);
		
		//Apple
		Qualification apple = new Qualification();
		apple.setClientName("Apple");
		apple.setProjectName("Proven Experience - Apple Digital and Mobile Strategy");
		apple.setSubtitle("Ubermind, now part of Deloitte Digital, assisted in developing digital channel and mobile strategies for Apple and brought added insight into an organization known for digital innovation.");
		apple.setProblemStatement("Apple engaged us to work on some of its most important initiatives for web, mobile, and the enterprise, due to our technical expertise with complex IT environments and cutting-edge technologies.");
		apple.setSolutionStatement("Have developed their very first Apple Online Store more than ten years ago and since then, we’ve contributed to a number of key Apple websites and services, including iTunes, Print Service, the Apple Online Store, and the Apple Online iPhone app. Our work boils down to a long-standing partnership with one of the world’s most recognizable brands.");
		apple.setRelevanceToClient("Our work for Apple exemplifies our ability to tackle large, scalable, enterprise, and service oriented architecture projects - skills that directly apply to building backend services for supporting mobile apps.");
		apple.setServiceLine(DeloitteServiceLine.technology);
		apple.setEmailButton("dgop507@aucklanduni.ac.nz");
		apple.setWebsiteButton("http://www.google.co.nz");
		apple.setStatus("open");
		apple.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831885/Apple.jpg");
		apple.setProjectImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831885/Apple.jpg");
		apple.setWebsiteButton("http://www.apple.com/");
		apple.setEmailButton(dummyEmail);
		addQual("qual_Apple", apple);
		
		
		//AVG
		Qualification avg = new Qualification();
		avg.setClientName("AVG");
		avg.setProjectName("Scaling service across the globe");
		avg.setStatus("confidential");
		avg.setProblemStatement("Together with SAP, we leveraged hybris to scale content capabilities to serve 300M global customers performing digital downloads for one of the world’s largest producers of anti-virus software, dynamically update a global website across 50+ locales and 30+ languages with targeted, personalized content and promotions, and implement an advanced e-commerce and Product Management system to allow for targeted selling, pricing, and product bundling.");
		avg.setSolutionStatement("Deloitte provided strategic guidance, program management. Adobe CQ WCM development, hybris e-commerce development, middleware/integration development, and testing services to AVG to help scale their digital platform to serve over 300M global customers.");
		avg.setRelevanceToClient("The result: centralized content management and promotion management with rapid optimization, multivariate testing, and localization, advanced user segmentation, targeting, and personalization capabilities, enterprise class product management platform for managing complicated product bundles, vouchers, and discounting on a region by region basis, scalability and flexibility for assimilation of new products and business lines and integrated customer service for shopping cart management, promotions, and cross/up-selling over the phone or through the web.");
		avg.setServiceLine(DeloitteServiceLine.strategy);
		avg.setEmailButton("dgop507@aucklanduni.ac.nz");
		avg.setWebsiteButton("http://www.google.co.nz");
		avg.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831889/AVG.jpg");
		avg.setProjectImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831889/AVG.jpg");
		avg.setWebsiteButton("http://www.avg.com/au-en/homepage");
		avg.setEmailButton(dummyEmail);
		addQual("qual_avg", avg);
		
		//Large Oil and Gas
		Qualification oilAndGas = new Qualification();
		oilAndGas.setClientName("Large Oil & Gas Company");
		oilAndGas.setProjectName("An Advanced Customer Experience that drivers digital shift.");
		oilAndGas.setStatus("confidential");
		oilAndGas.setProblemStatement("Our client is a leading provider of finished lubricants, asphalts, and speciality products, as well as one of the world’s largest suppliers of base stocks. Their manufacturing and distribution network reaches customers in over 170 countries. The goal was to transform its Fuels & Lubricants (F&L) division by unifying customer interactions and making it easy for branded wholesalers, distributors, direct customers, and other channel partners to place orders online.");
		oilAndGas.setSolutionStatement("Deloitte assisted our client in delivering a digital platform that supported online business and new customer acquisition, starting in Thailand, Singapore and Malaysia. To accomplish these goals, the team built a responsive B2B site on hybris technology and Real-time integrations with backend applications like SAP and Siebel CRM using SAP Process Orchestration (PO) as the ESB layer.");
		oilAndGas.setRelevanceToClient("Since the launch of the new solution, our client has established a consistent digital experience across brands, products, geographies and communication channels for both internal and external F&L customers, Streamlined order management experience enhanced by a rigorous back office rules-engine and product catalog logic and Robust account management functionality to empower customers to self-serve.");
		oilAndGas.setServiceLine(DeloitteServiceLine.operations);
		oilAndGas.setEmailButton("dgop507@aucklanduni.ac.nz");
		oilAndGas.setWebsiteButton("http://www.google.co.nz");
		oilAndGas.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831891/Oil_Gas.jpg");
		oilAndGas.setProjectImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442831891/Oil_Gas.jpg");
		oilAndGas.setEmailButton(dummyEmail);
		oilAndGas.setStatus("open");
		addQual("qual_gas", oilAndGas);
		

		
		//Random Qual 1
//		Qualification qual1 = new Qualification();
//		qual1.setClientName("Donald Trump");
//		qual1.setProjectName("We will build a wall so big");
//		qual1.setSolutionStatement("Mexico will pay for the wall");
//		qual1.setRelevanceToClient("Well I have built an empire and am highly successful");
//		qual1.setStatus(Status.open);
//		qual1.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442792694/Trump.jpg");
//		qualData.addQual(qual1);
//		
//		//Random Qual 2
//		Qualification qual2 = new Qualification();
//		qual2.setClientName("Heisenberg");
//		qual2.setProjectName("SAY MY NAME");
//		qual2.setSolutionStatement("Youre god damn right");
//		qual2.setRelevanceToClient("We cook");
//		qual2.setStatus(Status.open);
//		qual2.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442792691/Heisenberg.jpg");
//		qualData.addQual(qual2);
//		
//		//Random Qual 3
//		Qualification qual3 = new Qualification();
//		qual3.setClientName("Homer Simpson");
//		qual3.setProjectName("Doh");
//		qual3.setSolutionStatement("Smartest guy in the world");
//		qual3.setRelevanceToClient("I am from springfield");
//		qual3.setStatus(Status.open);
//		qual3.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442792695/Homer.png");
//		qualData.addQual(qual3);
//		
//		//Random Qual 4
//		Qualification qual4 = new Qualification();
//		qual4.setClientName("Louis Litt");
//		qual4.setProjectName("Time to Litt Up");
//		qual4.setSolutionStatement("Best lawyer in NYC after Harvey Specter");
//		qual4.setRelevanceToClient("I always screw things up");
//		qual4.setStatus(Status.open);
//		qual4.setClientImage("http://res.cloudinary.com/onlineportfolio/image/upload/v1442792771/Louis.png");
//		qualData.addQual(qual4);
		
		

		return ids;
	}

}
