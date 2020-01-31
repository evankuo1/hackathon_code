package gameAI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Player implements BoardObject {
	
	Color color;
	String shape;
	
	public Player() {
		color = Color.BLUE;
		shape = "circle";
	}
	
	
	public Color getColor() {
		return color;
	}
	
	public String getShape() {
		return shape;
	}
}
