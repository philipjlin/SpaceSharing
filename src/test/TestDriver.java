package test;

import squaredesk.renter.*;
import squaredesk.authentication.*;
import squaredesk.provider.*;

/**
 * TestDriver Class
 * Class with main method used for testing the Authentication Service API.
 * @author Philip Lin
 */
public class TestDriver{
	
	/*
	 * Things to check:
	 * 	1. Create Registered Users, Permissions, Roles and Services from input CSV file.
	 *  2. Add Roles to Registered Users and add Permissions to Services and Roles from CSV file.
	 *  3. Use an AuthenticationServiceVisitor to list the inventory of Registered Users, Permissions, Roles, and Services.
	 *  4. Login to SquareDesk as SquareDesk admin and receive authentication token.
	 *  5. Use Authentication Token to Create and Update Providers, Renters, and Office Spaces.
	 *  6. Search for OfficeSpace using Criteria object and input files
	 *  7. Book OfficeSpace.
	 *  8. Logout of current session.
	 */
	public static void main(String[] args){
		
		CSVReader reader = new CSVReader(args[0]);

		//Check 1:
		RegisteredUserManager.getInstance().createRegisteredUsers(reader);
		AuthenticationService.getInstance().createPermissions(reader);
		AuthenticationService.getInstance().createRoles(reader);
		AuthenticationService.getInstance().createServices(reader);
		
		//Check 2:
		AuthenticationService.getInstance().addPermissionsToServices(reader);
		AuthenticationService.getInstance().addPermissionsToRoles(reader);
		RegisteredUserManager.getInstance().addRolesToUsers(reader);
		
		//Check 3:
		AuthenticationServiceVisitor visitor = new AuthenticationServiceVisitor();
		visitor.visitAuthenticationService(AuthenticationService.getInstance());
		visitor.visitRegisteredUserManager(RegisteredUserManager.getInstance());
		visitor.listInventory();
		
		//Check 4:
		AuthenticationToken token = LoginService.getInstance().login("sam", "secret");
		System.out.println("Token Details: \n*****");
		System.out.println(token.getPrivileges().trim());
		System.out.println("Active State: " + token.getState());
		System.out.println("Date Created: " + token.getDateCreated());
		System.out.println("Timeout Length (seconds) : " + AuthenticationToken.timeoutLength);
		System.out.println("\n");
		
		//Check 5:
		addProviders(token);
		addOfficeSpaces(token);
		addRenters(token);
		
		//Check 6:
		System.out.println("Search Results: \n*****");
		SearchEngine.getInstance().searchPerfectMatches(token, RenterManager.getInstance().retrieveRenter("Renter1").getCriteria());
		
		//Check 7:
		Scheduler.getInstance().checkAvailability("OfficeSpace1", "10/28/14", "10/31/14");
		Scheduler.getInstance().createBooking(token, "Booking1", "Renter1", "OfficeSpace1", "9:00-17:00", "10/28/14", "10/29/14");		
		System.out.println("Booking Details: \n*****");
		System.out.println("Renter: " + Scheduler.getInstance().retrieveBooking("Booking1").getRenterIdentifier());
		System.out.println("OfficeSpace: " + Scheduler.getInstance().retrieveBooking("Booking1").getOfficeSpaceIdentifier());
		System.out.println("Hours: " + Scheduler.getInstance().retrieveBooking("Booking1").getHours());
		System.out.println("Start Date: " + Scheduler.getInstance().retrieveBooking("Booking1").getStartDate());
		System.out.println("End Date: " + Scheduler.getInstance().retrieveBooking("Booking1").getEndDate());
		
		//Check 8:
		LoginService.getInstance().logout(token);

	}
	
	
	
	/*
	 * Test Methods to add Providers, OfficeSpaces, and Renters
	 */
	public static void addProviders(AuthenticationToken token){
		
		//Create Provider
		ProviderManager.getInstance().createProvider(token, "Provider1");
		
		//Retrieve Provider instance to be used as DTOs during update 
		Provider providerDTO = ProviderManager.getInstance().retrieveProvider("Provider1");

		//Set the fields of the Provider DTO with actual information
		providerDTO.setGender("male");
		providerDTO.getOfficeSpaceIdentifiers().add("OfficeSpace1");
		
		providerDTO.getRatings().add(new Rating(5, "Neighbor Aber", "Mr. Rogers is such a great provider!", "10/10/14"));
		
		providerDTO.getContact().setName("Fred Rogers");
		providerDTO.getContact().setPhone("(123) 456-7890");
		providerDTO.getContact().setEmail("fred@neighborhood.com");
		
		providerDTO.getImage().setImage(  java.net.URI.create("http://www.wiredforbooks.org/images/FredRogers4.jpg") );
		providerDTO.getImage().setName("Profile");
		providerDTO.getImage().setCaption("Profile - Fred Rogers");
		
		providerDTO.getAccount().setNumber("0000001");
		providerDTO.getAccount().setPayPal("2303957698");
		
		//Update the Provider instances in the map of providers using the DTOs
		ProviderManager.getInstance().updateProvider(token, "Provider1", providerDTO);

	}
	
