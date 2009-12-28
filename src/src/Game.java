import javax.swing.JFrame;


public class Game extends JFrame{
	Game(){
		this.setLocation(200, 100);
		this.setVisible(true);
		this.add(GamePanel.getInstanc());
		this.pack();
		this.setResizable(false);
	}
}
