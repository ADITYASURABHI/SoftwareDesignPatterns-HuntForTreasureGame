package christopercolumbusfinal;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/******************
 * Map States:    *
 * 0: Water       *
 * 1: Island      *
 * 2: Pirate Ship *
 * 3: Treasure    *
 ******************/
public class OceanMap {
	private int[][] map;
	private int dimension;
	private int scale;
	private static OceanMap instance;
	private HashMap<Integer, Integer> stateInfo = new HashMap<Integer,Integer>();
	private OceanMap() {
		
	}
	
	/*
	 * Creates new instance of ocean map if instance is already created it returns that instance. 
	 * Singleton design pattern is implemented here
	 */
	public static OceanMap getInstance() {
		if(instance == null){
			instance = new OceanMap();
		}
		return instance;
	}
	
	/*
	 * Creates a new grid with user dimension and scale 
	 */
	public void setMap(int dimension, int scale) {
		this.dimension = dimension;
		this.scale = scale;
		map = new int[dimension][dimension];
		for(int x=0;x<dimension;x++) {
			for(int y=0;y<dimension;y++) {
				map[x][y] = 0;
			}
		}
		addItems(1,dimension*dimension/4);
		addItems(4,2);
		if((int)Math.floor((dimension*dimension)/400) == 0) {
			addItems(3,1);
		}else {
			addItems(3,(int)Math.floor((dimension*dimension)/400));
		}
		
	}
	
	/*
	 * Returns two dimensional array of positions *
	 */
	public int[][] getMap() {
		return map;
	}
	
	public HashMap<Integer, Integer> getStateInfo() {
		return stateInfo;
	}
	
	/*
	 * Returns dimension *
	 */
	public int getDimension() {
		return dimension;
	}
	
	/*
	 * Returns scale *
	 */
	public int getScale() {
		return scale;
	}
	
	/*
	 * Takes int x,y, returns its current state or -1 if its outside the grid*
	 */
	public int getState(int x, int y){
		try {
			return map[x][y];
		}catch(Exception e){
			return -1;
		}
		
	}
	
	/*
	 * Takes double x,y, returns its current state or -1 if its outside the grid*
	 */
	public int getState(double x, double y) {
		try {
			return map[(int)x][(int)y];
		}catch(Exception e){
			return -1;
		}
	}
	
	/*
	 * Takes point, returns its current state or -1 if its outside the grid *
	 */
	public int getState(Point point) {
		try {
			return map[(int)point.getX()][(int)point.getY()];
		}catch(Exception e){
			return -1;
		}
	}
	
	/*
	 * Takes point and state, and sets passed point to state *
	 */
	public void changePoint(Point coord, int change) {
		if(change >= 0 && change <=2)
			map[(int)coord.getX()][(int)coord.getY()] = change;
	}
	
	/*
	 * Takes state and number that should be added, adds number of state to OceanMap *
	 */
	private void addItems(int state, int num) {
		Random rand = new Random();
		for(int z=0;z<num;z++) {
			int x = rand.nextInt(dimension);
			int y = rand.nextInt(dimension);
			while(map[x][y] > 0) {
				x = rand.nextInt(dimension);
				x = rand.nextInt(dimension);
			}
			map[x][y] = state;
			if(state == 4)
			{
				stateInfo.put(x, y);
			}
		}
	}
}
