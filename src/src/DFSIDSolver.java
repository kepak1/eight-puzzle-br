import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;



public class DFSIDSolver extends Solver{
	private GameLogic gl = GameLogic.getInstance();
	private static State target;
	private static HashMap<State, State> M = new HashMap<State, State>();
	

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
	public String solve() {
		M.clear();
		target=null;
		State st = new State(GameLogic.getInstance());
		M.put(st, null);
		iterativeDeepening(st,30);
		return collectSolution();
	}
}
