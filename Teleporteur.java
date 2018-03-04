public class Teleporteur extends Case {
    
    /**
     * le teleporteur est represente par T et disparait lorsqu'un robot l'obtient
     */
    public Teleporteur() {
        this.representation = 'T';
        this.disparait = true;
    }
    
    public boolean interactionPossible(Robot robot) {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * change l'attribut teleporteur du robot pour indiquer qu'il en possede un.
     */
    public void interagir(Robot robot) {
        robot.setTeleporteur();
    }
}