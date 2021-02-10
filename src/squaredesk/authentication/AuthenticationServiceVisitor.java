package squaredesk.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * The AuthenticationServiceVisitor class defines a visitor pattern object that visits objects created by the
 * Authentication Service API and returns an inventory of Services, Roles, RegisteredUsers, and Permissions.
 * @author Philip Lin
 */
public class AuthenticationServiceVisitor{

	private List<Service> services = new ArrayList<Service>();
	private List<Role> roles = new ArrayList<Role>();
	private List<Permission> permissions = new ArrayList<Permission>();
	private List<RegisteredUser> registeredUsers = new ArrayList<RegisteredUser>();
	
	/**
	 * Visits the AuthenticationService object and adds all Services, Roles, and Permissions
	 * found to the respective lists maintained by the visitor.
	 * @param visited AuthenticationService object visited
	 */
	public void visitAuthenticationService(AuthenticationService visited){
		
		for( String key : visited.getServicesMap().keySet() )
			services.add(visited.getServicesMap().get(key));
		
		for( String key : visited.getRolesMap().keySet() )
			roles.add(visited.getRolesMap().get(key));
		
		for( String key : visited.getPermissionsMap().keySet() )
			permissions.add(visited.getPermissionsMap().get(key));
		
	}
	
	/**
	 * Visits the RegisteredUserManager object and adds all RegisteredUsers found to the 
	 * list maintained by the visitor.
	 * @param visited RegisteredUserManager object visited
	 */
	public void visitRegisteredUserManager(RegisteredUserManager visited){
		
		for( String key : visited.getRegisteredUsersMap().keySet() )
			registeredUsers.add(visited.getRegisteredUsersMap().get(key));
	}
	
	/**
	 * Lists the inventory as a String.
	 * @return String of the inventory
	 */
	public String listInventory(){
		
		String inventory = "";
		
		System.out.println("Services \n*****");
		for( Service service : services ){
			
			System.out.println("ID: " + service.getIdentifier());
			System.out.println("Name: " + service.getName());
			System.out.println("Description: " + service.getDescription());
			
			System.out.print("Service Permissions: ");
			for( Permission permission : service.getPermissions() )
				System.out.print(permission.getName() + ", ");
			
			System.out.println("\n");
		}
		System.out.println("\n");
		
		
		System.out.println("Roles \n*****");
		for( Role role : roles ){
			
			System.out.println("ID: " + role.getIdentifier());
			System.out.println("Name: " + role.getName());
			System.out.println("Description: " + role.getDescription());
			
			System.out.print("Role Permissions: ");
			for( Permission permission : role.getPermissions() )
				System.out.print(permission.getName() + ", ");
			
			System.out.println("\n");
		}
		System.out.println("\n");

		
		System.out.println("Permissions \n*****");
		for( Permission permission : permissions ){
			
			System.out.println("ID: " + permission.getIdentifier());
			System.out.println("Name: " + permission.getName());
			System.out.println("Description: " + permission.getDescription());
			System.out.println("");
		}
		System.out.println("\n");
		
		
		System.out.println("Registered Users \n*****");
		for( RegisteredUser registeredUser : registeredUsers ){
			
			System.out.println("ID: " + registeredUser.getIdentifier());
			System.out.println("Name: " + registeredUser.getName());
			System.out.println("User Name: " + registeredUser.getUser());
			System.out.println("Password: " + registeredUser.getPassword());
			
			System.out.print("Registered User Roles: ");
			for( Role role : registeredUser.getRoles() )
				System.out.print(role.getName());
			
			System.out.println("\n");
		}
		System.out.println("\n");

		return inventory;
	}

}