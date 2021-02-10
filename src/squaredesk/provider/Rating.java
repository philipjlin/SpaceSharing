package squaredesk.provider;

/**
 * The Rating class describes feedback items that providers give to renters or that
 *  renters give to providers or office spaces. Ratings include stars as well as comments.
 * 
 * @author Philip Lin
 */
public class Rating{

	private int stars;
	private String author;
	private String comment;
	private String date;
	
	/**
	 * Constructor
	 */
	public Rating(){
		
		stars = 0;
		author = "";
		comment = "";
		date = "";
	}
	
	/**
	 * Constructor
	 */
	public Rating(int stars, String author, String comment, String date){
		
		this.stars = stars;
		this.author = author;
		this.comment = comment;
		this.date = date;
	}
	
	/**
	 * Returns the number of stars in the rating.
	 */
	public int getStars(){ 
		
		return stars;
	}
	
	/**
	 * Sets the number of stars in the rating.
	 */
	public void setStars(int stars){
		
		this.stars = stars;
	}
	
	/**
	 * Returns the author of the rating
	 */
	public String getAuthor(){ 
		
		return author; 
	}
	
	/**
	 * Sets the author of the rating.
	 */
	public void setAuthor(String author){
		
		this.author = author;
	}
	
	/**
	 * Returns the comment of the rating.
	 */
	public String getComment(){ 
		
		return comment; 
	}
	
	/**
	 * Sets the comment of the rating.
	 */
	public void setComment(String comment){
		
		this.comment = comment;
	}
	
	/**
	 * Returns the date the rating was made.
	 */
	public String getDate(){ 
		
		return date; 
	}
	
	/**
	 * Sets the date the rating was made.
	 */
	public void setDate(String date){
		
		this.date = date;
	}

}