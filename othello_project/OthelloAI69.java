import java.util.ArrayList;

public class OthelloAI69 implements IOthelloAI{
    int MAX_DEPTH = 8;                                                                                  // The maximum depth of the search
    int MAX_TIME = 10000;                                                                               // The maximum time the search is allowed to take
    int CORNER_REWARD = 20;                                                                             // The reward for laying a piece in a corner
    int EDGE_REWARD = 10;                                                                               // The reward for laying a piece on an edge
    long totalTime;                                                                                     // The total time spent on making a move
    long maxTime;                                                                                       // The maximum time spent on a single search
    int laps;                                                                                           // The number of searches made

    public OthelloAI69() {
        totalTime = 0;
        maxTime = 0;
        laps = 0;
    }

    /**
     * Overload constructor to help run the AI with the OthelloStatistic class
     * By adding a randomSeed we can further add chaos to the AI
     * Further chaos is also added by running the AI concurrently in the OthelloStatistic class
     * @param randomSeed
     */
    public OthelloAI69(int randomSeed) {
        MAX_TIME += randomSeed;
        totalTime = 0;
        maxTime = 0;
        laps = 0;
    }

    /**
     * Calculates the move to make for the given game state.
     * @param s The current state of the game in which it should be the AI's turn.
     * @return the position that the AI considers optimal given its constraints
     */
    @Override
    public Position decideMove(GameState s) {
        long timer = System.currentTimeMillis();                                                        // Start the timer
        Tuple best;


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
        long elapsedTime = System.currentTimeMillis() - timer;                                          // Look at the elapsed time
        maxTime = Math.max(maxTime, elapsedTime);                                                       // Save the maximum time
        totalTime += elapsedTime;                                                                       // Add the elapsed time to the total time
        laps++;                                                                                         // Increment the number of searches made
        return best.pos;                                                                                // Return the best move
    }

    /**
     * Calculates the best possible move for Max in state s.
     * @param s the current GameState
     * @param depth the current depth of the search
     * @param p the current position that has to be played if the state is considered a leaf
     * @param alpha
     * @param beta
     * @param timer the time the search started
     * @return Returns the best possible move for Max can make in state s and its utility
     */
    private Tuple maxValue(GameState s, int depth, Position p, int alpha, int beta, long timer) {
        ArrayList<Position> moves = s.legalMoves();                                                     // Get all legal moves

        if (moves.isEmpty() || depth == MAX_DEPTH || System.currentTimeMillis() - timer > MAX_TIME) {   // Is-Cutoff states
            return new Tuple(p, eval(s, p, depth));                                                     // Return the utility of the state
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MIN_VALUE);                                 // Initialize the best move to the worst possible value
        
        depth++;                                                                                        // Increment the depth

        for (Position np : moves) {                                                                     // For each legal move call minValue
            GameState ns = createNewState(s, np);
            Tuple res = minValue(ns, depth, np, alpha, beta, timer);

            if (res.val > best.val) {
                alpha = Math.max(alpha, res.val);                                                       // Part of alpha-beta pruning
                best.val = res.val;
                best.pos = np;
            }
            if (res.val >= beta) {return best;}                                                         // Part of alpha-beta pruning
        }

        return best;
    }

