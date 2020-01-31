package gameAI;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	
	int width;
	int height;
	Model model;
	
	public Controller() {
		width = 5;
		height = 5;
		model = new Model(width, height);
	}
	
	int getWidth() {
		return width;
	}
	
	int getHeight() {
		return height;
	}
	
	List<Integer> getPlayerPos() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (model.getPosition(width, height) instanceof Player) {
					list.add(i);
					list.add(j);
				}
			}
		}
		return list;
	}
	
}
