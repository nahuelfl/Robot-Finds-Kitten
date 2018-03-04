
public class Grille {
    
    private Case[][] grille;
    private int largeurGrille;
    private int hauteurGrille;
    /**
     * Initialise la grille en créant les pièces, les portes, les murs
     * les clés et les items (le téléporteur, les NonKittenItems et le Kitten)
     * Il y a ‘nbrNonKitten‘ NonKittenItems au total sur tout le jeu
     *
     * @param nbrPiecesX nombre de pieces en largeur
     * @param nbrPiecesY nombre de pieces en hauteur
     * @param largeurPiece largeur des pieces, sans les murs
     * @param hauteurPiece hauteur des pieces, sans les murs
     * @param nbrNonKitten nombre de non-kitten items
     */
    public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) {
        
        largeurGrille = nbrPiecesX * (largeurPiece + 1) + 1;
        hauteurGrille = nbrPiecesY * (hauteurPiece + 1) + 1;
        
        grille = new Case[hauteurGrille][largeurGrille];
        
        
        //point milieu (en hauteur et en largeur) des pieces pour l'emplacement des portes
        int middleLargeur = (largeurPiece+1)/2;
        int middleHauteur = (hauteurPiece+1)/2;
        
        //placer les murs et les portes
        for (int i=0; i<hauteurGrille; i++) {
            for (int j=0; j<largeurGrille; j++) {
                
                //murs horizontaux
                if ((i % (hauteurPiece + 1) == 0)) {
                    
                    if (i != 0 && i != hauteurGrille-1 && j % (largeurPiece + 1) == middleLargeur) {
                        grille[i][j] = new Porte();
                    }
                    else {
                        grille[i][j] = new Mur();
                    }
                }
                //murs verticaux
                else if (j % (largeurPiece + 1) == 0) {
                    
                    if (j != 0 && j != largeurGrille-1 && i % (hauteurPiece + 1) == middleHauteur) {
                        grille[i][j] = new Porte();
                    }
                    else {
                        grille[i][j] = new Mur();
                    }
                } 
            }
        }
        
        //creation des cles, kitten, teleporteur et nonKitten items
        
        //placer une cle par piece 
        Point pointCle;
        
        int piecesSansCle = nbrPiecesX * nbrPiecesY;
        boolean[] pieces = new boolean[piecesSansCle]; //les pieces sans cle valent false
        
        int numeroPiece;
        
        while (piecesSansCle > 0) {
            
            pointCle = randomEmptyCell();
            numeroPiece = pointCle.getX() / (largeurPiece + 1) + (pointCle.getY() / (hauteurPiece + 1)) * nbrPiecesX;
            
            if (!pieces[numeroPiece]) { 
                grille[pointCle.getY()][pointCle.getX()] = new Cle();
                pieces[numeroPiece] = true;
                piecesSansCle--;
            }
        }
        
        //placer un kitten
        Point kittenPoint = randomEmptyCell();
        grille[kittenPoint.getY()][kittenPoint.getX()] = new Kitten();
        
        //placer un teleporteur
        
        Point teleporteurPoint = randomEmptyCell();
        grille[teleporteurPoint.getY()][teleporteurPoint.getX()] = new Teleporteur();
        
        //placer les nonKitten items
        Point nonKittenPoint;
        for (int k=0; k<nbrNonKitten; k++){
            nonKittenPoint = randomEmptyCell();
            grille[nonKittenPoint.getY()][nonKittenPoint.getX()] = new NonKitten();
        }
    }
    
    
    /**
     * Retourne une coordonnée de cellule qui ne contient rien
     *
     * @return un point innoccupe sur la grille
     */
    public Point randomEmptyCell() {

        int x;
        int y;
        x = (int) (Math.random() * (double) largeurGrille);
        y = (int) (Math.random() * (double) hauteurGrille);
        
        return (grille[y][x] == null) ? new Point(x, y) : randomEmptyCell();
    }
    
    /**
     * Indique si c’est possible pour le robot de marcher sur la
     * cellule de coordonnée (x, y)
     *
     * @param robot Le robot
     * @param position en x ou le robot voudrait se deplacer
     * @param position en y ou le robot voudrait se deplacer
     * @return true si le deplacement est possible
     */

    public boolean deplacementPossible(Robot robot, int x, int y) {
        
        boolean possible = false;
        
        if (grille[y][x] == null || grille[y][x].interactionPossible(robot)) {
            possible = true;
        }
        
        return possible;
    }
    
    /**
     *
     * Affiche la grille dans la console à coups de System.out.println(...)
     * @param robot Le robot qui se trouve sur la grille(represente par '#')
     */
    public void afficher(Robot robot) {

        int robotX = robot.getPos().getX();
        int robotY = robot.getPos().getY();
            
        String ligne;
        
        for (int l=0; l<hauteurGrille; l++) {
            ligne = "";
            
            for (int m=0; m<largeurGrille; m++) {
                
                if (l == robotY && m == robotX) {
                    ligne += "#";
                }
                else {
                    ligne += (grille[l][m] != null) ? grille[l][m].getRepresentation() : " ";
                }
            }
            System.out.println(ligne);
        }
        
    }
    
    /**
     * Lance l’interaction entre le Robot robot et la case de la grille sur
     * laquelle il se trouve
     * L’"interaction" peut être l’affichage d’un message (pour les NonKittenItems),
     * l’ouverture d’une Porte, le fait de ramasser une clé ou un téléporteur, ou encore
     * le fait de gagner la partie en trouvant le Kitten
     *
     * @param robot Le robot qui interagirait avec la case
     */
    void interagir(Robot robot) {
        
        int robotX = robot.getPos().getX();
        int robotY = robot.getPos().getY();
        
        if (grille[robotY][robotX] != null) {
            Case interactif = grille[robotY][robotX];
            
            if (interactif.disparait()) {
                grille[robotY][robotX] = null;
            }
            interactif.interagir(robot);
        }
    }
    
 
}