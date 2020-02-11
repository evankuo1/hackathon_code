package gameEngine;

import javafx.scene.paint.Color;

public interface BoardObject extends BoardAndTileObject {
	public Color getColor();
	public String getShape();
	public Direction move();
	public String onCollision();
}
