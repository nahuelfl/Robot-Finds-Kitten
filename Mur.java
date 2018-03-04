
public class Mur extends Case 
{
	/**
	 * Constructeur du Mur, la r�pr�sentation d'un mur est toujours '%' 
	 * 
	 */
	public Mur()
	{
		this.representation = '%';
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		return false;
	}

	@Override
	public void interagir(Robot robot) 
	{
		//Aucune interaction
	}

}
