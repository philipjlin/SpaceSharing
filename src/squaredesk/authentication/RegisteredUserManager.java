package squaredesk.authentication;

import java.util.HashMap;
import java.util.Map;

import squaredesk.renter.UserException;

/**
 * The RegisteredUserManager class manages a map of all the registered users,
 * including the CRUD operations of Registered Users. Only one instance of 
 * RegisteredUserManager is used in the program (singleton pattern).
 * @author Philip Lin
 */
public class RegisteredUserManager{

	private static RegisteredUserManager registeredUserManager = new RegisteredUserManager();
	
	private Map<String, RegisteredUser> registeredUsersMap = new HashMap<String, RegisteredUser>();
	
	/**
	 * Static method to obtain single instance of RegisteredUserManager.
	 * Follows the singleton design pattern.
	 * @return singleton RegisteredUserManager instance
	 */
	public static RegisteredUserManager getInstance(){

		return registeredUserManager;
	}
	
	/**
	 * Creates new RegisteredUser objects from a CSV file and adds them to the map of registered users.
	 * @param CSVReader object used to read input file
	 */
	public void createRegisteredUsers(CSVReader reader){
				
		reader.createUsersFromFile();
		
		registeredUsersMap = reader.getRegisteredUsersMap();				
	}
	
	/**
	 * Adds role objects to lists maintained by their corresponding registered users using the CSV file.
	 * @param CSVReader object used to read input file
	 */
	public void addRolesToUsers(CSVReader reader){

		reader.addRolesToUsersFromFile();
		
		registeredUsersMap = reader.getRegisteredUsersMap();
	}
	
	/**
	 * Returns the RegisteredUser object identified by the input identifier.
	 * @param id identifier of the registered user to retrieve
	 * @return RegisteredUser retrieved
	 */
	public RegisteredUser retrieveRegisteredUser(String id){
		
		try{
			
			if( !registeredUsersMap.containsKey(id) )
				throw new AuthenticationException("Permission Failed: No Registered User with id " + id + " exists.");
						
			return registeredUsersMap.get(id);	
			
		}catch(AuthenticationException ae){
			
			System.out.println(ae.getMessage());
		}
		
		return null;
	
	}
	
	/**
	 * Updates the RegisteredUser object with a new DTO RegisteredUser.
	 * @param token authentication token
	 * @param id identifier of the registered user to update
	 * @param updated RegisteredUser DTO to be used in the update
	 */
	public void updateRegisteredUser(AuthenticationToken token, String id, RegisteredUser updated){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) && token.getState() )
				throw new AccessDeniedException("Access Denied: Cannot Update Registered User.");
			
			if( !registeredUsersMap.containsKey(id) )
				throw new AuthenticationException("Update Failed: No Registered User with id " + id + " exists.");
			
			registeredUsersMap.put(id, updated);
		
		}catch(AccessDeniedException ade){
			
			System.out.println(ade.getMessage());
			
		}catch(AuthenticationException ae){
			
			System.out.println(ae.getMessage());
		}

	}
	
	/**
	 * Deletes the RegisteredUser object identified by the input identifier.
	 * @param token authentication token
	 * @param id identifier of the registered user to delete
	 */
	public void deleteRegisteredUser(AuthenticationToken token, String id){
		
		try{
			
			if( !(token.getPrivileges().contains("AUTHENTICATION_ADMIN_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN") && token.getState() ) )
				throw new AuthenticationException("Access Denied: Cannot Delete Registered User.");
			
			if( !registeredUsersMap.containsKey(id) )
				throw new UserException("Delete Failed: No Registered User with id " + id + " exists.");
			
			registeredUsersMap.remove(id);
			
		}catch(AuthenticationException ae){
			
			System.out.println(ae.getMessage());
			
		}catch(UserException ue){
			
			System.out.println(ue.getMessage());
		}

	}
	
	/*
	 * Getter
	 */
	public Map<String, RegisteredUser> getRegisteredUsersMap(){
		return registeredUsersMap;
	}

}