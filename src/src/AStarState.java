public class AStarState extends State implements Comparable<AStarState> {
	int g, h;

	AStarState() {
		super();
		h = findH();
	}

	public AStarState(GameLogic instance) {
		super(instance);
		h = findH();
	}

	public AStarState(int[] a) {
		super(a);
		h = findH();
	}

	private int findH() {
		int res = 0, top = this.getDim() * this.getDim();
		for (int i = 0; i < top; i++) {
			int d = this.getDigit(i) - 1;
			if (d == -1)
				d = top - 1;
			int x1 = i / this.getDim(), y1 = i % this.getDim(), x2 = d
					/ this.getDim(), y2 = d % this.getDim();
			res += Math.abs(x2 - x1);
			res += Math.abs(y2 - y1);
		}
		return res;
	}

	private int f() {
		return h + g;
	}

	@Override
	public int compareTo(AStarState t) {
		int l = this.f(), r = t.f();
		if (l > r)
			return 1;
		if (l < r)
			return -1;
		return 0;
	}

	public static AStarState moveUp(AStarState s) {
		AStarState res = (AStarState) State.moveUp(s);
		if (res != null) {
			res.g = s.g + 1;
			res.h = res.findH();
		}
		return res;
	}

	public static AStarState moveDown(AStarState s) {
		AStarState res = (AStarState) State.moveDown(s);
		if (res != null) {
			res.g = s.g + 1;
			res.h = res.findH();
		}
		return res;
	}

	public static AStarState moveLeft(AStarState s) {
		AStarState res = (AStarState) State.moveLeft(s);
		if (res != null) {
			res.g = s.g + 1;
			res.h = res.findH();
		}
		return res;
	}

	public static AStarState moveRight(AStarState s) {
		AStarState res = (AStarState) State.moveRight(s);
		if (res != null) {
			res.g = s.g + 1;
			res.h = res.findH();
		}
		return res;
	}
}
