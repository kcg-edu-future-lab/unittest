package unittest.sample;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import hitblow.EvaluationResult;
import hitblow.HitBlow;

public class HitBlowTest {
	@Test
	public void test_guess_1() {
		HitBlow hb = new HitBlow("1234");
		EvaluationResult er = hb.evaluate("1234");
		assertEquals(4, er.getHit());
		assertEquals(0, er.getBlow());
	}

	@Test
	public void test_guess_2() {
		HitBlow hb = new HitBlow("1234");
		EvaluationResult er = hb.evaluate("1378");
		assertEquals(1, er.getHit());
		assertEquals(1, er.getBlow());
	}

	@Test
	public void test_checkSequence() throws Throwable{
		Method m = HitBlow.class.getDeclaredMethod("checkSequence", CharSequence.class);
		m.setAccessible(true);
		HitBlow hb = new HitBlow("1234");
		assertFalse((Boolean)m.invoke(hb, "1123"));
	}
}
