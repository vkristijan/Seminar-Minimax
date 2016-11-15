package hr.fer.seminar;

import static hr.fer.seminar.utility.GameUtility.switchPlayer;

import hr.fer.seminar.utility.GameUtility;
import hr.fer.seminar.utility.ai.Ai;
import hr.fer.seminar.utility.ai.AiMinimax;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo extends Application{
	private static int ROW_HEIGHT = 200;
	private static int COLUMN_WIDTH = 200;
	
	private char[][] board;
	private Button[][] buttons;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		
		grid.getRowConstraints().addAll(
			new RowConstraints(ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT), 
			new RowConstraints(ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT),
			new RowConstraints(ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT));
		
		grid.getColumnConstraints().addAll(
			new ColumnConstraints(COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH), 
			new ColumnConstraints(COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH),
			new ColumnConstraints(COLUMN_WIDTH, COLUMN_WIDTH, COLUMN_WIDTH));
		
		board = GameUtility.clearBoard();
		buttons = new Button[3][3];
		
		for (int col = 0; col < 3; ++col){
			for (int row = 0; row < 3; ++row){
				int index = row * 3 + col;

				Button button = new Button();
				button.setPrefSize(COLUMN_WIDTH, ROW_HEIGHT);
				button.setFont(Font.font("San Serif" ,94));
				
				button.setOnMouseClicked((x) -> {
					play(index);
				});
				
				grid.add(button, col, row);
				buttons[row][col] = button;
			}
		}
		
		VBox root = new VBox();
		HBox hRoot = new HBox();
		Scene scene = new Scene(root);
		
		root.getChildren().add(hRoot);
		root.setAlignment(Pos.CENTER);

		hRoot.getChildren().add(grid);
		hRoot.setAlignment(Pos.CENTER);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private boolean flag = false;
	private Ai ai = new AiMinimax();
	
	private void play(int index) {
		int row = index / 3;
		int col = index % 3;
		
		if (flag) return;
		if (board[row][col] != '-'){
			return;
		}
		flag = true;
		
		buttons[row][col].setText("X");
		board[row][col] = 'X';

		if (GameUtility.checkWinner(board) == '-') {
			index = ai.nextMove(board, 'O');

			row = index / 3;
			col = index % 3;
			
			buttons[row][col].setText("O");
			board[row][col] = 'O';
		}
		
		if (GameUtility.checkWinner(board) != '-') {
			endGame();
		}
		
		flag = false;
	}

	private void endGame() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game over");
		
		
		switch (GameUtility.checkWinner(board)) {
			case 'D':
				alert.setHeaderText("DRAW");
				break;
			case 'X':
				alert.setHeaderText("You won!");
				break;
			case 'O':
				alert.setHeaderText("You lost!");
				break;
			default:
				break;
		}
		alert.showAndWait();
		
		board = GameUtility.clearBoard();
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
				buttons[i][j].setText("");
			}
		}
	}

}
