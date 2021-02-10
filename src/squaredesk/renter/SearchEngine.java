package squaredesk.renter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import knowledgeengine.*;
import squaredesk.authentication.AccessDeniedException;
import squaredesk.authentication.AuthenticationToken;
import squaredesk.provider.*;

/**
 * The Search Engine class leverages the KnowledgeGraph API to implement a method 
 * of searching for office spaces using any number of input String queries.
 * 
 * Only one instance of SearchEngine is used in the program (singleton pattern).
 * @author Philip Lin
 */
public class SearchEngine{
		
	private static SearchEngine searchEngine = new SearchEngine();
	
	/**
	 * Static method to obtain single instance of SearchEngine
	 * @return singleton SearchEngine instance
	 */
	public static SearchEngine getInstance(){

		return searchEngine;
	}
	
	/**
	 * When a search is performed, all the the OfficeSpaceVisitor obtains
	 * all the Triples from all current and updated OfficeSpace instances
	 * and adds them to the KnowledgeGraph using the N-Triple file.
	 * Then, queries from a Text file that are obtained from the Criteria
	 * object of the Renter are executed against the KnowledgeGraph.
	 * @param token Authentication token
	 * @param criteria Criteria object used to search for office spaces
	 */
	public void search(AuthenticationToken token, Criteria criteria){
			
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Use Search Function.");
				
		}catch(AccessDeniedException ade){
			System.out.println(ade.getExceptionDetails());
		}
		
		//Step 1: Create new instance of OfficeSpaceVisitor
		OfficeSpaceVisitor visitor = new OfficeSpaceVisitor();
		
		//Step 2: Go through and visit all OfficeSpace instances maintained by the OfficeSpaceManager instance
		for( OfficeSpace os : OfficeSpaceManager.getInstance().getOfficeSpaceMap().values() ){
			
			visitor.visitOfficeSpace(os);
			
			if( os.checkAvailability(criteria.getStartDate(), criteria.getEndDate()) )
				System.out.println(os.getIdentifier() + " is available for " + criteria.getStartDate() + " to " + criteria.getEndDate() + "\n");
		}
		
		//Step 3: Use the visitor to create the Triples file that contains all OfficeSpace info
		try{
			
			visitor.createTriplesFile();
			
		}catch(FileNotFoundException fnfe){
			System.out.println("Exception: Triples File Not Found.");
		}
		
		//Step 4: Import the Triples file to the KnowledgeGraph
		Importer importer = new Importer();
		importer.importTripleFile("inputTriples.nt");
	    KnowledgeGraph.getInstance().importTriples(importer.getTripleIdentifierList());
	    
	    //Step 5: Use the criteria input into the search engine to create a queries file
		try {
			
			criteria.createCriteriaQueriesFile();
			
		}catch(FileNotFoundException fnfe){
			System.out.println("Exception: Input Queries File Not Found.");
		}
		
		//Step 6: Use the query file in the query engine to find results of each query
		QueryEngine queryEngine = new QueryEngine();
	    queryEngine.importQueryFile("inputQueries.txt");
	    queryEngine.executeAllQueries(); //prints results of search
	}
	
	/**
	 * Searches for available Office Spaces that match all input queries as well
	 * by checking to see if all queries from the criteria are matched by the triples
	 * from one individual office space.
	 * @param token Authentication token
	 * @param Criteria object used to search for office spaces
	 * @return List of all office spaces that match all criteria
	 */
	public List<OfficeSpace> searchPerfectMatches(AuthenticationToken token, Criteria criteria){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Use Perfect Match Search Function.");
				
		}catch(AccessDeniedException ade){
			System.out.println(ade.getExceptionDetails());
		}
		
		
		Map<String, List<String>> qrm = new HashMap<String, List<String>>();
		
		List<OfficeSpace> perfectMatches = new ArrayList<OfficeSpace>();
		int counter = 0;
		
		OfficeSpaceVisitor visitor = new OfficeSpaceVisitor();
				
		for( OfficeSpace os : OfficeSpaceManager.getInstance().getOfficeSpaceMap().values() ){
			
			visitor.visitOfficeSpace(os);
			
			if( os.checkAvailability(criteria.getStartDate(), criteria.getEndDate()) )
				System.out.println(os.getIdentifier() + " is available for " + criteria.getStartDate() + " to " + criteria.getEndDate() + "\n"); //print
			
			try{ 
				
				visitor.createTriplesFile(); 
				
			}catch(FileNotFoundException fnfe){
				
				System.out.println("Exception: Triples File Not Found.");
			}
			
			Importer importer = new Importer();
			importer.importTripleFile("inputTriples.nt");
		    KnowledgeGraph.getInstance().importTriples(importer.getTripleIdentifierList());
		    
		    try { 
		    	
		    		criteria.createCriteriaQueriesFile(); 
		    	
		    }catch(FileNotFoundException fnfe){
		    	
				System.out.println("Exception: Input Queries File Not Found.");
		    }
		    
		    QueryEngine queryEngine = new QueryEngine();
		    queryEngine.importQueryFile("inputQueries.txt");
		    queryEngine.executeAllQueries(); //prints results of search
		    qrm = queryEngine.getQueryResultsMap();
					    
		    for( List<String> queryResultList : queryEngine.getQueryResultsMap().values() ){
		    		
		    		for( String result : queryResultList ){
		    			
		    			if( result.toLowerCase().contains( os.getIdentifier().toLowerCase() ) ){
		    				
		    				counter++;
		    				break;
		    			}
		    		}
		    }

		    if( counter == queryEngine.getQueryResultsMap().keySet().size() && os.checkAvailability(criteria.getStartDate(), criteria.getEndDate()) )
		    		perfectMatches.add(os); 
		}
		
		//print results
		for( String query : qrm.keySet() ){
			
			System.out.println(query);
			System.out.println(qrm.get(query) + "\n");
		}
		
		System.out.print("Perfect Matches: ");
		
		for( OfficeSpace os : perfectMatches )
			System.out.println(os.getIdentifier());
		System.out.println("\n");
		
		return perfectMatches; 
	}
	
}	