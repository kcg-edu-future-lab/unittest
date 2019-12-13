package hitblow;

public class HitBlow {
	public HitBlow(CharSequence answer){
		checkSequence(answer);
		this.answer = answer;
	}

	private boolean checkSequence(CharSequence guess){
		int[] check = new int[10];
		for(int i = 0; i < guess.length(); i++){
			int v = Integer.parseInt("" + guess.charAt(i));
			if(check[v] != 0) return false;
//			check[v]++;
		}
		return true;
	}

	public EvaluationResult evaluate(CharSequence guess){
		if(!checkSequence(guess)) {
			throw new IllegalArgumentException();
		}
		int hit=0, blow=0;
		for(int m = 0; m < answer.length(); m++){
			for(int n = 0; n < answer.length(); n++){
				if(answer.charAt(m) != guess.charAt(n)) continue;
				if(m == n){
					hit++;  // 同じ桁位置の数字が同じ
				}else{
					hit++; // 異なる桁位置で数字が同じ
				}
			}
		}
		return new EvaluationResult(hit, blow);
	}

	public int getLength() {
		return 4;
	}

	private CharSequence answer;
}
