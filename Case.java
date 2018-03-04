public abstract class Case {
    
    protected char representation;
    protected boolean disparait;
    
    /**
     * @return la representation de la classe
     */
    public char getRepresentation() {
        return representation;
    }
    
    /**
     * Indique si une interaction entre la case et le robot est
     * possible (ex.: le robot peut interagir avec un NonKittenItem
     * en tout temps, mais ne peut pas interagir avec un mur, le robot
     * peut interagir avec une porte seulement s’il possède une clé,
     * etc.)
     *
     * @param robot Le robot qui interagirait avec la case
     * @return true si une interaction entre le robot et la case est possible 
     */
    public abstract boolean interactionPossible(Robot robot);
    
    /**
     * Interaction entre la case et le robot
     * @param robot le robot qui interagirait avec la case
     */
    public abstract void interagir(Robot robot);
    
    
    /**
     * Génère un symbole aléatoire
     *
     * @return Un symbole ASCII compris entre ’:’ et ’~’ a part le 'T' reserve pour le teleporteur
     */
    public static char getRandomSymbole() {
        char symbole = (char) (Math.random() * (126 - 58) + 58);
        return (symbole == 'T') ? getRandomSymbole() : symbole;
    }
    
    /**
     * Indique si l'object peut disparaitre apres interaction avec le robot.
     *
     * @return true si l'object peut disparaitre, false sinon.
     */
    public boolean disparait() {
        return disparait;
    }
    
}