package christopercolumbusfinal;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	/***********************************************************
	 * Application Settings: * * dimension: number of cells in a row * scale: how
	 * big to make cells * numPirates: number of pirates on the board *
	 * numMonsterGroups: number of monster groups on the board *
	 ***********************************************************/
	 int dimension = 25;
	 int scale = 40;
	 
	 int whirlpool = 2;
	AnchorPane window;
	Stage stage;
	Scene scene;
	Image island;
	OceanMap oceanMap;
	Ship ship;
	PiratessFactory pirateFactory = new PiratessFactory();
	LinkedList<PirateShip> pirateShips = new LinkedList<PirateShip>();
	LinkedList<MonsterGroup> monsterGroups = new LinkedList<MonsterGroup>();

	public static void main(String[] args) {
		
		launch(args);

	}

/*
 * Start method creates the grid for the game and places the islands,treasures and whirlpool in the grid,
 * and it creates sea with blue color and grid with black. 
 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the number of Pirate Ships to be created:");
    	int numberofPirates =sc.nextInt();
    	System.out.println("Enter the number of Sea Monstergroups to be created:");
    	int numberofMonsterSets =sc.nextInt();

		/* Create grid and ship until game is winnable */
		startBeg();

		/* Generate grid view */
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK);
				if (oceanMap.getState(x, y) == 1) {
					island = new Image("/island.jpg", 50, 50, true, true);
					ImagePattern imagePattern = new ImagePattern(island);
					rect.setFill(imagePattern);
				} else if (oceanMap.getState(x, y) == 3) {
					island = new Image("/Treasure.jpg", 50, 50, true, true);
					ImagePattern imagePattern = new ImagePattern(island);
					rect.setFill(imagePattern);
				} else if (oceanMap.getState(x, y) == 4) {
					island = new Image("/whirlpool.jpg", 50, 50, true, true);
					ImagePattern imagePattern = new ImagePattern(island);
					rect.setFill(imagePattern);
				} else {
					rect.setFill(Color.PALETURQUOISE);
				}
				window.getChildren().add(rect);
			}
		}

		/*
		 * This statement places the CCShip in the top or bottom of the grid.
		 * So that the game will take more time to finish.
		 */
		window.getChildren().add(ship.getView());

		/*
		 * This part creates the number of pirates and places pirate ships in the grid
	     */
		
		for (int i = 0; i < numberofPirates; i++) {
			PirateShip pirateShip = pirateFactory.createPirate(ship.getLocation());
			window.getChildren().add(pirateShip.getView());
			pirateShips.add(pirateShip);
		}

		/*
		 * This Part generates the number of sea monsters to roam in the sea anywhere.
		 */
		for (int i = 0; i < numberofMonsterSets; i++) {
			MonsterGroup monsterGroup = new MonsterGroup();

			for (ImageView view : monsterGroup.getViews()) {
				window.getChildren().add(view);
			}
			monsterGroups.add(monsterGroup);
		}



		/* 
		 * This part adds pirate ships to observers (Design Pattern) so that at each move of CCShip the pirate ships
		 * can chase it accordingly
		 * 
		 */
		LinkedList<Observer> observers = new LinkedList<Observer>();
		for (PirateShip pirateShip : pirateShips) {
			observers.add(pirateShip);
		}
		ship.registerObservers(observers);

		startSailing(scene);
	}

	/*
	 * creates new game until game is winnable.
	 */
	private void startBeg() {
		
		// Singleton Design pattern instance
		oceanMap = OceanMap.getInstance(); 
		oceanMap.setMap(dimension, scale);
		window = new AnchorPane();
		scene = new Scene(window, dimension * scale, dimension * scale);
		stage.setScene(scene);
		stage.setTitle("Columbus Game");

		/* 
		 * Creates CCShip to play.
		 */
		Image shipImage = new Image("/ship.png", scale, scale, true, true);
		ImageView shipImageView = new ImageView(shipImage);
		ship = new Ship(shipImageView);

		/* 
		 * This concept checks whether there is a path to reach the treasure in the place
		 * where CCShip is placed. 
		 */

		boolean restart = true;
		LinkedList<Point> adj = SearchAlgorithm.getAdj(ship.getLocation());
		Point endGame = null;

		/* Finds the winning space */
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				if (oceanMap.getState(x, y) == 3) {
					endGame = new Point(x, y);
				}
			}
		}

		/* Gets the SearchAlgorithm */
		int[][] searchMap = SearchAlgorithm.getPath(ship.getLocation(), endGame);
		for (Point ad : adj) {

			/*
			 * If an adj point value is  less than Maximumvalue, then the path is possible, 
			 * no need to restart the game returns false.
			 */
			if (searchMap[(int) ad.getX()][(int) ad.getY()] < Integer.MAX_VALUE) {
				restart = false;
			}
		}

		/* 
		 * If there is no possible path exists, re-generate
		 */
		if (restart) {
			startBeg();
		} else {
			stage.show();
		}
	}

