package squaredesk.authentication;

/**
 * The Permission class represents the permission required to access a specific restricted method in any SquareDesk API.
 * Every restricted method in the Provider Service API, Renter Service API, or Authentication Service API will have a 
 * corresponding permission object. If a role has a specific permission object, then the method that the permission object 
 * corresponds to will be accessible via authentication token to users with that role.
 * @author Philip Lin
 */
public class Permission{

	private String identifier;
	private String name;
	private String description;
	
	/*
	 * Getters and Setters
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}