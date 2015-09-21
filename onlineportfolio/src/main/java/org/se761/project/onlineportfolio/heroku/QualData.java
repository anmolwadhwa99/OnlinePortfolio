package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.model.Status;

public class QualData extends Server{
	 
	
	public void addQual(Qualification qual){
		JSONObject jsonQual = new JSONObject();
		try {
			jsonQual.put("projectName", qual.getProjectName());
			jsonQual.put("clientName", qual.getClientName());
			jsonQual.put("problemStatement", qual.getProblemStatement());
			jsonQual.put("challengesFaced", qual.getChallengesFaced());
			jsonQual.put("solutionStatement", qual.getSolutionStatement());
			jsonQual.put("relavenceToClient", qual.getRelevanceToClient());
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
		Server.HTTPPostMethod(inputURL, jsonQual);
		
	}

	public static void main(String[] args) {
		
		//EA Sports 
		QualData qualData = new QualData();
		Qualification eaSports = new Qualification();
		eaSports.setProjectName("Capability and Strategy Assessment and Sales Analytics");
		eaSports.setClientName("EA Sports");
		eaSports.setProblemStatement("EA Sports, a gaming software developer and distributor, sought to align the entire organization on CRM vision, goals and establishment of high-priority CRM capabilities to build consistency across multiple brands and groups.");
		eaSports.setSolutionStatement("We performed a capability assessment of current state and identified gaps between existing processes and other leading industry practices in 50+ best-in-class CRM organizations. The assessment identified CRM capability gaps in insights and analytics, customer experience, marketing effectiveness, sales effectiveness, and service effectiveness. Using this assessment, we developed a multi-year roadmap that outlined initiatives to achieve the end vision.");
		eaSports.setRelevanceToClient("We used our assessment to develop a comprehensive 3-year CRM roadmap and incorporate it into the annual operating plan. The roadmap created internal alignment on CRM definition, vision, and priorities and extended vision to the technical environment required to enable and support the priorities and gained CIO support and sponsorship.");
		eaSports.setServiceLine("Technology");
		eaSports.setStatus(Status.open);
		qualData.addQual(eaSports);
		
		//Apple
		Qualification apple = new Qualification();
		apple.setClientName("Apple");
		apple.setProjectName("Proven Experience - Apple Digital and Mobile Strategy");
		apple.setSubtitle("Ubermind, now part of Deloitte Digital, assisted in developing digital channel and mobile strategies for Apple and brought added insight into an organization known for digital innovation.");
		apple.setProblemStatement("Apple engaged us to work on some of its most important initiatives for web, mobile, and the enterprise, due to our technical expertise with complex IT environments and cutting-edge technologies.");
		apple.setSolutionStatement("Have developed their very first Apple Online Store more than ten years ago and since then, we’ve contributed to a number of key Apple websites and services, including iTunes, Print Service, the Apple Online Store, and the Apple Online iPhone app. Our work boils down to a long-standing partnership with one of the world’s most recognizable brands.");
		apple.setRelevanceToClient("Our work for Apple exemplifies our ability to tackle large, scalable, enterprise, and service oriented architecture projects - skills that directly apply to building backend services for supporting mobile apps.");
		apple.setServiceLine("Technology");
		apple.setStatus(Status.confidential);
		qualData.addQual(apple);
		
		//AVG
		Qualification avg = new Qualification();
		avg.setClientName("AVG");
		avg.setProjectName("Scaling service across the globe");
		avg.setStatus(Status.confidential);
		avg.setProblemStatement("Together with SAP, we leveraged hybris to scale content capabilities to serve 300M global customers performing digital downloads for one of the world’s largest producers of anti-virus software, dynamically update a global website across 50+ locales and 30+ languages with targeted, personalized content and promotions, and implement an advanced e-commerce and Product Management system to allow for targeted selling, pricing, and product bundling.");
		avg.setSolutionStatement("Deloitte provided strategic guidance, program management. Adobe CQ WCM development, hybris e-commerce development, middleware/integration development, and testing services to AVG to help scale their digital platform to serve over 300M global customers.");
		avg.setRelevanceToClient("The result: centralized content management and promotion management with rapid optimization, multivariate testing, and localization, advanced user segmentation, targeting, and personalization capabilities, enterprise class product management platform for managing complicated product bundles, vouchers, and discounting on a region by region basis, scalability and flexibility for assimilation of new products and business lines and integrated customer service for shopping cart management, promotions, and cross/up-selling over the phone or through the web.");
		avg.setServiceLine("Consulting");
		qualData.addQual(avg);
		
		//Large Oil and Gas
		Qualification oilAndGas = new Qualification();
		oilAndGas.setClientName("Large Oil & Gas Company");
		oilAndGas.setProjectName("An Advanced Customer Experience that drivers digital shift.");
		oilAndGas.setStatus(Status.confidential);
		oilAndGas.setProblemStatement("Our client is a leading provider of finished lubricants, asphalts, and speciality products, as well as one of the world’s largest suppliers of base stocks. Their manufacturing and distribution network reaches customers in over 170 countries. The goal was to transform its Fuels & Lubricants (F&L) division by unifying customer interactions and making it easy for branded wholesalers, distributors, direct customers, and other channel partners to place orders online.");
		oilAndGas.setSolutionStatement("Deloitte assisted our client in delivering a digital platform that supported online business and new customer acquisition, starting in Thailand, Singapore and Malaysia. To accomplish these goals, the team built a responsive B2B site on hybris technology and Real-time integrations with backend applications like SAP and Siebel CRM using SAP Process Orchestration (PO) as the ESB layer.");
		oilAndGas.setRelevanceToClient("Since the launch of the new solution, our client has established a consistent digital experience across brands, products, geographies and communication channels for both internal and external F&L customers, Streamlined order management experience enhanced by a rigorous back office rules-engine and product catalog logic and Robust account management functionality to empower customers to self-serve.");
		oilAndGas.setServiceLine("Consulting");
		qualData.addQual(oilAndGas);
		
		
		
		
		

	}

}
