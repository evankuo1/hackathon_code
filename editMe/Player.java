package editMe;


import gameEngine.BoardObject;
import gameEngine.Controller;
import gameEngine.Direction;
import gameEngine.Down;
import gameEngine.Left;
import gameEngine.PlayerView;
import gameEngine.Right;
import gameEngine.Stay;
import gameEngine.Up;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

// The class. Don't touch this either!
public class Player implements BoardObject {
	
	// Don't touch these!
	Color color = Color.BLUE;
	String shape = "circle";
	PlayerView info;
	
	
	// Getter. Don't touch this!
	public Color getColor() {
		return color;
	}
	
	// Getter. Don't touch this!
	public String getShape() {
		return shape;
	}
	
	// Creates the view. Don't touch this!
	public void initializePlayerView(Controller controller) {
		info = new PlayerView(controller, this);
	}
	
	
	
	
	// WRITE EVERYTHING BELOW HERE
	
	// memory section. If you want to write variables that the player will remember,
	// write them here.
	
	
	// Write this!
	public Direction move() {
		Direction direction;
		if (info.getFoodX() < info.getMyX()) {
			direction = new Left();
		}
		else if (info.getFoodX() > info.getMyX()) {
			direction = new Right();
		}
		else if (info.getFoodY() < info.getMyY()) {
			direction = new Up();
		}
		else if (info.getFoodY() > info.getMyY()) {
			direction = new Down();
		}
		else {
			direction = new Stay();
		}
		return direction;
	}
}

