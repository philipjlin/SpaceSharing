package knowledgeengine;

/**
 * Triple Class
 * Class defining Triple objects. Triple objects have a String identifier,
 * Node subject, Predicate predicate, and Node object.
 * 
 * @author Philip Lin
 */
public class Triple{
	
	private String identifier;
	private Node subject;
	private Predicate predicate;
	private Node object;
	
	/**
	 * Constructor 
	 * 
	 * @param identifier unique identifier of the form "Subject Predicate Object"
	 * @param subject first part of the triple, a Node object
	 * @param predicate second part of the triple, a Predicate object
	 * @param object third part of the triple. a Node object
	 */
	public Triple(String identifier, Node subject, Predicate predicate, Node object){

		this.identifier = identifier;
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}
	
	/**
	 * Getter method
	 * 
	 * @return subject Node of the Triple
	 */
	public Node getSubject(){
		
		return subject;
	}
	
	/**
	 * Getter method
	 * 
	 * @return predicate Predicate of the Triple
	 */
	public Predicate getPredicate(){
		
		return predicate;
	}
	
	/**
	 * Getter method
	 * 
	 * @return object Node of the Triple
	 */
	public Node getObject(){
		
		return object;
	}
	
	/**
	 * Getter method
	 * 
	 * @return identifier String of the Triple
	 */
	public String getIdentifier(){

		return identifier;
	}
}