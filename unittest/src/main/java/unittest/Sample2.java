package unittest;

public class Sample2 {
	public int div(int a, int b) {
		if(b == 2) return shift(a);
		return a + b;
	}

	protected int shift(int a) {
		return a >> 1; // >>>
	}
}
