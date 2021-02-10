package squaredesk.provider;

import java.util.HashMap;
import java.util.Map;

import squaredesk.renter.UserException;
import squaredesk.authentication.AccessDeniedException;
import squaredesk.authentication.AuthenticationToken;

/**
 * The OfficeSpaceManager class manages a map of all the OfficeSpace objects, as well as 
 * the create, retrieve, update, and delete operations regarding instances of OfficeSpace.
 * 
 * Only one instance of OfficeSpaceManager is used in the program (singleton pattern).
 * @author Philip Lin
 */
public class OfficeSpaceManager {

	private Map<String, OfficeSpace> officeSpaceMap = new HashMap<String, OfficeSpace>();
	private static OfficeSpaceManager officeSpaceManager = new OfficeSpaceManager();
	
	/**
	 * Static method to obtain single instance of OfficeSpaceManager.
	 * Follows the singleton design pattern.
	 * @return singleton OfficeSpaceManager instance
	 */
	public static OfficeSpaceManager getInstance(){

		return officeSpaceManager;
	}
	
	/**
	 * Creates a new OfficeSpace object, as well as new objects for attributes of OfficeSpace.
	 * @param token authentication token needed for operation
	 * @param id identifier of the office space that is being created
	 */
	public void createOfficeSpace(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Acess Denied: Cannot Create OfficeSpace.");
			
			if( officeSpaceMap.containsKey(id) )
				throw new UserException("Creation Failed: Office space with id " + id + " already exists.");
			
			officeSpaceMap.put(id, new OfficeSpace(id));
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());		
		}catch(UserException ue){		
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Returns the OfficeSpace object identified by the input identifier from the map of office spaces.
	 * @param id identifier of the office space that is being retrieved
	 * @return OfficeSpace object retrieved, if operation is successful
	 */
	public OfficeSpace retrieveOfficeSpace(String id){ 
		
		try{
						
			if( !officeSpaceMap.containsKey(id) )
				throw new UserException("Retrive Failed: No OfficeSpace with id " + id + " exists.");
			
			return officeSpaceMap.get(id);
			
		}catch(UserException ue){		
			System.out.println(ue.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * Edits the OfficeSpace object identified by the input identifier from the map of office spaces.
	 * @param token authentication token needed for operation
	 * @param id identifier of the office space that is being updated
	 * @param updatedOS OfficeSpace DTO used to update the object in the Map of OfficeSpaces
	 */
	public void updateOfficeSpace(AuthenticationToken token, String id, OfficeSpace updatedOS){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Update OfficeSpace with id: "+ id);
			
			if( !officeSpaceMap.containsKey(id) )
				throw new UserException("Update Failed: No OfficeSpace with id " + id + " exists.");
			
			officeSpaceMap.put(id, updatedOS);
		
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Deletes the OfficeSpace object identified by the input identifier from the map of office spaces.
	 * @param token authentication token needed for operation
	 * @param id identifier of the office space that is being deleted
	 */
	public void deleteOfficeSpace(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Delete OfficeSpace with id: "+ id);
			
			if( !officeSpaceMap.containsKey(id) )
				throw new UserException("Delete Failed: No OfficeSpace with id " + id + " exists.");
			
			officeSpaceMap.remove(id);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Returns the Map of OfficeSpace objects
	 * @return Map of OfficeSpace objects with String keys.
	 */
	public Map<String, OfficeSpace> getOfficeSpaceMap(){
		
		return officeSpaceMap;
	}

}