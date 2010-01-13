import java.util.HashSet;
import java.util.Set;



public class DFSIDSolver extends Solver{
	/*
	 * This is not really DFSID, because it stores a table to check duplicates, 
	 * 	which requires extra memory
	 * */
	protected Set<State> S = new HashSet<State>();
	
	private static DFSIDSolver instance=new DFSIDSolver();
	private DFSIDSolver(){}
	public static DFSIDSolver getInstance(){return instance;}
	public static final int MAX_DEPTH=30;
	
	void iterativeDeepening(State s,int bound){
		S.clear();
		S.add(s);
		for(int i=1;i<=bound;i++){
			dfsid(s,i);
			if(target!=null)return;
		}
	}
	void dfsid(State s,int dep){
		if(dep<0)return;
		if(s.isTarget()){
			target=s;
		}
		if(target!=null)return;
		State ns;
		ns=State.moveUp(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=State.moveDown(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=State.moveLeft(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=State.moveRight(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
	}
	
	public String solve() {
		long st=System.currentTimeMillis();
		target=null;
		State s = new State(GameLogic.getInstance());
		iterativeDeepening(s,MAX_DEPTH);
		timeCost=System.currentTimeMillis()-st;
		return collectSolution();
	}
	public String solve(int[] a){
		long startTime=System.currentTimeMillis();
		target=null;
		State s = new State(a);
		iterativeDeepening(s,MAX_DEPTH);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		int[] a={4,7,0,5,6,3,8,1,2,};
		String res = instance.solve(a);
		System.out.println(res);
	}
	
}
