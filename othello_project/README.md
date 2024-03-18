# README

To run the game you need to type:

```shell
java Othello human DumAI
```

This will specifically play the game against the AI, DumAI, and you yourself will play.

To run it with our own bot, you need to type:

```shell
java Othello OurAI DumAI
```

Furthermore, you can specify the board size, by adding an int at the end of the command, like so:

```shell
java Othello OurAI DumAI 6
```

This will play the game on a 6*6 board while the default will be 8*8.

## Run statistics

To run the statistics, you need to type, where the last number specifies the number of games to be played:

```shell
java OthelloStatistic 10
```

To change which AI to play against, you need to change the code in the OthelloStatistic.java file.
