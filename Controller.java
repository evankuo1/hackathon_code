package gameAI;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	Model model;
	
	public Controller() {
		model = new Model();
	}
	
	int getBoardWidth() {
		return model.getBoardWidth();
	}
	
	int getBoardHeight() {
		return model.getBoardHeight();
	}
	
	List<Integer> getObjPos(Object obj) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < model.getBoardWidth(); i++) {
			for (int j = 0; j < model.getBoardHeight(); j++) {
				if (model.getPosition(i, j).equals(obj)) {
					list.add(i);
					list.add(j);
				}
			}
		}
		return list;
	}
	
	List<List> getObjectsWithPositions() {
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
	
	void placeObject(int x, int y, Object obj) {
		model.setPosition(x, y, obj);
	}
	
	void moveObject(int x, int y, Object obj) {
		
	}
	
}
