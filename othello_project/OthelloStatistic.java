import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is a statistical tool we used for experimenting with different settings,
 * running many games without having to restart each.
 */
public class OthelloStatistic {
    public static void main(String[] args) {
        int iterations = Integer.parseInt(args[0]);

        //To disable iterative simulations from depth 1 to aiDepth, comment this for loop out
        for (int i = 0; i < iterations; i++) {
            playGames();
        }
        
        // And remove the comment below
        // playGames();
        
    }

    private static void playGames() {
        int size = 8;				        // Number of rows and columns on the board
        long elapsedTime = 0;
        long maxTime = 0;
        int laps = 0;
        IOthelloAI ai1 = new DumAI();	                    // The AI for player 1
        LucasAI ai2 = new LucasAI();			// The AI for player 2
        int numberOfGames = 1;            // Number of games to be simulated
        int blackWon = 0;                   // Counter for black wins
        int whiteWon = 0;                   // Counter for white wins
        int draws = 0;                      // Counter for draws
        int averageWhiteTokens = 0;
        int averageBlackTokens = 0;
        boolean writeToFile = false;        // Set to true if you want to write to ./statistics/Statistics.txt

        for (int i = 0; i < numberOfGames; i++) {
            GameState state = new GameState(size, 1);

            while (!state.isFinished()) {
                if (state.legalMoves().isEmpty()) state.changePlayer();
                Position move;
                if (state.getPlayerInTurn() == 1) {
                    //System.out.println("Black's turn");
                    move = ai1.decideMove(state);
                }
                else {
                    //System.out.println("White's turn");
                    move = ai2.decideMove(state);
                }
                state.insertToken(move);
            }
            int[] terminalState = state.countTokens();

            if(terminalState[0] > terminalState[1]) {
                blackWon++;
            }
            else if(terminalState[0] < terminalState[1]) {
                whiteWon++;
            }
            else draws++;

            averageWhiteTokens += terminalState[1];
            averageBlackTokens += terminalState[0];
        }
        averageWhiteTokens = averageWhiteTokens/numberOfGames;
        averageBlackTokens = averageBlackTokens/numberOfGames;


        // Handles printing and file writing
        if(!writeToFile) {
            System.out.printf("---------\nBlack won: %d\nWhite won: %d\nDraws: %d\n", blackWon,whiteWon,draws);
            System.out.printf("The average number of white tokens at the end of a game was: %d\n", averageWhiteTokens);
            System.out.printf("The average number of black tokens at the end of a game was: %d\n", averageBlackTokens);
            System.out.printf("The average time it took for a search was: %d\n", (ai2.getElapsedTime()/ai2.getLaps()));
            System.out.printf("The maximum time it took for a search was: %d\n", ai2.getMaxTime());
        }
        else {
            try {
                FileWriter writer = new FileWriter("./statistics/Statistics.txt", true);
                var bf = new BufferedWriter(writer);
                PrintWriter out = new PrintWriter(bf);
                out.print(String.format("---------\nBlack won: %d\nWhite won: %d\nDraws: %d\n", blackWon,whiteWon,draws));
                out.print(String.format("The average number of white tokens at the end of a game was: %d\n", averageWhiteTokens));
                out.print(String.format("The average number of black tokens at the end of a game was: %d\n", averageBlackTokens));
                out.printf("The average time it took for a search was: %d\n", (ai2.getElapsedTime()/ai2.getLaps()));
                out.printf("The maximum time it took for a search was: %d\n", ai2.getMaxTime());
                bf.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}