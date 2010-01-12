import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BidirectionalSearch extends Solver {
	private static BidirectionalSearch instance = new BidirectionalSearch();

	private BidirectionalSearch() {}

	public static BidirectionalSearch getInstance() {
		return instance;
	}

	private State midL, midR;

	@SuppressWarnings("unchecked")
	private void bfs(State s) {
		Map[] M = new HashMap[2];
		M[0] = new HashMap<State, State>();
		M[1] = new HashMap<State, State>();
		Queue[] q = new LinkedList[2];
		q[0] = new LinkedList<State>();
		q[1] = new LinkedList<State>();
		State target = State.getTartet();
		q[0].add(s);
		q[1].add(target);
		M[0].put(s, s);
		M[1].put(target, target);
		State ns;
		int idx = 0;
		while (!q[0].isEmpty() && !q[1].isEmpty()) {
			s = (State) q[idx].poll();
			if (M[1 - idx].containsKey(s)) {
				if (idx == 0) {
					midL = s;
					midR = (State) M[1 - idx].get(s);
				} else {
					midR = s;
					midL = (State) M[1 - idx].get(s);
				}
				break;
			}
			ns = moveUp(s);
			if (ns != null && !M[idx].containsKey(ns)) {
				M[idx].put(ns, ns);
				q[idx].add(ns);
			}
			ns = moveDown(s);
			if (ns != null && !M[idx].containsKey(ns)) {
				M[idx].put(ns, ns);
				q[idx].add(ns);
			}
			ns = moveLeft(s);
			if (ns != null && !M[idx].containsKey(ns)) {
				M[idx].put(ns, ns);
				q[idx].add(ns);
			}
			ns = moveRight(s);
			if (ns != null && !M[idx].containsKey(ns)) {
				M[idx].put(ns, ns);
				q[idx].add(ns);
			}
			idx = 1 - idx;
		}
	}

	@Override
	public String collectSolution() {
		State cur, pa;
		StringBuffer s1 = new StringBuffer(), s2 = new StringBuffer();
		cur = midL;
		while (true) {
			pa = cur.pre;
			if (pa == null)
				break;
			s1.append(cur.move);
			cur = pa;
		}
		cur = midR;
		while (true) {
			pa = cur.pre;
			if (pa == null)
				break;
			switch (cur.move) {
			case 'u':
				s2.append('d');
				break;
			case 'd':
				s2.append('u');
				break;
			case 'l':
				s2.append('r');
				break;
			case 'r':
				s2.append('l');
				break;
			}
			cur = pa;
		}
		return s1.reverse().append(s2).toString();
	}

	public String solve() {
		long startTime = System.currentTimeMillis();
		target = null;
		State s = new State(GameLogic.getInstance());
		bfs(s);
		timeCost = System.currentTimeMillis() - startTime;
		return collectSolution();
	}

	public String solve(int[] a) {
		long startTime = System.currentTimeMillis();
		target = null;
		State s = new State(a);
		bfs(s);
		timeCost = System.currentTimeMillis() - startTime;
		return collectSolution();
	}

	public static void main(String[] args) {
		int[] a={4,7,0,5,6,3,8,1,2,};
		String res = instance.solve(a);
		System.out.println(res);
	}
}
