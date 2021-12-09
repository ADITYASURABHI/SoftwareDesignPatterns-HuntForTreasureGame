package christopercolumbusfinal;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ShipTest {

	@Test
	void testGetView() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 60);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		if(!ship.getView().equals(shipImageView))
			fail("The View is not generated correctly.");
	}

	@Test
	void testGetLocation() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 30);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		if(ship.getLocation()==ship.getLocation())
			fail("position of ships are not equal.");
	}

	@Test
	void testGoWest() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		Point shipPoint = ship.getLocation();
		int newState = oceanMap.getState(shipPoint.getX()-1, shipPoint.getY());
		ship.goWest();
		if(newState==0){
			if(!ship.getLocation().equals(new Point((int)shipPoint.getX()-1, (int)shipPoint.getY()))){
				fail("The ship should move in west direction.");
			}
		}else{
			if(!ship.getLocation().equals(shipPoint)){
				fail("The ship should not move in west direction.");
			}
		}

	}

	@Test
	void testGoEast() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 40);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		Point shipPoint = ship.getLocation();
		int newState = oceanMap.getState(shipPoint.getX()+1, shipPoint.getY());
		ship.goEast();
		if(newState==0){
			if(!ship.getLocation().equals(new Point((int)shipPoint.getX()+1, (int)shipPoint.getY()))){
				fail("The ship should move in east direction.");
			}
		}else{
			if(!ship.getLocation().equals(shipPoint)){
				fail("The ship should not move in east direction.");
			}
		}
	}

	@Test
	void testGoNorth() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		Point shipPoint = ship.getLocation();
		int newState = oceanMap.getState(shipPoint.getX(), shipPoint.getY()-1);
		ship.goNorth();
		if(newState==0){
			if(!ship.getLocation().equals(new Point((int)shipPoint.getX(), (int)shipPoint.getY()-1))){
				fail("The ship should move in North direction.");
			}
		}else{
			if(!ship.getLocation().equals(shipPoint)){
				fail("The ship shouldn't move in north direction.");
			}
		}
	}

	@Test
	void testGoSouth() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		ImageView shipImageView = new ImageView();

		Ship ship = new Ship(shipImageView);
		Point shipPoint = ship.getLocation();
		int newState = oceanMap.getState(shipPoint.getX(), shipPoint.getY()+1);
		ship.goSouth();
		if(newState==0){
			if(!ship.getLocation().equals(new Point((int)shipPoint.getX(), (int)shipPoint.getY()+1))){
				fail("The ship should move in south direction.");
			}
		}else{
			if(!ship.getLocation().equals(shipPoint)){
				fail("The ship shouldn't move in south direction.");
			}
		}	}

}
