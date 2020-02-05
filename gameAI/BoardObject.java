package gameAI;



import java.util.List;

import javafx.scene.paint.Color;

interface BoardObject {
	public Color getColor();
	public String getShape();
	public Direction move();
}
