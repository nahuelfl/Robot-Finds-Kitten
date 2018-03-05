public class Robot{
    
    private final String nom;
    private Point pos;
    private int cle;
    private boolean teleporteur;
    
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
     * @param cle nouveau nombre de cle 
     */
    public void setCle(int cle) {
        this.cle = cle; 
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
    public boolean getTeleporteur() {
        return teleporteur;
    }

}
