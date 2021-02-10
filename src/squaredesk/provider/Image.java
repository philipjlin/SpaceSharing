package squaredesk.provider;

/**
 * The Image class describes an image that is associated with a provider or office space,
 * and includes a name, caption, and an URI link to the image file.
 * 
 * @author Philip Lin
 */
public class Image {

	private java.net.URI image;
	private String name;
	private String caption;
	
	/**
	 * Constructor
	 */
	public Image(){
		
		image = java.net.URI.create("");
		name = "";
		caption = "";
	}
	
	/**
	 * Constructor
	 */
	public Image(java.net.URI image, String name, String caption){
		
		this.image = image;
		this.name = name;
		this.caption = caption;
	}
	
	/**
	 * Returns the URI image.
	 */
	public java.net.URI getImage(){ 
		
		return image; 
	}
	
	/**
	 * Sets the URI image.
	 */
	public void setImage(java.net.URI image){
		
		this.image = image;
	}
	
	/**
	 * Returns the image name.
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * Sets the image name.
	 */
	public void setName(String name){
		
		this.name = name;
	}
	
	/**
	 * Returns the image caption.
	 */
	public String getCaption(){ 
		
		return caption; 
	}
	
	/**
	 * Sets the image caption.
	 */
	public void setCaption(String caption){
		
		this.caption = caption;
	}

}