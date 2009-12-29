import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StatusBar extends JPanel {
	private static StatusBar instance = new StatusBar();
	private JTextField sol = new JTextField();

	private StatusBar() {
		this.setLayout(new BorderLayout());
		this.add(sol);
		sol.setEditable(false);
		sol.setPreferredSize(new Dimension(0, 25));
	}

	public void setSolution(String s, long timeCost) {

		StringBuffer res = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'u':
				res.append('↑');
				break;
			case 'd':
				res.append('↓');
				break;
			case 'l':
				res.append('←');
				break;
			case 'r':
				res.append('→');
				break;
			}
		}
		res.append(" " + s.length() + " steps "+timeCost+" ms");
		sol.setText(res.toString());
	}

	public static StatusBar getInstance() {
		return instance;
	}

	public void clearSolution() {
		sol.setText("");
	}
}
