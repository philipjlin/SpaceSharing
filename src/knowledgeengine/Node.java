package knowledgeengine;

/**
 * Node Class
 * Class defining Node objects. Node objects have a String identifier.
 * The subject and object parts of a Triple are defined by Node objects.
 *  
 * @author Philip Lin
 */
public class Node{
	
	private String identifier;
	
	/**
	 * Constructor
	 * 
	 * @param identifier unique identifier of the Node
	 */
	public Node(String identifier){

		this.identifier = identifier;
	}

	/**
	 * Getter method
	 * 
	 * @return identifier String of the Node
	 */
	public String getIdentifier(){

		return identifier;
	}
}