package knowledgeengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@SuppressWarnings("resource")

/**
 * QueryEngine class
 * Handles and reads content of input query text files. Individual Strings queries are
 * extracted individually from the input file and placed in a list to be used by the 
 * KnowledgeGraph.
 * 
 * The queries are used by the KnowledgeGraph class to map queries to Triples.
 * 
 * @author Philip Lin
 */
public class QueryEngine{
	
	String query;
	private Map<String, List<String>> queryResultsMap = new HashMap<String, List<String>>();
	  
	/**
	 * Executes and prints an individual query using the KnowledgeGraph.
	 * The results of the query are printed if the query exists in the KnowledgeGraph.
	 * 
	 * @param query String query to execute
	 */
	public void executeQuery(String query){
				
		//check if query is in the KnowledgeGraph
		if( KnowledgeGraph.getInstance().getQueryMapSet().containsKey(query) ){
			
			//add results to map
			for( Triple t : KnowledgeGraph.getInstance().executeQuery(query) )
	    			queryResultsMap.get(query).add(t.getIdentifier());
			
		}
		else
			queryResultsMap.get(query).add("<null>");
		
	}
  
	/**
	 * Imports the query file specified by the filename. The input file is scanned
	 * with a Scanner object using a delimiter, which produces individual queries.
	 * These queries are checked for proper formatting and passed into a List that
	 * the KnowledgeGraph class uses to construct its query Map.
	 * 
	 * An exception is thrown if any of the query Strings are improperly formatted
	 * An exception is also thrown if the file to import can't be found.
	 * 
	 * @param filename name of the input file with triples
	 */
	public void importQueryFile(String filename){
	
		try{
	      
			File file = new File(filename);
	
			Scanner importedQueries = new Scanner(file).useDelimiter("\\n"); //split file by "."
	
			while( importedQueries.hasNext() ){ //iterate through all queries
	
				query = importedQueries.next().toLowerCase().trim();
				
				 //check that query isn't a blank line at end of file and is properly formatted
				if( !query.equals("") ){
	
					if ( query.matches("(\\w.+|\\?)(\\s)(\\w.+|\\?)(\\s)(\\w.+|\\?)") )
						queryResultsMap.put(query, new ArrayList<String>());
					else
						throw new QueryEngineException(); //throw exception if improperly formatted	
				}
			}
	    }
	    catch( FileNotFoundException fnfe ){
	
	    		System.out.println("Exception: Query File Not Found");
	    }
	    catch( QueryEngineException qee ){
	    		
	    		System.out.println("Exception@Line: " + query);
	    }
	
	}
  
	/**
	 * Method for executing all queries in a query file.
	 */
	public void executeAllQueries(){
		
		for( String query : queryResultsMap.keySet() )
    			executeQuery(query);

	}
	
	public Map<String, List<String>> getQueryResultsMap(){
		
		return queryResultsMap;
	}
  
}