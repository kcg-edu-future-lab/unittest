package unittest.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AdderTest {
	@Test
	public void test_add() {
		Adder a = new Adder();
		assertEquals(8, a.add(3,  5));
	}
	
	@Test
	public void test_add_zero() {
		Adder a = new Adder();
		assertEquals(0, a.add(0, 0));
	}
}
