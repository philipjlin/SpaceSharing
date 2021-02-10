package squaredesk.renter;

/**
 * SearchEngineException.java
 * Exception class for handling search engine exceptions.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class SearchEngineException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public SearchEngineException(){
		
		super("Exception: Scheduled Booking Failed.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public SearchEngineException(String message){
		
		super(message);
	}
	
	/**
	 * Returns message associated with exception
	 */
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}
