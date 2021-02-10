package squaredesk.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * The Role class is used to group permissions for access to restricted methods together by role.
 * Associated permissions are kept in a list that each role has. Roles include administrator, 
 * registered user, and browser.
 * @author Philip Lin
 */
public class Role{

	private String identifier;
	private String name;
	private String description;
	private List<Permission> permissions = new ArrayList<Permission>();
	
	/**
	 * Adds a permission to the role.
	 * @param permission object to add to the list maintained by the Role object
	 */
	public void addPermission(Permission permission){
		
		permissions.add(permission);
	}
	
	/**
	 * Removes a permission from the role.
	 * @param permission object to remove from the list maintained by the Role object
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