package hr.fer.seminar.utility.ai;

import java.util.ArrayList;
import java.util.List;

import hr.fer.seminar.utility.heuristic.Heuristic;

/**
 * Defines an abstraction of an AI that can be used for the game
 * <a href="https://en.wikipedia.org/wiki/Connect_Four">"Connect Four"</a>. This
 * class defines the {@link Heuristic} that should be used to calculate the best
 * move, the depth of the AI search and the width and height of the board.
 * 
 * @author Kristijan Vulinovic
 */
public abstract class AbstractAi implements Ai {
	/**
	 * The {@link Heuristic} that should be used to calculate the best move,
	 * once the AI has reached the defined depth of search.
	 */
	protected Heuristic heuristic;
	/**
	 * The maximum depth that is allowed for the search tree. Once the depth is
	 * reached, a heuristic approximation will be called, as defined by
	 * {@link #heuristic}.
	 */
	protected int depth;

	/**
	 * The width (number of columns) of the board. The default value is 7.
	 */
	protected int width = 7;
	/**
	 * The height (number of rows) of the board. The default value is 6.
	 */
	protected int height = 6;

	/**
	 * Returns the {@linkplain #depth}.
	 * 
	 * @return the depth.
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the {@linkplain #depth} to the value given in the argument.
	 * 
	 * @param depth
	 *            the new value for the depth.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Returns the {@linkplain #width}.
	 * 
	 * @return the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the {@linkplain #width} to the value given in the argument.
	 * 
	 * @param width
	 *            the new value for the width.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Returns the {@linkplain #height}.
	 * 
	 * @return the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the {@linkplain #height} to the value given in the argument.
	 * 
	 * @param height
	 *            the new value for the height.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Calculates all the possible moves for a given situation. The current
	 * position is defined by a list of all the moves played so far. A single
	 * move is represented with a number, the column where the coin was dropped.
	 * 
	 * @param moves
	 *            a list of all the moves played so far.
	 * @return a list of possible moves. Every move is the index of the column
	 *         where the coin can be dropped.
	 */
	protected List<Integer> possibleMoves(List<Integer> moves) {
		List<Integer> possible = new ArrayList<>();

		int[] count = new int[width];
		for (int move : moves) {
			count[move]++;
		}

		for (int i = 0; i < width; ++i) {
			if (count[i] < height) {
				possible.add(i);
			}
		}

		return possible;
	}

}
