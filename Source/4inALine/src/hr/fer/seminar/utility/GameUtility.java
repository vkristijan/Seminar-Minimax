package hr.fer.seminar.utility;

import java.util.List;

import hr.fer.seminar.shapes.CellState;

public class GameUtility {
	public static CellState checkWinner(CellState[][] board) {
		int width = board.length;
		int height = board[0].length;

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				CellState winner = checkWinner(board, i, j);
				if (winner != CellState.EMPTY) {
					return winner;
				}
			}
		}
		return CellState.EMPTY;
	}

	public static CellState checkWinner(CellState[][] board, int column, int row) {
		int width = board.length;
		int height = board[0].length;

		if (column < 0 || column >= width) return CellState.EMPTY;
		if (row < 0 || row >= height) return CellState.EMPTY;

		if (board[column][row] == CellState.EMPTY) {
			return CellState.EMPTY;
		}

		// horizontal
		for (int i = column - 3; i <= column; ++i) {
			if (i < 0) continue;

			boolean won = true;
			for (int x0 = i; x0 < i + 4; ++x0) {
				if (x0 >= width || board[x0][row] != board[column][row]) {
					won = false;
					break;
				}
			}
			if (won) return board[column][row];
		}

		// horizontal
		for (int i = row - 3; i <= row; ++i) {
			if (i < 0) continue;

			boolean won = true;
			for (int y0 = i; y0 < i + 4; ++y0) {
				if (y0 >= height || board[column][y0] != board[column][row]) {
					won = false;
					break;
				}
			}
			if (won) return board[column][row];
		}

		// main diagonal
		for (int i = -3; i <= 0; ++i) {
			boolean won = true;
			for (int j = i; j < i + 4; ++j) {
				int x0 = column + j;
				int y0 = row + j;

				if (x0 < 0 || x0 >= width) won = false;
				if (y0 < 0 || y0 >= height) won = false;

				if (!won || board[x0][y0] != board[column][row]) {
					won = false;
					break;
				}
			}
			if (won) return board[column][row];
		}

		// second diagonal
		for (int i = -3; i <= 0; ++i) {
			boolean won = true;
			for (int j = i; j < i + 4; ++j) {
				int x0 = column + j;
				int y0 = row - j;

				if (x0 < 0 || x0 >= width) won = false;
				if (y0 < 0 || y0 >= height) won = false;

				if (!won || board[x0][y0] != board[column][row]) {
					won = false;
					break;
				}
			}
			if (won) return board[column][row];
		}

		return CellState.EMPTY;
	}

	public static CellState[][] listToBoard(List<Integer> moves, int width, int height) {
		CellState[][] board;
		board = new CellState[width][height];
		board = resetBoard(board);

		int[] positions = new int[width];
		for (int i = 0; i < width; ++i) {
			positions[i] = height - 1;
		}

		CellState currentPlayer = CellState.FIRST_PLAYER;

		for (int column : moves) {
			int row = positions[column];
			if (row < 0) return null;

			board[column][row] = currentPlayer;
			positions[column]--;

			currentPlayer = switchPlayer(currentPlayer);
		}

		return board;
	}

	private static CellState[][] resetBoard(CellState[][] board) {
		int width = board.length;
		int height = board[0].length;

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				board[i][j] = CellState.EMPTY;
			}
		}

		return board;
	}

	public static CellState switchPlayer(CellState currentPlayer) {
		if (currentPlayer == CellState.FIRST_PLAYER) {
			return CellState.SECOND_PLAYER;
		} else {
			return CellState.FIRST_PLAYER;
		}
	}
}
