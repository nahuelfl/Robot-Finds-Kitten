
public class Teleporteur extends Case
{
	/**
	 * Constructeur du Teleporteur
	 * 
	 * @param representation, caract�re r�pr�sentant le Teleporteur (choisi al�atoirement)
	 */
	public Teleporteur(char representation)
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
		//Le robot poss�de maintenant un t�l�porteur
		robot.setTeleporteur(true);
	}

}
