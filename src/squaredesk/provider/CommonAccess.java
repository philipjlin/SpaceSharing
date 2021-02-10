package squaredesk.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * The Common Access class keeps track of areas or items that are available 
 * for common use in the facility where an Office Space is located.
 * A common access could be a kitchen, coffee, game room, etc.
 * 
 * @author Philip Lin
 */
public class CommonAccess {
	
	private Map<String, Boolean> commonAccessMap; 
	
	/**
	 * Constructor
	 */
	public CommonAccess(){
		
		commonAccessMap = new HashMap<String, Boolean>();
	}
	
	/**
	 * Adds a common access area or item to the map of common access areas and items.
	 */
	public void addCommonAccess(String ca){
		
		commonAccessMap.put(ca, Boolean.FALSE);
	}
	
	/**
	 * Returns whether a particular common access area or item is available using the map.
	 */
	public boolean checkCommonAccessAvailability(String ca){
		
		return commonAccessMap.get(ca).booleanValue();
	}
	
	/**
	 * Changes the availability of a particular common access item in the map.
	 */
	public void editCommonAccessAvailability(String ca, Boolean availability){
		
		commonAccessMap.put(ca, availability);
		
	}
	
	/**
	 * Returns the map of common access items.
	 */
	public Map<String, Boolean> getCommonAccessMap(){
		
		return commonAccessMap; 
	} 	

}