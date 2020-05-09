package edu.okstate.executables;

import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.okstate.entities.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SaveFullRecord {

    static int headerID;

    public static List<ProjectDataFull> saveFullRecord(List<ProjectDataFull> list) {
        // create session factory
    	Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {

        	Log.printLog().log(Level.INFO, "Details arrived in SAVE FULL RECORD!!!");

            session.beginTransaction();
            for (ProjectDataFull proData : list)
                session.save(proData);

            session.getTransaction().commit();

            Log.printLog().log(Level.INFO, "Save full record SUCCESSFULL!!!");

        }finally {
            session.close();
            factory.close();
        }
        javax.swing.JFrame frame;
        frame = new JFrame("Information");
        JOptionPane.showMessageDialog(frame, "The record has been saved successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
        return list;
    }

    public static int getHeaderID() {
        return headerID;
    }

    public static void setHeaderID(int headerID) {
        SaveFullRecord.headerID = headerID;
    }
}
