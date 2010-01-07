import java.awt.GridLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameLogic gl = GameLogic.getInstance();
	private static Square[] squares;
	private static GamePanel instance = new GamePanel();

	private GamePanel() {
		this(3);
	}

	private GamePanel(int dim) {
		init(dim);
	}

	public void init(int dim) {
		this.removeAll();
		gl.init(dim);
		squares = new Square[dim * dim];
		this.setLayout(new GridLayout(dim, dim));
		for (int i = 0; i < dim * dim; i++) {
			squares[i] = new Square(i);
			this.add(squares[i]);
		}
		newGame();
	}

	public void newGame() {
		gl.shuffle();
		relocateSquares();
	}

	public static GamePanel getInstance() {
		return instance;
	}

	public int getEmptyIdx() {
		return gl.getEmptyIdx();
	}

	public int getDim() {
		return gl.getDim();
	}

	private void relocateSquares() {
		int dim = gl.getDim();
		for (int i = 0; i < dim * dim; i++) {
			squares[i].setValue(gl.getDigit(i));
		}
		this.repaint();
		System.out.println(gl);
	}

	public void moveUp() {
		gl.moveUp();
		relocateSquares();
		if (GameLogic.getInstance().getState() == 1) {
			new WinDialog();
		}
	}

	public void moveDown() {
		gl.moveDown();
		relocateSquares();
		if (GameLogic.getInstance().getState() == 1) {
			new WinDialog();
		}
	}

	public void moveLeft() {
		gl.moveLeft();
		relocateSquares();
		if (GameLogic.getInstance().getState() == 1) {
			new WinDialog();
		}
	}

	public void moveRight() {
		gl.moveRight();
		relocateSquares();
		if (GameLogic.getInstance().getState() == 1) {
			new WinDialog();
		}
	}


}
