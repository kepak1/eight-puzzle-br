import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WinDialog extends JDialog implements ActionListener {
	String s = "<html><h2>contratulations !</h2><br>"
			+ "<pre> like to try again?</pre></html>";
	JLabel msg = new JLabel(s);
	JButton ok = new JButton("ok");

	WinDialog() {
		this.setVisible(true);
		this.setLayout(null);

		this.setPreferredSize(new Dimension(200, 200));
		this.pack();
		msg.setBounds(30, 10, 200, 100);
		ok.setBounds(70, 120, 60, 30);
		this.add(msg);
		this.add(ok);
		this.setResizable(false);
		this.setLocation(this.getParent().getX(), this.getParent().getY());
		ok.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			GamePanel.getInstance().newGame();
			StatusBar.getInstance().clearSolution();
		}
		this.dispose();
	}
}
