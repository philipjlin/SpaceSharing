package knowledgeengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@SuppressWarnings("resource")

/**
 * Importer Class
 * Handles and reads the content of input N-Triple text files. Individual Strings of 
 * Triple identifiers are extracted individually from the input file and placed in 
 * a list to be used by the KnowledgeGraph.
 * 
 * Actual creation of Triple objects, Node objects, and Predicate objects is handled
 * by the KnowledgeGraph class.
 * 
 * @author Philip Lin
 */
public class Importer{
	
	private List<String> tripleIdentifierList = new ArrayList<String>();
	String identifier;

	/**
	 * Imports the N-Triple file specified by the filename. The input file is scanned
	 * with a Scanner object using a delimiter, which produces individual identifier Strings
	 * that represent a Triple. These identifiers are checked for proper formatting and 
	 * passed into a List that the KnowledgeGraph class uses to construct a set of 
	 * unique Triples with those identifiers.
	 * 
	 * An exception is thrown if any of the identifier Strings are improperly formatted
	 * An exception is also thrown if the file to import can't be found.
	 * 
	 * @param filename name of the input file with triples
	 */
	 public void importTripleFile(String filename){
		 
		 try{
			 
			 File file = new File(filename);
	
			 Scanner importedIdentifiers = new Scanner(file).useDelimiter("\\n"); //split file by "."
	
			 while( importedIdentifiers.hasNext() ){ //iterate through all identifiers
				 
				 identifier = importedIdentifiers.next().toLowerCase().trim();
				 
				 //check that identifier isn't a blank line at end of file and is properly formatted
				 if( !identifier.equals("") ){
	
					 if ( identifier.matches("(\\w.+)(\\s)(\\w.+)(\\s)(\\w.+)") )
						 getTripleIdentifierList().add(identifier);
					 else
						 throw new ImportException(); //throw exception if improperly formatted
				 }
	
			 }
	      
		 }
		 catch( FileNotFoundException fnfe ){
			 
			 System.out.println("Exception: Triple File Not Found");
		 }
	     catch( ImportException ie ){
	
	    	 	System.out.println("Exception@Line: " + identifier);
	    	 	ie.printStackTrace();
	     }
	
	 }
  
	  /***
	   * Getter method
	   * 
	   * @return list of Triple identifiers the KnowledgeGraph will use to build Triple database
	   */
	  public List<String> getTripleIdentifierList(){
	
		  return tripleIdentifierList;
	  }
	  
}