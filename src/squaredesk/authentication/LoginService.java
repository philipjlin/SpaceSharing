package squaredesk.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The LoginService class represents the service that handles user logins using a username and password.
 * Upon a successful login, the login service will issue an authentication token to a user that allows 
 * the user to access certain restricted service methods based on the user???s role and permissions.
 * @author Philip Lin
 */
public class LoginService{
	
	private static LoginService loginService = new LoginService();

	private Map<RegisteredUser, AuthenticationToken> tokensMap = new HashMap<RegisteredUser, AuthenticationToken>();
	
	private enum PrivilegeToken{PROVIDER_TOKEN, RENTER_TOKEN, SQUAREDESK_ADMIN_TOKEN, AUTHENTICATION_ADMIN_TOKEN}

	/**
	 * Static method to obtain single instance of LoginService.
	 * Follows the singleton design pattern.
	 * @return singleton LoginService instance
	 */
	public static LoginService getInstance(){

		return loginService;
	}
	
	/**
	 * Checks the user and password against the registered users and issues an 
	 * authentication token if there is a valid match. 
	 * @param user user name of registered user
	 * @param password password of registered user
	 * @return AuthenticationToken object used to access restricted methods
	 * @throws exception if credentials are invalid
	 */
	public AuthenticationToken login(String user, String password){
		
		Map<String, RegisteredUser> userdb = RegisteredUserManager.getInstance().getRegisteredUsersMap();
		
		try{
		
			for( RegisteredUser registeredUser : userdb.values() ){
				
				if( registeredUser.getUser().equals(user) && registeredUser.getPassword().equals(Integer.toString(password.hashCode())) ){
					
					AuthenticationToken token = new AuthenticationToken();
					token.setDateCreated(new Date());
					token.setState(true);
					
					//The type the token corresponds to the level of privilege
					String privileges = "";
					//Add strings to type for each role
					for( Role role : registeredUser.getRoles() ){
						
						if( role.getIdentifier().equals("renter_role") )
							privileges +=  " " + PrivilegeToken.RENTER_TOKEN.toString();
						if( role.getIdentifier().equals("provider_role") )
							privileges += " " + PrivilegeToken.PROVIDER_TOKEN.toString();
						if( role.getIdentifier().equals("authentication_admin_role") )
							privileges += " " + PrivilegeToken.AUTHENTICATION_ADMIN_TOKEN.toString();
						if( role.getIdentifier().equals("sd_admin_role") )
							privileges += " " + PrivilegeToken.SQUAREDESK_ADMIN_TOKEN.toString();
					}
							
					token.setPrivileges(privileges);
					
					tokensMap.put(registeredUser, token);
					
					return token;
				}		
				
			}
		
			throw new AuthenticationException("Login Failed: Invalid Login Information.");
		
		}catch(AuthenticationException ae){
			System.out.println(ae.getExceptionInfo());
		}
		
		return null;
	}
	
	/**
	 * Logs a registered user out of their current session, invalidating the state
	 * of the users authentication token.
	 * @param registeredUser whose token to invalidate
	 */
	public void logout(AuthenticationToken token){
		
		token.setState(false);
	}

	/*
	 * Getters and Setters
	 */
	public Map<RegisteredUser, AuthenticationToken> getTokensMap() {
		return tokensMap;
	}

}