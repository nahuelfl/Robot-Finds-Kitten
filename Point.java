public class Point {
    
    private final int x, y;
    
    /**
     * un point est une coordonne x et y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //aucun usage...
    boolean egal(int x, int y) {
        return x == this.x && y == this.y;
    }
    
    /**
     * @return la valeur x du point
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return la valeur y du point
     */
    public int getY() {
        return y;
    }
}