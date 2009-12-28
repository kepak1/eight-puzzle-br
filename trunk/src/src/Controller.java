import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements ActionListener{

	private static Controller instance=new Controller();
	private Controller() {
	}
	
	public static Controller getInstance(){
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GamePanel pg=GamePanel.getInstanc();
		int chosenIdx=((Square)e.getSource()).getIdx();
		int emptyIdx=pg.getEmptyIdx();
		if(chosenIdx==emptyIdx+3){
			pg.moveUp();
		}else if(chosenIdx==emptyIdx-3){
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
