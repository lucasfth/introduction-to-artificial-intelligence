import java.util.ArrayList;

public class OurAI implements IOthelloAI {
    /**
     * Returns first legal move
     */
    public final int max_depth = 5;
    public Position decideMove(GameState s) {
        PosValue bestMove = miniMax(s, false, 0);

        return bestMove.pos;
    }

    public PosValue miniMax(GameState s, boolean isMax, int depth) {
        depth++;
        ArrayList<Position> moves = s.legalMoves();

        // Return the value of the board when we finish
        if (depth == max_depth || s.isFinished()) {
            double util = utility(s);
            // System.out.println("Utility: " + util);
            return new PosValue(null, util); // Returns num of black tiles on board
        }

        PosValue currentBest;
        GameState simulatedState;

        if (isMax) {
            currentBest = new PosValue(null, Double.NEGATIVE_INFINITY);
            simulatedState = new GameState(s.getBoard(), 2);
        } else {
            currentBest = new PosValue(null, Double.POSITIVE_INFINITY);
            simulatedState = new GameState(s.getBoard(), 1);
        }

        // Finding the best move. 
        for (Position possibleMove : moves) {
            PosValue tmpValue;
            if (isMax) {
                tmpValue = miniMax(result(simulatedState, possibleMove), isMax, depth);
                if (tmpValue.val > currentBest.val) {
                    currentBest.val = tmpValue.val;
                    currentBest.pos = possibleMove;
                }
            }
            else {
                tmpValue = miniMax(result(simulatedState, possibleMove), !isMax, depth);
                if (tmpValue.val < currentBest.val) {
                    currentBest.val = tmpValue.val;
                    currentBest.pos = possibleMove;
                }
            }
        }

        return currentBest;
    }

    // This is what determines the pay off in a terminal state.
    public double utility(GameState s) {
        // Weights
        double diskDifferenceWeight = 0.5;
        double cornerWeight = 5.0;

        double blackDisks = Double.valueOf(s.countTokens()[0]);
        double whiteDisks = Double.valueOf(s.countTokens()[1]);
        
        // Disk parity
        double diskDifference = whiteDisks - blackDisks;

        // Player mobility
        // int playerValidMoves = s.legalMoves().size();
        // int opponentValidMoves = ns.legalMoves().size();
        // int mobility = playerValidMoves - opponentValidMoves;

        // Corner occupancy
        double whiteCornerPieces = cornerOccupancy(s);

        double utility = 
            diskDifference * diskDifferenceWeight +
            whiteCornerPieces * cornerWeight;

        return utility;
    }

    public double cornerOccupancy(GameState s) {
        int length = s.getBoard()[0].length - 1;

        int sum = 0;
        int leftTop = s.getBoard()[0][0];
        if (leftTop == 1) sum++;
        int rightTop = s.getBoard()[length][0];
        if (rightTop == 1) sum++;
        int rightBot = s.getBoard()[length][length];
        if (rightBot == 1) sum++;
        int leftBot = s.getBoard()[0][length];
        if (leftBot == 1) sum++;

        return Double.valueOf(sum);
    }

    // Returns new state after placing a tile at a position
    public GameState result(GameState state, Position pos) {
        GameState stateAfterPlay = state;
        if (stateAfterPlay.insertToken(pos)) {
            return stateAfterPlay;
        }

        return state;
    }

    public class PosValue {
        public Position pos;
        public double val;

        public PosValue(Position _pos, int _val) {
            this.pos = _pos;
            this.val = Double.valueOf(_val);
        }

        public PosValue(Position _pos, double _val) {
            this.pos = _pos;
            this.val = _val;
        }
    }
}
