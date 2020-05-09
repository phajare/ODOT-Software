package edu.okstate.executables;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.okstate.entities.*;

import org.hibernate.query.Query;

public class DeleteFullRecord {

	static int headerID;

	public static List<ProjectDataFull> deleteFullRecord(List<ProjectDataFull> list) {
		int projectId;
		// create session factory
		Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			projectId = list.get(0).getCompound().getProHeaderDataId().getId();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("delete ProjectDataFull p where p.compound.proHeaderDataId.id = :projectId");
			query.setParameter("projectId",projectId);
			int result = query.executeUpdate();
			Log.printLog().log(Level.INFO, "DELETE QUERY EXECUTION RESULT "+result);
			session.getTransaction().commit();
			Log.printLog().log(Level.INFO, "DELETED SUCCESSFULLY!!!");

		} finally {
			session.close();
			factory.close();
		}
		list  = SaveFullRecord.saveFullRecord(list);
		return list;
	}

	public static int getHeaderID() {
		return headerID;
	}

	public static void setHeaderID(int headerID) {
		DeleteFullRecord.headerID = headerID;
	}
}
