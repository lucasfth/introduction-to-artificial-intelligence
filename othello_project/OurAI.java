import java.util.ArrayList;

public class OurAI implements IOthelloAI {

   /**
	 * Returns first legal move
	 */
	public Position decideMove(GameState s){
		ArrayList<Position> moves = s.legalMoves();

      // If there are legal moves, return the first one
		if ( !moves.isEmpty() )
			return moves.get(0);
		else
			return new Position(-1,-1);
	}
   
}
