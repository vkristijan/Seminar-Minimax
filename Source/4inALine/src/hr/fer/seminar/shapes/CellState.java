package hr.fer.seminar.shapes;

import javafx.scene.paint.Color;

/**
 * Defines the state of a cell in the board. This also defines a player in a
 * certain move.
 * 
 * @author Kristijan Vulinovic
 *
 */
public enum CellState {
	/**
	 * Empty field in the board (no player).
	 */
	EMPTY(Color.TRANSPARENT),
	/**
	 * The first player.
	 */
	FIRST_PLAYER(Color.RED),
	/**
	 * The second player.
	 */
	SECOND_PLAYER(Color.YELLOW);

	/**
	 * The color to be used to draw the disk of the player.
	 */
	private Color color;

	/**
	 * Sets the color of the disk to the value given.
	 * 
	 * @param color
	 *            the color of the disk.
	 */
	private CellState(Color color) {
		this.color = color;
	}

	/**
	 * Returns the color that should be used to paint the disk for the player.
	 * 
	 * @return the color that should be used to paint the disk for the player.
	 */
	public Color getColor() {
		return color;
	}
}
