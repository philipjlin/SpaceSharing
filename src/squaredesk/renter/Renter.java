package squaredesk.renter;

import java.util.ArrayList;
import java.util.List;

import squaredesk.provider.Rating;

/**
 * The Renter class represents a user that is a renter in the SquareDesk system.
 * 
 * @author Philip Lin
 */
public class Renter extends User{
	
	private Criteria criteria;
	private List<Rating> ratings;
	
	/**
	 * Constructor 
	 * 
	 * @param identifier identifier of Renter object
	 */
	public Renter(String identifier){
		
		super(identifier);
		
		criteria = new Criteria();
		ratings = new ArrayList<Rating>();
	}
	
	/**
	 * Returns the list of Ratings associated with the Renter.
	 */
	public List<Rating> getRatings(){
		
		return ratings;
	}
	
	/**
	 * Returns the Criteria associated with the Renter.
	 */
	public Criteria getCriteria(){
		
		return criteria;
	}
	
}