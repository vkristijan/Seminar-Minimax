package hr.fer.seminar.utility.ai;

import java.util.List;


/**
 * This interface defines a method to calculate next move for a given player in
 * a certain position. <br>
 * The interface also defines 2 constants, {@linkplain #INF} and
 * {@linkplain #WIN_SCORE}
 * 
 * @author Kristijan Vulinovic
 */
public interface Ai {
	/**
	 * The maximum value that can be given as a score to a move.
	 */
	final int INF = 100000000;
	/**
	 * The score that a player gets once he wins.
	 */
	final int WIN_SCORE = INF - 5;

	/**
	 * Calculates the next move that should be played in the current position.
	 * The player who should make the move is defined in the argument. The
	 * current position is defined with a matrix, where every field of the
	 * matrix can be a character 'o' or 'x', or empty if the cell is empty.
	 * 
	 * @param board
	 *            a board holding the characters that are played in every field.
	 * @param currentPlayer
	 *            the player who should make his move.
	 * @return an integer representation of the move that should be played. The
	 *         number stands for the column where the coin should be dropped.
	 */
	int nextMove(char[][] board, char currentPlayer);
}
