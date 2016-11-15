package hr.fer.seminar.utility.heuristic;

import java.util.List;
import java.util.Random;

import hr.fer.seminar.shapes.CellState;
import hr.fer.seminar.utility.ai.Ai;

/**
 * This implementation of {@link Heuristic} doesn't evaluate the board, but
 * returns a random value.
 * 
 * @author Kristijan Vulinovic
 *
 */
public class RandomHeuristic implements Heuristic {
	/**
	 * Random number generator used to calculate the evaluation score.
	 */
	private static Random rnd = new Random();

	@Override
	public int evaluatePosition(List<Integer> moves, CellState currentPlayer) {
		int result = rnd.nextInt(Ai.WIN_SCORE);
		return result;
	}

}
