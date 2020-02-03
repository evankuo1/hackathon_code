package gameAI;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	InitialBoard initialBoard = new InitialBoard();
	Object[][] board;
	int width;
	int height;
	List objList = new ArrayList();
	
	// Constructor with width and height for size of board
	public Model () {
		
		width = initialBoard.getWidth();
		height = initialBoard.getHeight();
		
		// Create the board
		board = new Object[width][height];
		
		// Fill it with nulls initially
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = null;
			}
		}
		
		
		List<ArrayList> initialObjList = initialBoard.getObjList();
		for (int i = 0; i < initialObjList.size(); i++) {
			objList.add(initialObjList.get(i).get(2));
			ArrayList aList = initialObjList.get(i);
			board[(int) aList.get(0)][(int) aList.get(1)] = aList.get(2);
		}
	}
	
	// Get the object at the given position
	Object getPosition(int x, int y) {
		return board[x][y];
	}
	
	void setPosition(int x, int y, Object obj) {
		board[x][y] = obj;
	}
	
	void removePosition(int x, int y) {
		board[x][y] = null;
	}
	
	int getBoardWidth() {
		return width;
	}
	
	int getBoardHeight() {
		return height;
	}
	
	List getObjList() {
		return objList;
	}
	
}
