public class AStarState extends State implements Comparable<AStarState> {
	private int g, h;

	AStarState() {
		super();
		g = 0;
		h = findH();
	}

	public AStarState(GameLogic instance) {
		super(instance);
		g = 0;
		h = findH();
	}

	public AStarState(int[] a) {
		super(a);
		g = 0;
		h = findH();
	}

	public int findH() {
		int res = 0, top = this.getDim() * this.getDim();
		for (int i = 0; i < top; i++) {
			if(i==getEmptyIdx())continue;
			int d = this.getDigit(i) - 1;
			int x1 = i / this.getDim(), y1 = i % this.getDim(), x2 = d
					/ this.getDim(), y2 = d % this.getDim();
			res += Math.abs(x2 - x1);
			res += Math.abs(y2 - y1);
		}
		return res;
	}

	public void setH(int x) {
		h = x;
	}

	public int h() {
		return h;
	}

	public int g() {
		return g;
	}

	public int f() {
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
