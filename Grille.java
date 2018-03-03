/**
 * public class Grille
 * ===================
 * - Classe qui représente la grille de jeu
 * 
 *  private Case[][] grille
 *   un tableau 2D de cases
 *   
 *   Constructeur: public Grille (int nbrPiecesX, int nbrPiecesY,
 *   							  int largeurPiece, int hauteurPiece,
 *   							  int nbrNonKitten)
 *   	
 *   	Cette fonction initialise la grille en créant les pièces, les portes, les murs,
 *    	les clés et les item (le téléporteur, les NonKittenItems et le Kitten)
 *    	Il y a un 'nbrNonKitten' NonKittenItems au total sur tout le jeu
 *    
 *    public Point randomEmptyCell():
 *    	retourne une coordonnée de cellule qui ne contient rien
 *    
 *    public boolean deplacementPossible(Robot robot, int x, int y)
 *    	indique si c'est possible pour le robot de marcher sur la 
 *    	cellule de cordonnée (x,y)
 * 	
 * 	public void afficher(Robot robot)
 *		Affiche la grille dans la console à coups de System.out.println(...)
 *
 *	void interagir(Robot robot)
 *		Lance l'interaction entre le Robot robot et la case de la grille sur 
 *		laquelle il se trouve
 *
 *		L'interaction peut être l'affichage d'un message (pour les NonKittenItems), 
 *		l'ouverture d'ine Porte, le fait de ramasser une clé ou un téléporteur, ou encore
 *		le fait de gagner la partie en trouvant le Kitten
 *
 *	Les classes suivantes héritent de Case:
 *
 *	-public class Kitten extends Case
 *	-public class NonKitten extends Case
 *	-public class Cle extends Case
 *	-public class Porte extends Case
 *	-public class Teleporteur extends Case
 *	-public class Mur extends Case
 */
public class Grille 
{
	private Case[][] grille;
	
	//Constructeur
	public Grille (int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) 
	{
		this.grille = new Case[(nbrPiecesY * hauteurPiece) + (nbrPiecesY+1)][(nbrPiecesX * largeurPiece) + (nbrPiecesX+1)];
		
		//Initiation de chaque Case de la grille en tant qu'EmptyCase
		for(int i=0; i < this.grille.length; i++)
		{
			for(int j=0; j < this.grille[0].length; j++)
			{
				this.grille[i][j] = new EmptyCase();
			}
		}
		
		
		/*Murs et des Portes*/
		
		//Murs et Portes (Horizontaux)
		for(int i=0; i < this.grille.length; i += hauteurPiece+1)
		{
			for(int j=0; j < this.grille[0].length; j++)
			{
				if(i == 0 || i == this.grille.length-1)
					this.grille[i][j] = new Mur();
				else
				{
					if(j % ((largeurPiece+1)/nbrPiecesY) == 0)
						this.grille[i][j] = new Porte();
					else
						this.grille[i][j] = new Mur();
				}
			}
		}
		//Murs et Portes (Verticaux)
		for(int i=0; i < this.grille.length; i++)
		{
			for(int j=0; j < this.grille[0].length; j += largeurPiece+1)
			{
				if(j == 0 || j == this.grille[0].length-1)
					this.grille[i][j] = new Mur();
				else
				{
					if(i == 0 || i == this.grille.length-1)
						this.grille[i][j] = new Mur();
					else
					{
						if( i%((hauteurPiece+1)/nbrPiecesY)==0 && i!= hauteurPiece+1 )
							this.grille[i][j] = new Porte();
						else
							this.grille[i][j] = new Mur();
					}
				}
			}
		}
		
		/*Clés*/
		
		for(int i=1; i<this.grille.length; i+=hauteurPiece+1)
		{
			for(int j=1; j<this.grille[0].length; j+=largeurPiece+1)
			{
				Point random;
				int randomX;
				int randomY;
				
				do
				{
					random = randomEmptyCell();
					randomX = random.getX();
					randomY = random.getY();
				}
				while( !( (randomY < (i+hauteurPiece+1) && randomY >= i) && (randomX < (j+largeurPiece+1) && randomX >= j) ) );
				
				this.grille[randomY][randomX] = new Cle();
			}
		}
		
		
		/*Non-Kitten-Items et Téléporteur*/
		
		for(int i=0; i<nbrNonKitten; i++)
		{
			Point random2 = randomEmptyCell();
			int random2X = random2.getX();
			int random2Y = random2.getY();
			
			if(Math.random()*nbrNonKitten == i)	//Une des cases sensées être Non-Kitten est choisi aléatoirement pour être un téléporteur à la place
				this.grille[random2Y][random2X] = new Teleporteur(Case.getRandomSymbole());
			else
				this.grille[random2Y][random2X] = new NonKitten(Case.getRandomSymbole());
		}
		
	}
	
	//Retourne une coordonnée de cellule qui ne contient rien
	public Point randomEmptyCell() 
	{
		boolean caseVide = false;
		int rangee = 0;
		int colonne = 0;
		
		do
		{
			rangee = (int)(Math.random()*(this.grille.length));	//Valeur random entre 0 et nbrPiecesY
			colonne = (int)(Math.random()*(this.grille[0].length));	//Valeur random entre 0 et nbrPiecesX
			
			if(this.grille[rangee][colonne] instanceof EmptyCase)	//S'il n'y a rien dans la cellule random
				caseVide = true;
		}
		while(caseVide == false);
		
		return new Point(colonne, rangee);
	}
	
	//Indique si c'est possible pour le robot de marcher sur la cellule de cordonnée (x,y)
	public boolean deplacementPossible(Robot robot, int x, int y) 
	{
		
		if(this.grille[y][x] instanceof Mur)
			return false;
		
		int nbCles = robot.getNbCles();
		
		if(this.grille[y][x] instanceof Porte && nbCles == 0)
			return false;
		else
			return true;
	}
	
	//Affiche la grille dans la console à coups de System.out.println(...)
	public void afficher(Robot robot) 
	{	
		//Affichage du robot
		char temp = this.grille[robot.getPoint().getY()][robot.getPoint().getX()].getRepresentation();
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation('R');
		
		
		for(int i=0; i < this.grille.length; i++)
		{
			String rangee = "";	//String contenant une rangee de caractères de grille.
			
			for(int j=0; j < this.grille[0].length; j++)
			{
				rangee += this.grille[i][j].getRepresentation();
			}
			
			System.out.println(rangee);	//Imprime la grille dans la console
		}
		
		//On enlève le robot de la grille
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation(temp);
	}
	
	//Lance l'interaction entre le Robot robot et la case de la grille sur laquelle il se trouve
	void interagir(Robot robot)
	{
		if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interactionPossible(robot))
		{
			this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interagir(robot);
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Cle)
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Porte)	//Si le robot intéragit avec une porte (nbCles>0), il ouvre la porte e ton l'enlève de la grille
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
		}
	}
	
	//Setters
	public void setGrille(int posX, int posY, Case _case)
	{
		this.grille[posY][posX] = _case;
	}
	
}
