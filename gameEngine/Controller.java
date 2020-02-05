package gameEngine;


import java.util.ArrayList;
import java.util.List;

import editMe.Player;

public class Controller {
	
	Model model;
	
	public Controller() {
		model = new Model();
	}
	
	public int getBoardWidth() {
		return model.getBoardWidth();
	}
	
	public int getBoardHeight() {
		return model.getBoardHeight();
	}
	
	public List<Integer> getObjPos(Object obj) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < model.getBoardWidth(); i++) {
			for (int j = 0; j < model.getBoardHeight(); j++) {
				if (model.getPosition(i, j) != null && model.getPosition(i, j).equals(obj)) {
					list.add(i);
					list.add(j);
				}
			}
		}
		return list;
	}
	
	public List<List> getObjectsWithPositions() {
		List<List> objPosList = new ArrayList<List>();
		for (int i = 0; i < model.getBoardWidth(); i++) {
			for (int j = 0; j < model.getBoardHeight(); j++) {
				if (model.getPosition(i, j) != null) {
					List anObject = new ArrayList();
					anObject.add(i);
					anObject.add(j);
					anObject.add(model.getPosition(i, j));
					objPosList.add(anObject);
				}
			}
		}
		return objPosList;
	}
	
	public boolean makeMoves() {
		List objList = model.getObjList();
		for (int i = 0; i < objList.size(); i++) {
			BoardObject anObj = (BoardObject) objList.get(i);
			Direction theMove = anObj.move();
			List<Integer> pos = getObjPos(anObj);
			if (theMove instanceof Up) {
				if (pos.get(1) - 1 >= 0) {
					return moveObject(pos.get(0), pos.get(1) - 1, anObj);
				}
			}
			
			else if (theMove instanceof Down) {
				if (pos.get(1) + 1 < model.getBoardHeight()) {
					return moveObject(pos.get(0), pos.get(1) + 1, anObj);
				}
			}
			
			else if (theMove instanceof Left) {
				if (pos.get(0) - 1 >= 0) {
					return moveObject(pos.get(0) - 1, pos.get(1), anObj);
				}
			}
			
			else if (theMove instanceof Right){
				if (pos.get(1) + 1 < model.getBoardWidth()) {
					return moveObject(pos.get(0) + 1, pos.get(1), anObj);
				}
			}
		}
		return false;
	}
	
	public void placeObject(int x, int y, Object obj) {
		model.setPosition(x, y, obj);
	}
	
	public boolean handleCollision(int x, int y, Object first, Object second) {
		if (first instanceof Food && second instanceof Player) {
			model.setPosition(x, y, second);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean moveObject(int x, int y, Object obj) {
		List<Integer> pos = getObjPos(obj);
		model.removePosition(pos.get(0), pos.get(1));
		if (model.getPosition(x, y) == null) {
			model.setPosition(x, y, obj);
			return false;
		}
		else {
			boolean winLose = handleCollision(x, y, model.getPosition(x, y), obj);
			return winLose;
		}
	}
	
	public void givePlayerView() {
		List<List> theObjs = getObjectsWithPositions();
		for (int i = 0; i < theObjs.size(); i++) {
			if (theObjs.get(i).get(2) instanceof Player) {
				((Player) theObjs.get(i).get(2)).initializePlayerView(this);
			}
		}
	}
	
	public  Controller giveObjReference() {
		return this;
	}
	
	public Object getPos(int x, int y) {
		return model.getPosition(x, y);
	}

	
}
