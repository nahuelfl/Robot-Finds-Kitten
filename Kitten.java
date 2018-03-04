public class Kitten extends Case {
    
    private String nom;
    
    /**
     * Le kitten porte un nom et est represente par un symbole ascii aleatoire
     * 
     */
    public Kitten() {
        this.representation = getRandomSymbole();
        this.nom = noms[(int) (Math.random() * noms.length)];
    }
    
    public boolean interactionPossible(Robot robot) {
        return true;
    }
    
    /**
     * {@inheritDoc}
     * Le jeu se termine en affichant un message lorsque le robot interagit avec kitten.
     */
    public void interagir(Robot robot) {
        robot.endSearch();
        
        System.out.println("You found kitten! Way to go, Robot.");
        System.out.println(this.nom + " <3 " + robot.getNom());
    }
    
    public static String[] noms = new String[] {
        "Plato",
        "Aristotle",
        "Socrates",
        "Nietzsche",
        "John Locke",
        "Kant",
        "DesCartes",
        "Confucius",
        "Marx",
        "Kierkegaard",
        "Hume",
        "Rousseau",
        "Hegel",
        "Epicurus",
        "Sartre",
        "Leibniz",
        "Camus",
        "Laozi",
        "Simone",
    };
}
