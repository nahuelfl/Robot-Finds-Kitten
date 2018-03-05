
public class Porte extends Case {
    
    /**
     * Une porte est representee par ! et 
     * disparait de la grille lorsqu'elle est ouverte a l'aide d'une cle
     */
    public Porte() {
        this.representation = '!';
    }
    
    /*
     * le robot peut interagir avec la porte seulement s'il possede une cle
     * @return true si le robot peut ouvrir la porte
     */
    public boolean interactionPossible(Robot robot) {
        return (robot.getCle() > 0) ? true : false;
    }
    
    /*
     * Fait perdre une cle au robot a l'ouverture d'une porte
     * @param robot Le robot qui ouvrirait la porte.
     */
    public void interagir(Robot robot) {
        robot.setCle(robot.getCle()-1);
    }
    
    public boolean disparait() {
        return true;
    }
}
