package squaredesk.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * The Features class keeps track of features that are available in an Office Space for rent. 
 * Features in an office space could include WIFI, parking, or pets being allowed. The set 
 * of Common Access areas are also a part of the features of an office space.
 * 
 * @author Philip Lin
 */
public class Features {
	
	private Map<String, Boolean> featuresMap;
	private CommonAccess commonAccess;
	
	/**
	 * Constructor
	 */
	public Features(){
		
		featuresMap = new HashMap<String, Boolean>();
		commonAccess = new CommonAccess();
	}
	
	/**
	 * Adds a feature to the map of features in the office space.
	 */
	public void addFeature(String feature){
		
		featuresMap.put(feature, Boolean.FALSE);
	}
	
	/**
	 * Returns whether a particular feature is available in the office space using the map.
	 */
	public boolean checkFeatureAvailability(String feature){ 
		
		return featuresMap.get(feature).booleanValue();
	}
	
	/**
	 * Changes the availability of a particular feature item in the map.
	 */
	public void editFeatureAvailability(String feature, Boolean availability){
		
		featuresMap.put(feature, availability);
	}
	
	/**
	 * Returns the map of features.
	 */
	public Map<String, Boolean> getFeaturesMap(){ 
		
		return featuresMap; 
	}
	
	/**
	 * Returns the common access object.
	 */
	public CommonAccess getCommonAccess(){
		
		return commonAccess;
	}

}