package knowledgeengine;

/**
 * Predicate Class
 * Class defining Predicate objects. Predicate objects have a String identifier
 * The predicate part of a Triple is defined by a Predicate object.
 *  
 * @author Philip Lin
 */
public class Predicate{
	
	private String identifier;
	
	/**
	 * Constructor
	 * 
	 * @param identifier unique identifier of the Predicate
	 */
	public Predicate(String identifier){

		this.identifier = identifier;
	}
	
	/**
	 * Getter method
	 * 
	 * @return identifier String of the Predicate
	 */
	public String getIdentifier(){

		return identifier;
	}
}