	public static void addOfficeSpaces(AuthenticationToken token){
		
		//Create OfficeSpaces
		OfficeSpaceManager.getInstance().createOfficeSpace(token, "OfficeSpace1");
		OfficeSpaceManager.getInstance().createOfficeSpace(token, "OfficeSpace2");
		OfficeSpaceManager.getInstance().createOfficeSpace(token, "OfficeSpace3");
		
		//Retrieve the OfficeSpace instances to be used as DTOs during update
		OfficeSpace officeSpaceDTO1 = OfficeSpaceManager.getInstance().retrieveOfficeSpace("OfficeSpace1");
		OfficeSpace officeSpaceDTO2 = OfficeSpaceManager.getInstance().retrieveOfficeSpace("OfficeSpace2");
		OfficeSpace officeSpaceDTO3 = OfficeSpaceManager.getInstance().retrieveOfficeSpace("OfficeSpace3");
		
		//Set the fields of the OfficeSpace DTOs with actual information
		officeSpaceDTO1.getImages().add(new Image(java.net.URI.create("http://kidzshowz.files.wordpress.com/2013/10/mister-rogers-neighborhood-living-room_original.jpg"), "Office", "OfficeSpace"));
		
		officeSpaceDTO1.getRatings().add(new Rating(5, "Neighbor Aber", "Best Space Ever!", "10/10/14"));
		
		officeSpaceDTO1.getRate().setHourlyRate(10);
		officeSpaceDTO1.getRate().setDailyRate(50);
		
		officeSpaceDTO1.getLocation().setAddressPart("address1", "1 Story Street");
		officeSpaceDTO1.getLocation().setAddressPart("address2", "Harvard Extension School");
		officeSpaceDTO1.getLocation().setAddressPart("city", "Boston");
		officeSpaceDTO1.getLocation().setAddressPart("state", "MA");
		officeSpaceDTO1.getLocation().setAddressPart("zip", "02138");
		
		officeSpaceDTO1.getFeatures().addFeature("WIFI");
		officeSpaceDTO1.getFeatures().editFeatureAvailability("WIFI", Boolean.TRUE);
		officeSpaceDTO1.getFeatures().addFeature("Coffee");
		officeSpaceDTO1.getFeatures().editFeatureAvailability("Coffee", Boolean.TRUE);
		officeSpaceDTO1.getFeatures().getCommonAccess().addCommonAccess("Game Room");
		officeSpaceDTO1.getFeatures().getCommonAccess().editCommonAccessAvailability("Game Room", Boolean.FALSE);

		officeSpaceDTO1.getCapacity().setPeople(8);
		officeSpaceDTO1.getCapacity().setSize(289);
		officeSpaceDTO1.getCapacity().setWorkspaces(4);
		
		officeSpaceDTO1.getFacility().setType("House");
		officeSpaceDTO1.getFacility().addRoom("Kitchen");
		officeSpaceDTO1.getFacility().editRoomAvailability("Kitchen", Boolean.TRUE);
		officeSpaceDTO1.getFacility().addRoom("Dining Room");
		officeSpaceDTO1.getFacility().editRoomAvailability("Dining Room", Boolean.FALSE);
		
		officeSpaceDTO2.getImages().add(new Image(java.net.URI.create("http://homeornaments.com/wp-content/uploads/2014/10/Minimalist-House-7.jpg"), "Home", "OfficeSpace"));
		
		officeSpaceDTO2.getRatings().add(new Rating(4, "Chad Wick", "Suits My Needs Alright", "10/22/19"));
		
		officeSpaceDTO2.getRate().setHourlyRate(20);
		officeSpaceDTO2.getRate().setDailyRate(50);
		
		officeSpaceDTO2.getLocation().setAddressPart("address1", "1 Story Street");
		officeSpaceDTO2.getLocation().setAddressPart("address2", "Harvard Extension School");
		officeSpaceDTO2.getLocation().setAddressPart("city", "Boston");
		officeSpaceDTO2.getLocation().setAddressPart("state", "MA");
		officeSpaceDTO2.getLocation().setAddressPart("zip", "02238");
		
		officeSpaceDTO2.getFeatures().addFeature("WIFI");
		officeSpaceDTO2.getFeatures().editFeatureAvailability("WIFI", Boolean.FALSE);
		officeSpaceDTO2.getFeatures().addFeature("Coffee");
		officeSpaceDTO2.getFeatures().editFeatureAvailability("Coffee", Boolean.FALSE);
		officeSpaceDTO2.getFeatures().getCommonAccess().addCommonAccess("Game Room");
		officeSpaceDTO2.getFeatures().getCommonAccess().editCommonAccessAvailability("Game Room", Boolean.TRUE);

		officeSpaceDTO2.getCapacity().setPeople(10);
		officeSpaceDTO2.getCapacity().setSize(441);
		officeSpaceDTO2.getCapacity().setWorkspaces(10);
		
		officeSpaceDTO2.getFacility().setType("House");
		officeSpaceDTO2.getFacility().editRoomAvailability("Kitchen", Boolean.FALSE);
		officeSpaceDTO2.getFacility().addRoom("Dining Room");
		officeSpaceDTO2.getFacility().editRoomAvailability("Dining Room", Boolean.FALSE);
		
		officeSpaceDTO3.getImages().add(new Image(java.net.URI.create("http://apg.ap1.netdna-cdn.com/wp-content/uploads/2014/04/AbandonedDumpHouseVirginia12.jpg"), "Office", "OfficeSpace"));
		
		officeSpaceDTO3.getRatings().add(new Rating(1, "Optimus Prime", "This place is a dump.", "10/22/14"));
		
		officeSpaceDTO3.getRate().setHourlyRate(2);
		officeSpaceDTO3.getRate().setDailyRate(5);
		
		officeSpaceDTO3.getLocation().setAddressPart("address1", "1 Story Street");
		officeSpaceDTO3.getLocation().setAddressPart("address2", "Harvard Extension School");
		officeSpaceDTO3.getLocation().setAddressPart("city", "New York");
		officeSpaceDTO3.getLocation().setAddressPart("state", "NY");
		officeSpaceDTO3.getLocation().setAddressPart("zip", "20395");
		
		officeSpaceDTO3.getFeatures().addFeature("WIFI");
		officeSpaceDTO3.getFeatures().editFeatureAvailability("WIFI", Boolean.FALSE);
		officeSpaceDTO3.getFeatures().addFeature("Coffee");
		officeSpaceDTO3.getFeatures().editFeatureAvailability("Coffee", Boolean.TRUE);
		officeSpaceDTO3.getFeatures().getCommonAccess().addCommonAccess("Game Room");
		officeSpaceDTO3.getFeatures().getCommonAccess().editCommonAccessAvailability("Game Room", Boolean.FALSE);

		officeSpaceDTO3.getCapacity().setPeople(2);
		officeSpaceDTO3.getCapacity().setSize(100);
		officeSpaceDTO3.getCapacity().setWorkspaces(1);
		
		officeSpaceDTO3.getFacility().setType("Garage");
		officeSpaceDTO3.getFacility().addRoom("Kitchen");
		officeSpaceDTO3.getFacility().editRoomAvailability("Kitchen", Boolean.FALSE);
		officeSpaceDTO3.getFacility().addRoom("Dining Room");
		officeSpaceDTO3.getFacility().editRoomAvailability("Dining Room", Boolean.FALSE);
		
		//Update the OfficeSpace instances in the map with the DTOs	
		OfficeSpaceManager.getInstance().updateOfficeSpace(token, "OfficeSpace1", officeSpaceDTO1);
		OfficeSpaceManager.getInstance().updateOfficeSpace(token, "OfficeSpace2", officeSpaceDTO2);
		OfficeSpaceManager.getInstance().updateOfficeSpace(token, "OfficeSpace3", officeSpaceDTO3);
	}
	
