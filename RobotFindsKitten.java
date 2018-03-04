import java.util.Scanner;

public class RobotFindsKitten {
    /**
     * contient la logique de base du jeu
     * creation d'une nouvelle grille de jeu et d'un nouveu robot.
     * A chaque tour, le joueur fait deplacer le robot sur la grille.
     * Le jeu s'arrete lorsque le robot rencontre kitten.
     */
    public static void main(String[] args) {
        
        System.out.println("       Bienvenue dans RobotFindsKitten\n Super Dungeon Master 3000 Ultra Turbo Edition !");
        Grille grille = new Grille(5, 2, 11, 5, 50);
        Robot robot = new Robot("R.O.B.", grille.randomEmptyCell());
        
        Scanner scan = new Scanner(System.in);
        char move;
        int x;
        int y;
        
        while(robot.searching()) {
            
            grille.afficher(robot);
            
            //Statut du robot
			String statutRobot = robot.getNomRobot() + " [" + robot.getNbCles() + "]";
			
			if(robot.getTeleporteur() == true)
				statutRobot += "T";
			
			statutRobot += "> ";
			
			System.out.print(statutRobot);
           
            
            x = robot.getPos().getX();
            y = robot.getPos().getY();
    
            Point newPoint;
            
            move = scan.next().charAt(0);
            
            switch(move) {
                case 'w' : y--;
                    break;
                case 'a' : x--;
                    break;
                case 's' : y++;
                    break;
                case 'd' : x++;
                    break;
                case 't' : if(robot.hasTeleporteur()) {
                    newPoint = grille.randomEmptyCell();
                    robot.setPos(newPoint);
                    }
                default : x = -1;
            }
            //ne rien faire si le input est invalide (cas default)
            //sinon, verifier si le deplacement est possible avant de proceder
            if (x != -1 && grille.deplacementPossible(robot, x, y)) {
                newPoint = new Point(x, y);
                robot.setPos(newPoint);
            }
            
            grille.interagir(robot);
        }
    }
}
