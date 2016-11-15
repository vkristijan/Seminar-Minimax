package hr.fer.seminar.utility.heuristic;

import java.util.List;

import hr.fer.seminar.shapes.CellState;

/**
 * This implementation of {@link Heuristic} doesn't evaluate the board, but
 * returns 0 for every given position.
 * 
 * @author Kristijan Vulinovic
 *
 */
public class NoHeuristic implements Heuristic {

	@Override
	public int evaluatePosition(List<Integer> moves, CellState currentPlayer) {
		return 0;
	}

}
