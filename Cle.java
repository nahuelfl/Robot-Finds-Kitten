public class Cle extends Case {
    
    /**
     * Une cle, representee par ', permet d'ouvrir une porte.
     */
    public Cle() {
        this.representation = '\'';
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
    
    public boolean disparait(){
        returns true;
    }
}
