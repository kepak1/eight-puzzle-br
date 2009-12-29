

public class State {
	int[] digit;
	int dim, emptyIdx;
	State target;
	char move; // from parent state to this state
	State pre;
	
	State(){}
	State(GameLogic gl) {
		dim = gl.getDim();
		emptyIdx = gl.getEmptyIdx();
		digit = new int[dim * dim];
		for (int i = 0; i < dim * dim; i++) {
			digit[i] = gl.getDigit(i);
		}
	}
	State(String s){
		dim = (int)(Math.sqrt(s.length())+1e-6);
		digit = new int[dim * dim];
		for (int i = 0; i < dim * dim; i++) {
			digit[i] = s.charAt(i)-'0';
			if(digit[i]==0)emptyIdx=i;
		}
	}

	public void copy(State s){
		dim = s.dim;
		emptyIdx = s.emptyIdx;
		digit = new int[s.digit.length];
		for (int i = 0; i < s.digit.length; i++) {
			digit[i] = s.digit[i];
		}
	}
	@Override
	public int hashCode() {
		int res = 0;
		for (int i = 0; i < dim * dim; i++) {
			res *= 31;
			res += digit[i];
		}
		return res;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof State))
			return false;
		State t = (State) o;
		if (dim != t.dim)
			return false;
		for (int i = 0; i < t.digit.length; i++) {
			if (digit[i] != t.digit[i])
				return false;
		}
		return true;
	}

	public boolean isTarget() {
		for (int i = 0; i < dim * dim - 1; i++) {
			if (digit[i] != i + 1)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < dim * dim; i++) {
			if (digit[i] == 0) {
				res.append(" ");
			} else {
				res.append(Integer.toString(digit[i]));
			}
			res.append(i % dim == dim - 1 ? '\n' : '\t');
		}
		return res.toString();
	}
}
