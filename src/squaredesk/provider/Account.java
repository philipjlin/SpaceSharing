package squaredesk.provider;

/**
 * The Account class keeps track of information used to handle transactions 
 * between the renter and provider through PayPal on the ShareDesk client.
 * 
 * @author Philip Lin
 */
public class Account {
	
	private String accountNumber;
	private String paypalAccount;
	
	/**
	 * Constructor
	 */
	public Account(){
		
		accountNumber = "";
		paypalAccount = "";
	}
	
	/**
	 * Returns the account number.
	 */
	public String getNumber(){
		
		return accountNumber;
	}
	
	/**
	 * Sets the account number.
	 */
	public void setNumber(String number){
		
		this.accountNumber = number;
	}
	
	/**
	 * Returns the PayPal account number.
	 */
	public String getPayPal(){ 
		
		return paypalAccount; 
	}
	
	/**
	 * Sets the PayPal account number.
	 */
	public void setPayPal(String paypalAccount){
		
		this.paypalAccount = paypalAccount;
	}
	
}