package editMe;



import java.util.ArrayList;
import java.util.List;

import gameEngine.BoardObject;
import gameEngine.Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;




public class View extends Application {
	
	Controller controller = new Controller();
	List shapes = new ArrayList();
	
	boolean gotVictoryOrLoss = false;
	
	public void drawBackground(StackPane root) {
		for (int i = 0; i < controller.getBoardHeight(); i++) {
			for (int j = 0; j < controller.getBoardWidth(); j++) {
				Rectangle rect = drawBackgroundRectangle();
				root.getChildren().add(rect);
				StackPane.setAlignment(rect, Pos.CENTER);
				rect.setTranslateX((-50 * (controller.getBoardWidth()/2)) + (j * 50));
				rect.setTranslateY((-50 * (controller.getBoardHeight()/2))+ (i * 50));
			}
		}
	}
	
	public Rectangle drawBackgroundRectangle() {
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
			shapes.add(objShape);
			StackPane.setAlignment(objShape, Pos.CENTER);
			objShape.setTranslateX((-50 * (controller.getBoardWidth()/2)) + ((int) specificObj.get(0) * 50));
			objShape.setTranslateY((-50 * (controller.getBoardHeight()/2))+ ((int)specificObj.get(1) * 50));
			root.getChildren().add(objShape);
		}
	}
	
	public void wipeBoard(StackPane root) {
		for (int i = 0; i < shapes.size(); i++) {
			root.getChildren().remove(shapes.get(i));
		}
		shapes = new ArrayList();
	}
	
	public void drawVictory(StackPane root) {
		Text text = new Text();
		text.setText("You Won!");
		StackPane.setAlignment(text, Pos.TOP_CENTER);
		text.setTranslateY(30);
		root.getChildren().add(text);
	}
	 
	
	public void oneTurn(StackPane root) {
		if (!gotVictoryOrLoss) {
			boolean winLose = controller.makeMoves();
			wipeBoard(root);
			drawBoard(root);
			if (winLose == true) {
				drawVictory(root);
				gotVictoryOrLoss = true;
			}
		}
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
		
		// Draw background
		drawBackground(root);

		// Draw the initial board state
		drawBoard(root);
		
		// Give the player the view
		controller.givePlayerView();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> oneTurn(root)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	
   public static void main(String[] args) {
       launch(args);
   }
}