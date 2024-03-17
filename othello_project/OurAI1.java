import java.util.ArrayList;

public class OurAI1 implements IOthelloAI {
    /**
     * Returns first legal move
     */
    public final int max_depth = 6;

    public Position decideMove(GameState s) {
        PosValue bestMove;
        if (s.getPlayerInTurn() == 2) {
            bestMove = min(s, 0);
        } else {
            bestMove = max(s, 0);
        }
        return bestMove.pos;
    }


    public void alphaBetaSearhc(GameState s) {

    }

    public PosValue max(GameState s, int depth) {
        depth++;
        // printBoard(s);
        ArrayList<Position> moves = s.legalMoves();

        // Return the value of the board when we finish
        if (depth == max_depth || s.isFinished()) {
            double util = utility(s);
            System.out.println("Utility: " + util);
            return new PosValue(null, util); // Returns num of black tiles on board
        }

        PosValue currentBest = new PosValue(null, Double.NEGATIVE_INFINITY);
        GameState simulatedState = new GameState(s.getBoard(), 2);

        // Finding the best move.
        for (Position possibleMove : moves) {
            PosValue tmpValue = min(result(simulatedState, possibleMove), depth);
            if (tmpValue.val > currentBest.val) {
                currentBest.val = tmpValue.val;
                currentBest.pos = possibleMove;
            }
        }
        return currentBest;
    }

    public PosValue min(GameState s, int depth) {
        depth++;
        // printBoard(s);
        ArrayList<Position> moves = s.legalMoves();

        // Return the value of the board when we finish
        if (depth == max_depth || s.isFinished()) {
            double util = utility(s);
            System.out.println("Utility: " + util);
            return new PosValue(null, util); // Returns num of black tiles on board
        }

        PosValue currentBest = new PosValue(null, Double.POSITIVE_INFINITY);
        GameState simulatedState = new GameState(s.getBoard(), 1);

        // Finding the best move.
        for (Position possibleMove : moves) {
            PosValue tmpValue = max(result(simulatedState, possibleMove), depth);
            if (tmpValue.val > currentBest.val) {
                currentBest.val = tmpValue.val;
                currentBest.pos = possibleMove;
            }
        }
        return currentBest;
    }

    // This is what determines the pay off in a terminal state.
    public double utility(GameState s) {
        int[] res = s.countTokens();

        // Used for calculating opponents number of moves:
        GameState ns = s;
        ns.changePlayer();

        // Weights
        double diskDifferenceWeight = 0.5;
        double cornerWeight = 5.0;
        double edgeWeight = 2.5;
        double mobilityWeight = 2.0;

        // Disk parity (Difference in tokens between max and min player)
        double diskDifference = 100 * (res[0] - res[1]) / (res[0] + res[1]);

        // Mobility (Possible moves for max and min player)
        int maxMoves = s.legalMoves().size();
        int minMoves = ns.legalMoves().size();
        double mobility = getMobility(maxMoves, minMoves);

        // Corner occupancy
        double cornerPieces = cornerOccupancy(s);

        // Edge occupancy
        // double edgePieces = isEdge(s);

        double utility = diskDifference * diskDifferenceWeight +
                cornerPieces * cornerWeight +
                // edgePieces * edgeWeight +
                mobility * mobilityWeight;

        return utility;
    }

    public double getMobility(int player1, int player2) {
        double mobility;

        if (player1 + player2 != 0) {
            mobility = 100 * (player1 - player2) / (player1 + player2);
        } else {
            mobility = 0;
        }
        return Double.valueOf(mobility);
    }

    public double cornerOccupancy(GameState s) {
        int length = s.getBoard()[0].length - 1;

        int sum = 0;
        int leftTop = s.getBoard()[0][0];
        if (leftTop == 1)
            sum++;
        else
            sum--;
        int rightTop = s.getBoard()[length][0];
        if (rightTop == 1)
            sum++;
        else
            sum--;
        int rightBot = s.getBoard()[length][length];
        if (rightBot == 1)
            sum++;
        else
            sum--;
        int leftBot = s.getBoard()[0][length];
        if (leftBot == 1)
            sum++;
        else
            sum--;

        return Double.valueOf(sum);
    }

    public double isEdge(GameState s) {
        int[][] board = s.getBoard();
        int length = s.getBoard()[0].length - 1;
        int sum = 0;

        // top edge
        for (int i = 1; i < board.length; i++) {
            sum += board[0][i];
        }

        // bottom edge
        for (int i = 1; i < board.length; i++) {
            sum += board[length][i];
        }

        // left edge
        for (int i = 1; i < board.length; i++) {
            sum += board[0][i];
        }

        // right edge
        for (int i = 0; i < board.length; i++) {
            sum += board[length][i];
        }

        if (s.getPlayerInTurn() == 1) {
            return Double.valueOf(sum);
        } else {
            return Double.valueOf(-sum);

        }

    }

    // Returns new state after placing a tile at a position
    public GameState result(GameState state, Position pos) {
        GameState stateAfterPlay = state;
        if (stateAfterPlay.insertToken(pos)) {
            return stateAfterPlay;
        }

        return state;
    }

    public void printBoard(GameState s) {
        int[][] board = s.getBoard();

        for (int i = 0; i < board.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < board.length; j++) {
                if (j == board.length - 1) {
                    System.out.print(board[j][i]);
                } else {
                    System.out.print(board[j][i] + ", ");
                }
            }
            System.out.print(" ]");

            System.out.println();
        }

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
