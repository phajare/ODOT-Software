/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.okstate.executables;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
/**
 * A simple Java program that exports data from database to Excel file.
 * @author Nam Ha Minh
 * (C) Copyright codejava.net
 */
public class AdvancedDb2ExcelExporter {
 
    public static String goToExport(int description, String project_name) throws FileNotFoundException, IOException, InvalidFormatException {
        return new AdvancedDb2ExcelExporter().export(description, project_name);
    }
     
    public String export(int description, String project_name) throws FileNotFoundException, IOException, InvalidFormatException {
    	
    	//FileReader reader = new FileReader("config.properties");
    		
    	//Properties properties = new Properties();
    	//properties.load(reader);
    	
    	ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		DatabaseDetails db = context.getBean("myDatabseDetails", DatabaseDetails.class);
    	OPCPackage pack = null;
    	String jdbcURL = "jdbc:mysql://"+db.getUrl()+":3306/stepbystep?allowPublicKeyRetrieval=TRUE";
        String username = db.getUsername();
        String password = db.getPassword();
        
        //Date date = java.util.Calendar.getInstance().getTime();
        String date = java.time.LocalTime.now().toString().substring(0, 8).replace(":", "");
        //String now = date.substring(0, 7).replace(":", "");
        String user = System.getProperty("user.name");
        String docs = "C:\\Users\\"+user+"\\Documents\\";
        String excelFilePath = docs + project_name +"_"+ date +"-export.xlsx";
 
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT Project_ID, Template_Activity_Name, Activity_Duration, Activity_Task_Predecessor_Logic FROM project_data_full where Project_ID="
        +description+" AND Template_Activity_Child = 0";
        	
        	//String sql = "SELECT Project_ID, Template_Activity_Name, Activity_Duration, Activity_Task_Predecessor_Logic FROM project_data_full where Project_ID="
        	  //      +description;
            ///ODOT-Software/src/main/java/edu/okstate/executables/program.vbs
 
            Statement statement = connection.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");

            sheet.disableLocking();
            writeHeaderLine(sheet);
 
            writeDataLines(result, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            try {
				workbook.write(outputStream);
			} catch (Exception e) {
				
				// TODO Auto-generated catch block
				pack = OPCPackage.open(excelFilePath);
			}
            outputStream.close();
            sheet.disableLocking();
            workbook.close();
            statement.close();
            
            if(pack != null)
            	pack.close();
            //File file = new File(excelFilePath);
            //file.setReadable(true);
            //file.setWritable(true);
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        
        return excelFilePath;
    }
 
    private void writeHeaderLine(XSSFSheet sheet) {
 
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Task Mode");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Name");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Duration");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Predecessors");
 
    }
 
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            String taskMode = "Auto Schedule";
            String taskName = result.getString("Template_Activity_Name");
            String duration = Double.toString(result.getDouble("Activity_Duration"));
            String predecessors = result.getString("Activity_Task_PredecesSor_Logic");
 
            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(taskMode);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(taskName);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(duration);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(predecessors);
        }
    }
 
}