package squaredesk.provider;

/**
 * The Contact class keeps track of contact information for a renter or provider, 
 * including phone number and email address.
 * 
 * @author Philip Lin
 */
public class Contact {
	
	private String name;
	private String phone;
	private String email;
	
	/**
	 * Constructor
	 */
	public Contact(){
		
		name = "";
		phone = "";
		email = "";
	}
	
	/**
	 * Returns the name.
	 */
	public String getName(){ 
		
		return name; 
	}
	
	/**
	 * Sets the name.
	 */
	public void setName(String name){
		
		this.name = name;
	}
	
	/**
	 * Returns the phone number.
	 */
	public String getPhone(){ 
		
		return phone; 
	}
	
	/**
	 * Sets the phone number.
	 */
	public void setPhone(String phone){
		
		this.phone = phone;
	}
	
	/**
	 * Returns the email address.
	 */
	public String getEmail(){ 
		
		return email; 
	}
	
	/**
	 * Sets the email address.
	 */
	public void setEmail(String email){
		
		this.email = email;
	}

}