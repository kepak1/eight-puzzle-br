
public class AStarState extends State implements Comparable<AStarState>{
	int g;
	AStarState(){
		super();
	}
	public AStarState(GameLogic instance) {
		super(instance);
	}
	public AStarState(String str) {
		super(str);
	}
	public void copy(AStarState s){
		super.copy(s);
		g=s.g;
	}
	public int findH(){
		int res=0;
		for(int i=0;i<dim*dim;i++){
			int d=digit[i]-1;
			if(digit[i]==0)d=dim*dim-1;
			int x1=i/dim,y1=i%dim,
			x2=d/dim,y2=d%dim;
			res+=Math.abs(x2-x1);
			res+=Math.abs(y2-y1);
		}
		return res;
	}
	@Override
	public int compareTo(AStarState t) {
		int l=findH()+g,r=t.findH()+t.g;
		if(l>r)return 1;
		if(l<r)return -1;
		return 0;
	}
}
