package edu.okstate.executables;

import java.util.ArrayList;
import java.util.Collections;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import edu.okstate.entities.*;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CopyFullRecord {

    static int headerID;

    @SuppressWarnings("unchecked")
    public static ArrayList<String> copyFullRecord(String choice) {

        List<ProjectHeaderData> headerDetails;
        List<ProjectDataFull> projectDataFulls;
        ArrayList<String> toReturn = new ArrayList<String>();

        // create session factory
        Configuration config = ContextConfig.getConfig();
        SessionFactory factory = config.buildSessionFactory();
        
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            headerDetails = session.createQuery("from ProjectHeaderData p where p.description = :choice")
            		.setParameter("choice", choice).list();

            int templateId = headerDetails.get(0).getTemplateId().getId();
            int projectId = headerDetails.get(0).getId();
            
            projectDataFulls = session.createQuery("from ProjectDataFull p where p.compound.proHeaderDataId.id = :projectId")
            		.setParameter("projectId", projectId).list();

            TemplateHeaderData tempheader = new TemplateHeaderData();
            tempheader.setId(templateId);

            CompoundKeyTempData compoundTemp = new CompoundKeyTempData();
            compoundTemp.setRowId(1);
            compoundTemp.setTempHeaderDataId(tempheader);

            ProjectHeaderData proheader = new ProjectHeaderData();
            proheader.setTemplateId(tempheader);

            CompoundKeyProData compoundData = new CompoundKeyProData();
            compoundData.setCompound(compoundTemp);
            compoundData.setProHeaderDataId(proheader);

            //session.beginTransaction();
            new Log();
        	Log.printLog().log(Level.INFO, "Data getting saved...\n");
            session.save(proheader);

            ArrayList<Integer> rows = new ArrayList<Integer>();
            ArrayList<Double> avgProd = new ArrayList<Double>();
            ArrayList<Double> minProd = new ArrayList<Double>();
            ArrayList<Double> maxProd = new ArrayList<Double>();
            ArrayList<String> actPred = new ArrayList<String>();

            ArrayList<String> actName = new ArrayList<String>();
            ArrayList<String> actUnit = new ArrayList<String>();
            ArrayList<Integer> actLevel = new ArrayList<Integer>();
            ArrayList<Integer> actChild = new ArrayList<Integer>();
            ArrayList<Integer> actParent = new ArrayList<Integer>();

            rows = (ArrayList<Integer>) session.createQuery("select compound.rowId from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            avgProd = (ArrayList<Double>) session.createQuery("select avgProdRate from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            minProd = (ArrayList<Double>) session.createQuery("select minProdRate from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            maxProd = (ArrayList<Double>) session.createQuery("select maxProdRate from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actPred = (ArrayList<String>) session.createQuery("select predecessorLogic from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actName = (ArrayList<String>) session.createQuery("select actName from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actUnit = (ArrayList<String>) session.createQuery("select unit from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actLevel = (ArrayList<Integer>) session.createQuery("select level from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actChild = (ArrayList<Integer>) session.createQuery("select child from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();
            actParent = (ArrayList<Integer>) session.createQuery("select parent from TemplateData t where t.compound.tempHeaderDataId.id = :templateId").setParameter("templateId", templateId).list();

            int max = Collections.max(rows);
            new Log();
        	Log.printLog().log(Level.INFO, "The maximum no. of rows in the column of the table are: " + max);

            for (int i = 1; i <= max; i++) {
                CompoundKeyTempData compoundTemp1 = new CompoundKeyTempData();
                compoundTemp1.setRowId(i);
                compoundTemp1.setTempHeaderDataId(tempheader);
                CompoundKeyProData compoundData1 = new CompoundKeyProData();
                compoundData1.setCompound(compoundTemp1);
                compoundData1.setProHeaderDataId(proheader);
                ProjectDataFull proData1 = new ProjectDataFull();
                proData1.setCompound(compoundData1);
                if (avgProd.get(i - 1) == null) {
                    proData1.setAvgProdRate(0);
                } else {
                    proData1.setAvgProdRate(avgProd.get(i - 1));
                }
                proData1.setComments(projectDataFulls.get(i-1).getComments());
                proData1.setDuration(projectDataFulls.get(i-1).getDuration());
                proData1.setDurationOverride(projectDataFulls.get(i-1).getDurationOverride());
                if (maxProd.get(i - 1) == null) {
                    proData1.setMaxProdRate(0);
                } else {
                    proData1.setMaxProdRate(maxProd.get(i - 1));
                }
                if (minProd.get(i - 1) == null) {
                    proData1.setMinProdRate(0);
                } else {
                    proData1.setMinProdRate(minProd.get(i - 1));
                }
                proData1.setQuantity(projectDataFulls.get(i-1).getQuantity());
                if (actPred.get(i - 1) == null) {
                    proData1.setTaskPredecessor("");
                } else {
                    proData1.setTaskPredecessor(actPred.get(i - 1));
                }
                proData1.setTechDetails(projectDataFulls.get(i-1).getTechDetails());
                if (actName.get(i - 1) == null) {
                    proData1.setActivityName("");
                } else {
                    proData1.setActivityName(actName.get(i - 1));
                }
                if (actUnit.get(i - 1) == null) {
                    proData1.setActivityUnit("--");
                } else {
                    proData1.setActivityUnit(actUnit.get(i - 1));
                }
                if (actLevel.get(i - 1) == null) {
                    proData1.setActivityLevel(0);
                } else {
                    proData1.setActivityLevel(actLevel.get(i - 1));
                }
                if (actChild.get(i - 1) == null) {
                    proData1.setActivityChild(0);
                } else {
                    proData1.setActivityChild(actChild.get(i - 1));
                }
                if (actParent.get(i - 1) == null) {
                    proData1.setActivityParent(0);
                } else {
                    proData1.setActivityParent(actParent.get(i - 1));
                }
            	Log.printLog().log(Level.INFO, "Data getting saved...\n" + proData1);
                session.save(proData1);
            }
        	Log.printLog().log(Level.INFO, "Project Header ID: " + proheader.getId());

            headerID = proheader.getId();

            String description = "Project_Description_" + headerID;
            toReturn.add(description);
            String county = "County_" + headerID;
            toReturn.add(county);
            String project = "Project_" + headerID;
            toReturn.add(project);
            String job = "Job Piece_" + headerID;
            toReturn.add(job);
            String highway = "Highway_" + headerID;
            toReturn.add(highway);
            String contact = "Contact_" + headerID;
            toReturn.add(contact);
            String date = "2007-02-01";
            toReturn.add(date);
            String ref = "Refernce Marks_" + headerID;
            toReturn.add(ref);
            String sta = "STA_" + headerID;
            toReturn.add(sta);

            proheader.setDescription(description);
            proheader.setCounty(county);
            proheader.setProjNum(project);
            proheader.setJobNum(job);
            proheader.setHgNum(highway);
            proheader.setEngineer(contact);
            proheader.setDate(date);
            proheader.setRef(ref);
            proheader.setSta(sta);
            
            Log.printLog().log(Level.INFO, "Updated data is getting saved in project header data...");
            session.save(proheader);

            session.getTransaction().commit();

            Log.printLog().log(Level.INFO, "Updated data saved in project header data!!!");

        } finally {
            session.close();
            factory.close();
        }
        javax.swing.JFrame frame;
        frame = new JFrame("Information");
        JOptionPane.showMessageDialog(frame, "LIST ALL PROJECTS to check the newly created project\nNote: Project with the highest value is the latest one", "COPY Successful!", JOptionPane.INFORMATION_MESSAGE);
        return toReturn;
    }

    public static int getHeaderID() {
        return headerID;
    }

    public static void setHeaderID(int headerID) {
        CopyFullRecord.headerID = headerID;
    }
}