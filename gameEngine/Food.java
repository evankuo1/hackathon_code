package gameEngine;

import javafx.scene.paint.Color;

public class Food implements BoardObject {
	
	Color color;
	String shape;

	public Food() {
		color = Color.YELLOW;
		shape = "rectangle";
	}
	
	
	public Color getColor() {
		return color;
	}
	
	public String getShape() {
		return shape;
	}
	
	public Direction move() {
		Direction theMove = new Stay();
		return theMove;
	}
	
	public String onCollision() {
		return "goal";
	}
	
	public String toString() {
		return "goal";
	}
}
