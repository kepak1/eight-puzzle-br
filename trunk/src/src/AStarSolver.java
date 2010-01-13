import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class AStarSolver extends Solver {
	private static AStarSolver instance=new AStarSolver();
	private AStarSolver(){}
	public static AStarSolver getInstance(){return instance;}
	


	protected HashMap<State, State> M = new HashMap<State, State>();
	PriorityQueue<AStarState> open = new PriorityQueue<AStarState>();
	Set<AStarState> close = new HashSet<AStarState>();

	
	//Scanner in=new Scanner(System.in);
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
			//System.out.println(s.findH()+" "+s.g);
			//in.next();
			ns=AStarState.moveUp(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=AStarState.moveDown(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=AStarState.moveLeft(s);
			if(ns!=null && !close.contains(ns)){
				open.add(ns);
			}
			ns=AStarState.moveRight(s);
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
		open.add(st);
		AStar();
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public String solve(int[] a){
		long startTime=System.currentTimeMillis();
		open.clear();
		close.clear();
		M.clear();
		AStarState st=new AStarState(a);
		open.add(st);
		AStar();
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		int[] a={4,7,0,5,6,3,8,1,2};
		String res = instance.solve(a);
		System.out.println(res);
		System.out.println(instance.close.size());
	}

}
