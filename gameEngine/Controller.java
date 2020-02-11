// Package declaration
package gameEngine;

// Imports
import java.util.ArrayList;
import java.util.List;

import editMe.Player;


public class Controller {

	// Construct the model
	Model model;
	
	// Indicates whether player has won/lost
	WinLoss winLoss = null;
	
	
	// Constructor. Simply create the model
	public Controller() {
		model = new Model();
	}
	
	// Returns the width of the board as an int
	public int getBoardWidth() {
		return model.getBoardWidth();
	}
	
	// Returns the height of the board as an int
	public int getBoardHeight() {
		return model.getBoardHeight();
	}
	
	public String winOrLoss() {
		
		if (winLoss instanceof Win) {
			return "win";
		}
		
		else if (winLoss instanceof Loss) {
			return "loss";
		}
		
		else {
			return "neither";
		}
	}
	
	// Gets the position of the given object. It returns the position
	// as an arraylist where the 1st index is the x and the second is the y.
	public List<Integer> getObjPos(Object obj) {
		
		// Arraylist to hold x and y
		List<Integer> list = new ArrayList<Integer>();
		
		// For every x in the board
		for (int i = 0; i < model.getBoardWidth(); i++) {
			// For every y in the board
			for (int j = 0; j < model.getBoardHeight(); j++) {
				// If the x,y position matches the object, add it to the list
				if (model.getPosition(i, j) != null && model.getPosition(i, j).equals(obj)) {
					list.add(i);
					list.add(j);
				}
			}
		}
		
		// Return the list. Should only have 1 object in it
		return list;
	}
	
	// Gets the positions of all of the objects in the board.
	// Will return a list of lists where every inner list
	// has the following format: index 0 is x, index 1 is y,
	// and index 2 is the object at that position
	public List<List> getObjectsWithPositions() {
		
		// List of lists with objects.
		List<List> objPosList = new ArrayList<List>();
		
		// For every x
		for (int i = 0; i < model.getBoardWidth(); i++) {
			// For every y
			for (int j = 0; j < model.getBoardHeight(); j++) {
				// If there's an object at that position
				if (model.getPosition(i, j) != null && !(model.getPosition(i, j) instanceof NothingSpace)) {
					List anObject = new ArrayList();		// Make a new list
					anObject.add(i);						// x
					anObject.add(j);						// y
					anObject.add(model.getPosition(i, j));	// object
					objPosList.add(anObject); 				// Add this list to the overall list
				}
			}
		}
		
		// Return list of lists
		return objPosList;
	}
	
	// Loops through all of the objects and calls their move() to get them to move.
	public void makeMoves() {
		
		// Get the objects list
		List objList = model.getObjList();
		
		// For every object
		for (int i = 0; i < objList.size(); i++) {
			
			// Get the object and get its move/position
			BoardObject anObj = (BoardObject) objList.get(i);
			Direction theMove = anObj.move();
			List<Integer> pos = getObjPos(anObj);
			
			// If the object moved up
			if (theMove instanceof Up) {
				// Check if it can move up
				if (pos.get(1) - 1 >= 0) {
					// Move the object
					moveObject(pos.get(0), pos.get(1) - 1, anObj);
				}
			}
			
			// If the object moved down
			else if (theMove instanceof Down) {
				// Check if it can move down
				if (pos.get(1) + 1 < model.getBoardHeight()) {
					// Move the object
					moveObject(pos.get(0), pos.get(1) + 1, anObj);
				}
			}
			
			// If the object moved left
			else if (theMove instanceof Left) {
				// Check if it can move left
				if (pos.get(0) - 1 >= 0) {
					// Move the object
					moveObject(pos.get(0) - 1, pos.get(1), anObj);
				}
			}
			
			// IF the object moved right
			else if (theMove instanceof Right){
				// Check if it can move right
				if (pos.get(1) + 1 < model.getBoardWidth()) {
					// Move the object
					moveObject(pos.get(0) + 1, pos.get(1), anObj);
				}
			}
		}
	}
	
	// Places the object at the given position. Erases previous object
	// at that position if there was one
	public void placeObject(int x, int y, Object obj) {
		model.setPosition(x, y, obj);
	}
	
	public void handleCollision(int x1, int y1, int x2, int y2, int x3, int y3, BoardAndTileObject first, BoardAndTileObject second) {
		
		String fC = first.onCollision();
		String sC = second.onCollision();
		
		if (fC.equals("player") && sC.equals("goal")) {
			model.setPosition(x3, y3, first);
			winLoss = new Win();
		}
		
		else if (fC.equals("goal") && sC.equals("player")) {
			model.setPosition(x3, y3, second);
			winLoss = new Win();
		}
		
		else if (fC.equals("player") && sC.equals("nothingSpace")) {
			model.setPosition(x1, y1, first);
			model.setPosition(x3, y3, second);
		}
		
		else if (fC.equals("nothingSpace") && sC.equals("player")) {
			model.setPosition(x2, y2, second);
			model.setPosition(x3, y3, first);
		}
		
		else if (fC.equals("player") && sC.equals("enemy")) {
			model.setPosition(x3, y3, second);
			winLoss = new Loss();
		}
		
		else if (fC.equals("enemy") && sC.equals("player")) {
			model.setPosition(x3, y3, first);
			winLoss = new Loss();
		}
	}
	
	// Moves the given object to the given position. It moves the object,
	// meaning that it also removes the object from its previous position
	public void moveObject(int x, int y, Object obj) {
		
		// Get the object
		List<Integer> pos = getObjPos(obj);
		
		// Remove the object from its previous position
		model.removePosition(pos.get(0), pos.get(1));
		
		// If the new position is null, then we can place it there
		if (model.getPosition(x, y) == null) {
			model.setPosition(x, y, obj);
		}
		
		// If the new position was instead filled, then a collision has occurred
		else {
			handleCollision(pos.get(0), pos.get(1), x, y, x, y, (BoardAndTileObject) obj,  (BoardAndTileObject) model.getPosition(x, y));
		}
	}
	
	// Gives an instance of the PlayerView class to the player object.
	// This allows the player to "see" the board and make decisions based on what they see.
	public void givePlayerView() {
		
		// Gets a list of the objects
		List<List> theObjs = getObjectsWithPositions();
		
		// Loop through the objects
		for (int i = 0; i < theObjs.size(); i++) {
			// If that object is the player
			if (theObjs.get(i).get(2) instanceof Player) {
				// Give the player a playerview
				((Player) theObjs.get(i).get(2)).initializePlayerView(this);
			}
		}
	}
	
	// Returns an instance of this
	public  Controller giveObjReference() {
		return this;
	}
	
	// Gets the object/null at a given position
	public Object getPos(int x, int y) {
		return model.getPosition(x, y);
	}

	
}
