package squaredesk.authentication;

/**
 * AuthenticationException.java
 * Exception class for handling errors from the Authentication Service API classes.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class AuthenticationException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public AuthenticationException(){
		
		super("Exception: Authentication Failed.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public AuthenticationException(String message){
		
		super(message);
	}
	
	/**
	 * Returns info associated with exception
	 */
	public String getExceptionInfo(){
		
		return super.getMessage();
	}
	
}