package hitblow;

public class EvaluationResult {
	public EvaluationResult() {
	}
	public EvaluationResult(int hit, int blow) {
		this.hit = hit;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
	}
	public int getBlow() {
		return blow;
	}
	public void setBlow(int blow) {
		this.blow = blow;
	}
	private int hit;
	private int blow;
}
