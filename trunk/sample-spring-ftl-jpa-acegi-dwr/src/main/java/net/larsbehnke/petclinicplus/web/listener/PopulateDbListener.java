package net.larsbehnke.petclinicplus.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.larsbehnke.petclinicplus.jpa.populator.DbPopulator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class PopulateDbListener implements ServletContextListener{

	private static Log log = LogFactory.getLog(PopulateDbListener.class);
	
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("--- Servlet context destroyed ----------------------------------------");
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		log.info("Populating database...");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		DbPopulator populator = (DbPopulator)ctx.getBean("dbPopulator");
		populator.populate();
		log.info("--- Servlet context initialized -------------------------------------");
	}
	

}
