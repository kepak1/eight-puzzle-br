public abstract class Solver {
	public abstract String solve();

	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static State moveUp(State s) {
		if (s.emptyIdx >= s.dim * s.dim - s.dim)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx + s.dim);
		res.emptyIdx += s.dim;
		res.move = 'u';
		return res;
	}

	public static State moveDown(State s) {
		if (s.emptyIdx < s.dim)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx - s.dim);
		res.emptyIdx -= s.dim;
		res.move = 'd';
		return res;
	}

	public static State moveLeft(State s) {
		if (s.emptyIdx % s.dim == s.dim - 1)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx + 1);
		res.emptyIdx += 1;
		res.move = 'l';
		return res;
	}

	public static State moveRight(State s) {
		if (s.emptyIdx % s.dim == 0)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx - 1);
		res.emptyIdx -= 1;
		res.move = 'r';
		return res;
	}
}
