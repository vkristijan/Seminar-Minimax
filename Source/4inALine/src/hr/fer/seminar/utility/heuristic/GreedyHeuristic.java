package hr.fer.seminar.utility.heuristic;

import java.util.List;

import hr.fer.seminar.shapes.CellState;
import hr.fer.seminar.utility.GameUtility;

public class GreedyHeuristic implements Heuristic {
	private int pairScore = 2;
	private int tripleScore = 5;
	
	public int getPairScore() {
		return pairScore;
	}

	public void setPairScore(int pairScore) {
		this.pairScore = pairScore;
	}

	public int getTripleScore() {
		return tripleScore;
	}

	public void setTripleScore(int tripleScore) {
		this.tripleScore = tripleScore;
	}

	private int width;
	private int height;

	public GreedyHeuristic(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public int evaluatePosition(List<Integer> moves, CellState currentPlayer) {
		CellState[][] board = GameUtility.listToBoard(moves, width, height);

		int score = 0;
		score += (pairScore * numberOfPairs(board, currentPlayer));
		score += (tripleScore * numberOfTriples(board, currentPlayer));
		
		return score;
	}

	private int numberOfTriples(CellState[][] board, CellState currentPlayer) {
		int count = 0;

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				count += check(board, i, j, 3, currentPlayer);
			}
		}

		return count;
	}

	private int numberOfPairs(CellState[][] board, CellState currentPlayer) {
		int count = 0;

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				count += check(board, i, j, 2, currentPlayer);
			}
		}

		return count;
	}

	public int check(CellState[][] board, int column, int row, int length, CellState currentPlayer) {
		length++;
		if (column < 0 || column >= width) return 0;
		if (row < 0 || row >= height) return 0;

		if (board[column][row] != CellState.EMPTY) {
			return 0;
		}

		int count = 0;
		CellState otherPlayer = GameUtility.switchPlayer(currentPlayer);
		// horizontal
		for (int i = column - (length - 1); i <= column; ++i) {
			if (i < 0) continue;

			int player = 1;
			int opponent = 1;
			for (int x0 = i; x0 < i + length; ++x0) {
				if (x0 >= width || board[x0][row] == currentPlayer) {
					player++;
				}
				if (x0 >= width || board[x0][row] == otherPlayer) {
					opponent++;
				}
			}

			if (player == length) count++;
			if (opponent == length) count--;
		}

		// horizontal
		for (int i = row - (length - 1); i <= row; ++i) {
			if (i < 0) continue;

			int player = 1;
			int opponent = 1;
			for (int y0 = i; y0 < i + length; ++y0) {
				if (y0 >= height || board[column][y0] == currentPlayer) {
					player++;
				}
				if (y0 >= height || board[column][y0] == otherPlayer) {
					opponent++;
				}
			}

			if (player == length) count++;
			if (opponent == length) count--;
		}

		// main diagonal
		for (int i = -(length - 1); i <= 0; ++i) {
			int player = 1;
			int opponent = 1;

			for (int j = i; j < i + length; ++j) {
				int x0 = column + j;
				int y0 = row + j;

				if (x0 < 0 || x0 >= width) break;
				if (y0 < 0 || y0 >= height) break;

				if (board[x0][y0] == currentPlayer) {
					player++;
				}
				if (board[x0][y0] == otherPlayer) {
					opponent++;
				}
			}

			if (player == length) count++;
			if (opponent == length) count--;
		}

		// second diagonal
		for (int i = -(length - 1); i <= 0; ++i) {
			int player = 1;
			int opponent = 1;

			for (int j = i; j < i + length; ++j) {
				int x0 = column + j;
				int y0 = row - j;

				if (x0 < 0 || x0 >= width) break;
				if (y0 < 0 || y0 >= height) break;

				if (board[x0][y0] == currentPlayer) {
					player++;
				}
				if (board[x0][y0] == otherPlayer) {
					opponent++;
				}
			}

			if (player == length) count++;
			if (opponent == length) count--;
		}

		return count;
	}

}
