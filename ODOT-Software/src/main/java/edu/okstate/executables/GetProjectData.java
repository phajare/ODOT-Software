package edu.okstate.executables;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.okstate.entities.*;
import java.util.List;
import java.util.logging.Level;


public class GetProjectData {

	@SuppressWarnings("unchecked")
	public static List<ProjectDataFull> getProjectData(String choice) {
		
		List<ProjectHeaderData> headerDetails;
		List<ProjectDataFull> projectDetails;

		// create session factory
		Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			headerDetails = session.createQuery("from ProjectHeaderData p where p.description = :choice").setParameter("choice", choice).list();
			int projectID = headerDetails.get(0).getId();
			Log.printLog().log(Level.INFO, "Project ID: " + projectID);
			int templateID = headerDetails.get(0).getTemplateId().getId();
			Log.printLog().log(Level.INFO, "Template ID: " + templateID);

			projectDetails = session.createQuery("from ProjectDataFull p where p.compound.proHeaderDataId.id = :projectID").setParameter("projectID", projectID).list();

			if(projectDetails.isEmpty())
				Log.printLog().log(Level.SEVERE, "ProjectDetails List Arrived from ProjectDataAll is EMPTY!!!");

			session.getTransaction().commit();
			Log.printLog().log(Level.SEVERE, "GetProjectData SUCCESSFULL!!!");
		}finally {
			session.close();
			factory.close();
		}
		return projectDetails;
	}
}