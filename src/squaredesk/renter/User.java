package squaredesk.renter;

import squaredesk.provider.Account;
import squaredesk.provider.Contact;
import squaredesk.provider.Image;

/**
 * The User class represents a user that can either be a provider, renter,
 * or both in the SquareDesk system.
 * 
 * @author Philip Lin
 */
public class User{
	
	private String identifier;
	private String gender;
	private Account account;
	private Contact contact;
	private Image image;
	
	/**
	 * Constructor 
	 * 
	 * @param identifier identifier of the User object
	 */
	public User(String identifier){
		
		this.identifier = identifier;
		gender = "";
		account = new Account();
		contact = new Contact();
		image = new Image();
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
	
	public String getGender(){
		
		return gender;
	}
	
	public void setGender(String gender){
		
		this.gender = gender;
	}
	
	public Account getAccount(){
		
		return account;
	}
	
	public Contact getContact(){
		
		return contact;
	}
	
	public Image getImage(){
		
		return image;
	}
	
}