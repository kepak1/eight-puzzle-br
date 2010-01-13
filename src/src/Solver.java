public abstract class Solver {
	public abstract String solve();

	protected GameLogic gl = GameLogic.getInstance();
	protected State target;
	protected long timeCost;

	public String collectSolution() {
		State cur = target, pa;
		StringBuffer res = new StringBuffer();
		while (true) {
			pa = cur.getPre();
			if (pa == null)
				break;
			res.append(cur.getMove());
			cur = pa;
		}
		target = null;
		return res.reverse().toString();
	}

}
