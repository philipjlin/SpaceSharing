package squaredesk.renter;

import java.util.HashMap;
import java.util.Map;

import squaredesk.authentication.AccessDeniedException;
import squaredesk.authentication.AuthenticationToken;

/**
 * The RenterManager class manages a map of all the Renter objects, as well as the 
 * create, retrieve, update, and delete operations regarding instances of Renter.
 * 
 * Only one instance of RenterManager is used in the program (singleton pattern).
 * 
 * @author Philip Lin
 */
public class RenterManager {

	private static RenterManager renterManager = new RenterManager();
	
	private Map<String, Renter> renterMap = new HashMap<String, Renter>();
	
	/**
	 * Static method to obtain single instance of RenterManager.
	 * Follows the singleton design pattern.
	 * @return singleton RenterManager instance
	 */
	public static RenterManager getInstance(){

		return renterManager;
	}
	
	/**
	 * Creates a new Renter object, as well as new objects for classes Renter references.
	 * @param token authentication token needed for operation
	 * @param id identifier of the renter that is being created
	 */
	public void createRenter(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Create Renter.");
			
			if( renterMap.containsKey(id) )
				throw new UserException("Creation Failed: Renter with id " + id + " already exists.");
			
			renterMap.put(id, new Renter(id));
							
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}

	}
	
	/**
	 * Returns the Renter object identified by the input identifier from the map of renters.
	 * @param id identifier of the renter that is being retrieved
	 * @return Renter object retrieved, if operation is successful
	 */
	public Renter retrieveRenter(String id){
		
		try{
			
			if( !renterMap.containsKey(id) )
				throw new UserException("Retrive Failed: No Renter with id " + id + " exists.");
			
			return renterMap.get(id);
			
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * Edits the Renter object identified by the input identifier from the map of renters.
	 * @param token authentication token needed for operation
	 * @param id identifier of the renter that is being updated
	 * @param updatedRenter Renter DTO used to update the object in the map of renters
	 */
	public void updateRenter(AuthenticationToken token, String id, Renter updatedRenter){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Update Renter with id: "+ id);
			
			if( !renterMap.containsKey(id) )
				throw new UserException("Update Failed: No Renter with id " + id + " exists.");
			
			renterMap.put(id, updatedRenter);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Deletes the Renter object identified by the input identifier from the map of renters.
	 * @param token authentication token needed for operation
	 * @param id identifier of the renter that is being deleted
	 */
	public void deleteRenter(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Delete Renter with id: "+ id);
			
			if( !renterMap.containsKey(id) )
				throw new UserException("Delete Failed: No Renter with id " + id + " exists.");
			
			renterMap.remove(id);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Returns the Map of Renter objects
	 */
	public Map<String, Renter> getRenterMap(){
		
		return renterMap;
	}
}