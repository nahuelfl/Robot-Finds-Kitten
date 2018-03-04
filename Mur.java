public class Mur extends Case {
    /**
     * la grille est formee de murs representes par le symbole % 
     */
    public Mur() {
        this.representation = '%';
        this.disparait = false;
    }
    
    public boolean interactionPossible(Robot robot) {
        return false;
    }
    
    /**
     * (@inheritDoc)
     * aucune interaction entre un mur et un robot
     */
    public void interagir(Robot robot) {
    }
}