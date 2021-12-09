package christopercolumbusfinal;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

class OceanMapTest {

	@Test
	void testGetInstance() {
		OceanMap oceanMap1 = OceanMap.getInstance();
		OceanMap oceanMap2 = OceanMap.getInstance();
		if(oceanMap1 != oceanMap2)
			fail("Instance that is created is not same in getInstance() function");
	}

	@Test
	void testSetMap() {
		OceanMap oceanMap = OceanMap.getInstance();
		int[][] map1 = oceanMap.getMap();
		oceanMap.setMap(20, 40);
		int[][] map2 = oceanMap.getMap();
		if(map2.equals(map1))
			fail("Map not changing when we change the values");
	}

	@Test
	void testGetMap() {
		OceanMap oceanMap = OceanMap.getInstance();
		String mapClass = oceanMap.getMap().getClass().toString();
		if(!mapClass.equals("class [[I"))
			fail("Does not return an 2 Dimensional array");
	}

	@Test
	void testGetDimension() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(25, 40);
		if(oceanMap.getDimension()!=25)
			fail("The Dimensions Given are not same.");
	}

	@Test
	void testGetScale() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(25, 40);
		if(oceanMap.getScale()!=40)
			fail("The Scales Are not the same.");
	}

	@Test
	void testGetStateIntInt() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		int[][] map = oceanMap.getMap();
		if(map[5][5]!= oceanMap.getState(5, 5))
				fail("Int Values are not same.!");
	}

	@Test
	void testGetStateDoubleDouble() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		int[][] map = oceanMap.getMap();
		if(map[5][5]!= oceanMap.getState(5.0,5.0))
			fail("Double values are not same.!");
	}

	@Test
	void testGetStatePoint() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		int[][] map = oceanMap.getMap();
		if(map[5][5]!= oceanMap.getState(new Point(5,5)))
			fail("Point Values are not same.!");
	}

	@Test
	void testChangePoint() {
		OceanMap oceanMap = OceanMap.getInstance();
		oceanMap.setMap(20, 50);
		int point = oceanMap.getState(5,5);
		oceanMap.changePoint(new Point(5,5), point+1);
		if(oceanMap.getState(5,5)== point)
			fail("Points Values doesnt change.!");
	}

}
