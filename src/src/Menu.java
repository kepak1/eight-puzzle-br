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
	JMenu newGame = new JMenu("new"), solve = new JMenu("solve"),
			control = new JMenu("control");
	JMenuItem two = new JMenuItem("2 X 2"), three = new JMenuItem("3 X 3"),
			four = new JMenuItem("4 X 4"), five = new JMenuItem("5 X 5");
	JMenuItem bfs = new JMenuItem("bfs"), dfsid = new JMenuItem("dfsid"),
			AStar = new JMenuItem("A*"), bidirection = new JMenuItem(
					"bidirection"), AStarID = new JMenuItem("A*ID"),shuffle=new JMenuItem("shuffle");

	static Menu instance = new Menu();

	private Menu() {
		this.add(newGame);
		this.add(solve);
		this.add(control);

		newGame.add(two);
		newGame.add(three);
		newGame.add(four);
		newGame.add(five);

		solve.add(bfs);
		solve.add(dfsid);
		solve.add(bidirection);
		solve.add(AStar);
		solve.add(AStarID);

		control.add(shuffle);

		shuffle.addActionListener(this);
		
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);

		bfs.addActionListener(this);
		dfsid.addActionListener(this);
		AStar.addActionListener(this);
		bidirection.addActionListener(this);
		AStarID.addActionListener(this);

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
		} else if (e.getSource() == AStarID) {
			Solver sol = AStarIDSolver.getInstance();
			StatusBar.getInstance().setSolution(sol.solve(), sol.timeCost);
		} else if(e.getSource()==shuffle){
			GamePanel.getInstance().shuffle();
		}
		Game.getInstance().pack();
	}
}
