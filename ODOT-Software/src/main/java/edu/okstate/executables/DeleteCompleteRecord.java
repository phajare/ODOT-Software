package edu.okstate.executables;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.okstate.entities.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.query.Query;

public class DeleteCompleteRecord {

    static int headerID;

    @SuppressWarnings("unchecked")
	public static void deleteCompleteRecord(String choice) {
        int projectId;
        List<ProjectHeaderData> headerDetails;
        // create session factory
        Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            headerDetails = session.createQuery("from ProjectHeaderData p where p.description = :choice")
            		.setParameter("choice", choice).list();

            projectId = headerDetails.get(0).getId();

            @SuppressWarnings("rawtypes")
            Query query1 = session.createQuery("delete ProjectHeaderData p where p.id = :projectId");
            query1.setParameter("projectId", projectId);
            int result1 = query1.executeUpdate();
            Log.printLog().log(Level.INFO, "DELETE QUERY EXECUTION RESULT " + result1);
            @SuppressWarnings("rawtypes")
			Query query = session.createQuery("delete ProjectDataFull p where p.compound.proHeaderDataId.id = :projectId");
            query.setParameter("projectId", projectId);
            int result = query.executeUpdate();
            Log.printLog().log(Level.INFO, "DELETE PROJECTFULLDATA QUERY EXECUTION RESULT " + result);
            session.getTransaction().commit();
            Log.printLog().log(Level.INFO, "DELETED SUCCESSFULLY!!!");

        } finally {
            session.close();
            factory.close();
        }
    }

    public static int getHeaderID() {
        return headerID;
    }

    public static void setHeaderID(int headerID) {
        DeleteCompleteRecord.headerID = headerID;
    }
}
