package unittest.web.jetty;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import unittest.jpa.EntityManagerFactory;
import unittest.jpa.User;

public class LoginTest extends JettyTest{
	@Test
	public void test_login_success() throws Throwable{
		EntityManager em = EntityManagerFactory.create();
		em.getTransaction().begin();
		String username = "user001";
		String dispname = "京情太郎";
		String password = RandomStringUtils.random(12);
		User u = new User(username, dispname, new BCryptPasswordEncoder().encode(password));
		em.persist(u);
		em.getTransaction().commit();
		Map<String, String> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		Document doc = parse("/login", params);
		assertTrue(doc.text().contains(dispname));
	}

	@Test
	public void test_login_fail() throws Throwable{
		Map<String, String> params = new HashMap<>();
		params.put("username", "aaa");
		params.put("password", "bbb");
		try{
			parse("/login", params);
		} catch(HttpStatusException e) {
			if(e.getStatusCode() == 403) {
				return;
			}
		}
		fail();
	}
}
