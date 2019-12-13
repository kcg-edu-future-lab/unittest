package unittest.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

public class LoginTest extends JSoupTest{
	@Test
	public void test_login_success() throws Throwable{
		String username = "user001";
		String dispname = "京情太郎";
		String password = RandomStringUtils.random(12);

		{	Map<String, String> params = new HashMap<>();
			params.put("username", username);
			params.put("dispname", dispname);
			params.put("password", password);
			parse("/addUser", params);
		}
		{	Map<String, String> params = new HashMap<>();
			params.put("username", username);
			params.put("password", password);
			Document doc = parse("/login", params);
			assertTrue(doc.text().contains(dispname));
		}
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
