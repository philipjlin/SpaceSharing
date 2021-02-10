package squaredesk.provider;

/**
 * The Capacity class keeps track of capacity information of the Office Space, including 
 * the maximum amount of people, size of the space, and the number of available workspaces.
 * 
 * @author Philip Lin
 */
public class Capacity {

	private int people;
	private int size;	
	private int workspaces;
	
	/**
	 * Constructor
	 */
	public Capacity(){
		
		people = 0;
		size = 0;
		workspaces = 0;
	}

	/**
	 * Returns the maximum capacity.
	 */
	public int getPeople(){ 
		
		return people; 
	}
	
	/**
	 * Sets the maximum capacity.
	 */
	public void setPeople(int people){
		
		this.people = people;
	}
	
	/**
	 * Returns the size.
	 */
	public int getSize(){ 
		
		return size; 
	}
	
	/**
	 * Sets the size.
	 */
	public void setSize(int size){
		
		this.size = size;
	}
	
	/**
	 * Returns the number of individual workspaces.
	 */
	public int getWorkspaces(){ 
		
		return workspaces; 
	}
	
	/**
	 * Sets the number of individual workspaces.
	 */
	public void setWorkspaces(int workspaces){
		
		this.workspaces = workspaces;
	}	

}