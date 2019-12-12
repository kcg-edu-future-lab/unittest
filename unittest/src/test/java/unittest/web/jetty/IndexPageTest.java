package unittest.web.jetty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class IndexPageTest extends JettyTest{
	@Test
	public void testLoginFormExists() throws Throwable{
		Document doc = parse("/");
		Elements elements = doc.select("form input");
		assertTrue(elements.size() > 0, "フォームはinputダグを含んでいる。");
		assertEquals("username", elements.get(0).attr("name"), "最初のinputタグのnameはusername");
		assertEquals("password", elements.get(1).attr("name"), "2番目のinputタグのnameはpassword");
	}
}
