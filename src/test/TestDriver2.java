package test;

import squaredesk.authentication.AuthenticationService;
import squaredesk.authentication.AuthenticationToken;
import squaredesk.authentication.CSVReader;
import squaredesk.authentication.LoginService;
import squaredesk.authentication.RegisteredUserManager;
import squaredesk.provider.ProviderManager;

/**
 * TestDriver Class
 * Class with main method used for testing an exception in the Authentication Service API
 * that occurs when a user tries to access a restricted method without the proper permission.
 * @author Philip Lin
 */
public class TestDriver2 {

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
		System.out.println(token.getPrivileges().trim());
		
		//Attempt to access method lucy (renter role) does not have access to
		ProviderManager.getInstance().createProvider(token, "Provider1");
	}

}