
public class Point 
{
	private final int x, y;
	
	//Constructeur
	public Point(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	//V�rifie si l'attribut x est �gal � l'attribut y
	boolean egal(int x, int y) 
	{
		return x == this.x && y == this.y;
	}
	
	public int getX() 
	{
		return x;
	}
	
	public int getY() 
	{
		return y;
	}
	
}
