import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	private GameLogic gl=GameLogic.getInstance();
	private static Square squares[]=new Square[9];
	private static GamePanel instance=new GamePanel();
	private GamePanel(){
		this.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++){
			squares[i]=new Square(i);
			this.add(squares[i]);
		}
		newGame();
	}
	
	public void newGame(){
		gl.shuffle();
		relocateSquares();
	}

	public static  GamePanel getInstanc(){
		return instance;
	}
	public int getEmptyIdx(){
		return gl.getEmptyIdx();
	}
	private void relocateSquares(){
		for(int i=0;i<9;i++){
			squares[i].setValue(gl.getDigit(i));
		}
		this.repaint();
	}
	public void moveUp(){
		gl.moveUp();
		relocateSquares();
	}
	public void moveDown(){
		gl.moveDown();
		relocateSquares();
	}
	public void moveLeft(){
		gl.moveLeft();
		relocateSquares();
	}
	public void moveRight(){
		gl.moveRight();
		relocateSquares();
	}
	
}
