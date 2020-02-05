package gameEngine;



import java.util.List;

import javafx.scene.paint.Color;

public interface BoardObject {
	public Color getColor();
	public String getShape();
	public Direction move();
}
