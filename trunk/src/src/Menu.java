import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu game = new JMenu("game");
	JMenu newGame = new JMenu("new game");
	JMenu solve = new JMenu("solve");
	JMenuItem two = new JMenuItem("2 X 2"), three = new JMenuItem("3 X 3"),
			four = new JMenuItem("4 X 4"), five = new JMenuItem("5 X 5"),
			six = new JMenuItem("6 X 6");
	JMenuItem bfs = new JMenuItem("bfs"), dfsid = new JMenuItem("dfsid"),
			AStar = new JMenuItem("AStar"),bidirection=new JMenuItem("bidirection");

	static Menu instance = new Menu();

	private Menu() {
		game.add(newGame);
		game.add(solve);

		newGame.add(two);
		newGame.add(three);
		newGame.add(four);
		newGame.add(five);
		newGame.add(six);

		solve.add(bfs);
		solve.add(dfsid);
		solve.add(AStar);
		solve.add(bidirection);

		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		
		bfs.addActionListener(this);
		dfsid.addActionListener(this);
		AStar.addActionListener(this);
		bidirection.addActionListener(this);

		this.add(game);

	}

	public static Menu getInstance() {
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == two) {
			GamePanel.getInstance().init(2);
		} else if (e.getSource() == three) {
			GamePanel.getInstance().init(3);
		} else if (e.getSource() == four) {
			GamePanel.getInstance().init(4);
		} else if (e.getSource() == five) {
			GamePanel.getInstance().init(5);
		} else if (e.getSource() == six) {
			GamePanel.getInstance().init(6);
		} else if (e.getSource() == bfs) {
			Solver sol = BFSSolver.getInstance();
			StatusBar.getInstance().setSolution(sol.solve(), sol.timeCost);

		} else if (e.getSource() == dfsid) {
			Solver sol = DFSIDSolver.getInstance();
			StatusBar.getInstance().setSolution(sol.solve(), sol.timeCost);
		} else if (e.getSource() == AStar) {
			Solver sol = AStarSolver.getInstance();
			StatusBar.getInstance().setSolution(sol.solve(), sol.timeCost);
		} else if (e.getSource() == bidirection) {
			Solver sol = BidirectionalSearchSolver.getInstance();
			StatusBar.getInstance().setSolution(sol.solve(), sol.timeCost);
		}
		Game.getInstance().pack();
	}
}
