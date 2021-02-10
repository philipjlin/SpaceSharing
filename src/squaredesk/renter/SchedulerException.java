package squaredesk.renter;

/**
 * SchedulerException.java
 * Exception class for handling scheduling errors.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class SchedulerException extends Exception{
		
	/**
	 * Constructor 
	 * 
	 * Default with message
	 */
	public SchedulerException(){
		
		super("Exception: Scheduled Booking Failed.");
	}
	
	/**
	 * Constructor
	 * 
	 * @param message message for exception
	 */
	public SchedulerException(String message){
		
		super(message);
	}
	
	/**
	 * Returns details associated with exception
	 */
	public String getExceptionDetails(){
		
		return super.getMessage();
	}
	
}