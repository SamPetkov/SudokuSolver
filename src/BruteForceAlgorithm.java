/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author petko
 */
public class BruteForceAlgorithm {
	private int[][] board;
	private int[][] copy;
	
	private final int delay = 0;
	
	BruteForceAlgorithm(int[][] board){
		this.board = board;
		copy = new int[board.length][board.length];
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				copy[i][j] = board[i][j];
			}
		}
	}

	public void solve(){
		boolean backtrack = false;
		for(int row = 0; row<9; row++){
			for(int col = 0; col <9; col ++){
				
				if(board[row][col] == copy[row][col] && !backtrack && board[row][col] != 0){
					continue ;
				}
				
				if(board[row][col] == 0 || board[row][col] != copy[row][col]){
					if(!setNumber(row, col)){
						backtrack = true;
						board[row][col] = 0;
						GUI.buttons[row][col].setText("");
						GUI.buttons[row][col].validate();
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						col -= 2;
						if(col == -2){
							col = 7;
							row --;
						}
						
					}else{
						backtrack = false;
					}
				}else if(backtrack){
					col-= 2;
					if(col == -2){
						col = 7;
						row --;
					}
				}
			}
		}
		
	}
	private boolean setNumber(int row, int col){
		int counter = board[row][col];
		
		for(int i = counter + 1 ; i <= 9; i++){
			if(checkNumber(row, col, i)){
				board[row][col] = i;
				GUI.buttons[row][col].setText(i+"");
				GUI.buttons[row][col].validate();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean checkNumber(int row, int col, int number){
		int BoxRow = (row / 3) * 3;
		int Boxcol = (col / 3) * 3;
		
		for(int r = 0; r<3; r++){
			for(int c = 0; c<3; c++){
				if(board[BoxRow + r][Boxcol + c] == number){
					return false;
				}
			}
		}
		
		for(int i = 0; i< 9; i++){	
			if(board[row][i] == number || board[i][col] == number){
				return false;
			}
		}
		return true;
	}

}  

