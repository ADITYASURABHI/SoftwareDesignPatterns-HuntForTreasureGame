package christopercolumbusfinal;

import java.awt.Point;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PiratessFactory {

/*
	* Randomly creates different types of pirates ship depending upon user inputs.
*/
	public PirateShip createPirate(Point shipLoc) {
		OceanMap oceanMap = OceanMap.getInstance();
		int scale = oceanMap.getScale();
		PirateShip pirateShip = null;
		Random rand = new Random();
		Image pirateShipImage = null;
		Image pirateShipImageNoSails = null;
		switch(rand.nextInt(3)){
		    case 0:
				pirateShipImage = new Image("/pirateShip.png",scale,scale,true,true);
				pirateShip = new MainPirate(pirateShipImage, pirateShipImageNoSails, shipLoc);
				break;
				
			case 1:
				pirateShipImage = new Image("/Roamingpirate.jpg",scale,scale,true,true);
				pirateShip = new RoamingPirate(pirateShipImage, pirateShipImageNoSails, shipLoc);
				break;
			case 2:
				pirateShipImage = new Image("/SpecialPirate.jpg", scale, scale, true, true);
				pirateShip = new SpecialPirate(pirateShipImage, shipLoc);
				break;
		}

		return pirateShip;
	}

}
