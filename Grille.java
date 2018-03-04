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
	
	/**
	 * Constructeur de la grille
	 * 
	 * @param nbrPiecesX,	le nombre de pièces (horizontalement) de la grille
	 * @param nbrPiecesY,	le nombre de pièces (verticalement) de la grille
	 * @param largeurPiece,	largeur de chaque pièce
	 * @param hauteurPiece, hauteur de chaque pièce
	 * @param nbrNonKitten,	nombre d'objets NonKittens à générer
	 */
	public Grille (int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) 
	{
		//Met dans l'attribut (Case[][] grille) un tableau de cases selon le nombre de pièces et les dimensions de chaque pièce
		this.grille = new Case[(nbrPiecesY * hauteurPiece) + (nbrPiecesY+1)][(nbrPiecesX * largeurPiece) + (nbrPiecesX+1)];
		
		//Initiation de chaque Case de la grille en tant qu'EmptyCase (soit une classe enfant de Case ayant une representation = ' ')
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
				if(i == 0 || i == this.grille.length-1) //Seulement des murs aux extrémités de la grille
					this.grille[i][j] = new Mur();
				else
				{
					if(j % ((largeurPiece+1)/nbrPiecesY) == 0)	//On met une Porte au milieu de chaque colonnes de Murs en bas de chaque pièce
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
				if(j == 0 || j == this.grille[0].length-1 || i == 0 || i == this.grille.length-1)	//Seulement des murs aux extrémités de la grille
					this.grille[i][j] = new Mur();
				else
				{
					if( i%((hauteurPiece+1)/nbrPiecesY)==0 && i!= hauteurPiece+1 )	//On met une Porte au milieu de chaque colonnes de Murs à droite de chaque pièce
						this.grille[i][j] = new Porte();
					else
						this.grille[i][j] = new Mur();
				}
			}
		}
		
		
		/*Clés*/
		
		//Une Clé est placé aléatoirement dans chaque pièce de la grille
		for(int i=1; i<this.grille.length; i+=hauteurPiece+1) //hauteurPiece+1 est la première rangée de chaque pièce 
		{
			for(int j=1; j<this.grille[0].length; j+=largeurPiece+1) //largeurPiece+1 est la première colonne de chaque pièce
			{
				Point random;
				int randomX;
				int randomY;
				
				//Des cases vides randoms sont cherchés jusqu'à en trouver une qui se trouve endans des dimensions de chaque pièce
				do
				{
					random = randomEmptyCell();
					randomX = random.getX();
					randomY = random.getY();
				}
				while( !( (randomY < (i+hauteurPiece+1) && randomY >= i) && (randomX < (j+largeurPiece+1) && randomX >= j) ) );	//Tant que la case ne se retrouve pas dans le dimensions de la pièce voulue
				
				this.grille[randomY][randomX] = new Cle();
			}
		}
		
		
		/*Non-Kitten-Items et Téléporteur*/
		
		//Les Non-Kitten-Ittems sont initialisés à une case vide choisie aléatoirement puis avec une representation aléatoire
		for(int i=0; i<nbrNonKitten; i++)
		{
			Point random = randomEmptyCell();
			int randomX = random.getX();
			int randomY = random.getY();
			
			if(Math.random()*nbrNonKitten == i)	//Une des cases sensées être Non-Kitten est choisi aléatoirement pour être un téléporteur à la place
				this.grille[randomY][randomX] = new Teleporteur(Case.getRandomSymbole());
			else
				this.grille[randomY][randomX] = new NonKitten(Case.getRandomSymbole());
		}
		
	}
	
	
	/**
	 * Trouve des cases vides dans la grille (de facon aléatoire)
	 * 
	 * @return Un Point réprésentant les cordonnées d'une case vide choisi aléatoirement
	 */
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
	

	/**
	 * Indique si c'est possible pour le robot de marcher sur la cellule de cordonnée (x,y)
	 * 
	 * @param robot
	 * @param x, cordonée x de la case
	 * @param y, cordonnée y de la case
	 * 
	 * @return un boléen indiquant si le déplacement est possible
	 */
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
	

	/**
	 * Affiche la grille dans la console à coups de System.out.println(...)
	 * 
	 * @param robot
	 */
	public void afficher(Robot robot) 
	{	
		//La case où est sensée de se retrouver le robot (selon sa position) est affectée d'une représentation ('R') temporairement
		char temp = this.grille[robot.getPoint().getY()][robot.getPoint().getX()].getRepresentation();
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation('R');
		
		
		for(int i=0; i < this.grille.length; i++)
		{
			String rangee = "";	//String contenant une rangee de caractères de la grille.
			
			for(int j=0; j < this.grille[0].length; j++)
			{
				rangee += this.grille[i][j].getRepresentation(); //Chaque representation de chaque case de la grille est rajoutée à la string (rangée par rangée)
			}
			
			System.out.println(rangee);	//Imprime la rangée de caractères dans la console
		}
		
		//On enlève le robot de la grille
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation(temp);
	}
	

	/**
	 * Lance l'interaction entre le Robot robot et la case de la grille sur 
	 * laquelle il se trouve
	 *
	 * L'interaction peut être l'affichage d'un message (pour les NonKittenItems), 
	 * l'ouverture d'ine Porte, le fait de ramasser une clé ou un téléporteur, ou encore
	 * le fait de gagner la partie en trouvant le Kitten
	 * 
	 * @param robot
	 */
	void interagir(Robot robot)
	{
		if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interactionPossible(robot))
		{
			this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interagir(robot);
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Cle)	//Si le robot intéragit avec une clé elle est enlevé de la grille (il la ramasse)
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Porte)	//Si le robot intéragit avec une porte (nbCles>0), il ouvre la porte et on l'enlève de la grille
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
		}
	}
	
	//Setter d'une case de la grille
	public void setGrille(int posX, int posY, Case _case)
	{
		this.grille[posY][posX] = _case;
	}
	
}
