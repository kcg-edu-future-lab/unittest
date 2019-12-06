package unittest.servlet;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.server.web.WebServer;

import unittest.jpa.EntityManagerFactory;
import unittest.jpa.User;

/**
 * Application Lifecycle Listener implementation class Initializer
 *
 */
@WebListener
public class Initializer implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce)  { 
		EntityManagerFactory.initialize("unittest");
		ws = new WebServer();
		ws.init();
		ws.start();
		h2consoleThread = new Thread(() -> {
			ws.listen();
		});
		h2consoleThread.setDaemon(true);
		h2consoleThread.start();
		EntityManager em = EntityManagerFactory.create();
		em.getTransaction().begin();
		em.persist(new User("user1", "pass", "user1"));
		em.getTransaction().commit();
		em.close();
	}

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
	}

	private WebServer ws;
	private Thread h2consoleThread;
}
