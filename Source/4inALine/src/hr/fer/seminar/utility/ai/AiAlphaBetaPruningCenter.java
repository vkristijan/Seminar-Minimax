package hr.fer.seminar.utility.ai;

import java.util.ArrayList;
import java.util.List;

import hr.fer.seminar.shapes.CellState;
import hr.fer.seminar.utility.GameUtility;
import hr.fer.seminar.utility.heuristic.Heuristic;

/**
 * This implementation of the {@link Ai} uses a
 * <a href="https://en.wikipedia.org/wiki/Minimax">Minimax algorithm</a>,
 * improved by
 * <a href="https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning">Alpha-beta
 * pruning</a> in order to calculate the best move possible. After reaching a
 * certain depth of the search tree, a {@link Heuristic} algorithm will be
 * called to calculate the best move.
 * 
 * @author Kristijan Vulinovic
 */
public class AiAlphaBetaPruningCenter extends AbstractAi {
	/**
	 * Creates a new instance of this {@link Ai} algorithm. The
	 * {@link Heuristic} used in this instance is defined in the argument. The
	 * maximal depth of the search tree is also defined with an argument.
	 * 
	 * @param heuristic
	 *            the heuristic that should be used for this instance.
	 * @param depth
	 *            the maximal depth of the search tree.
	 */
	public AiAlphaBetaPruningCenter(Heuristic heuristic, int depth) {
		super();
		this.heuristic = heuristic;
		this.depth = depth;
	}

	/**
	 * Calculates the maximum possible score for the current player. In order to
	 * calculate the best move, this method will recursively calculate all the
	 * possible moves and evaluate their scores, until it reaches a certain
	 * depth in the search tree when it will just call a {@link Heuristic}. <br>
	 * This method is used to calculate the moves for the currently playing
	 * player.
	 * 
	 * @param moves
	 *            a list of all the moves played so far.
	 * @param currentPlayer
	 *            he player who should make his move.
	 * @param depth
	 *            The maximum depth that is allowed for the search tree. Once
	 *            the depth is reached, a heuristic approximation will be
	 *            called, as defined by {@link #heuristic}.
	 * @param alpha
	 *            the maximum possible score in the current situation
	 * @param beta
	 *            the minimum possible score in the current situation
	 * @return the maximum possible score that the current player can achieve in
	 *         the given situation.
	 */
	private int max(List<Integer> moves, CellState currentPlayer, int depth, int alpha, int beta) {
		if (depth > this.depth) {
			return heuristic.evaluatePosition(moves, currentPlayer);
		}

		List<Integer> possible = possibleMoves(moves);
		if (possible.size() == 0) return 0;

		int score;
		int size = possible.size();

		int bestValue = -INF;
		for (int i = 0; i < size; ++i) {
			int x = i + size / 2;
			x %= size;
			
			List<Integer> tmpMoves = moves;
			tmpMoves.add(possible.get(x));

			if (GameUtility.checkWinner(GameUtility.listToBoard(tmpMoves, width, height)) == currentPlayer) {

				score = WIN_SCORE;
			} else {
				score = min(tmpMoves, GameUtility.switchPlayer(currentPlayer), depth + 1, alpha, beta);
			}
			tmpMoves.remove(tmpMoves.size() - 1);

			if (score > bestValue) {
				bestValue = score;
			}

			if (bestValue > alpha) {
				alpha = bestValue;
			}
			if (alpha >= beta) {
				break;
			}
		}

		return bestValue;
	}

	/**
	 * Calculates the minimum possible score for the current player. In order to
	 * calculate the best move, this method will recursively calculate all the
	 * possible moves and evaluate their scores, until it reaches a certain
	 * depth in the search tree when it will just call a {@link Heuristic}. <br>
	 * This method is used to calculate the moves for the opponent.
	 * 
	 * @param moves
	 *            a list of all the moves played so far.
	 * @param currentPlayer
	 *            he player who should make his move.
	 * @param depth
	 *            The maximum depth that is allowed for the search tree. Once
	 *            the depth is reached, a heuristic approximation will be
	 *            called, as defined by {@link #heuristic}.
	 * @param alpha
	 *            the maximum possible score in the current situation
	 * @param beta
	 *            the minimum possible score in the current situation
	 * @return the minimum possible score that the current player can achieve in
	 *         the given situation.
	 */
	private int min(List<Integer> moves, CellState currentPlayer, int depth, int alpha, int beta) {
		if (depth > this.depth) {
			return heuristic.evaluatePosition(moves, currentPlayer);
		}

		List<Integer> possible = possibleMoves(moves);
		if (possible.size() == 0) return 0;

		int score;
		int size = possible.size();

		int bestValue = INF;
		for (int i = 0; i < size; ++i) {
			int x = i + size / 2;
			x %= size;
			
			List<Integer> tmpMoves = moves;
			tmpMoves.add(possible.get(x));

			if (GameUtility.checkWinner(GameUtility.listToBoard(tmpMoves, width, height)) == currentPlayer) {

				score = -WIN_SCORE;
			} else {
				score = max(tmpMoves, GameUtility.switchPlayer(currentPlayer), depth + 1, alpha, beta);
			}

			tmpMoves.remove(tmpMoves.size() - 1);

			if (score < bestValue) {
				bestValue = score;
			}

			if (bestValue < beta) {
				beta = bestValue;
			}
			if (alpha >= beta) {
				break;
			}
		}

		return bestValue;
	}

	@Override
	public int nextMove(List<Integer> moves, CellState currentPlayer) {
		int alpha = -INF;
		int beta = INF;

		List<Integer> possible = possibleMoves(moves);
		if (possible.size() == 0) {
			throw new IllegalStateException("The game is already over!");
		}

		int[] score = new int[possible.size()];

		int bestValue = -INF;
		int bestMove = -1;
		for (int i = 0; i < score.length; ++i) {
			int x = i + score.length / 2;
			x %= score.length;
			
			List<Integer> tmpMoves = new ArrayList<>(moves);
			tmpMoves.add(possible.get(x));

			if (GameUtility.checkWinner(GameUtility.listToBoard(tmpMoves, width, height)) == currentPlayer) {

				score[x] = WIN_SCORE;
			} else {
				score[x] = min(tmpMoves, GameUtility.switchPlayer(currentPlayer), 1, alpha, beta);
			}

			if (score[x] > bestValue) {
				bestValue = score[x];
				bestMove = possible.get(x);
			}

			if (bestValue > alpha) {
				alpha = bestValue;
			}
			if (alpha >= beta) {
				break;
			}
		}
		return bestMove;
	}
}
