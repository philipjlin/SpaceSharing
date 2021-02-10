package squaredesk.authentication;

import java.util.Date;

/**
 * The AuthenticationToken class represents a token given to users after they login that is used to 
 * give them access to restricted methods according to their roles. The authentication token has a 
 * set time from its creation to its expiration, and this period of time represents a login session 
 * in which the registered user can use methods they have permissions for.
 * @author Philip Lin
 */
public class AuthenticationToken{

	private String privileges;
	private Date dateCreated;
	private boolean state;
	public static long timeoutLength = 1800;
		
	/*
	 * Getters and Setters
	 */
	public String getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	//Gets the state of the token. Checks to see if token is expired before getting state.
	public boolean getState() {
		
		if( state == true && dateCreated.getTime()/1000 + timeoutLength > new Date().getTime()/1000 )
			return true;
		else
			return false;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
}