package edu.okstate.executables;

import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.okstate.entities.CompoundKeyProData;
import edu.okstate.entities.ProjectDataFull;
import edu.okstate.entities.ProjectHeaderData;
import edu.okstate.entities.TemplateData;
import edu.okstate.entities.TemplateHeaderData;

public class ContextConfig {
	
	public static Configuration getConfig() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		DatabaseDetails db = context.getBean("myDatabseDetails", DatabaseDetails.class);
		Configuration config = new Configuration();
		
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://"+db.getUrl()+":3306/stepbystep?allowPublicKeyRetrieval=TRUE");
        config.setProperty("hibernate.connection.username", db.getUsername());
        config.setProperty("hibernate.connection.password", db.getPassword());
        config.setProperty("hibernate.connection.pool_size", "1");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.current_session_context_class", "thread");
        
        config.addAnnotatedClass(ProjectHeaderData.class);
        config.addAnnotatedClass(CompoundKeyProData.class);
        config.addAnnotatedClass(ProjectDataFull.class);
        config.addAnnotatedClass(TemplateHeaderData.class);
        config.addAnnotatedClass(TemplateData.class);
        config.addAnnotatedClass(TemplateData.class);
        return config;
	}

}
