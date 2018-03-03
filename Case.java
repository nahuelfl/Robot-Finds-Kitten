
public abstract class Case 
{
	protected char representation;
	
	public Case()
	{
		this.representation = ' ';
	}
	
	/**
	 * Retourne la repr�sentation de la case (un seul caract�re)
	 * 
	 * @return la repr�sentation de la case
	 */
	public char getRepresentation() 
	{
		return this.representation;
	}
	
	public void setRepresentation(char representation)
	{
		this.representation = representation;
	}
	
	/** 
	 * Indique si une interaction entre la case et le robot est 
	 * possible (ex.: le robot peut interagir avec un NonKittenItem 
	 * en tout temps, mais ne peut pas interagir avec un mur, le robot 
	 * peut interagir avec une porte seulement s�il poss�de une cl�, * etc.) 
	 * 
	 * @param robot Le robot qui interagirait avec la case 
	 * @return true si une interaction entre le robot et la case est possible 
	 */ 
	public abstract boolean interactionPossible(Robot robot);
	
	/** 
	 * Interaction entre la case et le robot 
	 * 
	 * @param robot 
	 */ 
	public abstract void interagir(Robot robot);
	
	/** 
	 * G�n�re un symbole al�atoire
	 * 
	 * @return Un symbole ASCII compris entre �:� et �~� 
	 */ 
	public static char getRandomSymbole() 
	{ 
		return (char) (Math.random() * (126 - 58) + 58); 
	}

}
