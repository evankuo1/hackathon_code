package editMe;



import java.util.ArrayList;
import java.util.List;

import gameEngine.BoardObject;
import gameEngine.Controller;
import gameEngine.NothingSpace;
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
	
	// Creates controller and shapes list
	Controller controller = new Controller();
	List shapes = new ArrayList();
	
	// Determines whether user has won or lost
	boolean gotVictoryOrLoss = false;
	
	
	
	
	
	
	
	// Draws the background. Currently just green rectangles of size 50x50
	public void drawBackground(StackPane root) {
		
		
		// For every x
		for (int i = 0; i < controller.getBoardHeight(); i++) {
			
			// For every y
			for (int j = 0; j < controller.getBoardWidth(); j++) {
				
				if (!(controller.getPos(j, i) instanceof NothingSpace)) {
					
					// Get a background rectangle
					Rectangle rect = drawBackgroundRectangle();
					
					// Add the rect to the root
					root.getChildren().add(rect);
					
					// Position it
					StackPane.setAlignment(rect, Pos.CENTER);
					rect.setTranslateX((-50 * (controller.getBoardWidth()/2)) + (j * 50));
					rect.setTranslateY((-50 * (controller.getBoardHeight()/2))+ (i * 50));
					
				}
			}
		}
	}
	
	
	
	
	
	
	// Returns a green rectangle of size 50x50
	public Rectangle drawBackgroundRectangle() {
		Rectangle rect = new Rectangle();
		rect.setWidth(50);
		rect.setHeight(50);
		rect.setFill(Color.GREEN);
		rect.setStroke(Color.BLACK);
		return rect;
	}
	
	
	
	
	
	// Gets the visual representation of a BoardObject and then
	// returns an object based on that. Currently supports circles
	// and rectangles and any color
	public Shape getObjProperties(BoardObject obj) {
		
		// Circle
		if (obj.getShape().equals("circle")) {
			Circle circle = new Circle();
			circle.setRadius(10);
			circle.setFill(obj.getColor());
			return circle;
		}
		
		// Rectangle
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
	
	
	
	
	
	
	
	// Draws the BoardObjects onto the view board.
	public void drawBoard(StackPane root) {
		
		// Gets the objects with their positions
		List<List> objList = controller.getObjectsWithPositions();
		
		// Loop through every object
		for (int i = 0; i < objList.size(); i++) {
			
			// Get the object
			List specificObj = objList.get(i);
			
			// Get the visual representation of the object
			Shape objShape = getObjProperties((BoardObject)specificObj.get(2));
			
			// Add this new object shape to the shape list
			shapes.add(objShape);
			
			// Set the alignment of the shape based on its position on the board
			StackPane.setAlignment(objShape, Pos.CENTER);
			objShape.setTranslateX((-50 * (controller.getBoardWidth()/2)) + ((int) specificObj.get(0) * 50));
			objShape.setTranslateY((-50 * (controller.getBoardHeight()/2))+ ((int)specificObj.get(1) * 50));
			
			// Add the shape to the board
			root.getChildren().add(objShape);
		}
	}
	
	
	
	
	
	
	// Erases all of the Board Object representations from the board
	public void wipeBoard(StackPane root) {
		
		// For every shape
		for (int i = 0; i < shapes.size(); i++) {
			// Remove it from the board
			root.getChildren().remove(shapes.get(i));
		}
		
		// Reset the shapes list to be an empty list
		shapes = new ArrayList();
	}
	
	
	
	
	
	
	// Draws the victory text onto the screen
	public void drawVictory(StackPane root) {
		Text text = new Text();
		text.setText("We Won!");
		StackPane.setAlignment(text, Pos.TOP_CENTER);
		text.setTranslateY(30);
		root.getChildren().add(text);
	}
	
	
	
	
	
	
	
	
	
	// Draws the defeat text onto the screen
	public void drawDefeat(StackPane root) {
		Text text = new Text();
		text.setText("We were defeated! :(");
		StackPane.setAlignment(text, Pos.TOP_CENTER);
		text.setTranslateY(30);
		root.getChildren().add(text);
	}
	
	
	
	
	
	
	
	
	// Executes one turn in the game
	public void oneTurn(StackPane root) {
		// If victory or defeat hasn't been achieved yet
		if (!gotVictoryOrLoss) {
			
			// Make the moves
			controller.makeMoves();
			
			// Wipe the board
			wipeBoard(root);
			
			// Draw the board
			drawBoard(root);
			
			// If we won
			if (controller.winOrLoss().equals("win")) {
				drawVictory(root);
				gotVictoryOrLoss = true;
			}
			
			// If we lost
			else if (controller.winOrLoss().equals("loss")) {
				drawDefeat(root);
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
		
		// Set up a timeline to play the moves
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> oneTurn(root)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	
	
	
	
	
   public static void main(String[] args) {
	   // Launch javafx
       launch(args);
   }
}