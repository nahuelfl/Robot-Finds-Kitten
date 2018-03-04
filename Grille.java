/**
 * public class Grille
 * ===================
 * - Classe qui repr�sente la grille de jeu
 * 
 *  private Case[][] grille
 *   un tableau 2D de cases
 *   
 *   Constructeur: public Grille (int nbrPiecesX, int nbrPiecesY,
 *   							  int largeurPiece, int hauteurPiece,
 *   							  int nbrNonKitten)
 *   	
 *   	Cette fonction initialise la grille en cr�ant les pi�ces, les portes, les murs,
 *    	les cl�s et les item (le t�l�porteur, les NonKittenItems et le Kitten)
 *    	Il y a un 'nbrNonKitten' NonKittenItems au total sur tout le jeu
 *    
 *    public Point randomEmptyCell():
 *    	retourne une coordonn�e de cellule qui ne contient rien
 *    
 *    public boolean deplacementPossible(Robot robot, int x, int y)
 *    	indique si c'est possible pour le robot de marcher sur la 
 *    	cellule de cordonn�e (x,y)
 * 	
 * 	public void afficher(Robot robot)
 *		Affiche la grille dans la console � coups de System.out.println(...)
 *
 *	void interagir(Robot robot)
 *		Lance l'interaction entre le Robot robot et la case de la grille sur 
 *		laquelle il se trouve
 *
 *		L'interaction peut �tre l'affichage d'un message (pour les NonKittenItems), 
 *		l'ouverture d'ine Porte, le fait de ramasser une cl� ou un t�l�porteur, ou encore
 *		le fait de gagner la partie en trouvant le Kitten
 *
 *	Les classes suivantes h�ritent de Case:
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
	 * @param nbrPiecesX,	le nombre de pi�ces (horizontalement) de la grille
	 * @param nbrPiecesY,	le nombre de pi�ces (verticalement) de la grille
	 * @param largeurPiece,	largeur de chaque pi�ce
	 * @param hauteurPiece, hauteur de chaque pi�ce
	 * @param nbrNonKitten,	nombre d'objets NonKittens � g�n�rer
	 */
	public Grille (int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) 
	{
		//Met dans l'attribut (Case[][] grille) un tableau de cases selon le nombre de pi�ces et les dimensions de chaque pi�ce
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
				if(i == 0 || i == this.grille.length-1) //Seulement des murs aux extr�mit�s de la grille
					this.grille[i][j] = new Mur();
				else
				{
					if(j % ((largeurPiece+1)/nbrPiecesY) == 0)	//On met une Porte au milieu de chaque colonnes de Murs en bas de chaque pi�ce
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
				if(j == 0 || j == this.grille[0].length-1 || i == 0 || i == this.grille.length-1)	//Seulement des murs aux extr�mit�s de la grille
					this.grille[i][j] = new Mur();
				else
				{
					if( i%((hauteurPiece+1)/nbrPiecesY)==0 && i!= hauteurPiece+1 )	//On met une Porte au milieu de chaque colonnes de Murs � droite de chaque pi�ce
						this.grille[i][j] = new Porte();
					else
						this.grille[i][j] = new Mur();
				}
			}
		}
		
		
		/*Cl�s*/
		
		//Une Cl� est plac� al�atoirement dans chaque pi�ce de la grille
		for(int i=1; i<this.grille.length; i+=hauteurPiece+1) //hauteurPiece+1 est la premi�re rang�e de chaque pi�ce 
		{
			for(int j=1; j<this.grille[0].length; j+=largeurPiece+1) //largeurPiece+1 est la premi�re colonne de chaque pi�ce
			{
				Point random;
				int randomX;
				int randomY;
				
				//Des cases vides randoms sont cherch�s jusqu'� en trouver une qui se trouve endans des dimensions de chaque pi�ce
				do
				{
					random = randomEmptyCell();
					randomX = random.getX();
					randomY = random.getY();
				}
				while( !( (randomY < (i+hauteurPiece+1) && randomY >= i) && (randomX < (j+largeurPiece+1) && randomX >= j) ) );	//Tant que la case ne se retrouve pas dans le dimensions de la pi�ce voulue
				
				this.grille[randomY][randomX] = new Cle();
			}
		}
		
		
		/*Non-Kitten-Items et T�l�porteur*/
		
		//Les Non-Kitten-Ittems sont initialis�s � une case vide choisie al�atoirement puis avec une representation al�atoire
		for(int i=0; i<nbrNonKitten; i++)
		{
			Point random = randomEmptyCell();
			int randomX = random.getX();
			int randomY = random.getY();
			
			if(Math.random()*nbrNonKitten == i)	//Une des cases sens�es �tre Non-Kitten est choisi al�atoirement pour �tre un t�l�porteur � la place
				this.grille[randomY][randomX] = new Teleporteur(Case.getRandomSymbole());
			else
				this.grille[randomY][randomX] = new NonKitten(Case.getRandomSymbole());
		}
		
	}
	
	
	/**
	 * Trouve des cases vides dans la grille (de facon al�atoire)
	 * 
	 * @return Un Point r�pr�sentant les cordonn�es d'une case vide choisi al�atoirement
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
	 * Indique si c'est possible pour le robot de marcher sur la cellule de cordonn�e (x,y)
	 * 
	 * @param robot
	 * @param x, cordon�e x de la case
	 * @param y, cordonn�e y de la case
	 * 
	 * @return un bol�en indiquant si le d�placement est possible
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
	 * Affiche la grille dans la console � coups de System.out.println(...)
	 * 
	 * @param robot
	 */
	public void afficher(Robot robot) 
	{	
		//La case o� est sens�e de se retrouver le robot (selon sa position) est affect�e d'une repr�sentation ('R') temporairement
		char temp = this.grille[robot.getPoint().getY()][robot.getPoint().getX()].getRepresentation();
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation('R');
		
		
		for(int i=0; i < this.grille.length; i++)
		{
			String rangee = "";	//String contenant une rangee de caract�res de la grille.
			
			for(int j=0; j < this.grille[0].length; j++)
			{
				rangee += this.grille[i][j].getRepresentation(); //Chaque representation de chaque case de la grille est rajout�e � la string (rang�e par rang�e)
			}
			
			System.out.println(rangee);	//Imprime la rang�e de caract�res dans la console
		}
		
		//On enl�ve le robot de la grille
		this.grille[robot.getPoint().getY()][robot.getPoint().getX()].setRepresentation(temp);
	}
	

	/**
	 * Lance l'interaction entre le Robot robot et la case de la grille sur 
	 * laquelle il se trouve
	 *
	 * L'interaction peut �tre l'affichage d'un message (pour les NonKittenItems), 
	 * l'ouverture d'ine Porte, le fait de ramasser une cl� ou un t�l�porteur, ou encore
	 * le fait de gagner la partie en trouvant le Kitten
	 * 
	 * @param robot
	 */
	void interagir(Robot robot)
	{
		if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interactionPossible(robot))
		{
			this.grille[robot.getPoint().getY()][robot.getPoint().getX()].interagir(robot);
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Cle)	//Si le robot int�ragit avec une cl� elle est enlev� de la grille (il la ramasse)
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
			
			if(this.grille[robot.getPoint().getY()][robot.getPoint().getX()] instanceof Porte)	//Si le robot int�ragit avec une porte (nbCles>0), il ouvre la porte et on l'enl�ve de la grille
				this.grille[robot.getPoint().getY()][robot.getPoint().getX()] = new EmptyCase();
		}
	}
	
	//Setter d'une case de la grille
	public void setGrille(int posX, int posY, Case _case)
	{
		this.grille[posY][posX] = _case;
	}
	
}
