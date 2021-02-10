package squaredesk.renter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Criteria class is used to define an object that contains search criteria 
 * for looking up available office spaces using the search engine. The criteria 
 * are stored as a list of different String queries to be executed against the 
 * knowledge graph of the search engine.
 * 
 * @author Philip Lin
 */
public class Criteria{
	
	private List<String> features;
	private String city;
	private String state;
	private String zip;
	private String facilityType;
	private double minRating;
	
	private String startDate;
	private String endDate;
	
	private List<String> criteriaQueryList = new ArrayList<String>();
	
	/**
	 * Constructor
	 */
	public Criteria(){
		
		features = new ArrayList<String>();
		state = "";
		city = "";
		zip = "";
		facilityType = "";
		minRating = 0;
		
		startDate = "";
		endDate = "";
	}
	
	/**
	 * Adds queries for desired features to the list of criteria queries.
	 */
	public void addFeaturesQueries(){
		
		for( String feature : features )
			criteriaQueryList.add("? has_feature " + feature);
	}
	
	/**
	 * Adds queries for desired location information to the list of criteria queries.
	 */
	public void addLocationQuery(){
		
		criteriaQueryList.add("? has_state " + state);
		criteriaQueryList.add("? has_city " + city);
		criteriaQueryList.add("? has_zip " + zip);
	}
	
	/**
	 * Adds query for desired facility type to the list of criteria queries.
	 */
	public void addFacilityTypeQuery(){
		
		criteriaQueryList.add("? is_facility_type " + facilityType);
	}
	
	/**
	 * Adds query for minimum rating to the list of criteria queries.
	 */
	public void addMinRatingQuery(){
		
		criteriaQueryList.add("? has_min_rating " + minRating);
	}
	
	/**
	 * Creates the text file used to import Renter criteria queries to
	 * check against the KnowledgeGraph.
	 * 
	 * @throws FileNotFoundException if text file with queries can't be located
	 */
	public void createCriteriaQueriesFile() throws FileNotFoundException{
		
		//add queries to the list of criteria queries
		addFeaturesQueries();
		addLocationQuery();
		addFacilityTypeQuery();
		addMinRatingQuery();
		
		//create text file and write all criteria queries from this Criteria object to file
		PrintWriter out = new PrintWriter("inputQueries.txt");
		
		for( String criteriaQuery : criteriaQueryList )
			out.println(criteriaQuery);
			
		out.close();
	}
	
	/*
	 * Getters and Setters
	 */
	public List<String> getFeatures(){
		
		return features;
	}
	
	public void addFeature(String feature){
		
		features.add(feature);
	}
	
	public String getCity(){
		
		return city;
	}
	
	public void setCity(String city){
		
		this.city = city;
	}
	
	public String getState(){
		
		return state;
	}
	
	public void setState(String state){
		
		this.state = state;
	}
	
	public String getZip(){
		
		return zip;
	}
	
	public void setZip(String zip){
		
		this.zip = zip;
	}
	
	public double getMinRating(){
		
		return minRating;
	}
	
	public void setMinRating(double minRating){
		
		this.minRating = minRating;
	}
	
	public String getFacilityType(){
		
		return facilityType;
	}
	
	public void setFacilityType(String facilityType){
		
		this.facilityType = facilityType;
	}
	
	/*
	 * Availability Getters and Setters
	 */
	public String getStartDate(){
		
		return startDate;
	}
	
	public String getEndDate(){
		
		return endDate;
	}
	
	public void setStartandEndDates(String startDate, String endDate){
		
		this.startDate = startDate;
		this.endDate = endDate;
	}

}