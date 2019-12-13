package unittest.web.jetty;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import unittest.servlet.AddUserServlet;
import unittest.servlet.CharacterEncodingFilter;
import unittest.servlet.IndexServlet;
import unittest.servlet.Initializer;
import unittest.servlet.LoginServlet;
import unittest.web.JSoupTest;

public class JettyTest extends JSoupTest{
	private static Server server;

	@BeforeAll
	public static void setUp() throws Throwable{
		server = new Server(8080);
		WebAppContext wac = new WebAppContext();
		wac.setContextPath("/unittest_webapp");
		wac.setResourceBase("src/main/webapp");
		wac.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		wac.setConfigurations(new Configuration[]{
				new AnnotationConfiguration(),
				new WebXmlConfiguration()
		});
		wac.addEventListener(new Initializer());
		wac.addFilter(CharacterEncodingFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		wac.addServlet(IndexServlet.class, "/");
		wac.addServlet(LoginServlet.class, "/login");
		wac.addServlet(AddUserServlet.class, "/addUser");
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
