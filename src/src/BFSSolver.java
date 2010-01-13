import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSSolver extends Solver{
	
	private static BFSSolver instance=new BFSSolver();
	private BFSSolver(){}
	public static BFSSolver getInstance(){return instance;}

	private void bfs(State s) {
		Set<State> S = new HashSet<State>();
		Queue<State> q=new LinkedList<State>();
		S.add(s);
		q.add(s);
		State ns;
		while(!q.isEmpty()){
			s=q.poll();
			if(s.isTarget()){
				target=s;
				break;
			}
			ns=State.moveUp(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=State.moveDown(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=State.moveLeft(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=State.moveRight(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
		}
	}
	
	public String solve(){
		long startTime=System.currentTimeMillis();
		target=null;
		State s = new State(GameLogic.getInstance());
		bfs(s);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public String solve(int[] a){
		long startTime=System.currentTimeMillis();
		target=null;
		State s = new State(a);
		bfs(s);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		int[] a={4,7,0,5,6,3,8,1,2,};
		String res = instance.solve(a);
		System.out.println(res);
	}
	
}
