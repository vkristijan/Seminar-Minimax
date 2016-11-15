package hr.fer.seminar.utility.heuristic;

import java.util.List;

import hr.fer.seminar.shapes.CellState;

/**
 * This interface defines a method to evaluate the current position. The
 * calculation done is not perfect and does not guarantee to return the best
 * most accurate score for given position. However, the calculation process is
 * really fast (linear to the number of board fields) and can be used as a fast
 * prediction.
 * 
 * @author Kristijan Vulinovic
 */
public interface Heuristic {
	/**
	 * Evaluates the current position and returns a score that is used to
	 * predict who will win. The position is specified by a list of all the
	 * moves played so far. A single move is represented by the number of column
	 * where the coin should be dropped.
	 * 
	 * @param moves
	 *            list of all moves played so far.
	 * @param currentPlayer
	 *            the player who has to make the next move.
	 * @return an integer score evaluation of the current position. The higher
	 *         the score, the better the chances are that the current player
	 *         will win the game. The score can also be negative in case that
	 *         the current player is going to loose.
	 */
	int evaluatePosition(List<Integer> moves, CellState currentPlayer);
}
