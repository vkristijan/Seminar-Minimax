package hr.fer.seminar;

import static hr.fer.seminar.utility.GameUtility.switchPlayer;

import java.util.LinkedList;
import java.util.List;

import hr.fer.seminar.shapes.CellState;
import hr.fer.seminar.shapes.GridField;
import hr.fer.seminar.utility.GameUtility;
import hr.fer.seminar.utility.ai.Ai;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruning;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningMultithreaded;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningRandom;
import hr.fer.seminar.utility.ai.AiAlphaBetaPruningRandomMultithreaded;
import hr.fer.seminar.utility.heuristic.NoHeuristic;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComparisonDemo extends Application {
	private final int height = 6;
	private final int width = 7;

	private CellState[][] board;
	private GridField[][] gridCell;
	private CellState currentPlayer;
	private List<Integer> moves;
	
	private Ai ai = new AiAlphaBetaPruningRandom(new NoHeuristic(), 7);
	private Ai ai2 = new AiAlphaBetaPruningRandomMultithreaded(new NoHeuristic(), 7);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		HBox hRoot = new HBox();
		GridPane grid = new GridPane();
		moves = new LinkedList<>();

		board = GameUtility.listToBoard(moves, width, height);
		currentPlayer = CellState.FIRST_PLAYER;

		grid.getRowConstraints().addAll(new RowConstraints(100, 100, 100), new RowConstraints(100, 100, 100),
			new RowConstraints(100, 100, 100), new RowConstraints(100, 100, 100), new RowConstraints(100, 100, 100),
			new RowConstraints(100, 100, 100));
		grid.getColumnConstraints().addAll(new ColumnConstraints(100, 100, 100), new ColumnConstraints(100, 100, 100),
			new ColumnConstraints(100, 100, 100), new ColumnConstraints(100, 100, 100),
			new ColumnConstraints(100, 100, 100), new ColumnConstraints(100, 100, 100),
			new ColumnConstraints(100, 100, 100));

		gridCell = new GridField[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				int column = i;
				GridField newField = new GridField();
				newField.setOnMouseClicked((e) -> {
					dropCoin(column);
				});

				gridCell[i][j] = newField;
				grid.add(gridCell[i][j], i, j);
			}
		}

		Scene scene = new Scene(root, 1000, 600);

		root.getChildren().add(hRoot);
		root.setAlignment(Pos.CENTER);

		hRoot.getChildren().add(grid);
		hRoot.setAlignment(Pos.CENTER);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void dropCoin(int column) {
		if (board[column][0] != CellState.EMPTY) return;
		moves.add(column);
		
		currentPlayer = switchPlayer(currentPlayer);
		int next = ai.nextMove(moves, currentPlayer);
		if (next != ai2.nextMove(moves, currentPlayer)){
			System.err.println("Algorithm 1 returned move: " + next);
			System.err.println("Algorithm 2 returned move: " + ai2.nextMove(moves, currentPlayer));
		}
		moves.add(next);

		board = GameUtility.listToBoard(moves, width, height);

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				gridCell[i][j].setColor(board[i][j].getColor());
			}
		}
		
		currentPlayer = switchPlayer(currentPlayer);
		
		if (moves.size() == 42 || GameUtility.checkWinner(board) != CellState.EMPTY){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Game over");
		    
		    switch (GameUtility.checkWinner(board)) {
				case EMPTY:
					alert.setHeaderText("DRAW");
					break;
				case FIRST_PLAYER:
					alert.setHeaderText("You won!");
					break;
				case SECOND_PLAYER:
					alert.setHeaderText("You lost!");
					break;
				default:
					break;
			}
		    alert.showAndWait();
		}
	}
}
