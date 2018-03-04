
public class Porte extends Case
{
	/**
	 * Constructeur de la Porte, la r�pr�sentation d'une porte est toujours '!' 
	 * 
	 */
	public Porte()
	{
		this.representation = '!';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		if(robot.getNbCles() > 0)	//Interaction possible si le robot poss�de au moins une cl�
			return true;
		else
			return false;
	}

	@Override
	public void interagir(Robot robot) 
	{
		int nbCles = robot.getNbCles();	//Le nombre de cl�s du robot est diminu� de 1
		robot.setNbCles(--nbCles);
		
		//La Porte est enlev� de la grille et remplac� par une EmptyCase (dans la classe Grille)
	}

}
