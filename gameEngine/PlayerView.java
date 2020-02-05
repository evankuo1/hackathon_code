package gameEngine;

import java.util.List;

import editMe.Player;

public class PlayerView {
	
	Controller controller;
	Player player;
	Food food;
	
	public PlayerView(Controller theController, Player thePlayer) {
		controller = theController;
		player = thePlayer;
		List<List> foodList = controller.getObjectsWithPositions();
		for (int i = 0; i < foodList.size(); i++) {
			if (foodList.get(i).get(2) instanceof Food) {
				food = (Food) foodList.get(i).get(2);
			}
		}
	}
	
	
	public int getMyX() {
		return controller.getObjPos(player).get(0);
	}
	
	public int getMyY() {
		return controller.getObjPos(player).get(1);
	}
	
	public int getFoodX() {
		return controller.getObjPos(food).get(0);
	}
	
	public int getFoodY() {
		return controller.getObjPos(food).get(1);
	}
	
	public Object getPosition(int x, int y) {
		return controller.getPos(x, y);
	}
	
}
