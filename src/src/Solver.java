
public abstract class Solver {
	public abstract String solve();
	
	protected GameLogic gl = GameLogic.getInstance();
	protected State target;
	protected long timeCost;
	
	
	public String collectSolution(){
		State cur=target,pa;
		StringBuffer res=new StringBuffer();
		while(true){
			pa=cur.pre;
			if(pa==null)break;
			res.append(cur.move);
			cur=pa;
		}
		target=null;
		return res.reverse().toString();
	}
	private static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static State moveUp(State s) {
		if (s.emptyIdx >= s.dim * s.dim - s.dim)
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
		swap(res.digit, res.emptyIdx, res.emptyIdx + s.dim);
		res.emptyIdx += s.dim;
		res.move = 'u';
		res.pre=s;
		return res;
	}

	public static State moveDown(State s) {
		if (s.emptyIdx < s.dim)
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
		swap(res.digit, res.emptyIdx, res.emptyIdx - s.dim);
		res.emptyIdx -= s.dim;
		res.move = 'd';
		res.pre=s;
		return res;
	}

	public static State moveLeft(State s) {
		if (s.emptyIdx % s.dim == s.dim - 1)
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
		swap(res.digit, res.emptyIdx, res.emptyIdx + 1);
		res.emptyIdx += 1;
		res.move = 'l';
		res.pre=s;
		return res;
	}

	public static State moveRight(State s) {
		if (s.emptyIdx % s.dim == 0)
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
		swap(res.digit, res.emptyIdx, res.emptyIdx - 1);
		res.emptyIdx -= 1;
		res.move = 'r';
		res.pre=s;
		return res;
	}
	
	
}
