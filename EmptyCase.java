
public class EmptyCase extends Case
{
	/**
	 * Constructeur du EmptyCase la réprésentation d'une EmptyCase est toujours ' ' 
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
