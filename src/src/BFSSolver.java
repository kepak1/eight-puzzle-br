import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSSolver extends Solver{
	
	private static BFSSolver instance=new BFSSolver();
	private BFSSolver(){}
	public static BFSSolver getInstance(){return instance;}

	protected Set<State> S = new HashSet<State>();
	private void bfs(State s) {
		Queue<State> q=new LinkedList<State>();
		q.add(s);
		q.isEmpty();
		State ns;
		while(!q.isEmpty()){
			s=q.poll();
			if(s.isTarget()){
				target=s;
				break;
			}
			ns=moveUp(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=moveDown(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=moveLeft(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
			ns=moveRight(s);
			if(ns!=null && !S.contains(ns)){
				S.add(ns);
				q.add(ns);
			}
		}
	}
	
	public String solve(){
		long startTime=System.currentTimeMillis();
		S.clear();
		target=null;
		State s = new State(GameLogic.getInstance());
		S.add(s);
		bfs(s);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public String solve(String str){
		long startTime=System.currentTimeMillis();
		S.clear();
		target=null;
		State s = new State(str);
		S.add(s);
		bfs(s);
		timeCost=System.currentTimeMillis()-startTime;
		return collectSolution();
	}
	
	public static void main(String[] args){
		String res=instance.solve("470563812");
		System.out.println(res);
	}
	
}
