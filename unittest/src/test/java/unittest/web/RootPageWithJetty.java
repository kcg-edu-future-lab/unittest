package unittest.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import unittest.servlet.IndexServlet;
import unittest.servlet.Initializer;

public class RootPageWithJetty {
	@Test
	public void testLoginFormExists() throws Throwable{
		Server s = new Server(8080);
		WebAppContext wac = new WebAppContext();
		File temp = new File("temp");
		temp.mkdirs();
		wac.setTempDirectory(temp);
		wac.setContextPath("/unittest");
		wac.setResourceBase("src/main/webapp");
		wac.setParentLoaderPriority(true);
		wac.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		Configuration[] configurations = {
				new AnnotationConfiguration(),
				new WebInfConfiguration(),
				new WebXmlConfiguration(),
				new MetaInfConfiguration(),
				new FragmentConfiguration(),
				new EnvConfiguration(),
				new PlusConfiguration(),
				new JettyWebXmlConfiguration()
		};
		wac.setConfigurations(configurations);
		wac.addEventListener(new Initializer());
		wac.addServlet(IndexServlet.class, "/");
		s.setHandler(wac);
		s.start();
		try {
			Document doc = Jsoup.parse(new URL("http://localhost:8080/unittest/"), 5000);
			Elements elements = doc.select("form input");
			assertTrue(elements.size() > 0, "フォームはinputダグを含んでいる。");
			assertEquals("username", elements.get(0).attr("name"), "最初のinputタグのnameはusername");
			assertEquals("password", elements.get(1).attr("name"), "2番目のinputタグのnameはpassword");
		} finally {
			s.stop();
		}
}
}