    /**
     * Calculates the best possible move for Min in state s.
     * @param s the current GameState
     * @param depth the current depth of the search
     * @param p the current position that has to be played if the state is considered a leaf
     * @param alpha
     * @param beta
     * @param timer the time the search started
     * @return Returns the best possible move for Min can make in state s and its utility
     */
    private Tuple minValue(GameState s, int depth, Position p, int alpha, int beta, long timer) {
        ArrayList<Position> moves = s.legalMoves();                                                     // Get all legal moves

        if (moves.isEmpty() || depth == MAX_DEPTH || System.currentTimeMillis() - timer > MAX_TIME) {   // Is-Cutoff states
            return new Tuple(p, eval(s, p, depth));                                                     // Return the utility of the state
        }

        Tuple best = new Tuple(new Position(-1,-1), Integer.MAX_VALUE);                                 // Initialize the best move to the worst possible value
        
        depth++;                                                                                        // Increment the depth

        for (Position np : moves) {                                                                     // For each legal move call maxValue
            GameState ns = createNewState(s, np);
            Tuple res = maxValue(ns, depth, np, alpha, beta, timer);

            if (res.val < best.val) {
                beta = Math.min(beta, res.val);                                                         // Part of alpha-beta pruning
                best.val = res.val;
                best.pos = np;
            }
            if (res.val <= alpha) {return best;}                                                        // Part of alpha-beta pruning
        }

        return best;
    }

    /**
     * Creates a new GameState with the token of the player in turn inserted at position p.
     * @param s the current GameState
     * @param p the position where the token should be inserted
     * @return Returns a new GameState with the token of the player in turn inserted at position p
     */
    private GameState createNewState(GameState s, Position p) {
        GameState ns = new GameState(s.getBoard(), s.getPlayerInTurn());
        if (ns.insertToken(p)) {
            return ns;
        }
        return s;
    }

    /**
     * Calculate the expected utility of the state s.
     * @param s the current GameState
     * @param p the position that has to be played
     * @param depth the current depth of the search
     * @return Returns the expected utility of the state s
     */
    private int eval(GameState s, Position p, int depth) {                                              // Calculate the expected utility of a state
        int aux = 0;

        aux += getCornerEdgeUtility(s, p);                                                              // Reward for being in a corner or edge

        if (s.isFinished()) {aux += 300;}                                                               // Reward for winning
        else if (depth == MAX_DEPTH) {aux += countTokens(s);}                                           // Reward for having more tokens
        return isBlackTurn(s) ? aux : -aux;                                                             // Return the utility based upon the player in turn
    }

    /**
     * Counts the number of tokens in the GameState s.
     * @param s the current GameState
     * @return the number of tokens in the GameState s for the player in turn
     */
    private int countTokens(GameState s) {
        return isBlackTurn(s) ? s.countTokens()[0] : s.countTokens()[1];
    }

    /**
     * Helper function to see if the current player is black
     * @param s the current GameState
     * @return return true if the current player is black
     */
    private boolean isBlackTurn(GameState s) {
        return s.getPlayerInTurn() == 1;
    }

    /**
     * Returns a reward for the position p based on its location on the board.
     * @param s
     * @param p
     * @return
     */
    private int getCornerEdgeUtility(GameState s, Position p) {
        int aux = 0;

        if (p.col == 0 || p.col == s.getBoard().length - 1) {
            if (p.row == 0 || p.row == s.getBoard().length - 1) {aux += CORNER_REWARD;}                 // Reward for being in a corner
            else if (p.row >= 2 && p.row <= s.getBoard().length -3) {aux += EDGE_REWARD;}               // Reward for being on an edge
        } else if (p.col >= 2 && p.col <= s.getBoard().length - 3 &&
                   p.row == 0 || p.row == s.getBoard().length - 1) {
            aux += EDGE_REWARD;                                                                         // Reward for being on an edge
        }
        return aux;
    }

    /**
     * Helper function to run the AI with the OthelloStatistic class
     * @return the total time spent in the AI
     */
    public long getTotalTime() {return totalTime;}
    /**
     * Helper function to run the AI with the OthelloStatistic class
     * @return the maximum time spent in a single search
     */
    public long getMaxTime() {return maxTime;}
    /**
     * Helper function to run the AI with the OthelloStatistic class
     * @return the number of searches made
     */
    public int getLaps() {return laps;}

    /**
     * A simple tuple class to store a position and a value
     * @param pos the position
     * @param val the value
     */
    public class Tuple {
        public Position pos;
        public int val;

        public Tuple(Position pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }
}
