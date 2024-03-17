import java.util.ArrayList;
import java.util.Arrays;

public class OurAI implements IOthelloAI {

	int MAX_DEPTH = 2;

 	@Override
	public Position decideMove(GameState s){
		// System.out.println("Init board:\n" + Arrays.deepToString(s.getBoard()));
		System.out.println("Init board:\n");
		printBoard(s.getBoard());

		System.out.println("Amount of legal moves for " + (isBlackTurn(s) ? "black" : "white") + " : " + s.legalMoves().size());

		ArrayList<Position> moves = s.legalMoves();
		int bestMove = 0;
		Position bestPosition = moves.get(0);

		for (Position p : moves) {
			System.out.println("--- Outloop iteration ---");

			int[][] newBoard = getNewBoard(s, p);

			GameState ns = getNewGameState(s, newBoard);

			Tuple<Position, Integer, Integer> result = minimax(ns, 0);
			System.out.println("Result from minimax: " + result.getValue() + " and pos: " + result.getPosition());
			if (result.getValue() > bestMove) {
				bestMove = result.getValue();
				bestPosition.row = p.row;
				bestPosition.col = p.col;
				System.out.println("**************** Best position ****************\n" + bestPosition);
			}
			System.out.println("--- End of outloop iteration ---\n\n");
		}
		System.out.println("OurAI has decided a move");
		return bestPosition;
	}

	public Tuple<Position, Integer, Integer> minimax(GameState s, Integer depth){
		// Get all legal moves


		ArrayList<Position> moves = s.legalMoves();
		depth++;


		if (moves.isEmpty())
			return new Tuple<Position, Integer, Integer>(new Position(-1, -1), 0, depth);
		
		Tuple<Position, Integer, Integer> maxMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);
		Tuple<Position, Integer, Integer> minMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);

		for (Position p: moves) {
			int[][] newBoard = getNewBoard(s, p);
			GameState ns = getNewGameState(s, newBoard);

			if (ns.isFinished()) {
				int winner = findWinner(ns);
				System.out.println("\t\tFound winning state for: " + (isBlackTurn(ns) ? "black" : "white"));
				System.out.println("\t\tWill return pos: " + p + " with winner: " + winner + " and depth: " + depth);
				return new Tuple<Position, Integer, Integer>(p, winner, depth);
			}


			if (depth == MAX_DEPTH) {
				System.out.println("\t\tReached max depth for: " + (isBlackTurn(ns) ? "black" : "white"));

				Tuple<Position, Integer, Integer> ret = getBestDepthMove(s, ns, p);
				System.out.println("\t\tWill return pos: " + p + " with winner: " + ret.getValue() + " and depth: " + depth);
				return ret;
			}


			// Call minimax recursively with mimicked game state
			Tuple<Position, Integer, Integer> recursive = minimax(ns, depth);


			System.out.println("\tMaxMove val:   " + maxMove.getValue() + " and pos: " + maxMove.getPosition().col + ", " + maxMove.getPosition().row);
			System.out.println("\tMinMove val:   " + minMove.getValue() + " and pos: " + minMove.getPosition().col + ", " + minMove.getPosition().row);
			System.out.println("\tRecursive val: " + recursive.getValue() + " and pos: " + recursive.getPosition().col + ", " + recursive.getPosition().row);
			System.out.println("\t" + (isBlackTurn(ns) ? "Black" : "White") + " turn");


			if (maxMove.getValue() < recursive.getValue()  &&  isBlackTurn(ns)) {
				maxMove.setValue(recursive.getValue());

			} else if ((minMove.getValue() > recursive.getValue()) && !isBlackTurn(ns)){
				minMove.setValue(recursive.getValue());
			}
		}
		if (!isBlackTurn(s)){
			System.out.println("\tReturning MaxMove: " + maxMove.getValue());
			return maxMove;
		}
		System.out.println("\tReturning MinMove: " + minMove.getValue());
		return minMove;
	}

	public GameState getNewGameState(GameState s, int[][] board) {
		System.out.println("Creating new game state");
		System.out.println("\tIsBlackTurn from: " + isBlackTurn(s) + " to " + (isBlackTurn(s) ? 2 : 1));
		return new GameState(board, isBlackTurn(s) ? 2 : 1);
	}

	/**
	 * Returns a new board with the move made
	 * @param s is the current game state
	 * @param p is the position of the move
	 * @return new board
	 */
	public int[][] getNewBoard(GameState s, Position p) {

		int[][] originalBoard = s.getBoard();
		int[][] newBoard = new int[originalBoard.length][originalBoard.length]; // originalBoard.length can be used in both dimensions since it's a square

		for (int i = 0; i < originalBoard.length; i++) {
			System.arraycopy(originalBoard[i], 0, newBoard[i], 0, originalBoard[i].length);
		}

		newBoard[p.row][p.col] = isBlackTurn(s) ? 1 : 2;

		printBoard(newBoard);

		return newBoard;
	}

	/**
	 * Returns the best move for the current player
	 * @param s is the current game state
	 * @param ns is the next game state
	 * @return the best move
	 */
	public Tuple<Position, Integer, Integer> getBestDepthMove(GameState s, GameState ns, Position p) {
		int[] nsTokens = ns.countTokens();
		int[] sTokens = s.countTokens();

		if (isBlackTurn(ns)){
			return new Tuple<Position, Integer, Integer>(p, (nsTokens[0] > sTokens[0] ? nsTokens[0] : sTokens[0]), 0);
		} else {
			return new Tuple<Position, Integer, Integer>(p, (nsTokens[1] < sTokens[1] ? nsTokens[1] : sTokens[1]), 0);
		}
	}

	/**
	 * Returns true if the player in turn is black
	 * @param s
	 * @return true for black, false for white
	 */
	public static boolean isBlackTurn(GameState s) {
		return s.getPlayerInTurn() == 1;
	}

	/**
	 * Returns the winner of the game
	 * Assumes that the game is finished
	 * @param s
	 * @return 1 for black, 2 for white, 0 for draw
	 */
	public int findWinner(GameState s) {
		int[] state = s.countTokens();

		if (state[0] > state[1]) {
			return 1;
		} else if (state[0] < state[1]) {
			return 2;
		} else {
			return 0;
		}
	}

	public void printBoard(int[][] board) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < board.length; i++) {
			sb.append(Arrays.toString(board[i]) + "\n");
		}

		System.out.println(sb.toString());
	}


	public class Tuple<P, V, D> {
		private Position position;
		private int value;
		private int depth;

		public Tuple(Position position, int value, int depth) {
			this.position = position;
			this.value = value;
			this.depth = depth;
		}

		public void setPosition(Position position) { this.position = position; }

		public Position getPosition() { return position; }

		public void setValue(int value) { this.value = value; }

		public int getValue() { return value; }

		public void setDepth(int depth) { this.depth = depth; }

		public int getDepth() { return depth; }
	}
}
