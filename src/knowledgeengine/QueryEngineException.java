package knowledgeengine;

/**
 * QueryEngineException.java
 * Exception class for handling invalid query format in input query file.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class QueryEngineException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public QueryEngineException(){
		
		super("Exception: Invalid Query Format.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public QueryEngineException(String message){
		
		super(message);
	}
	
	/**
	 * Returns message for exception
	 */
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}