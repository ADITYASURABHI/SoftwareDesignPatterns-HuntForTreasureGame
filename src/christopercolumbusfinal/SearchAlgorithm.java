package christopercolumbusfinal;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/*
 * We created search algorithm class and implemented breadth first search concept which can be used in any class.
 */
public final class SearchAlgorithm {
	Point beginning;
	Point end;
	int[][] SearchMap; 
	LinkedList<Point> visited;
	Queue<AlgorithmList> queue;
	
	/*
	 Gets the starting and ending point of the grid and performs the search algorithm.
	 */
	public static int[][] getPath(Point beginning, Point end) {
		
		OceanMap oceanMap = OceanMap.getInstance();
		int[][] SearchMap = new int[oceanMap.getDimension()][oceanMap.getDimension()];
		
		/* Creates a map of maximum value for finding minimum distance */
		for(int x=0;x<oceanMap.getDimension();x++) {
			for(int y=0;y<oceanMap.getDimension();y++) {
				SearchMap[x][y] = Integer.MAX_VALUE;
			}
		}
		
		LinkedList<Point> visited = new LinkedList<Point>();
		Queue<AlgorithmList> queue = new LinkedList<AlgorithmList>();
		
		/* Add end point to visited and queue, with beginning distance of 0 */
		visited.add(end);
		queue.add(new AlgorithmList(end, 0));
		
		/* Find adjacent points for every point in queue and add them to queue if they are not visited */
		while(!queue.isEmpty()) {
			AlgorithmList cur = queue.poll();
			SearchMap[cur.getX()][cur.getY()] = cur.getDistance();
			LinkedList<Point> adj = getAdj(cur.getPoint());
			for(Point pt : adj) {
				if(!visited.contains(pt)) {
					visited.add(pt);
					queue.add(new AlgorithmList(pt,cur.getDistance()+1));
				}
				
			}
		}
		
		
		return SearchMap;
	}
	
	/*
	 * Get valid nearby points from current point*
	 */
	public static LinkedList<Point> getAdj(Point point) {
		LinkedList<Point> validAdj = new LinkedList<Point>();
		OceanMap oceanMap = OceanMap.getInstance();
		int x = (int)point.getX();
		int y = (int)point.getY();
		
		/* Check south */
		if(oceanMap.getState(x,y+1) == 0) {
			validAdj.add(new Point(x,y+1));
		}
		
		/* Check north */
		if(oceanMap.getState(x,y-1) == 0) {
			validAdj.add(new Point(x,y-1));
		}
		
		/* Check east */
		if(oceanMap.getState(x+1,y) == 0) {
			validAdj.add(new Point(x+1,y));
		}
		
		/* Check west */
		if(oceanMap.getState(x-1,y) == 0) {
			validAdj.add(new Point(x-1,y));
		}
		
		return validAdj;
	}
}
