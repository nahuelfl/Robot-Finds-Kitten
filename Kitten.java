
public class Kitten extends Case
{
	private String nomKitten;
	
	/**
	 * Constructeur du Kitten
	 * 
	 * @param representation, caractère réprésentant le Kitten 
	 * @param nomKitten, nom du Kitten
	 */
	public Kitten(char representation, String nomKitten)
	{
		this.representation = representation;
		this.setNomKitten(nomKitten);
	}
	
	@Override
	public boolean interactionPossible(Robot robot) 
	{
		return true;
	}

	@Override
	public void interagir(Robot robot) 
	{
		//Le message de fin de jeu est affiché, puis le jeu se termine
		String messageFin = "You found kitten! Way to go, robot. \n" + this.getNomKitten() + " <3 " + robot.getNomRobot();
		System.out.println(messageFin);
		
	}

	//Getters
	public String getNomKitten() 
	{
		return nomKitten;
	}
	
	//Setters
	public void setNomKitten(String nomKitten) 
	{
		this.nomKitten = nomKitten;
	}

}
