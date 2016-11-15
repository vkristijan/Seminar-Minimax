package hr.fer.seminar.utility;

import java.util.ArrayList;
import java.util.List;

public class GameUtility {
	public static char checkWinner(char[][] board) {
		if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') return 'O';
	    if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') return 'O';
	    if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') return 'O';
	    if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') return 'O';
	    if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') return 'O';
	    if (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') return 'O';
	    if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') return 'O';
	    if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') return 'O';
	    
	    if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') return 'X';
	    if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') return 'X';
	    if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') return 'X';
	    if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') return 'X';
	    if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') return 'X';
	    if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') return 'X';
	    if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') return 'X';
	    if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') return 'X';
	    
	    if (possibleMoves(board).size() == 0) return 'D';
	    
	    return '-';
	}

	public static char switchPlayer(char currentPlayer) {
		if (currentPlayer == 'X') {
			return 'O';
		} else {
			return 'X';
		}
	}

	public static List<Integer> possibleMoves(char[][] board) {
		List<Integer> possibleMoves = new ArrayList<>();
		
		if (board[0][0] == '-') possibleMoves.add(0);
		if (board[0][1] == '-') possibleMoves.add(1);
		if (board[0][2] == '-') possibleMoves.add(2);
		
		if (board[1][0] == '-') possibleMoves.add(3);
		if (board[1][1] == '-') possibleMoves.add(4);
		if (board[1][2] == '-') possibleMoves.add(5);
		
		if (board[2][0] == '-') possibleMoves.add(6);
		if (board[2][1] == '-') possibleMoves.add(7);
		if (board[2][2] == '-') possibleMoves.add(8);
		
		return possibleMoves;
	}
	
	public static char[][] clearBoard(){
		char[][] board = new char[3][3];
		
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
				board[i][j] = '-';
			}
		}
		
		return board;
	}
}
