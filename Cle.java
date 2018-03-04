public class Cle extends Case {
    
    /**
     * Une cle, representee par ', permet d'ouvrir une porte.
     * Elle disparait de la grille quand un robot la ramasse.
     */
    public Cle() {
        this.representation = '\'';
        this.disparait = true;
    }
    
    public boolean interactionPossible(Robot robot) {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * le robot a une cle de plus lorsqu'il en trouve une.
     */
    public void interagir(Robot robot) {
        robot.setCle(true);
    }
}