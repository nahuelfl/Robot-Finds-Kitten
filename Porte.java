
public class Porte extends Case
{
	public Porte()
	{
		this.representation = '!';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		if(robot.getNbCles() != 0)	//Interaction possible si le robot possède au moins une clé
			return true;
		else
			return false;
	}

	@Override
	public void interagir(Robot robot) 
	{
		int nbCles = robot.getNbCles();
		robot.setNbCles(nbCles--);
		
		this.representation = ' ';
	}

}
