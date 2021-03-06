package unittest.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import unittest.jpa.EntityManagerFactory;

/**
 * Application Lifecycle Listener implementation class Initializer
 *
 */
@WebListener
public class Initializer implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce)  { 
		EntityManagerFactory.initialize("webapp");
/*
		ws = new WebServer();
		ws.init();
		ws.start();
		h2consoleThread = new Thread(() -> {
			ws.listen();
		});
		h2consoleThread.setDaemon(true);
		h2consoleThread.start();
	}
	private WebServer ws;
	private Thread h2consoleThread;

	public void contextDestroyed(ServletContextEvent sce)  {
		if(ws != null) {
			ws.stop();
			h2consoleThread.interrupt();
			try {
				h2consoleThread.join(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			h2consoleThread = null;
			ws = null;
		}
//*/
	}
}
