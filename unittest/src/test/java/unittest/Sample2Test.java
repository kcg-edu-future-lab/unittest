package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class Sample2Test {
	@Test
	void test_div() {
		Sample2 sample = new Sample2();
		assertEquals(-1, sample.div(-2, 2));
	}

	@Test
	void test_shift() throws Throwable{
		Sample2 sample = new Sample2();
		//assertEquals(-1, sample2.shift(-2));
		Method m = Sample2.class.getDeclaredMethod("shift", int.class);
		m.setAccessible(true);
		Integer r = (Integer)m.invoke(sample, -2);
		assertEquals(-1, r.intValue());
		
	}
}
