package squaredesk.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * The service class is used to group permissions for access to restricted methods together by the service API those methods are a part of.
 * The three groups are the provider service, renter service, and authentication service. For each restricted method in an API, there will 
 * be corresponding permission for that method in the service object for that API. 
 * @author Philip Lin
 */
public class Service{
	
	private String identifier;
	private String name;
	private String description;
	private List<Permission> permissions = new ArrayList<Permission>();
	
	/**
	 * Adds a permission to the service.
	 * @param permission object to add to the list maintained by the Service object
	 */
	public void addPermission(Permission permission){
		
		permissions.add(permission);
	}
	
	/**
	 * Removes a permission from the service.
	 * @param permission object to remove from the list maintained by the Service object
	 */
	public void removePermission(Permission permission){
		
		permissions.remove(permission);
	}

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

	public List<Permission> getPermissions() {
		return permissions;
	}

}