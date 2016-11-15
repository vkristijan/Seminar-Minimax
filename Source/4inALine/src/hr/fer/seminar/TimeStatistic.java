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
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningCenter;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningMultithreaded;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningRandom;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningRandomMultithreaded;
import hr.fer.seminar.utility.ai.AiMinimax;
import hr.fer.seminar.utility.heuristic.GreedyHeuristic;
import hr.fer.seminar.utility.heuristic.RandomHeuristic;

public class TimeStatistic {
	private static final int height = 6;
	private static final int width = 7;

	private static CellState currentPlayer;
	private static List<Integer> moves;

	private static Ai player;

	public static void main(String[] args) throws IOException {
		BufferedWriter out = Files.newBufferedWriter(Paths.get("times.txt"));

		player = new AiAlphaBetaPruningRandom(new RandomHeuristic(), 6);

		int counter = 50;
		while (counter-- > 0) {
			play(out);
			out.write("\n");
			out.flush();
			System.out.println(counter);
		}
		out.close();
	}

	private static void play(BufferedWriter out) throws IOException {
		moves = new LinkedList<>();

		currentPlayer = CellState.FIRST_PLAYER;

		while (moves.size() < 42
				&& GameUtility.checkWinner(GameUtility.listToBoard(moves, width, height)) == CellState.EMPTY) {

			long start_time = System.nanoTime();

			moves.add(player.nextMove(moves, currentPlayer));
			currentPlayer = GameUtility.switchPlayer(currentPlayer);

			long end_time = System.nanoTime();
			double difference = (end_time - start_time) / 1e6;
			out.write(difference + " ");
		}
	}
}
