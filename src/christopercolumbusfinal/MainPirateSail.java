package christopercolumbusfinal;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/*
 * Here we created a class MainPirateSail where we implemented an algorithm to chase or search
 * CC Ship in the grid.
 */
public class MainPirateSail implements SailStrategy{
	
	
	@Override
	public void sail(Ship ship, PirateShip pirateShip) {
		Point location = ship.getLocation();
		Point pirateLocation = pirateShip.getLocation();
		Random rand = new Random();
		
		/* Only move if you need to */
		if(!location.equals(pirateLocation)) {
			
			Point newPoint = null;
			
			/* Grab possible adjacent locations */
			LinkedList<Point> adj = SearchAlgorithm.getAdj(pirateShip.getLocation());
			
			/* Grab the 2D array of distances */
			int[][] bfsMap = SearchAlgorithm.getPath(pirateShip.getLocation(), ship.getLocation());
			int minDistance = Integer.MAX_VALUE;
			
			/* Look for smallest distance and set newPoint */ 
			for(Point point : adj) {
				int newDist = bfsMap[(int)point.getX()][(int)point.getY()];
				if(newDist < minDistance) {
					minDistance = newDist;
					newPoint = point;
				}
			}
			
			/* If a newPoint is set, call updateEverything() */
			if(newPoint != null) {
				pirateShip.updateEverything(newPoint);
			}
		}
	}
}
