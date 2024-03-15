import java.util.ArrayList;

public class OurAI implements IOthelloAI {

   /**
	 * Returns first legal move
	 */
 @Override
	public Position decideMove(GameState s){
		Tuple<Position, Integer, Integer> result = minimax(s, 0);
		return result.getPosition();
	}

	public Tuple<Position, Integer, Integer> minimax(GameState s, Integer depth){
		// Get all legal moves
		ArrayList<Position> moves = s.legalMoves();

		depth++;

		Tuple<Position, Integer, Integer> maxMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);
		Tuple<Position, Integer, Integer> minMove = new Tuple<Position, Integer, Integer>(moves.get(0), 0, depth);

		for (Position p: moves) {
			int[][] newBoard = s.getBoard();

			// Mimic a move (0 for empty, 1 for black, 2 for white)
			newBoard[p.row][p.col] = s.getPlayerInTurn() == 1 ? 1 : 2;

			// Put the mimicked move into a new GameState and switch the players turn
			GameState ns = new GameState(newBoard, s.getPlayerInTurn() == 1 ? 2 : 1);

			if (ns.isFinished()) {
				int winner = findWinner(ns);
				return new Tuple<Position, Integer, Integer>(p, winner, depth);
			}

			if (depth == 2) {
				int[] nsTokens = ns.countTokens();
				int[] sTokens = s.countTokens();
				
				if (ns.getPlayerInTurn() == 1){
					return new Tuple<Position, Integer, Integer>(p, (nsTokens[0] > sTokens[0] ? nsTokens[0] : sTokens[0]), depth);
				} else {
					return new Tuple<Position, Integer, Integer>(p, (nsTokens[1] < sTokens[1] ? nsTokens[1] : sTokens[1]), depth);
				}
			}
			
			// Call minimax recursively with mimicked game state
			Tuple<Position, Integer, Integer> recursive = minimax(ns, depth);

			if (maxMove.getValue() < recursive.getValue()  &&  ns.getPlayerInTurn() == 1) {
				maxMove = recursive;

			} else if ((minMove.getValue() > recursive.getValue()) && ns.getPlayerInTurn() == 2){
				minMove = recursive;
			}
		}
		if (s.getPlayerInTurn() == 1){
			return maxMove;
		}
		return minMove;
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
