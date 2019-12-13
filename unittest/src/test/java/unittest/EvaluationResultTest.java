package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hitblow.EvaluationResult;

public class EvaluationResultTest {
	@Test
	public void test_ctor() throws Throwable{
		EvaluationResult er = new EvaluationResult(2, 0);
		assertEquals(2, er.getHit());
		assertEquals(0, er.getBlow());
	}
}
