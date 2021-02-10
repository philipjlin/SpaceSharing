package knowledgeengine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * KnowledeGraph class
 * Manages the set of active Triples and the queries that match those Triples.
 * Instances of Nodes, Predicate, Triples are created by the KnowledgeGraph using 
 * the information from input triple file, the Importer class reads. 
 * 
 * Nodes, Predicates, and Triples are stored in separate Maps with their identifiers as 
 * keys. Only one unique instance of each Node, Predicate, and Triple will be kept in their
 * respective maps. They are accessed using the getter methods with identifiers.
 * 
 * KnowledgeGraph also maintains a Map of queries mapped to a Set of Triples that match
 * the query. Each query in the Map is unique, and queries are added to the Map based on
 * the input triples. Every possible unique query that would match a particular Triple will
 * be included in the Map.
 * 
 * The KnowledgeGraph is a singleton in the Singleton design pattern, with the
 * singular instance of KnowledgeGraph obtained by a static method.
 * 
 * @author Philip Lin
 */
public class KnowledgeGraph{
	
	private static KnowledgeGraph singleton = new KnowledgeGraph();
	
	String subject, predicate, object; //identifiers of Triple objects
	String[] splitIdentifier = new String[3];
	String[] queryVars = new String[8];

	private Map<String, Node> nodeMap = new HashMap<String, Node>();
	private Map<String, Predicate> predicateMap = new HashMap<String, Predicate>();
	private Map<String, Triple> tripleMap = new HashMap<String, Triple>();
	private Map<String, Set<Triple>> queryMapSet = new HashMap<String, Set<Triple>>();
	
	/**
	 * Static method to obtain single instance of KnowledgeGraph
	 * 
	 * @return singleton KnowledgeGraph instance
	 */
	public static KnowledgeGraph getInstance(){

		return singleton;
	}
	
	/**
	 * Imports Triples the KnowledgeGraph using a List of triple identifiers obtained from
	 * the Importer class.
	 * 
	 * Based on the existing contents of the Maps, it is then determined whether or not
	 * to define and add new Node, Predicate and Triple objects to their respective Maps.
	 * 
	 * Queries are also defined from the Triple identifiers, with every unique query associated
	 * with a Set of corresponding Triples. Queries are checked against the query Map for uniqueness
	 * and added to the query Map with corresponding Triples as required.
	 * 
	 * @param tripleList
	 */
	public void importTriples(List<String> tripleList){

		for( String triple : tripleList ){ //iterate through all triple identifiers

			splitIdentifier = triple.trim().split(" "); //split triple identifier into 3 parts
			subject = splitIdentifier[0];
			predicate = splitIdentifier[1];
			object = splitIdentifier[2];
			
			//add objects to map if it isn't already
			if( !nodeMap.containsKey(subject) )
				nodeMap.put(subject, new Node(subject));

			if( !predicateMap.containsKey(predicate) )
				predicateMap.put(predicate, new Predicate(predicate));

			if( !nodeMap.containsKey(object) )
				nodeMap.put(object, new Node(object));

			if( !tripleMap.containsKey(triple))	
				tripleMap.put(triple, new Triple(triple, getNode(subject), getPredicate(predicate), getNode(object)));
		
			//variations of queries that match current Triple 
			queryVars[0] = triple;
			queryVars[1] = "? " + predicate + " " + object;
			queryVars[2] = subject + " ? " + object;
			queryVars[3] = subject + " " + predicate + " ?";
			queryVars[4] = "? ? " + object;
			queryVars[5] = "? " + predicate + " ?";
			queryVars[6] = subject + " ? ?";
			queryVars[7] = "? ? ?";
	
			for( String q : queryVars ){	//iterate through all query variations
				
				//add query to map if it isn't already
				if( !queryMapSet.containsKey(q) )
					queryMapSet.put(q, new HashSet<Triple>());
				
				executeQuery(q).add(getTriple(triple)); //add Triple to the Set associated with the query
			}
		}

	}
	
	/**
	 * Executes a query on the KnowledgeGraph
	 * 
	 * @param query String query to execute
	 * @return Set of Triples that match the input query
	 */
	public Set<Triple> executeQuery(String query){

		return queryMapSet.get(query);
	}
	
	/**
	 * Getter method
	 * 
	 * @return Node in the node Map
	 */
	public Node getNode(String identifier){

		return nodeMap.get(identifier);
	}
	
	/**
	 * Getter method
	 * 
	 * @return Predicate in the predicate Map
	 */
	public Predicate getPredicate(String identifier){

		return predicateMap.get(identifier);
	}
	
	/**
	 * Getter method
	 * 
	 * @return Triple in the triple Map
	 */
	public Triple getTriple(String identifier){

		return tripleMap.get(identifier);
	}
	
	/**
	 * Getter method
	 * 
	 * @return HashMap of queries and corresponding Set of Triples
	 */
	public Map<String, Set<Triple>> getQueryMapSet(){
		
		return queryMapSet;
	}

}