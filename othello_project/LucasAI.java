import java.util.ArrayList;
import java.util.Arrays;

public class LucasAI implements IOthelloAI{
    int MAX_DEPTH = 5;

    @Override
    public Position decideMove(GameState s) {
        Tuple best;

        long timer = System.currentTimeMillis();

        int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

        if (isBlackTurn(s)) {
            best = new Tuple (new Position(-1,-1), Integer.MIN_VALUE);
            for (Position p : s.legalMoves()) {
                GameState ns = createNewState(s, p);
                Tuple tmp = minValue(ns, 0, p, alpha, beta);

                if (tmp.val > best.val) {
                    best.val = tmp.val;
                    best.pos = p;
                }
            }
        } else {
            best = new Tuple (new Position(-1,-1), Integer.MAX_VALUE);
            for (Position p : s.legalMoves()) {
                GameState ns = createNewState(s, p);
                Tuple tmp = maxValue(ns, 0, p, alpha, beta);

                if (tmp.val < best.val) {
                    best.val = tmp.val;
                    best.pos = p;
                }
            }
        }
        System.out.println("Time: " + ((System.currentTimeMillis() - timer)/1000) + "s");
        return best.pos;
    }

    private Tuple maxValue(GameState s, int depth, Position p, int alpha, int beta) {
        ArrayList<Position> moves = s.legalMoves();

        if (moves.isEmpty() || depth == MAX_DEPTH) {
            return new Tuple(p, utility(s, p, depth));
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MIN_VALUE);
        
        depth++;

        for (Position np : moves) {
            GameState ns = createNewState(s, np);
            Tuple res = minValue(ns, depth, np, alpha, beta);

            if (res.val > best.val) {
                alpha = Math.max(alpha, res.val);
                best.val = res.val;
                best.pos = np;
            }
            if (res.val >= beta) {return best;}
        }

        return best;
    }

    private Tuple minValue(GameState s, int depth, Position p, int alpha, int beta) {
        ArrayList<Position> moves = s.legalMoves();

        if (moves.isEmpty() || depth == MAX_DEPTH) {
            return new Tuple(p, utility(s, p, depth));
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MAX_VALUE);
        
        depth++;

        for (Position np : moves) {
            GameState ns = createNewState(s, np);
            Tuple res = maxValue(ns, depth, np, alpha, beta);

            if (res.val < best.val) {
                beta = Math.min(beta, res.val);
                best.val = res.val;
                best.pos = np;
            }
            if (res.val <= alpha) {return best;}
        }

        return best;
    }

    private GameState createNewState(GameState s, Position p) {
        GameState ns = new GameState(s.getBoard(), s.getPlayerInTurn());
        if (ns.insertToken(p)) {
            return ns;
        }
        return s;
    }

    private int utility(GameState s, Position p, int depth) {
        int aux = 0;

        // System.out.println("Player: " + (isBlackTurn(s) ? "black" : "white"));

        if (isCorner(p, s)) {
            aux += 20;
            // System.out.println("\tGot corner");
        }
        else if (isEdge(p, s)) {
            aux += 10;
            // System.out.println("\tGot edge");
        }

        if (s.isFinished()) {
            aux = 300;
            // System.out.println("\tGame finished");
        }
        else if (depth == MAX_DEPTH) {
            aux += countTokens(s);
            // System.out.println("\tMax depth");
        }

        // System.out.println("\tUtility: " + (isBlackTurn(s) ? aux : -aux) + "\n");

        // If black turn then return a positive value
        // else return a negative value
        return isBlackTurn(s) ? aux : -aux;
    }

    private int countTokens(GameState s) {
        return isBlackTurn(s) ? s.countTokens()[0] : s.countTokens()[1];
    }

    private boolean isBlackTurn(GameState s) {
        return s.getPlayerInTurn() == 1;
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

    public void printBoard(int[][] board) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < board.length; i++) {
			sb.append(Arrays.toString(board[i]) + "\n");
		}

		System.out.println(sb.toString());
	}

    /**
     * 
     * @param p
     * @param s
     * @return if the position is on the edge of the board
     */
    private boolean isEdge(Position p, GameState s) {
		return ((p.row >= 2 && p.row <= s.getBoard().length - 3 && (p.col == 0 || (p.col == s.getBoard().length - 1)))
		|| p.col >= 2 && p.col <= s.getBoard().length - 3 && (p.row == 0 || (p.row == s.getBoard().length - 1)));
	}

    /**
     * 
     * @param p
     * @param s
     * @return if the position is in the corner of the board
     */
    private boolean isCorner(Position p, GameState s) {
		return ((p.row == 0 || p.row == s.getBoard().length - 1) && (p.col == 0 || p.col == s.getBoard().length - 1));
	}

    public class Tuple {
        public Position pos;
        public int val;

        public Tuple(Position pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }
}
