
public class Teleporteur extends Case
{
	/**
	 * Constructeur du Teleporteur
	 * 
	 * @param representation, caractère réprésentant le Teleporteur (choisi aléatoirement)
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
		//Le robot possède maintenant un téléporteur
		robot.setTeleporteur(true);
	}

}
