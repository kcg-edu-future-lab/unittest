package unittest.web;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class RootPage {
	@Test
	public void testLoginFormExists() throws Throwable{
		Document doc = Jsoup.parse(new URL("http://localhost:8080/unittest/"), 1000);
		Elements elements = doc.select("form input");
		assertTrue(elements.size() > 0, "フォームはinputダグを含んでいる。");
		assertEquals("username", elements.get(0).attr("name"), "最初のinputタグのnameはusername");
		assertEquals("password", elements.get(1).attr("name"), "2番目のinputタグのnameはpassword");
	}
}
