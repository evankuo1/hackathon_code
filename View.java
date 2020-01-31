package gameAI;

import java.util.List;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;



public class View extends Application {
	
	Controller controller = new Controller();
	
	public void drawBackground(StackPane root) {
		for (int i = 0; i < controller.getBoardHeight(); i++) {
			for (int j = 0; j < controller.getBoardWidth(); j++) {
				Rectangle rect = drawRectangle();
				root.getChildren().add(rect);
				StackPane.setAlignment(rect, Pos.CENTER);
				rect.setTranslateX((-50 * (controller.getBoardWidth()/2)) + (j * 50));
				rect.setTranslateY((-50 * (controller.getBoardHeight()/2))+ (i * 50));
			}
		}
	}
	
	public Rectangle drawRectangle() {
		Rectangle rect = new Rectangle();
		rect.setWidth(50);
		rect.setHeight(50);
		rect.setFill(Color.GREEN);
		rect.setStroke(Color.BLACK);
		return rect;
	}
	
	public Shape getObjProperties(BoardObject obj) {
		
		
		// Circle
		if (obj.getShape().equals("circle")) {
			Circle circle = new Circle();
			circle.setRadius(10);
			circle.setFill(obj.getColor());
			return circle;
		}
		
		else if (obj.getShape().equals("rectangle")) {
			Rectangle rect = new Rectangle();
			rect.setWidth(20);
			rect.setHeight(20);
			rect.setFill(obj.getColor());
			return rect;
		}
		
		
		// Default is a blue circle
		Circle circle = new Circle();
		circle.setRadius(10);
		circle.setFill(Color.BLUE);
		return circle;
	}
	
	public void drawBoard(StackPane root) {
		List<List> objList = controller.getObjectsWithPositions();
		for (int i = 0; i < objList.size(); i++) {
			List specificObj = objList.get(i);
			Shape objShape = getObjProperties((BoardObject)specificObj.get(2));
			StackPane.setAlignment(objShape, Pos.CENTER);
			objShape.setTranslateX((-50 * (controller.getBoardWidth()/2)) + ((int) specificObj.get(0) * 50));
			objShape.setTranslateY((-50 * (controller.getBoardHeight()/2))+ ((int)specificObj.get(1) * 50));
			root.getChildren().add(objShape);
		}
		
	}
	
	public void oneTurn(StackPane root) {

	}
	 
	
	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		
		// Set title
		primaryStage.setTitle("The Board");
		
		// Set up the root
		StackPane root = new StackPane();
		
		// Set up and show the scene
		primaryStage.setScene(new Scene(root, controller.getBoardWidth() * 100, controller.getBoardHeight() * 100));
		primaryStage.show();
		drawBackground(root);
		
		drawBoard(root);
	}
	
	
   public static void main(String[] args) {
       launch(args);
   }
}