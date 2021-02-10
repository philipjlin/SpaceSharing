package knowledgeengine;

/**
 * ImportException.java
 * Exception class for handling invalid query format in input query file.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class ImportException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public ImportException(){
		
		super("Exception: Invalid Input Triple Format.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public ImportException(String message){
		
		super(message);
	}
	
	/**
	 * Returns message associated with exception
	 */
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}