/*
 * Key handling (handler) functions is implemented in this part.(left,right,up,down)
 */
	private void startSailing(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent key) {
				Point oldposition = ship.getLocation();
				switch (key.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				Point shipposition = ship.getLocation(); // gets ships currrent location

				/*
				 * Here Whirlpool Concept is been implemented where it takes the points of whirlpool
				 * and moves the ship to next whirlpool location if it reaches a particular whirlpool.
				 */
				Point whirlPoolLoc_1 = new Point();
				Point whirlPoolLoc_2 = new Point();
				Object location1 = OceanMap.getInstance().getStateInfo().keySet().toArray()[0];
				Object locationValue1 = OceanMap.getInstance().getStateInfo().get(location1);
				whirlPoolLoc_1.setLocation(Integer.parseInt(location1.toString()),
						Integer.parseInt(locationValue1.toString()));
				Object location2 = OceanMap.getInstance().getStateInfo().keySet().toArray()[1];
				Object locationValue2 = OceanMap.getInstance().getStateInfo().get(location2);
				whirlPoolLoc_2.setLocation(Integer.parseInt(location2.toString()),
						Integer.parseInt(locationValue2.toString()));

				/* If CCship moved,
				 * checks for whirlpool location and if its in a whirlpool it moves the ship to other whirlpool
		
				 * location and checks for end of game */
				if (!oldposition.equals(shipposition)) {

					if (shipposition.equals(whirlPoolLoc_1)) {
						ship.getView().setX(whirlPoolLoc_2.getX() * scale);
						ship.getView().setY(whirlPoolLoc_2.getY() * scale);
						
						ship.setLocation(new Double(whirlPoolLoc_2.getX()).intValue(), new Double(whirlPoolLoc_2.getY()).intValue());
						//shippos = ship.getLocation();
					} else if (shipposition.equals(whirlPoolLoc_2)) {
						
						//ship.setView(whirlPoolLoc_1.getX() * scale,whirlPoolLoc_1.getY() * scale);
					    ship.getView().setX(whirlPoolLoc_1.getX() * scale);
						ship.getView().setY(whirlPoolLoc_1.getY() * scale);
						ship.setLocation(new Double(whirlPoolLoc_1.getX()).intValue(), new Double(whirlPoolLoc_1.getY()).intValue());
						
						//shippos = ship.getLocation();
					} else {
						//ship.setView(shippos.x * scale,shippos.y * scale);
						
						ship.getView().setX(shipposition.x * scale);
						ship.getView().setY(shipposition.y * scale);
					}

					checkFinish();
				}
			}
		});

	}

	/*
	 * This method checks whether the CCShip reaches the treasure or got caught in the middle by pirates 
	 * or sea monsters.
	 */
	private void checkFinish() {

		/* Check if ship is in treasure */
		if (oceanMap.getState(ship.getLocation()) == 3) {
			endGame(3);
		}

		/* 
		 * Checks whether CCship is in pirate ships location */
		for (PirateShip pirateShip : pirateShips) {
			if (ship.getLocation().equals(pirateShip.getLocation())) {
				endGame(1);
			}
		}

		/* Check if CCship is in sea monsters location */
		checkMonsters();

		/* This method is used to move the Sea monsters to a new location*/
		for (MonsterGroup monsterGroup : monsterGroups) {
			monsterGroup.move();
		}

		/* This method is used to check whether sea monsters are in CCShip location*/
		checkMonsters();
	}

/*
 * This function checks whether Sea monsters are in any ships location.
 */
	private void checkMonsters() {
		for (MonsterGroup monsterGroup : monsterGroups) {
			if (monsterGroup.hasMonster(ship.getLocation())) {
				/* If a user ship, end game */
				endGame(2);
			}
			for (PirateShip pirateShip : pirateShips) {
				if (monsterGroup.hasMonster(pirateShip.getLocation())) {
					/* If a pirate ship, change decorator */
					pirateShip.eatSails();
				}
			}
		}
	}

/*
 * Stops the game and displays the message to Stop the Game.
 */
	private void endGame(int message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Columbus Game");
		Text text = new Text("");
		switch (message) {
		case 3:
			alert.setHeaderText("You win!");
			text = new Text("\nYou successfully Found the Treasure in the Sea.");
			break;
		
		case 2:
			alert.setHeaderText("You Lose!!!");
			text = new Text(
					"\n Sea Monsters Attacked you and Destroyed your Ship.");
			break;
			
		case 1:
			alert.setHeaderText("You Lose!!!");
			text = new Text("\nA pirate ship Attacked  and Destroyed your Ship.");
			break;
		
		}
		
		text.setTextAlignment(TextAlignment.CENTER);
		text.setWrappingWidth(400);
		alert.getDialogPane().setContent(text);

		alert.showAndWait();
		System.exit(0);
	}

}
