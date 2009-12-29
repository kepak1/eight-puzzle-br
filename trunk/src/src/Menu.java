import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class Menu extends JMenuBar implements ActionListener{
	JMenu game = new JMenu("game");
	JMenu newGame=new JMenu("new game");
	JMenuItem two = new JMenuItem("2 X 2"), three = new JMenuItem("3 X 3"),
			four = new JMenuItem("4 X 4"),solve=new JMenuItem("solve");
	static Menu instance = new Menu();

	private Menu(){
		game.add(newGame);
		game.add(solve);
		newGame.add(two);
		newGame.add(three);
		newGame.add(four);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		solve.addActionListener(this);
		this.add(game);
		
	}

	public static Menu getInstance() {
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==two){
			GamePanel.getInstance().init(2);
		}else if(e.getSource()==three){
			GamePanel.getInstance().init(3);
		}else if(e.getSource()==four){
			GamePanel.getInstance().init(4);
		}else if(e.getSource()==solve){
			StatusBar.getInstance().setSolution(BFSSolver.getInstance().solve());
		}
		Game.getInstance().pack();
	}
}
