/**
 *public class RobotFindsKitten
 *================================
 *Classe principale
 *-Contient la fonction main() et la logique de base du jeu
 * C'est dans cette fonction que se fait la création d'une new 
 * Grille(...) et d'un new Robot(...)
 **/
import java.util.Scanner;


public class RobotFindsKitten 
{
	public static void main(String[] args)
	{
		//Message de Bienvenue
		String messageBienvenue = "\t Bienvenue dans RobotFindsKitten \n Super Dungeon Master 3000 Ultra Turbo Edition !";
		System.out.println(messageBienvenue);
		
	
		/* INITIALISATION DU JEU*/
		
		//Robot
		String nomRobot = "R.O.B.";
		Point point = new Point(1,1);
		int nbCles = 0;
		boolean teleporteur = false;
		
		Robot robot = new Robot(nomRobot, point, nbCles, teleporteur);		
		
		//Grille
		int nbrPiecesX = 5;
		int nbrPiecesY = 2;
		int largeurPiece = 11;
		int hauteurPiece = 5;
		int nbrNonKitten = 10;
		
		Grille grille = new Grille(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece, nbrNonKitten);

		//Kitten
		
		Kitten kitten = new Kitten(Case.getRandomSymbole(),"Caramel");
		Point random3 = grille.randomEmptyCell();
		int random3X = random3.getX();
		int random3Y = random3.getY();
		grille.setGrille(random3X, random3Y, kitten);
		
		
		/* GAMEPLAY */
		do
		{
			//Affichage du jeu
			grille.afficher(robot);
			
			//Statut du robot
			String statutRobot = robot.getNomRobot() + " [" + robot.getNbCles() + "]";
			
			if(robot.getTeleporteur() == true)
				statutRobot += "T";
			
			statutRobot += "> ";
			
			System.out.print(statutRobot);
		
			//Input pour le mouvement du robot
			Scanner scanner = new Scanner(System.in);
			char input = scanner.next().charAt(0);
			
			System.out.println(" ");
			
			int posRobotX = robot.getPoint().getX();
			int posRobotY = robot.getPoint().getY();
			
			//Analyse des touches (W,A,S,D)
			switch(input)
			{
				case 'W': 
				case 'w': posRobotY--; break; 	//W
				case 'S': 
				case 's': posRobotY++; break;	//S
				case 'A': 
				case 'a': posRobotX--; break;	//A
				case 'D':
				case 'd': posRobotX++; break;	//D
			}
			
			//Teleportation
			if(input == 't' || input == 'T')
			{
				if(robot.getTeleporteur())
				{
					robot.setPoint(grille.randomEmptyCell());	//Aucune verification de déplacement (la méthode l'éffectue déjà)
				}
			}
			
			if(grille.deplacementPossible(robot, posRobotX, posRobotY))
				robot.setPoint(new Point(posRobotX, posRobotY));
			
			//Interaction
			grille.interagir(robot);
		}
		while(!robot.getPoint().egal(random3X, random3Y));	//Tant que Robot find pas Kitten 
		
		//Fin du jeu lorsque Kitten est trouvé
	}

}
