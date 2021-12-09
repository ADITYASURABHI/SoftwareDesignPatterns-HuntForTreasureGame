package christopercolumbusfinal;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoamingPirate extends PirateShip{
	
	/*
	 * PirateRoaming Constructor.
	 */
	RoamingPirate(Image shipImage, Image shipNoSailsImage, Point shipLoc){
		OceanMap oceanMap = OceanMap.getInstance(); // singleton design pattern object declaration.
		this.shipView = new ImageView(shipImage);
		this.noSailsImage = shipNoSailsImage;
		this.sailStrategy = new RoamingPirateSail();
		this.decorator = new SailingDecorator(this.sailStrategy); // Decorator pattern assignment.
		
		/* Get random point until point is valid */
		Random rand = new Random();
		x = rand.nextInt(oceanMap.getDimension());
		y = rand.nextInt(oceanMap.getDimension());
		while(oceanMap.getState(x,y) > 0 || Math.abs((int)shipLoc.getX()-x) < 3 || Math.abs((int)shipLoc.getY()-y) < 3) {
			x = rand.nextInt(oceanMap.getDimension());
			y = rand.nextInt(oceanMap.getDimension());
		}
		
		/* Set view location */
		shipView.setX(x*oceanMap.getScale());
		shipView.setY(y*oceanMap.getScale());
	}
	
	/*
	 * calls decorator.sail() to sail.
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			Ship ship = (Ship)o;
			decorator.sail(ship, this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
