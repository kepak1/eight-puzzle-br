import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Square extends JButton {
	private int idx;	//the location of square, not the value(text)

	Square(int idx){
		this.setPreferredSize(new Dimension(100, 100));
		this.idx=idx;
		setValue(idx);
		this.addActionListener(Controller.getInstance());
	}
	public int getIdx(){
		return idx;
	}
	public void setValue(int v){
		if (v != 0) {
			this.setText(Integer.toString(v));
			this.setBackground(Color.orange);
			this.setForeground(Color.white);
		}else{
			this.setText("");
			this.setBackground(Color.gray);
		}
	}
}
