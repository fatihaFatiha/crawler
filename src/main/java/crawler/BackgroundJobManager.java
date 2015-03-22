package crawler;

import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 
 * 
 * @author BERNIZ Khadija
 * @author BJIJE Fatiha
 * @author ZOUBIR Fatima Zohra
 * 
 * 
 * 
 */
import javax.servlet.*;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) {

		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new WebCrawler(), 0, 1, TimeUnit.DAYS);

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		scheduler.shutdownNow();
	}

}
