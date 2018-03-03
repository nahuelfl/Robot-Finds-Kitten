
public class EmptyCase extends Case
{
	public EmptyCase()
	{
		this.representation = ' ';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		return true;
	}

	@Override
	public void interagir(Robot robot) 
	{
		// TODO Auto-generated method stub
		
	}
}
