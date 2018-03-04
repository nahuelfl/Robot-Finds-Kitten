public class Robot{
    
    private final String nom;
    private Point pos;
    private int cle;
    private boolean teleporteur;
    private boolean searching;
    private String status;
    
    /**
     * Constructeur 
     * @param nom nom du robot
     * @param pos position initiale du robot
     */
    public Robot(String nom, Point pos) {
        this.nom = nom;
        this.pos = pos;
        this.cle = 0;
        this.teleporteur = false;
        this.searching = true;
    }
    
    /**
     * @return nom du robot
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * @param pos nouvelle position ou on veut placer le robot
     */
    public void setPos(Point pos) {
        this.pos = pos;
    }
    
    /**
     * @return la position du robot
     */
    public Point getPos() {
        return this.pos;
    }
    
    /**
     * @param increment vaut true pour l'obtention et false pour l'usage d'une cle
     */
    public void setCle(boolean increment) {
        cle += (increment) ? 1 : -1; 
    }
    
    /**
     * @return le nombre de cles que le robot possede
     */
    public int getCle() {
        return this.cle;
    }
    
    /**
     * change la valeur de l'attribut teleporteur du robot lorsqu'il le trouve
     */
    public void setTeleporteur() {
        teleporteur = true;
    }
    
    /**
     * @return true si le robot possede le teleporteur
     */
    public boolean hasTeleporteur() {
        return teleporteur;
    }
    
    /**
     * change l'attribut du robot qui indique s'il cherche toujours le kitten
     */
    public void foundKitten() {
        searching = false;
    }
    
    /**
     * @return true tant que le robot cherche le kitten
     */
    public boolean searching() {
        return searching;
    }
    
    /**
     * message affiche a chaque tour sur la console
     */
    public void status() {
        status = nom + " [" + cle + "]";
        status += (teleporteur) ? "T> " : "> ";
        System.out.print(status);
    }
}
