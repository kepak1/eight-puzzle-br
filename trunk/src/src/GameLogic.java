import java.util.Random;

public class GameLogic {
	private static GameLogic instance = new GameLogic();
	private int dim;
	private int[] digit;
	private int emptyIdx;
	private Random rand = new Random();
	/*
	 * state: 0: playing 1: win
	 */
	private int state;

	private GameLogic() {
		this(3);
	}

	private GameLogic(int dim) {
		init(dim);
	}


	public void init(int dim) {
		digit = new int[dim * dim];
		this.dim = dim;
		int top=dim * dim - 1;
		for (int i = 0; i < top; i++)
			digit[i] = 1 + i;
		digit[top] = 0;
		emptyIdx = top;
		state = 0;
	}
	
	public void shuffle() {
		for (int i = 0; i < 100; i++) {
			randomMove();
		}
		updateState();
		if(state==1)shuffle();
	}

	public int getDim(){
		return dim;
	}
	public int getDigit(int i) {
		return digit[i];
	}

	public int getEmptyIdx() {
		return emptyIdx;
	}

	public int getState() {
		return state;
	}

	private void randomMove() {
		while (true) {
			switch (Math.abs(rand.nextInt()) % 4) {
			case 0:
				if (moveUp())
					return;
				else
					break;
			case 1:
				if (moveDown())
					return;
				else
					break;
			case 2:
				if (moveLeft())
					return;
				else
					break;
			case 3:
				if (moveRight())
					return;
				else
					break;
			}
		}
	}

	public static GameLogic getInstance() {
		return instance;
	}

	private void swapDigit(int i, int j) {
		int t = digit[i];
		digit[i] = digit[j];
		digit[j] = t;
	}

	private void updateState() {
		int top=dim * dim - 1;
		for (int i = 0; i < top; i++) {
			if (digit[i] != 1 + i) {
				state = 0;
				return;
			}
		}
		/*
		 * else, win already
		 */
		state = 1;
	}

	public boolean moveDown() {
		if (emptyIdx < dim)
			return false;
		swapDigit(emptyIdx, emptyIdx - dim);
		emptyIdx -= dim;
		updateState();
		return true;
	}

	public boolean moveUp() {
		if (emptyIdx >= dim * dim - dim)
			return false;
		swapDigit(emptyIdx, emptyIdx + dim);
		emptyIdx += dim;
		updateState();
		return true;
	}

	public boolean moveLeft() {
		if (emptyIdx % dim == dim - 1)
			return false;
		swapDigit(emptyIdx, emptyIdx + 1);
		emptyIdx += 1;
		updateState();
		return true;
	}

	public boolean moveRight() {
		if (emptyIdx % dim == 0)
			return false;
		swapDigit(emptyIdx, emptyIdx - 1);
		emptyIdx -= 1;
		updateState();
		return true;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < dim * dim; i++) {
			if (digit[i] == 0) {
				res.append(" ");
			} else {
				res.append(Integer.toString(digit[i]));
			}
			res.append(i % dim == dim - 1 ? '\n' : '\t');
		}
		return res.toString();
	}

}
