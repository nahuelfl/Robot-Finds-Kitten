
public class Cle extends Case
{
	public Cle()
	{
		this.representation = '\'';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		return true;
	}

	@Override
	public void interagir(Robot robot) 
	{
		int nbCles = robot.getNbCles();
		robot.setNbCles(nbCles++);
	}

}
