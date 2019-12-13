package unittest.web.jetty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import unittest.servlet.IndexServlet;
import unittest.servlet.Initializer;
import unittest.servlet.LoginServlet;

public class JettyTest {
	protected Document parse(String path) throws MalformedURLException, IOException {
		return Jsoup.parse(new URL("http://localhost:8080/unittest" + path), 5000);
	}

	protected Document parse(String path, String name, String value) throws MalformedURLException, IOException {
		return Jsoup.connect("http://localhost:8080/unittest" + path).timeout(5000).data(name, value).post();
	}

	protected Document parse(String path, Map<String, String> parameters) throws MalformedURLException, IOException {
		return Jsoup.connect("http://localhost:8080/unittest" + path).timeout(5000).data(parameters).post();
	}

	private static Server server;

	@BeforeAll
	public static void setUp() throws Throwable{
		server = new Server(8080);
		WebAppContext wac = new WebAppContext();
		wac.setContextPath("/unittest");
		wac.setResourceBase("src/main/webapp");
		wac.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		wac.setConfigurations(new Configuration[]{
				new AnnotationConfiguration(),
				new WebXmlConfiguration()
		});
		wac.addEventListener(new Initializer());
		wac.addServlet(IndexServlet.class, "/");
		wac.addServlet(LoginServlet.class, "/login");
/* 以下，自動でサーブレットやListenerをスキャンさせたいが動作しない
		URL classes = IndexServlet.class.getProtectionDomain().getCodeSource().getLocation();
		wac.getMetaData().setWebInfClassesDirs(Arrays.asList(Resource.newResource("target/classes"), Resource.newResource(classes)));
		wac.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", 
				".*target/classes/.*");
		wac.setParentLoaderPriority(false);
		WebAppClassLoader wcl = new WebAppClassLoader(wac);
		wcl.addClassPath("target/classes");
		wcl.addClassPath("src/test/webapp/WEB-INF/classes");
		wac.setClassLoader(wcl);
*/
		server.setHandler(wac);
		server.start();
	}

	@AfterAll
	public static void tearDown() throws Throwable{
		server.stop();
	}
}