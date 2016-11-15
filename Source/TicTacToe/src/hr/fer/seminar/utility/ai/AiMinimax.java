package hr.fer.seminar.utility.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.seminar.utility.GameUtility;

/**
 * This implementation of the {@link Ai} uses a standard
 * <a href="https://en.wikipedia.org/wiki/Minimax">Minimax algorithm</a> in
 * order to calculate the best move possible. 
 * 
 * @author Kristijan Vulinovic
 */
public class AiMinimax implements Ai {

	@Override
	public int nextMove(char[][] board, char currentPlayer) {
		List<Integer> moves = GameUtility.possibleMoves(board);
		Collections.shuffle(moves);
		if (moves.size() == 0){
			throw new IllegalStateException("The game is already over!");
		}
		
		int maxScore = -INF;
		int bestMove = -1;
		
		for (int move : moves){
			int row = move / 3;
			int col = move % 3;
			
			board[row][col] = currentPlayer;
			int score = 0;
			if (GameUtility.checkWinner(board) == currentPlayer){
				score = WIN_SCORE;
			} else {
				score = min(board, GameUtility.switchPlayer(currentPlayer), 1);
			}
			
			if (score > maxScore){
				maxScore = score;
				bestMove = move;
			}
			
			board[row][col] = '-';
		}
		
		return bestMove;
	}

	private int max(char[][] board, char currentPlayer, int depth) {
		List<Integer> moves = GameUtility.possibleMoves(board);
		if (moves.size() == 0){
			return 0;
		}
		
		int maxScore = -INF;
		
		for (int move : moves){
			int row = move / 3;
			int col = move % 3;
			
			board[row][col] = currentPlayer;
			int score = 0;
			if (GameUtility.checkWinner(board) == currentPlayer){
				score = WIN_SCORE - depth;
			} else {
				score = min(board, GameUtility.switchPlayer(currentPlayer), depth + 1);
			}
			
			if (score > maxScore){
				maxScore = score;
			}
			
			board[row][col] = '-';
		}
		
		return maxScore;
	}
	
	private int min(char[][] board, char currentPlayer, int depth) {
		List<Integer> moves = GameUtility.possibleMoves(board);
		if (moves.size() == 0){
			return 0;
		}
		
		int minScore = INF;
		
		for (int move : moves){
			int row = move / 3;
			int col = move % 3;
			
			board[row][col] = currentPlayer;
			int score = 0;
			if (GameUtility.checkWinner(board) == currentPlayer){
				score = -WIN_SCORE + depth;
			} else {
				score = max(board, GameUtility.switchPlayer(currentPlayer), depth + 1);
			}
			
			if (score < minScore){
				minScore = score;
			}
			
			board[row][col] = '-';
		}
		
		return minScore;
	}
	
}
