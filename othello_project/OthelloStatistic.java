import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is a statistical tool we used for experimenting with different settings,
 * running many games without having to restart each.
 */
public class OthelloStatistic {
    public static void main(String[] args) {
        int iterations = Integer.parseInt(args[0]);
        ExecutorService executor = Executors.newFixedThreadPool(iterations);

        List<Future<String>> futures = new ArrayList<>();

        // Loading spinner variables
        AtomicBoolean isRunning = new AtomicBoolean(true);
        String loadingMessage = "Running games... ";
        String[] spinner = new String[] { "⢿", "⣻", "⣽", "⣾", "⣷", "⣯", "⣟", "⡿" };
        int spinnerLength = spinner[0].length();

        Thread spinnerThread = new Thread(() -> { // Create a new thread for the spinner
            int i = 0;
            while (isRunning.get()) {
                System.out.print("\r" + loadingMessage + spinner[i++ % spinner.length]);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Clear the loading string
            System.out.print("\r" + " ".repeat(loadingMessage.length() + spinnerLength) + "\r");
        });

        spinnerThread.start(); // Start the spinner thread

        //To disable iterative simulations from depth 1 to aiDepth, comment this for loop out
        for (int i = 0; i < iterations; i++) {
            futures.add(executor.submit(() -> OthelloStatistic.playGames()));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        isRunning.set(false); // Stop the spinner thread
        try {
            spinnerThread.join(); // Wait for the spinner thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < iterations; i++) {
            try {
                System.out.println("Game " + i + " -------------------");
                System.out.println(futures.get(i).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished all threads");
        
        // And remove the comment below
        // playGames();
        
    }

    private static String playGames() {
        int randomNum = ThreadLocalRandom.current().nextInt(-5, 5 + 1) * 1000;
        int size = 8;				        // Number of rows and columns on the board
        IOthelloAI ai1 = new DumAI();	    // The AI for player 1
        OthelloAI69 ai2 = new OthelloAI69(randomNum); // The AI for player 2
        int numberOfGames = 1;              // Number of games to be simulated
        int blackWon = 0;                   // Counter for black wins
        int whiteWon = 0;                   // Counter for white wins
        int draws = 0;                      // Counter for draws
        int averageWhiteTokens = 0;
        int averageBlackTokens = 0;

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
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Black won: %d\nWhite won: %d\nDraws: %d\n", blackWon,whiteWon,draws));
        sb.append(String.format("The average number of white tokens at the end of a game was: %d\n", averageWhiteTokens));
        sb.append(String.format("The average number of black tokens at the end of a game was: %d\n", averageBlackTokens));
        sb.append(String.format("The average time it took for a search was: %d\n", (ai2.getTotalTime()/ai2.getLaps())));
        sb.append(String.format("The maximum time it took for a search was: %d\n", ai2.getMaxTime()));


        return sb.toString();
    }
}