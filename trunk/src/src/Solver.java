import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solver {
	private GameLogic gl = GameLogic.getInstance();
	private static State target;
	private static HashMap<State, State> M = new HashMap<State, State>();
	
	static class State {
		int[] digit;
		int dim, emptyIdx;
		State target;
		char move;	//from parent state to this state
		State(GameLogic gl) {
			dim = gl.getDim();
			emptyIdx = gl.getEmptyIdx();
			digit = new int[dim * dim];
			for (int i = 0; i < dim * dim; i++) {
				digit[i] = gl.getDigit(i);
			}
		}

		State(State s) {
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
			for(int i=0;i<dim*dim-1;i++){
				if(digit[i]!=i+1)
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
		res.move='u';
		return res;
	}

	public static State moveDown(State s) {
		if (s.emptyIdx < s.dim)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx - s.dim);
		res.emptyIdx -= s.dim;
		res.move='d';
		return res;
	}

	public static State moveLeft(State s) {
		if (s.emptyIdx % s.dim == s.dim - 1)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx + 1);
		res.emptyIdx += 1;
		res.move='l';
		return res;
	}

	public static State moveRight(State s) {
		if (s.emptyIdx % s.dim == 0)
			return null;
		State res = new State(s);
		swap(res.digit, res.emptyIdx, res.emptyIdx - 1);
		res.emptyIdx -= 1;
		res.move='r';
		return res;
	}

	private static void bfs(State s) {
		Queue<State> q=new LinkedList<State>();
		q.add(s);
		q.isEmpty();
		State ns;
		while(!q.isEmpty()){
			s=q.poll();
			if(s.isTarget()){
				System.out.println(s);
				target=s;
				break;
			}
			ns=moveUp(s);
			if(ns!=null && !M.containsKey(ns)){
				M.put(ns,s);
				q.add(ns);
			}
			ns=moveDown(s);
			if(ns!=null && !M.containsKey(ns)){
				M.put(ns,s);
				q.add(ns);
			}
			ns=moveLeft(s);
			if(ns!=null && !M.containsKey(ns)){
				M.put(ns,s);
				q.add(ns);
			}
			ns=moveRight(s);
			if(ns!=null && !M.containsKey(ns)){
				M.put(ns,s);
				q.add(ns);
			}
		}
	}
	static void iterativeDeepening(State s,int bound){
		for(int i=1;i<=bound;i++){
			M.clear();
			M.put(s, null);
			dfsid(s,i);
			if(target!=null)return;
		}
	}
	static void dfsid(State s,int dep){
		State ns;
		if(dep<0)return;
		if(s.isTarget()){
			System.out.println(s);
			target=s;
		}
		if(target!=null)return;
		ns=moveUp(s);
		if(ns!=null && !M.containsKey(ns)){
			M.put(ns,s);
			dfsid(ns,dep-1);
		}
		ns=moveDown(s);
		if(ns!=null && !M.containsKey(ns)){
			M.put(ns,s);
			dfsid(ns,dep-1);
		}
		ns=moveLeft(s);
		if(ns!=null && !M.containsKey(ns)){
			M.put(ns,s);
			dfsid(ns,dep-1);
		}
		ns=moveRight(s);
		if(ns!=null && !M.containsKey(ns)){
			M.put(ns,s);
			dfsid(ns,dep-1);
		}
	}
	private static String collectSolution(){
		State cur=target,pa;
		StringBuffer res=new StringBuffer();
		while(true){
			pa=M.get(cur);
			if(pa==null)break;
			res.append(cur.move);
			cur=pa;
		}
		res.reverse();
		return res.toString();
	}
	public static String solve() {
		M.clear();
		target=null;
		State st = new State(GameLogic.getInstance());
		M.put(st, null);
		iterativeDeepening(st,30);
		//bfs(st);
		return collectSolution();
	}
}
