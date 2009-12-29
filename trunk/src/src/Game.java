import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame{
	private static Game instance=new Game();
	private Game(){
		this.setLocation(200, 100);
		this.setVisible(false);
		this.setLayout(new BorderLayout());
		this.add(GamePanel.getInstance(),BorderLayout.CENTER);
		this.add(Menu.getInstance(),BorderLayout.NORTH);
		this.add(StatusBar.getInstance(),BorderLayout.SOUTH);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel.getInstance().init(3);
		this.pack();
	}
	public static Game getInstance(){
		return instance;
	}
}