	public static void addRenters(AuthenticationToken token){
		
		//Create Renter
		RenterManager.getInstance().createRenter(token, "Renter1");
				
		//Retrieve Renter instances to be used as Data Transfer Objects during update.
		Renter renterDTO = RenterManager.getInstance().retrieveRenter("Renter1");
				
		//Set the fields of the Renter DTO with actual information
		renterDTO.setGender("male");
		renterDTO.getRatings().add(new Rating(2, "Mr. Rodgers", "Neighbor Aber is a good renter!", "10/10/14"));
				
		renterDTO.getContact().setName("Fred Rogers");
		renterDTO.getContact().setPhone("(123) 456-7890");
		renterDTO.getContact().setEmail("fred@neighborhood.com");
				
		renterDTO.getImage().setImage(  java.net.URI.create("http://www.wiredforbooks.org/images/FredRogers4.jpg") );
		renterDTO.getImage().setName("Profile");
		renterDTO.getImage().setCaption("Profile - Fred Rogers");
			
		renterDTO.getAccount().setNumber("0000001");
		renterDTO.getAccount().setPayPal("2303957698");
				
		renterDTO.getCriteria().addFeature("WIFI");
		renterDTO.getCriteria().addFeature("Coffee");
		renterDTO.getCriteria().setCity("Boston");
		renterDTO.getCriteria().setState("MA");
		renterDTO.getCriteria().setZip("02138");
		renterDTO.getCriteria().setFacilityType("House");
		renterDTO.getCriteria().setMinRating(3.5);
		renterDTO.getCriteria().setStartandEndDates("10/24/14", "10/25/14");
				
		//Update Renter instances in the map with the DTOs
		RenterManager.getInstance().updateRenter(token, "Renter1", renterDTO);
	}
	
}