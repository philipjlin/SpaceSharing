package squaredesk.provider;

import java.util.HashMap;
import java.util.Map;

/**
 * The Facility class is used to describe the office space as a type of facility with
 * one or more rooms. The facility type could be house, garage, warehouse, etc.
 * 
 * @author Philip Lin
 */
public class Facility {
	
	private String type;
	private Map<String, Boolean> roomsMap;
	
	/**
	 * Constructor
	 */
	public Facility(){
		
		type = "";
		roomsMap = new HashMap<String, Boolean>();
	}
	
	/**
	 * Adds a room to the map of possible rooms in the facility.
	 */
	public void addRoom(String room){
		
		roomsMap.put(room, Boolean.FALSE);
	}
	
	/**
	 * Returns whether a particular room is available in the facility using the map.
	 */
	public boolean checkRoomAvailability(String room){ 
		
		return roomsMap.get(room).booleanValue(); 
	}	
	
	/**
	 * Changes the availability of a particular room in the map.
	 */
	public void editRoomAvailability(String room, Boolean availability){
		
			roomsMap.put(room, availability);
	}
	
	/**
	 * Returns the map of possible rooms in the facility.
	 */
	public Map<String, Boolean> getRoomsMap(){ 
		
		return roomsMap; 
	}
	
	/**
	 * Gets the type of facility.
	 */
	public String getType(){
		
		return type;
	}
	
	/**
	 * Sets the type of facility.
	 */
	public void setType(String type){
		
		this.type = type;
	}

}