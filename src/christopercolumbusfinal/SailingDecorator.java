package christopercolumbusfinal;

import java.util.Random;

public class SailingDecorator implements SailStrategy{
	
	private SailStrategy decorated;
	
	/*
	 * SailingDecorator constructor *
	*/
    public SailingDecorator(SailStrategy decorated)
    {
         this.decorated = decorated;
    }
	
    /*
     *  Calls SailStrategy.sail() *
     */
	@Override
	public void sail(Ship ship, PirateShip pirateShip) {
		Random rand = new Random();
		//1 in 6 chance that ship doesn't move to make game playable
		if(rand.nextInt(6) != 0)
			decorated.sail(ship, pirateShip);
		
	}

}