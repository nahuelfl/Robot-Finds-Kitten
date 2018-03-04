
public class EmptyCase extends Case
{
	/**
	 * Constructeur du EmptyCase la r�pr�sentation d'une EmptyCase est toujours ' ' 
	 */
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
		//Aucune interaction
	}
}
