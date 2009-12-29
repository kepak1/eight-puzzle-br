import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSolver extends Solver {
	private static AStarSolver instance=new AStarSolver();
	private AStarSolver(){}
	public static AStarSolver getInstance(){return instance;}
	
	AStarState moveUp(AStarState s) {
		AStarState res = (AStarState) super.moveUp(s);
		if (res != null)
			res.g = s.g + 1;
		return res;
	}

	AStarState moveDown(AStarState s) {
		AStarState res = (AStarState) super.moveDown(s);
		if (res != null)
			res.g = s.g + 1;
		return res;
	}

	AStarState moveLeft(AStarState s) {
		AStarState res = (AStarState) super.moveLeft(s);
		if (res != null)
			res.g = s.g + 1;
		return res;
	}

	AStarState moveRight(AStarState s) {
		AStarState res = (AStarState) super.moveRight(s);
		if (res != null)
			res.g = s.g + 1;
		return res;
	}

	protected HashMap<State, State> M = new HashMap<State, State>();
	PriorityQueue<AStarState> open = new PriorityQueue<AStarState>();
	Set<AStarState> close = new HashSet<AStarState>();

	public void AStar(){
		AStarState s,ns;
		while(!open.isEmpty()){
			s=open.poll();
			if(s.isTarget()){
				target=s;
				break;
			}
			if(close.contains(s))continue;
			close.add(s);
			ns=moveUp(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=moveDown(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=moveLeft(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=moveRight(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
		}
	}
	@Override
	public String solve() {
		long startTime=System.currentTimeMillis();
		open.clear();
		close.clear();
		M.clear();
		AStarState st=new AStarState(GameLogic.getInstance());
		st.g=0;
		open.add(st);
		AStar();
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public String solve(String str){
		long startTime=System.currentTimeMillis();
		open.clear();
		close.clear();
		M.clear();
		AStarState st=new AStarState(str);
		st.g=0;
		open.add(st);
		AStar();
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		String res=instance.solve("470563812");
		System.out.println(res);
	}

}
