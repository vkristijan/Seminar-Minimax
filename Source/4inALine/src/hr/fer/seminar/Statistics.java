package hr.fer.seminar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import hr.fer.seminar.shapes.CellState;
import hr.fer.seminar.utility.GameUtility;
import hr.fer.seminar.utility.ai.Ai;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruning;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningRandom;
import hr.fer.seminar.utility.ai.AiMinimaxMultithreaded;
import hr.fer.seminar.utility.heuristic.GreedyHeuristic;
import hr.fer.seminar.utility.heuristic.Heuristic;
import hr.fer.seminar.utility.heuristic.NoHeuristic;
import hr.fer.seminar.utility.heuristic.RandomHeuristic;

public class Statistics {
	private static final int height = 6;
	private static final int width = 7;

	private static CellState currentPlayer;
	private static List<Integer> moves;

	private static Ai player1;
	private static Ai player2;

	public static int getScore(int numberOfGames, Ai player1, Ai player2) {
		int score = 0;

		Statistics.player1 = player1;
		Statistics.player2 = player2;
		
		for (int i = 0; i < numberOfGames / 2; ++i){
			int result = play();
			if (result == 1) score++; 
			if (result == 2) score--; 
		}
		
		Statistics.player1 = player2;
		Statistics.player2 = player1;
		
		for (int i = 0; i < numberOfGames / 2; ++i){
			int result = play();
			if (result == 2) score++; 
			if (result == 1) score--; 
		}
		
		return score;
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter out = Files.newBufferedWriter(Paths.get("output.txt"));

		GreedyHeuristic h1 = new GreedyHeuristic(width, height);
		h1.setPairScore(7);
		h1.setTripleScore(-3);
		
		player1 = new AiAlphaBetaPruningRandom(h1, 5);
		player2 = new AiAlphaBetaPruningRandom(h1, 5);

		int counter = 100;
		while (counter-- > 0) {
			int win = play();
			if (win == 0) out.write("draw\n");
			if (win == 1) out.write("player1\n");
			if (win == 2) out.write("player2\n");

			moves.forEach(System.out::print);
			System.out.println();
			System.out.println(counter);
		}
		out.close();
	}

	private static int play() {
		moves = new LinkedList<>();

		currentPlayer = CellState.SECOND_PLAYER;

		while (moves.size() < 42
				&& GameUtility.checkWinner(GameUtility.listToBoard(moves, width, height)) == CellState.EMPTY) {
			if (currentPlayer == CellState.FIRST_PLAYER) {
				moves.add(player1.nextMove(moves, currentPlayer));
			} else {
				moves.add(player2.nextMove(moves, currentPlayer));
			}
			currentPlayer = GameUtility.switchPlayer(currentPlayer);
		}

		CellState winner = GameUtility.checkWinner(GameUtility.listToBoard(moves, width, height));

		switch (winner) {
			case EMPTY:
				return 0;
			case FIRST_PLAYER:
				return 1;
			case SECOND_PLAYER:
				return 2;
			default:
				return -1;
		}
	}

}
