package squaredesk.authentication;

/**
 * AccessDeniedException.java
 * Exception class for handling unauthorized attempts to access restricted methods.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")
public class AccessDeniedException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public AccessDeniedException(){
		
		super("Exception: Invalid Authentication Token.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public AccessDeniedException(String message){
		
		super(message);
	}
	
	/**
	 * Returns message associated with exception
	 */
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}