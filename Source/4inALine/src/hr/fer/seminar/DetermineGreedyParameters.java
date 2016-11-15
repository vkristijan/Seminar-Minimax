package hr.fer.seminar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import hr.fer.seminar.utility.ai.Ai;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningMultithreaded;
import hr.fer.seminar.utility.heuristic.GreedyHeuristic;

public class DetermineGreedyParameters {
	private static final int height = 6;
	private static final int width = 7;
	private static Random random = new Random();
	private static List<GreedyHeuristic> population;

	private static final int MAX_POPULATION = 20;
	private static final int NUMBER_OF_GENERATIONS = 100;

	public static void main(String[] args) {
		population = new ArrayList<>();

		for (int i = 0; i < MAX_POPULATION; ++i) {
			population.add(generateRandomHeuristic());
		}

		for (int i = 0; i < NUMBER_OF_GENERATIONS; ++i) {
			mutate();
			crossover();
			eliminate();

			GreedyHeuristic best = population.get(0);
			System.out.println("Pair score: " + best.getPairScore());
			System.out.println("Triple score: " + best.getTripleScore());
			
			population.forEach((x)->System.out.println(x.getPairScore() + " " + x.getTripleScore()));
			System.out.println("---");
		}
	}

	private static final int NUMBER_OF_GAMES = 20;

	private static void eliminate() {
		population.sort(new Comparator<GreedyHeuristic>() {

			@Override
			public int compare(GreedyHeuristic o1, GreedyHeuristic o2) {
				Ai player1 = new AiAlphaBetaPruningMultithreaded(o1, 5);
				Ai player2 = new AiAlphaBetaPruningMultithreaded(o2, 5);

				return -Statistics.getScore(NUMBER_OF_GAMES, player1, player2);
			}
		});

		population.subList(MAX_POPULATION, population.size()).clear();
	}

	private static void crossover() {
		// TODO Auto-generated method stub
	}

	private static final int MAX_MUTATION = 10;

	private static void mutate() {
		int n = population.size();
		for (int i = 0; i < n; ++i) {
			GreedyHeuristic heuristic = population.get(i);
			int pairScore = heuristic.getPairScore();
			int tripleScore = heuristic.getTripleScore();

			pairScore += (random.nextInt(2 * MAX_MUTATION) - MAX_MUTATION);
			tripleScore += (random.nextInt(2 * MAX_MUTATION) - MAX_MUTATION);

			heuristic = new GreedyHeuristic(width, height);
			heuristic.setPairScore(pairScore);
			heuristic.setTripleScore(tripleScore);
			population.add(heuristic);
		}
	}

	private static GreedyHeuristic generateRandomHeuristic() {
		int pairScore = 0;
		int tripleScore = 0;

		GreedyHeuristic heuristic = new GreedyHeuristic(width, height);
		heuristic.setPairScore(pairScore);
		heuristic.setTripleScore(tripleScore);
		return heuristic;
	}
}
