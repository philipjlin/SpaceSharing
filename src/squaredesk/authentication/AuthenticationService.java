package squaredesk.authentication;

import java.util.HashMap;
import java.util.Map;

import squaredesk.renter.UserException;

/*
 * 
 * TODO: Change the String authentication token parameter into AuthenticationToken object parameter
 * so that you can check the token's state.
 * 
 * 
 * 
 */

/**
 * The AuthenticationService class is used to define an object that maintains and edits 
 * Service, Role, and Permission objects, including create, retrieve and delete operations. 
 * The services, roles, and permissions created by the authentication service are used to 
 * determine the level of access to restricted methods in the SquareDesk application for registered users. 
 * @author Philip Lin
 */
public class AuthenticationService{

	private static AuthenticationService authenticationService = new AuthenticationService();
	
	private Map<String, Service> servicesMap = new HashMap<String, Service>();
	private Map<String, Role> rolesMap = new HashMap<String, Role>();
	private Map<String, Permission> permissionsMap = new HashMap<String, Permission>();
	
	/**
	 * Static method to obtain single instance of RegisteredUserManager.
	 * Follows the singleton design pattern.
	 * @return singleton RegisteredUserManager instance
	 */
	public static AuthenticationService getInstance(){

		return authenticationService;
	}
	
	/**
	 * Creates service objects from a CSV file and adds them to the map of service objects.
	 * @param CSVReader object used to read input file
	 */
	public void createServices(CSVReader reader){
					
		reader.createServicesFromFile();
			
		servicesMap = reader.getServicesMap();	
	}
	
	/**
	 * Creates role objects from a CSV file and adds them to the map of roles.
	 * @param CSVReader object used to read input file
	 */
	public void createRoles(CSVReader reader){
						
		reader.createRolesFromFile();
			
		rolesMap = reader.getRolesMap();	
	}
	
	/**
	 * Creates permission objects from a CSV file and adds them to the map of permissions.
	 * @param CSVReader object used to read input file
	 */
	public void createPermissions(CSVReader reader){
				
		reader.createPermissionsFromFile();
			
		permissionsMap = reader.getPermissionsMap();	
	}
	
	/**
	 * Adds permission objects to lists maintained by their corresponding services.
	 * @param CSVReader object used to read input file
	 */
	public void addPermissionsToServices(CSVReader reader){
		
		reader.addPermissionsToServicesFromFile();
		
		servicesMap = reader.getServicesMap();
	}
	
	/**
	 * Adds permission objects to lists maintained by their corresponding roles.
	 * @param CSVReader object used to read input file
	 */
	public void addPermissionsToRoles(CSVReader reader){
		
		reader.addPermissionsToRolesFromFile();
		
		rolesMap = reader.getRolesMap();
	}
	
	/**
	 * Returns a service object from the map of services.
	 * @param id identifier of Service object to retrieve
	 * @return Service object retrieved
	 */
	public Service retrieveService(String id){
		
		try{
							
			if( !servicesMap.containsKey(id) )
				throw new AuthenticationException("Retrive Failed: No Service with id " + id + " exists.");
			
			return servicesMap.get(id);
				
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Returns a role object from the map of roles.
	 * @param id identifier of Role object to retrieve
	 * @return Role object retrieved
	 */
	public Role retrieveRole(String id){
		
		try{
			
			if( !rolesMap.containsKey(id) )
				throw new AuthenticationException("Retrive Failed: No Role with id " + id + " exists.");
						
			return rolesMap.get(id);
					
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
		return null;
	} 
	
	/**
	 * Returns a permission object from the map of permissions.
	 * @param id identifier of Permission object to retrieve 
	 * @return Permission object retrieved
	 */
	public Permission retrievePermission(String id){
		
		try{
			
			if( !permissionsMap.containsKey(id) )
				throw new AuthenticationException("Permission Failed: No Permission with id " + id + " exists.");
						
			return permissionsMap.get(id);
	
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Updates a service object using a new Service DTO.
	 * @param token authentication token
	 * @param id identifier of the Service object to be updated
	 * @param updatedService Service DTO to be used in the update
	 */
	public void updateService(AuthenticationToken token, String id, Service updatedService){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AccessDeniedException("Access Denied: Cannot Update Service");
			
			if( !servicesMap.containsKey(id) )
				throw new AuthenticationException("Update Failed: No Service with id " + id + " exists.");
			
			servicesMap.put(id, updatedService);
		
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
	}
	
	/**
	 * Updates a role object using a new Role DTO.
	 * @param token authentication token
	 * @param id identifier of the Role object to be updated
	 * @param updatedRole Role DTO to be used in the update
	 */
	public void updateRole(AuthenticationToken token, String id, Role updatedRole){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AccessDeniedException("Access Denied: Cannot Update Role");
			
			if( !rolesMap.containsKey(id) )
				throw new AuthenticationException("Update Failed: No Role with id " + id + " exists.");
			
			rolesMap.put(id, updatedRole);
		
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
	}
	
	/**
	 * Updates a permission object using a new Permission DTO.
	 * @param token authentication token
	 * @param id identifier of the Permission object to be updated
	 * @param updatedPermission Permission DTO to be used in the update
	 */
	public void updatePermission(AuthenticationToken token, String id, Permission updatedPermission){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AccessDeniedException("Access Denied: Cannot Update Permission");
			
			if( !permissionsMap.containsKey(id) )
				throw new AuthenticationException("Update Failed: No Permission with id " + id + " exists.");
			
			permissionsMap.put(id, updatedPermission);
		
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}
		
	}
	
	/**
	 * Deletes a Service object from the map of services.
	 * @param token authentication token
	 * @param id identifier of the Service object to delete
	 */
	public void deleteService(AuthenticationToken token, String id){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AuthenticationException("Access Denied: Cannot Delete Service.");
			
			if( !servicesMap.containsKey(id) )
				throw new UserException("Delete Failed: No Service with id " + id + " exists.");
			
			servicesMap.remove(id);
			
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
	}
	
	/**
	 * Deletes a Role object from the map of roles.
	 * @param token authentication token
	 * @param id identifier of the Role object to delete
	 */
	public void deleteRole(AuthenticationToken token, String id){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AuthenticationException("Access Denied: Cannot Delete Role.");
			
			if( !rolesMap.containsKey(id) )
				throw new UserException("Delete Failed: No Role with id " + id + " exists.");
			
			rolesMap.remove(id);
			
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
	
	}
	
	/**
	 * Deletes a Permission object from the map of permissions.
	 * @param token authentication token
	 * @param id identifier of the Permission object to delete
	 */
	public void deletePermission(AuthenticationToken token, String id){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AuthenticationException("Access Denied: Cannot Delete Permission.");
			
			if( !permissionsMap.containsKey(id) )
				throw new UserException("Delete Failed: No Permission with id " + id + " exists.");
			
			permissionsMap.remove(id);
			
		}catch(AuthenticationException ae){
			System.out.println(ae.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
	
	}
	
	/*
	 * Getters
	 */
	public Map<String, Service> getServicesMap(){
		return servicesMap;
	}
	
	public Map<String, Role> getRolesMap(){
		return rolesMap;
	}
	
	public Map<String, Permission> getPermissionsMap(){
		return permissionsMap;
	}
	
}