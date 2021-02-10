package squaredesk.renter;

/**
 * The Booking class is used to define an object that contains information 
 * about an office space reservation between a renter and a provider.
 * 
 * @author Philip Lin
 */
public class Booking{
	
	private String identifier;
	private String renterIdentifier;
	private String officeSpaceIdentifier;
	private String hours;
	private String startDate;
	private String endDate;
	private boolean paymentStatus;
	
	/**
	 * Constructor
	 */
	public Booking(String identifier, String renterIdentifier, String officeSpaceIdentifier, String hours, String startDate, String endDate){
		
		this.identifier = identifier;
		this.renterIdentifier = renterIdentifier;
		this.officeSpaceIdentifier = officeSpaceIdentifier;
		this.hours = hours;
		this.startDate = startDate;
		this.endDate = endDate;
		paymentStatus = false;
	}
	
	/*
	 * Getters and Setters
	 */
	public String getIdentifier(){
		
		return identifier;
	}
	
	public void setIdentifier(String identifier){
		
		this.identifier = identifier;
	}
	
	public String getRenterIdentifier(){
		
		return renterIdentifier;
	}
	
	public void setRenterIdentifier(String renterIdentifier){
		
		this.renterIdentifier = renterIdentifier;
	}
	
	public String getOfficeSpaceIdentifier(){
		
		return officeSpaceIdentifier;
	}
	
	public void setOfficeSpaceIdentifier(String officeSpaceIdentifier){
		
		this.officeSpaceIdentifier = officeSpaceIdentifier;
	}
	
	public String getHours(){
		
		return hours;
	}
	
	public void setHours(String hours){
		
		this.hours = hours;
	}
	
	public String getStartDate(){
		
		return startDate;
	}
	
	public void setStartDate(String startDate){
		
		this.startDate = startDate;
	}
	
	public String getEndDate(){
		
		return endDate;
	}
	
	public void setEndDate(String endDate){
		
		this.endDate = endDate;
	}
	
	public boolean getPaymentStatus(){
		
		return paymentStatus;
	}
	
	public void setPaymentStatus(boolean paymentStatus){
		
		this.paymentStatus = paymentStatus;
	}

}