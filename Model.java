package gameAI;

public class Model {
	
	Object[][] board;
	
	
	// Constructor with width and height for size of board
	public Model (int width, int height) {
		
		// Create the board
		board = new Object[width][height];
		
		// Fill it with nulls initially
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = null;
			}
		}
	}
	
	// Get the object at the given position
	Object getPosition(int x, int y) {
		return board[x][y];
	}
	
	
}
