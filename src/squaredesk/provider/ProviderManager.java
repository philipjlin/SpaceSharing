package squaredesk.provider;

import java.util.HashMap;
import java.util.Map;

import squaredesk.renter.UserException;
import squaredesk.authentication.AccessDeniedException;
import squaredesk.authentication.AuthenticationToken;

/**
 * The ProviderManager class manages a map of all the Provider objects, as well as the 
 * create, retrieve, update, and delete operations regarding instances of Provider.
 * 
 * Only one instance of ProviderManager is used in the program (singleton pattern).
 * @author Philip Lin
 */
public class ProviderManager{

	private Map<String, Provider> providerMap = new HashMap<String, Provider>();
	private static ProviderManager providerManager = new ProviderManager();
	
	/**
	 * Static method to obtain single instance of ProviderManager.
	 * Follows the singleton design pattern.
	 * @return singleton ProviderManager instance
	 */
	public static ProviderManager getInstance(){

		return providerManager;
	}
	
	/**
	 * Creates a new Provider object, as well as new objects for classes Provider references.
	 * @param token authentication token needed for operation
	 * @param id identifier of the provider that is being created
	 */
	public void createProvider(AuthenticationToken token, String id){
		
		try{
		
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Create Provider.");
			
			if( providerMap.containsKey(id) )
				throw new UserException("Creation Failed: Provider with id " + id + " already exists.");
			
			providerMap.put(id, new Provider(id));
							
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}

	}
	
	/**
	 * Returns the Provider object identified by the input identifier from the map of providers.
	 * @param id identifier of the provider that is being retrieved
	 * @return Provider object retrieved, if operation is successful
	 */
	public Provider retrieveProvider(String id){
		
		try{
			
			if( !providerMap.containsKey(id) )
				throw new UserException("Retrive Failed: No Provider with id " + id + " exists.");
			
			return providerMap.get(id);

		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
		return null;
		
	}
	
	/**
	 * Edits the Provider object identified by the input identifier from the map of providers.
	 * 
	 * @param token authentication token needed for operation
	 * @param id identifier of the provider that is being updated
	 * @param updatedProvider Provider DTO used to update the object in the Map of Providers
	 */
	public void updateProvider(AuthenticationToken token, String id, Provider updatedProvider){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Update Provider with id: "+ id);
			
			if( !providerMap.containsKey(id) )
				throw new UserException("Update Failed: No Provider with id " + id + " exists.");
			
			providerMap.put(id, updatedProvider);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Deletes the Provider object identified by the input identifier from the map of providers.
	 * @param token authentication token needed for operation
	 * @param id identifier of the provider that is being deleted
	 */
	public void deleteProvider(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("PROVIDER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Delete Provider with id: "+ id);
			
			if( !providerMap.containsKey(id) )
				throw new UserException("Delete Failed: No Provider with id " + id + " exists.");
			
			providerMap.remove(id);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
		
	}
	
	/**
	 * Returns the Map of Provider objects
	 * @return Map of Provider objects with String keys.
	 */
	public Map<String, Provider> getproviderMap(){
		
		return providerMap;
	}
}