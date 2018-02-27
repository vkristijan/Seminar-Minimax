# Seminar Minimax

Source code and documentation for a seminar about Minimax algorithm. The documentation explains the Minimax algorithm, using the game Tic-Tac-Toe as an example. Alpha-Beta pruning is introduced as an optimization of the main algorithm, that still finds the optimal solution. The described algorithms are implemented in the game "4 in a row". Because of a large number of possible game states, a heuristic approximation is used to improve calculation speed.

## Minimax
Minimax algorithm is used to determine the best possible move in a zero-sum game. In order to do so, it tries every possible move for the current player and calculates the expected result for that move. After that, it simply chooses the move with the highest score. Because of that, this step is called the max step.
To calculate the expected score for a given move, the algorithm checks every possible opponent move, with the assumption that the opponent plays optimally. After that, it chooses the move with the least score (meaning that it is the highest score for the opponent). This step is called the min step. 
Those two steps are repeated until a final game state is reached which can be seen in the image below.

<img src="Documentation/Samples/tttExample.png" width="250">

## Alpha-Beta pruning

## 4 in a row
