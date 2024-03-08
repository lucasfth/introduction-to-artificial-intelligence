# strategy

## Strategy for going for corners, edges, and center

A good strategy for Othello is to lay in the corners first and then the edges.
Also important to have pieces in the center and to then move to the corners and sides from there.

I, Lucas, therefore think it would be a good idea to call `GameState.getBoard()`, returns a two dimensional int array, so we can look at how big the board is.

## Strategy for going for captures

It is also important to lay in positions where you can capture the opponents pieces, for this we can use `GameState.captureInDirection(Position p, int deltaX, int deltaY)`, which returns an int.
