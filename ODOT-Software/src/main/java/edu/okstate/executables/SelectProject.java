package edu.okstate.executables;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.logging.Level;

import org.hibernate.query.Query;

import edu.okstate.entities.Log;


public class SelectProject {

	@SuppressWarnings("unchecked")
	public static ArrayList<String> returnHeaderDetails(String choice) {

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		ArrayList<String> headerDetails = new ArrayList<String>();

		// create session factory
		Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query q = session.createQuery("select p.description, p.county, p.projNum, p.jobNum, p.hgNum, p.Engineer, p.date, p.ref, p.sta from ProjectHeaderData p where p.description = :choice").setParameter("choice", choice);
			list = (ArrayList<Object[]>)q.list();
			session.getTransaction().commit();

			for(Object[] employee: list){

				for(int i = 0; i < 9; i++){
					headerDetails.add((String)employee[i]);
				}
			}
			new Log();
			Log.printLog().log(Level.INFO, "Selected Project Details:\n"+headerDetails);

		}finally {
			session.close();
			factory.close();
		}

		return headerDetails;
	}
}