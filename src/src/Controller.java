import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener, KeyEventDispatcher {

	private static Controller instance = new Controller();

	private Controller() {
	}

	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(((Square)e.getSource()).getSize());
		GamePanel pg = GamePanel.getInstance();
		int dim = pg.getDim();
		int chosenIdx = ((Square) e.getSource()).getIdx();
		int emptyIdx = pg.getEmptyIdx();
		if (chosenIdx == emptyIdx + dim) {
			pg.moveUp();
		} else if (chosenIdx == emptyIdx - dim) {
			pg.moveDown();
		} else if (chosenIdx == emptyIdx + 1) {
			pg.moveLeft();
		} else if (chosenIdx == emptyIdx - 1) {
			pg.moveRight();
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID()!=KeyEvent.KEY_PRESSED)return false;
		GamePanel pg = GamePanel.getInstance();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			pg.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			pg.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			pg.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			pg.moveRight();
			break;
		}
		
		return false;
	}
	
}
