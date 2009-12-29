import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{

	private static Controller instance=new Controller();
	private Controller() {
	}
	
	public static Controller getInstance(){
		return instance;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(((Square)e.getSource()).getSize());
		GamePanel pg=GamePanel.getInstance();
		int dim=pg.getDim();
		int chosenIdx=((Square)e.getSource()).getIdx();
		int emptyIdx=pg.getEmptyIdx();
		if(chosenIdx==emptyIdx+dim){
			pg.moveUp();
		}else if(chosenIdx==emptyIdx-dim){
			pg.moveDown();
		}else if(chosenIdx==emptyIdx+1){
			pg.moveLeft();
		}else if(chosenIdx==emptyIdx-1){
			pg.moveRight();
		}
		if(GameLogic.getInstance().getState()==1){
			new WinDialog();
		}
	}

}
