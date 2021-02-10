package squaredesk.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * The RegisteredUser class represents a person who is registered with the SquareDesk application.
 * Each registered user has any number of specific roles with specific permissions to access the 
 * methods of the SquareDesk APIs.
 * @author Philip Lin
 */
public class RegisteredUser{

	private String identifier;
	private String name;
	private String user;
	private String password;
	private List<Role> roles = new ArrayList<Role>();
	
	/**
	 * Adds a role to the user.
	 * @param Role object to add to the list maintained by the RegisteredUser object
	 */
	public void addRole(Role role){
		
		roles.add(role);
	}
	
	/**
	 * Removes a role from the user.
	 * @param Role object to remove from the list maintained by the RegisteredUser object
	 */
	public void removeRole(Role role){
		
		roles.remove(role);
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Integer.toString(password.hashCode());
	}

	public List<Role> getRoles() {
		return roles;
	}
	
}