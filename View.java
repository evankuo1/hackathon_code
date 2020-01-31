package gameAI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application {
	
	Controller controller = new Controller();
	Object[][] backgroundRectangles = new Object[controller.getWidth()][controller.getHeight()];
	
	public void drawBackground(StackPane root) {
		for (int i = 0; i < controller.getHeight(); i++) {
			for (int j = 0; j < controller.getWidth(); j++) {
				Rectangle rect = drawRectangle();
				backgroundRectangles[i][j] = rect;
				root.getChildren().add(rect);
				StackPane.setAlignment(rect, Pos.CENTER);
				rect.setTranslateX((-50 * (controller.getWidth()/2)) + (j * 50));
				rect.setTranslateY((-50 * (controller.getHeight()/2))+ (i * 50));
			}
		}
		System.out.println(3/2);
	}
	
	// dennisdangnguyen@email.arizona.edu
	
	
	public Rectangle drawRectangle() {
		Rectangle rect = new Rectangle();
		rect.setWidth(50);
		rect.setHeight(50);
		rect.setFill(Color.GREEN);
		rect.setStroke(Color.BLACK);
		return rect;
	}
	
	public void drawPlayer() {
		Rectangle rect = (Rectangle) backgroundRectangles[controller.getPlayerPos().get(0)][controller.getPlayerPos().get(1)];
	}
	 
	
	@Override
	public void start(Stage primaryStage) {
		
		// Set title
		primaryStage.setTitle("The Board");
		
		// Set up the root
		StackPane root = new StackPane();
		
		// Set up and show the scene
		primaryStage.setScene(new Scene(root, controller.getWidth() * 100, controller.getHeight() * 100));
		primaryStage.show();
		
		drawBackground(root);
	}
	
   public static void main(String[] args) {
       launch(args);
   }
}