
public class NonKitten extends Case
{
	public NonKitten(char representation)
	{
		this.representation = representation;
	}
	
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		return true;
	}

	@Override
	public void interagir(Robot robot)
	{
		//output description
		
	}

}
