
public class Porte extends Case
{
	/**
	 * Constructeur de la Porte, la réprésentation d'une porte est toujours '!' 
	 * 
	 */
	public Porte()
	{
		this.representation = '!';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		if(robot.getNbCles() > 0)	//Interaction possible si le robot possède au moins une clé
			return true;
		else
			return false;
	}

	@Override
	public void interagir(Robot robot) 
	{
		int nbCles = robot.getNbCles();	//Le nombre de clés du robot est diminué de 1
		robot.setNbCles(--nbCles);
		
		//La Porte est enlevé de la grille et remplacé par une EmptyCase (dans la classe Grille)
	}

}
