package edu.okstate.executables;

import java.util.ArrayList;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import edu.okstate.entities.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProjectSearch {

    @SuppressWarnings("unchecked")
    public static ArrayList<String> returnTempalteHeaders(String a, String b, String c, String d, String e, String f, String g, String h, String i) {

        ArrayList<String> list = new ArrayList<String>();

        // create session factory
        Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
        
        // create session
        Session session = factory.getCurrentSession();
        
        StringBuilder sba = new StringBuilder();
        String des;
        if(!a.trim().isEmpty()) {
        	sba.append("%" + a.trim() + "%");
        	des = sba.toString();
        }
        else
        	des = a.trim();
        
        StringBuilder sbb = new StringBuilder();
        String cou;
        if(!b.trim().isEmpty()) {
        	sbb.append("%" + b.trim() + "%");
        	cou = sbb.toString();
        }
        else
        	cou = b.trim();
        
        StringBuilder sbc = new StringBuilder();
        String prn;
        if(!c.trim().isEmpty()) {
        	sbc.append("%" + c.trim() + "%");
        	prn = sbc.toString();
        }
        else
        	prn = c.trim();
        
        StringBuilder sbd = new StringBuilder();
        String jpn;
        if(!d.trim().isEmpty()) {
        	sbd.append("%" + d.trim() + "%");
        	jpn = sbd.toString();
        }
        else
        	jpn = d.trim();
        
        StringBuilder sbe = new StringBuilder();
        String hin;
        if(!e.trim().isEmpty()) {
        	sbe.append("%" + e.trim() + "%");
        	hin = sbe.toString();
        }
        else
        	hin = e.trim();
        
        StringBuilder sbf = new StringBuilder();
        String pec;
        if(!f.trim().isEmpty()) {
        	sbf.append("%" + f.trim() + "%");
        	pec = sbf.toString();
        }
        else
        	pec = f.trim();
        
        StringBuilder sbg = new StringBuilder();
        String led;
        if(!g.trim().isEmpty()) {
        	sbg.append("%" + g.trim() + "%");
        	led = sbg.toString();
        }
        else
        	led = g.trim();
        
        StringBuilder sbh = new StringBuilder();
        String rem;
        if(!h.trim().isEmpty()) {
        	sbh.append("%" + h.trim() + "%");
        	rem = sbh.toString();
        }
        else
        	rem = h.trim();
        
        StringBuilder sbi = new StringBuilder();
        String sta;
        if(!i.trim().isEmpty()) {
        	sbi.append("%" + i.trim() + "%");
        	sta = sbi.toString();
        }
        else
        	sta = i.trim();
        
        try {
            session.beginTransaction();
            if(g.trim().isEmpty()){
            	list = (ArrayList<String>) session.createQuery("select p.description from ProjectHeaderData p where p.description like :des or p.county like :cou or p.projNum like :prn or p.jobNum like :jpn or p.hgNum like :hin or p.Engineer like :pec or p.ref like :rem or p.sta like :sta")
                        .setParameter("des", des)
                        .setParameter("cou", cou)
                        .setParameter("prn", prn)
                        .setParameter("jpn", jpn)
                        .setParameter("hin", hin)
                        .setParameter("pec", pec)
                        //.setParameter("g", g.trim())
                        .setParameter("rem", rem)
                        .setParameter("sta", sta)
                        .list();
            }else {
            	list = (ArrayList<String>) session.createQuery("select p.description from ProjectHeaderData p where p.description like :des or p.county like :cou or p.projNum like :prn or p.jobNum like :jpn or p.hgNum like :hin or p.Engineer like :pec or p.date like :led or p.ref like :rem or p.sta like :sta")
                        .setParameter("des", des)
                        .setParameter("cou", cou)
                        .setParameter("prn", prn)
                        .setParameter("jpn", jpn)
                        .setParameter("hin", hin)
                        .setParameter("pec", pec)
                        .setParameter("led", led)
                        .setParameter("rem", rem)
                        .setParameter("sta", sta)
                        .list();
            }

            session.getTransaction().commit();

            Log.printLog().log(Level.SEVERE, "Search Project SUCCESSFULL!!!");

        } catch (Exception exc) {
            javax.swing.JFrame frame;
            frame = new JFrame("Warning");
            JOptionPane.showMessageDialog(frame, "Please give VALID Entries", "NO SEARCH RESULTS FOUND", JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
            factory.close();
        }
        return list;
    }

}
