package squaredesk.provider;
import java.util.ArrayList;
import java.util.List;

import squaredesk.renter.User;

/**
 * The Provider class represents a Provider with certain attributes and a list of 
 * OfficeSpace identifiers that keep track of OfficeSpace objects associated with 
 * the provider in ShareDesk.
 * 
 * @author Philip Lin
 */
public class Provider extends User{
	
	private List<String> officeSpaceIdentifiers;
	private List<Rating> ratings;
	
	/**
	 * Constructor 
	 * 
	 * @param identifier identifier of the Provider object
	 */
	public Provider(String identifier){
		
		super(identifier);
		
		officeSpaceIdentifiers = new ArrayList<String>();
		ratings = new ArrayList<Rating>();	
	}
	
	/**
	 * Returns the list of office space identifiers associated with the provider.
	 */
	public List<String> getOfficeSpaceIdentifiers(){
		
		return officeSpaceIdentifiers;
	}
	
	/**
	 * Returns the list of ratings associated with this provider.
	 */
	public List<Rating> getRatings(){
		
		return ratings;
	}
	
}