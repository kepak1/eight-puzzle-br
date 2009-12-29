import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;



public class DFSIDSolver extends Solver{
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
		ns=moveUp(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=moveDown(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=moveLeft(s);
		if(ns!=null && !S.contains(ns)){
			S.add(ns);
			dfsid(ns,dep-1);
			if(target!=null)return;
			S.remove(ns);
		}
		ns=moveRight(s);
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
	public String solve(String str){
		long startTime=System.currentTimeMillis();
		target=null;
		State s = new State(str);
		iterativeDeepening(s,MAX_DEPTH);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		String res=instance.solve("470563812");
		System.out.println(res);
	}
	
}
