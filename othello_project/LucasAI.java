import java.util.ArrayList;

public class LucasAI implements IOthelloAI{
    int MAX_DEPTH = 8;
    int MAX_TIME = 9500;
    int CORNER_REWARD = 20;
    int EDGE_REWARD = 10;

    @Override
    public Position decideMove(GameState s) {
        Tuple best;

        long timer = System.currentTimeMillis();

        int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

        if (isBlackTurn(s)) {
            best = new Tuple (new Position(-1,-1), Integer.MIN_VALUE);
            for (Position p : s.legalMoves()) {
                GameState ns = createNewState(s, p);
                Tuple tmp = minValue(ns, 0, p, alpha, beta, timer);

                if (tmp.val > best.val) {
                    best.val = tmp.val;
                    best.pos = p;
                }
                if (System.currentTimeMillis() - timer > MAX_TIME) {break;}
            }
        } else {
            best = new Tuple (new Position(-1,-1), Integer.MAX_VALUE);
            for (Position p : s.legalMoves()) {
                GameState ns = createNewState(s, p);
                Tuple tmp = maxValue(ns, 0, p, alpha, beta, timer);

                if (tmp.val < best.val) {
                    best.val = tmp.val;
                    best.pos = p;
                }
                if (System.currentTimeMillis() - timer > MAX_TIME) {break;}
            }
        }
        System.out.println("Time: " + ((System.currentTimeMillis() - timer)/1000) + "s");
        return best.pos;
    }

    private Tuple maxValue(GameState s, int depth, Position p, int alpha, int beta, long timer) {
        ArrayList<Position> moves = s.legalMoves();

        if (moves.isEmpty() || depth == MAX_DEPTH || System.currentTimeMillis() - timer > MAX_TIME) {                        // Terminal states
            return new Tuple(p, utility(s, p, depth));
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MIN_VALUE);
        
        depth++;

        for (Position np : moves) {
            GameState ns = createNewState(s, np);
            Tuple res = minValue(ns, depth, np, alpha, beta, timer);

            if (res.val > best.val) {
                alpha = Math.max(alpha, res.val);                           // Part of alpha-beta pruning
                best.val = res.val;
                best.pos = np;
            }
            if (res.val >= beta) {return best;}                             // Part of alpha-beta pruning
        }

        return best;
    }

    private Tuple minValue(GameState s, int depth, Position p, int alpha, int beta, long timer) {
        ArrayList<Position> moves = s.legalMoves();

        if (moves.isEmpty() || depth == MAX_DEPTH || System.currentTimeMillis() - timer > MAX_TIME) {                        // Terminal states
            return new Tuple(p, utility(s, p, depth));
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MAX_VALUE);
        
        depth++;

        for (Position np : moves) {
            GameState ns = createNewState(s, np);
            Tuple res = maxValue(ns, depth, np, alpha, beta, timer);

            if (res.val < best.val) {
                beta = Math.min(beta, res.val);                             // Part of alpha-beta pruning
                best.val = res.val;
                best.pos = np;
            }
            if (res.val <= alpha) {return best;}                            // Part of alpha-beta pruning
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

        // if (isCorner(p, s)) {aux += 20;}
        // else if (isEdge(p, s)) {aux += 10;}
        aux += getCornerEdgeUtility(s, p);

        if (s.isFinished()) {aux += 300;}
        else if (depth == MAX_DEPTH) {aux += countTokens(s);}
        return isBlackTurn(s) ? aux : -aux;
    }

    private int countTokens(GameState s) {
        return isBlackTurn(s) ? s.countTokens()[0] : s.countTokens()[1];
    }

    private boolean isBlackTurn(GameState s) {
        return s.getPlayerInTurn() == 1;
    }

    private int getCornerEdgeUtility(GameState s, Position p) {
        int aux = 0;

        if (p.col == 0 || p.col == s.getBoard().length - 1) {
            if (p.row == 0 || p.row == s.getBoard().length - 1) {aux += CORNER_REWARD;}
            else if (p.row >= 2 && p.row <= s.getBoard().length -3) {aux += EDGE_REWARD;}
        } else if (p.col >= 2 && p.col <= s.getBoard().length - 3 && p.row == 0 || p.row == s.getBoard().length - 1) {
            aux += EDGE_REWARD;
        }
        return aux;
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
