package christopercolumbusfinal;

public class StaticDecorator implements SailStrategy{
	private SailStrategy decorated;

	/**************************************************
			* Constructor of StaticsDecorator Class *
	***************************************************/
    public StaticDecorator(SailStrategy decorated)
    {
         this.decorated = decorated;
    }

	/**************************************************
						* Override to Do Nothing *
	***************************************************/
	@Override
	public void sail(Ship ship, PirateShip pirateShip) {
		//do nothing

	}
}
