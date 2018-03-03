/**
 * public class Robot
 * ==================
 * - Classe qui représente le robot
 * 
 *   Le robot a minimalement les attributs suivants:
 *  -(String) nom du robot
 *  -(Point) positionx, y sur la grille
 *  -(int) nombre de clés collectées (et pas encore utilisées)
 *  -(boolean) possède le téléporteur ou non
 */

public class Robot 
{
	private String nomRobot;
	private Point point;
	private int nbCles;
	private boolean teleporteur;
	
	//Constructeur
	public Robot (String nomRobot, Point point, int nbCles, boolean teleporteur)
	{
		this.nomRobot = nomRobot;
		this.point = point;
		this.nbCles = nbCles;
		this.teleporteur = teleporteur;
	}
	
	//Getters
	public String getNomRobot()
	{
		return this.nomRobot;
	}
	
	public Point getPoint()
	{
		return this.point;
	}
	
	public int getNbCles()
	{
		return this.nbCles;
	}
	
	public boolean getTeleporteur()
	{
		return this.teleporteur;
	}
	
	//Setters
	public void setNomRobot(String nomRobot)
	{
		this.nomRobot = nomRobot;
	}
	
	public void setPoint(Point point)
	{
		this.point = point;
	}
	
	public void setNbCles(int nbCles)
	{
		this.nbCles = nbCles;
	}
	
	public void setTeleporteur(boolean teleporteur)
	{
		this.teleporteur = teleporteur;
	}
}
