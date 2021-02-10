package test;

import squaredesk.authentication.AuthenticationService;
import squaredesk.authentication.CSVReader;
import squaredesk.authentication.LoginService;
import squaredesk.authentication.RegisteredUserManager;

/**
 * TestDriver Class
 * Class with main method used for testing an exception in the Authentication Service API
 * that occurs when a client tries to log in with invalid user and/or password information.
 * @author Philip Lin
 */
public class TestDriver4 {

	public static void main(String[] args){
		
		CSVReader reader = new CSVReader(args[0]);

		RegisteredUserManager.getInstance().createRegisteredUsers(reader);
		AuthenticationService.getInstance().createPermissions(reader);
		AuthenticationService.getInstance().createRoles(reader);
		AuthenticationService.getInstance().createServices(reader);
		
		AuthenticationService.getInstance().addPermissionsToServices(reader);
		AuthenticationService.getInstance().addPermissionsToRoles(reader);
		RegisteredUserManager.getInstance().addRolesToUsers(reader);
		
		//Attempt to Login using a wrong password
		LoginService.getInstance().login("lucy", "wrong_password");		
	}
	
}
