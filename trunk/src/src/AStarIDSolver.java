

public class AStarIDSolver extends Solver {
	private static AStarIDSolver instance = new AStarIDSolver();

	private AStarIDSolver() {
	}

	public static AStarIDSolver getInstance() {
		return instance;
	}

	/*
	 * 	This is not really A*ID, because it stores a table to check duplicates, 
	 * 	which requires extra memory
	 * */
	//private Map<AStarState, Integer> close = new HashMap<AStarState, Integer>();

	private int f_bound;

	public void dfsID(AStarState s) {
		if (s.isTarget()) {
			target = s;
			return;
		}
		if(target!=null)return;
		AStarState ns;
	/*
		Integer preF = close.get(s);
		if (preF != null && preF <= s.f()) {
			return;
		} else {
			close.put(s, s.f());
		}
		*/
		ns = AStarState.moveUp(s);
		if (ns != null && s.getMove()!='d' && ns.f() <= f_bound) {
			dfsID(ns);
		}
		ns = AStarState.moveDown(s);
		if (ns != null && s.getMove()!='u' && ns.f() <= f_bound) {
			dfsID(ns);
		}
		ns = AStarState.moveLeft(s);
		if (ns != null && s.getMove()!='r' && ns.f() <= f_bound) {
			dfsID(ns);
		}
		ns = AStarState.moveRight(s);
		if (ns != null && s.getMove()!='l' && ns.f() <= f_bound) {
			dfsID(ns);
		}
	}

	public void AStarID(AStarState st) {
		f_bound = 0;
		target = null;
		while (target == null) {
			f_bound += 1;
	//		close.clear();
			dfsID(st);
		}
	}

	@Override
	public String solve() {
		long startTime = System.currentTimeMillis();

		AStarID(new AStarState(GameLogic.getInstance()));
		timeCost = System.currentTimeMillis() - startTime;
		return collectSolution();
	}

	public String solve(int[] a) {
		long startTime = System.currentTimeMillis();

		AStarID(new AStarState(a));
		timeCost = System.currentTimeMillis() - startTime;
		return collectSolution();
	}

	public static void main(String[] args) {
		int[] a = { 4, 7, 0, 5, 6, 3, 8, 1, 2 };
		String res = instance.solve(a);
		System.out.println(res);
		//System.out.println(instance.close.size());
	}
}
