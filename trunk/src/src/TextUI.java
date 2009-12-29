import java.util.Scanner;

public class TextUI {
	public static void main(String[] args) {
		GameLogic gl = GameLogic.getInstance();
		String cmd;

		Scanner in = new Scanner(System.in);
		do {
			gl.init(4);
			while (true) {
				System.out.println(gl);
				cmd = in.nextLine();
				if (cmd.equals("u")) {
					gl.moveUp();
				} else if (cmd.equals("d")) {
					gl.moveDown();
				} else if (cmd.equals("l")) {
					gl.moveLeft();
				} else if (cmd.equals("r")) {
					gl.moveRight();
				}
				if (gl.getState() == 1) {
					System.out.println("Congratulations!");
					System.out.println("Try again?(y/n)!");
					cmd = in.nextLine();
					break;
				}
			}
		} while (cmd.equals("y"));
	}
}
