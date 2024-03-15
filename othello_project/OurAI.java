import java.util.ArrayList;
import java.util.Arrays;

public class OurAI implements IOthelloAI {

   /**
	 * Returns first legal move
	 */
 @Override
	public Position decideMove(GameState s){
		System.out.println("Init board:\n" + Arrays.deepToString(s.getBoard()));

		System.out.println("OurAI is thinking...");
	
		System.out.println("Legal moves for " + s.getPlayerInTurn() + ": " + s.legalMoves().size());

		ArrayList<Position> moves = s.legalMoves();
		int bestMove = 0;
		Position bestPosition = moves.get(0);

		for (Position p : moves) {
			System.out.println("--------- Trying outer position thing --------");

			int[][] newBoard = getNewBoard(s, p);
			System.out.println("\t above new board called from outer loop");
			GameState ns = getNewGameState(s, newBoard);
			
			Tuple<Position, Integer, Integer> result = minimax(ns, 0);
			if (result.getValue() > bestMove) {
				bestMove = result.getValue();
				bestPosition = result.getPosition();
				System.out.println("**************** Best position ****************\n" + bestPosition);
			}
		}
		System.out.println("OurAI has decided a move");
		return bestPosition;
	}

	public Tuple<Position, Integer, Integer> minimax(GameState s, Integer depth){
		// Get all legal moves
		ArrayList<Position> moves = s.legalMoves();
		depth++;

		Tuple<Position, Integer, Integer> maxMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);
		Tuple<Position, Integer, Integer> minMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);

		for (Position p: moves) {
			int[][] newBoard = getNewBoard(s, p);
			System.out.println("\t new board called from minimax... " + Arrays.deepToString(newBoard));
			GameState ns = getNewGameState(s, newBoard);
			System.out.println("Create new Game state ns: " + Arrays.deepToString(ns.getBoard()));
			if (ns.isFinished()) {
				int winner = findWinner(ns);
				return new Tuple<Position, Integer, Integer>(p, winner, depth);
			}

			if (depth == 2) {
				return getBestDepthMove(s, ns);
			}

			// Call minimax recursively with mimicked game state
			Tuple<Position, Integer, Integer> recursive = minimax(ns, depth);

			if (maxMove.getValue() < recursive.getValue()  &&  isBlackTurn(ns)) {
				maxMove = recursive;

			} else if ((minMove.getValue() > recursive.getValue()) && !isBlackTurn(ns)){
				minMove = recursive;
			}
		}
		if (isBlackTurn(s)){
			return maxMove;
		}
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
		int[][] newBoard = new int[originalBoard.length][];
		for (int i = 0; i < originalBoard.length; i++) {
			newBoard[i] = Arrays.copyOf(originalBoard[i], originalBoard[i].length);
		}

		newBoard[p.row][p.col] = isBlackTurn(s) ? 1 : 2;

		return newBoard;
		// System.out.println("Get board 1: \n" + Arrays.deepToString(s.getBoard()));
		// int[][] newBoard = s.getBoard();
		// System.out.println("Get new board: \n" + Arrays.deepToString(newBoard) + " - 1");

		// newBoard[p.row][p.col] = isBlackTurn(s) ? 1 : 2;
		// System.out.println("p.row: " + p.row + " ::::  p.col: " + p.col);
		// System.out.println("Get new board:\n" + Arrays.deepToString(newBoard) + " - 2");

		// System.out.println("Creating new board from " + Arrays.deepToString(s.getBoard()) + " to " + Arrays.deepToString(newBoard));
		// return newBoard;
	}
	
	/**
	 * Returns the best move for the current player
	 * @param s is the current game state
	 * @param ns is the next game state
	 * @return the best move
	 */

	public Tuple<Position, Integer, Integer> getBestDepthMove(GameState s, GameState ns) {
	   int[] nsTokens = ns.countTokens();
      int[] sTokens = s.countTokens();

		if (ns.getPlayerInTurn() == 1){
			return new Tuple<Position, Integer, Integer>(null, (nsTokens[0] > sTokens[0] ? nsTokens[0] : sTokens[0]), 0);
		} else {
			return new Tuple<Position, Integer, Integer>(null, (nsTokens[1] < sTokens[1] ? nsTokens[1] : sTokens[1]), 0);
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


	public class Tuple<P, V, D> {
		private Position position;
		private Integer value;
		private Integer depth;

		public Tuple(Position position, Integer value, Integer depth) {
			this.position = position;
			this.value = value;
			this.depth = depth;
		}

		public void setPosition(Position position) { this.position = position; }

		public Position getPosition() { return position; }

		public void setValue(Integer value) { this.value = value; }

		public Integer getValue() { return value; }

		public void setDepth(Integer depth) { this.depth = depth; }

		public Integer getDepth() { return depth; }
	}
}
