package gameEngine;

import javafx.scene.paint.Color;

public class Enemy implements BoardObject {
	
	Color color;
	String shape;

	public Enemy() {
		color = Color.RED;
		shape = "circle";
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
		return "enemy";
	}
	
	public String toString() {
		return "enemy";
	}

}
