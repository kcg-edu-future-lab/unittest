package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SampleTest {
	@Test
	void test() {
		Sample sample = new Sample();
		assertEquals(8, sample.add(5, 3));
	}
}
