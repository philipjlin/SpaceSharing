package test;

import squaredesk.renter.RenterManager;
import squaredesk.authentication.AuthenticationService;
import squaredesk.authentication.AuthenticationToken;
import squaredesk.authentication.CSVReader;
import squaredesk.authentication.LoginService;
import squaredesk.authentication.RegisteredUserManager;


/** TestDriver Class
 * Class with main method used for testing an exception in the Authentication Service API
 * that occurs when a user tries to access a restricted method with an expired authentication token.
 * @author Philip Lin
 */
public class TestDriver3 {

	public static void main(String[] args){
		
		CSVReader reader = new CSVReader(args[0]);

		RegisteredUserManager.getInstance().createRegisteredUsers(reader);
		AuthenticationService.getInstance().createPermissions(reader);
		AuthenticationService.getInstance().createRoles(reader);
		AuthenticationService.getInstance().createServices(reader);
		
		AuthenticationService.getInstance().addPermissionsToServices(reader);
		AuthenticationService.getInstance().addPermissionsToRoles(reader);
		RegisteredUserManager.getInstance().addRolesToUsers(reader);
		
		AuthenticationToken token = LoginService.getInstance().login("lucy", "4567");
		AuthenticationToken.timeoutLength = 1;
		System.out.println(token.getState());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(token.getState());
		
		//Attempt to access method after authentication token has expired.
		RenterManager.getInstance().createRenter(token, "Renter1");
	}
	
}
