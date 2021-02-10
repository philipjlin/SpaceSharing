package squaredesk.provider;

/**
 * The Location class describes the address various identifying attributes 
 * such as street, city, zip and country of an available Office Space.
 * 
 * @author Philip Lin
 */
public class Location {
	
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	
	/**
	 * Constructor
	 */
	public Location(){
		
		address1 = "";
		address2 = "";
		city = "";
		state = "";
		zip = "";
	}
	
	/**
	 * Returns the full address, formatted.
	 */
	public String getAddress	(){ 
		
		return address1 + "\n" +
			   address2 + "\n" +
			   city + ", " + state + " " + zip; 
	}
	
	public String getAddressPart(String part){
		
		switch( part ){
		
			case "address1":	 	return address1;
			case "address2":		return address2;
			case "city":			return city;
			case "state":		return state;
			case "zip":			return zip;
			default:				return null;
		}
	}
	
	/**
	 * Edits a specified portion of the address, based on the input: address1, address2, city, state, zip, or country.
	 */
	public void setAddressPart(String part, String edit){
		
		switch( part ){
		
			case "address1":	 	address1 = edit;
								break;
			case "address2":		address2 = edit;
								break;
			case "city":			city = edit;
								break;
			case "state":		state = edit;
								break;
			case "zip":			zip = edit;
								break;
		}
	}

}