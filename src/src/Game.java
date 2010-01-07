import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Game instance = new Game();

	private Game() {

		this.setLocation(200, 100);
		this.setVisible(false);
		this.setLayout(new BorderLayout());
		this.add(GamePanel.getInstance(), BorderLayout.CENTER);
		this.add(Menu.getInstance(), BorderLayout.NORTH);
		this.add(StatusBar.getInstance(), BorderLayout.SOUTH);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Eight-Puzzle");
		GamePanel.getInstance().init(3);
		this.pack();

		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(Controller.getInstance());
	}

	public static Game getInstance() {
		return instance;
	}
}
