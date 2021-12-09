package christopercolumbusfinal;

import java.awt.Point;

/*
 * A list is created for algorithm queue and a constructor is created for algorithm tuple.
*/
public class AlgorithmList {
	private int distance;
	private Point point;
	
	AlgorithmList(Point point, int distance){
		this.distance = distance;
		this.point = point;
	}
	
/*
 * This method returns a point in the tuple values.
 */
	public Point getPoint() {
		return point;
	}
	
/*
 * This method returns the distance in the tuple values.
 */
	public int getDistance() {
		return distance;
	}
	
/*
 * This method returns the X coordinate as integer in the tuple values.
 */
	public int getX() {
		return (int)point.getX();
	}
	
/*
 * This method returns the Y coordinate as integer in the tuple values.
 */
	public int getY() {
		return (int)point.getY();
	}
	
}
