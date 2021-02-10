package squaredesk.renter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import squaredesk.provider.OfficeSpace;
import squaredesk.provider.Rating;

/**
 * OfficeSpaceVisitor
 * 
 * Visitor pattern class that defines an object that visits individual
 * office space instances and uses the office space attributes to get 
 * and store triples to add to the knowledge graph of the search engine.
 * 
 * @author Philip Lin
 */
public class OfficeSpaceVisitor{

	private List<String> triplesList = new ArrayList<String>();
		
	/**
	 * Visits the office space instance and adds Triples to a list to be used to  
	 * add triples to the knowledge graph of the seach engine.
	 * 
	 * @param OfficeSpace to be visited
	 */
	public void visitOfficeSpace(OfficeSpace visitedOS){
		
		//Go through all features and make Triples for available features
		for( String feature : visitedOS.getFeatures().getFeaturesMap().keySet() ){
			
			if( visitedOS.getFeatures().checkFeatureAvailability(feature) == true )
				triplesList.add(visitedOS.getIdentifier() + " has_feature " + feature);
		}
		
		//Go through the locations and make Triples for location city, state, and zip code
		triplesList.add(visitedOS.getIdentifier() + " has_city " + visitedOS.getLocation().getAddressPart("city"));
		triplesList.add(visitedOS.getIdentifier() + " has_state " + visitedOS.getLocation().getAddressPart("state"));
		triplesList.add(visitedOS.getIdentifier() + " has_zip " + visitedOS.getLocation().getAddressPart("zip"));
		
		//Get the facility type of the office space and make a Triple
		triplesList.add(visitedOS.getIdentifier() + " is_facility_type " + visitedOS.getFacility().getType());
		
		//Get the average rating (to nearest 1/2 star) of the office space.
		//Average ratings equal to or lower than the actual average rating will also added to the KnowledgeGraph as min_rating triples.
		double sum = 0;
		double total = visitedOS.getRatings().size();
		double averageRating, averageRatingToHalf;
		
		if( total == 0 )
			averageRating = 0;
		else{
			
			for( Rating rating : visitedOS.getRatings() )
				sum += rating.getStars();
			
			averageRating = sum/total;
		}
		
		for( averageRatingToHalf = Math.round(averageRating * 2)/2 ; averageRatingToHalf >= 0 ; averageRatingToHalf -= 0.5 )
			triplesList.add(visitedOS.getIdentifier() + " has_min_rating " + averageRatingToHalf);
		
	}
	
	/**
	 * Creates the N-Triples file used to import attribute Triples of the OfficeSpace to the KnowledgeGraph
	 * 
	 * @throws FileNotFoundException if N-Triples file with triples can't be located
	 */
	public void createTriplesFile() throws FileNotFoundException{
		
		//create N-Triples file and write all Triples in triplesList to file
		PrintWriter out = new PrintWriter("inputTriples.nt");
		
		for( String triple : triplesList )
			out.println(triple);
		
		out.close();
	}
	
}