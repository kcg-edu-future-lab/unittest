package unittest.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoupTest {
	protected Document parse(String path) throws MalformedURLException, IOException {
		return Jsoup.parse(new URL(urlbase + path), 5000);
	}

	protected Document parse(String path, String name, String value) throws MalformedURLException, IOException {
		return Jsoup.connect(urlbase + path).timeout(5000).data(name, value).post();
	}

	protected Document parse(String path, Map<String, String> parameters) throws MalformedURLException, IOException {
		return Jsoup.connect(urlbase + path).timeout(5000).data(parameters).post();
	}

	private static String urlbase = "http://localhost:8080/unittest_webapp";
}
