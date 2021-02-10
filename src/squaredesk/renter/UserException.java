package squaredesk.renter;

/**
 * UserException.java
 * 
 * Exception class for handling errors with the CRUD operations for Users(Providers and Renters). 
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")
public class UserException extends Exception{
	
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public UserException(){
		
		super("Exception: Creation Failed.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public UserException(String message){
		
		super(message);
	}
	
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}