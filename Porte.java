
public class Porte extends Case
{
	public Porte()
	{
		this.representation = '!';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		if(robot.getNbCles() != 0)	//Interaction possible si le robot poss�de au moins une cl�
			return true;
		else
			return false;
	}

	@Override
	public void interagir(Robot robot) 
	{
		this.representation = ' ';
	}

}
