package squaredesk.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The OfficeSpace class represents an office space with certain attributes that 
 * can be rented from a provider to a renter in ShareDesk.
 * 
 * @author Philip Lin
 */
public class OfficeSpace {
		
	private String identifier;
	private List<Rating> ratings;
	private List<Image> images;
	private Rate rate;
	private Location location;
	private Features features;
	private Capacity capacity;
	private Facility facility;
	
	private boolean[] availabilityCalendar;
	
	/**
	 * Constructor
	 * 
	 * @param identifier identifier of the OfficeSpace object
	 */
	public OfficeSpace(String identifier){
		
		this.identifier = identifier;
		ratings = new ArrayList<Rating>();
		images = new ArrayList<Image>();
		
		rate = new Rate();
		location = new Location();
		features = new Features();
		capacity = new Capacity();
		facility = new Facility();
		
		availabilityCalendar = new boolean[366];
		Arrays.fill(availabilityCalendar, true);
	}
	
	
	public String getIdentifier(){
		
		return identifier;
	}
	
	/*
	 * Getters for object attributes with own getters/setters
	 */
	public List<Rating> getRatings(){
		
		return ratings;
	}
	
	public List<Image> getImages(){
		
		return images;
	}
	
	public Rate getRate(){
		
		return rate;
	}
	
	public Location getLocation(){
		
		return location;
	}
	
	public Features getFeatures(){
		
		return features;
	}
	
	public Capacity getCapacity(){
		
		return capacity;
	}
	
	public Facility getFacility(){
		
		return facility;
	}
	
	
	/*
	 * Availability Methods
	 */
	public boolean checkAvailability(String startDate, String endDate){
		
		Arrays.copyOfRange(availabilityCalendar, convertDateToNumber(startDate), convertDateToNumber(endDate));
		
		for( boolean b : availabilityCalendar )	
			if( !b )
				return false;
		
		return true;
	}
	
	public void setBookedDates(String startDate, String endDate){
		
		Arrays.fill(availabilityCalendar, convertDateToNumber(startDate), convertDateToNumber(endDate), false); 
	}
	
	public void removeBookedDates(String startDate, String endDate){
		
		Arrays.fill(availabilityCalendar, convertDateToNumber(startDate), convertDateToNumber(endDate), true);
	}
	
	public int convertDateToNumber(String date){
		
		int month = Integer.parseInt(date.split("/")[0]);
		int day 	= Integer.parseInt(date.split("/")[1]);
		int number = 0;
		
		switch( month ){
		
			case 1: return number = day;
			case 2: number = 31 + day;
					break;
			case 3:	number = 59 + day;
					break;
			case 4:	number = 90 + day;
					break;
			case 5: number = 120 + day;
					break;
			case 6: number = 151 + day;
					break;
			case 7: number = 181 + day;
					break;
			case 8: number = 212 + day;
					break;
			case 9: number = 242 + day;
					break;
			case 10:number = 273 + day;
					break;
			case 11:number = 303 + day;
					break;
			case 12:number = 334 + day;
					break;
		}
		return number;
	}
}