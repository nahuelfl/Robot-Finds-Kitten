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
	/**
	 * Fonction principale (main) qui contient la logique de base du jeu
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Message de Bienvenue
		String messageBienvenue = "\t Bienvenue dans RobotFindsKitten \n Super Dungeon Master 3000 Ultra Turbo Edition !";
		System.out.println(messageBienvenue);
		
	
		/* INITIALISATION DU JEU*/
		
		//Attributs du robot
		String nomRobot = "R.O.B.";
		Point point = new Point(1,1);
		int nbCles = 0;
		boolean teleporteur = false;
		
		//Instanciation du robot
		Robot robot = new Robot(nomRobot, point, nbCles, teleporteur);		
		
		//Attributs de la grille
		int nbrPiecesX = 5;
		int nbrPiecesY = 2;
		int largeurPiece = 11;
		int hauteurPiece = 5;
		int nbrNonKitten = 10;
		
		//Instanciation de la grille
		Grille grille = new Grille(nbrPiecesX, nbrPiecesY, largeurPiece, hauteurPiece, nbrNonKitten);

		//Attributs du kitten
		Kitten kitten = new Kitten(Case.getRandomSymbole(),"Caramel");
		Point randomKittenPoint = grille.randomEmptyCell();
		int randomKittenX = randomKittenPoint.getX();
		int randomKittenY = randomKittenPoint.getY();
		
		//Instanciation du kitten
		grille.setGrille(randomKittenX, randomKittenY, kitten);
		
		
		//Déroulement du jeu
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
			String inputString = scanner.next().toUpperCase();
			char input = inputString.charAt(0);

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
			
			//Teleportation (Analyse de touche (T))
			if(input == 't' || input == 'T')
			{
				if(robot.getTeleporteur())
				{
					robot.setPoint(grille.randomEmptyCell());	//Aucune verification de déplacement (la méthode l'éffectue déjà)
				}
			}
			
			//Déplacement du robot (si le déplacement est valide)
			if(grille.deplacementPossible(robot, posRobotX, posRobotY))
				robot.setPoint(new Point(posRobotX, posRobotY));
			
			//Interaction (si possible)
			grille.interagir(robot);
		}
		while(!robot.getPoint().egal(randomKittenX, randomKittenY));	//Tant que Robot trouve pas Kitten 
		
		//Le jeu fini lorsque robot trouve kitten, dans tel cas leur intéraction affiche le message de fin du jeu.
	}

}
