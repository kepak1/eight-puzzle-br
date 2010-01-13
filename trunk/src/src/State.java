
public class State {
	private int[] digit;
	private int dim, emptyIdx;
	// State target;
	private char move; // from parent state to this state
	private State pre;

	public int getDigit(int i) {
		return digit[i];
	}

	public int getDim() {
		return dim;
	}

	public int getEmptyIdx() {
		return emptyIdx;
	}

	public char getMove() {
		return move;
	}

	public State getPre() {
		return pre;
	}

	private void swapDigit(int i, int j) {
		int t = digit[i];
		digit[i] = digit[j];
		digit[j] = t;
	}

	private void setMove(char c) {
		move = c;
	}

	private void setPre(State s) {
		pre = s;
	}

	private void setEmptyIdx(int x) {
		emptyIdx = x;
	}

	State() {
		this(GameLogic.getInstance());
	}

	State(GameLogic gl) {
		dim = gl.getDim();
		emptyIdx = gl.getEmptyIdx();
		digit = new int[dim * dim];
		for (int i = 0; i < dim * dim; i++) {
			digit[i] = gl.getDigit(i);
		}
	}

	State(int[] a) {
		dim = (int) (Math.sqrt(a.length) + 1e-6);
		int top = dim * dim;
		digit = new int[top];
		for (int i = 0; i < top; i++) {
			digit[i] = a[i];
			if (digit[i] == 0)
				emptyIdx = i;
		}
	}

	static public State getTartet() {
		State res = new State();
		int top = res.dim * res.dim - 1;
		for (int i = 0; i < top; i++)
			res.digit[i] = i + 1;
		res.digit[top] = 0;
		res.emptyIdx = top;
		return res;
	}

	protected void copy(State s) {
		dim = s.dim;
		emptyIdx = s.emptyIdx;
		digit = new int[s.digit.length];
		int len = s.digit.length;
		for (int i = 0; i < len; i++) {
			digit[i] = s.digit[i];
		}
	}

	@Override
	public int hashCode() {
		int res = 0, top = dim * dim - 1;
		for (int i = 0; i < top; i++) {
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
		int len = t.digit.length;
		for (int i = 0; i < len; i++) {
			if (digit[i] != t.digit[i])
				return false;
		}
		return true;
	}

	public boolean isTarget() {
		int top = dim * dim - 1;
		for (int i = 0; i < top; i++) {
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

	public static State moveUp(State s) {
		if (s.getEmptyIdx() >= s.getDim() * s.getDim() - s.getDim())
			return null;
		State res = null;
		try {
			res = s.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		res.copy(s);
		res.swapDigit(res.getEmptyIdx(), res.getEmptyIdx() + res.getDim());
		res.setEmptyIdx(res.getEmptyIdx() + s.getDim());
		res.setMove('u');
		res.setPre(s);
		return res;
	}

	public static State moveDown(State s) {
		if (s.getEmptyIdx() < s.getDim())
			return null;
		State res = null;
		try {
			res = s.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.copy(s);
		res.swapDigit(res.getEmptyIdx(), res.getEmptyIdx() - s.getDim());
		res.setEmptyIdx(res.getEmptyIdx() - s.getDim());
		res.setMove('d');
		res.setPre(s);
		return res;
	}

	public static State moveLeft(State s) {
		if (s.getEmptyIdx() % s.getDim() == s.getDim() - 1)
			return null;
		State res = null;
		try {
			res = s.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.copy(s);
		res.swapDigit(res.getEmptyIdx(), res.getEmptyIdx() + 1);
		res.setEmptyIdx(res.getEmptyIdx() + 1);
		res.setMove('l');
		res.setPre(s);
		return res;
	}

	public static State moveRight(State s) {
		if (s.getEmptyIdx() % s.getDim() == 0)
			return null;
		State res = null;
		try {
			res = s.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.copy(s);
		res.swapDigit(res.getEmptyIdx(), res.getEmptyIdx() - 1);
		res.setEmptyIdx(res.getEmptyIdx() - 1);
		res.setMove('r');
		res.setPre(s);
		return res;
	}
}
