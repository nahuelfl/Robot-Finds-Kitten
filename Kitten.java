
public class Kitten extends Case
{
	private String nomKitten;
	
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
