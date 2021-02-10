package squaredesk.renter;

import java.util.HashMap;
import java.util.Map;

import squaredesk.authentication.AccessDeniedException;
import squaredesk.authentication.AuthenticationToken;
import squaredesk.provider.OfficeSpaceManager;

/**
 * The Scheduler class is responsible for creating reservations for a renter to rent 
 * an office space that a provider has listed, if that office space is currently available.
 * The Scheduler also can remove reservations when they are over or no longer needed.
 * 
 * Only one instance of Scheduler is used in the program (singleton pattern).
 * @author Philip Lin
 */
public class Scheduler{
	
	private static Scheduler scheduler = new Scheduler();
	
	private Map<String, Booking> bookingsMap = new HashMap<String, Booking>();

	
	/**
	 * Static method to obtain single instance of Scheduler
	 * 
	 * @return singleton Scheduler instance
	 */
	public static Scheduler getInstance(){

		return scheduler;
	}
		
	/**
	 * Creates a new Booking object with all fields of the Booking required to be set upon creation.
	 * @param token authentication token needed for operation
	 * @param id identifier of the booking that is being created
	 */
	public void createBooking(AuthenticationToken token, String id, String renterIdentifier, String officeSpaceIdentifier, String hours, String startDate, String endDate){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Create Booking.");
			
			if( bookingsMap.containsKey(id) )
				throw new SchedulerException("Booking Failed: Booking with id " + id + " already exists.");
			
			if( !checkAvailability(officeSpaceIdentifier, startDate, endDate) )
				throw new SchedulerException("Booking Failed: OfficeSpace with id " + officeSpaceIdentifier + " is unavailable.");
			
			bookingsMap.put(id, new Booking(id, renterIdentifier, officeSpaceIdentifier, hours, startDate, endDate));
			OfficeSpaceManager.getInstance().retrieveOfficeSpace(officeSpaceIdentifier).setBookedDates(startDate, endDate);
			
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());	
		}catch(SchedulerException se){
			System.out.println(se.getMessage());
		}
		
	}
	
	/**
	 * Returns the Booking object identified by the input identifier from the map of bookings.
	 * @param id identifier of the booking that is being retrieved
	 * @return Booking object retrieved, if operation is successful
	 */
	public Booking retrieveBooking(String id){
		
		try{
			
			if( !bookingsMap.containsKey(id) )
				throw new SchedulerException("Retrive Failed: No Booking with id " + id + " exists.");
			
			return bookingsMap.get(id);
			
		}catch(SchedulerException se){
			System.out.println(se.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Deletes the Booking object identified by the input identifier from the map of bookings.
	 * 
	 * @param token authentication token needed for operation
	 * @param id identifier of the booking that is being deleted
	 */
	public void deleteBooking(AuthenticationToken token, String id){
		
		try{
			
			if( token.getState() == false )
				throw new AccessDeniedException("Access Denied: Expired Authentication Token.");
			
			if( !(token.getPrivileges().contains("RENTER_TOKEN")) && !(token.getPrivileges().contains("SQUAREDESK_ADMIN_TOKEN")) )
				throw new AccessDeniedException("Access Denied: Cannot Delete Booking with id: "+ id);
			
			if( !bookingsMap.containsKey(id) )
				throw new UserException("Delete Failed: No Booking with id " + id + " exists.");
			
			bookingsMap.remove(id);
			OfficeSpaceManager.getInstance().retrieveOfficeSpace(id).removeBookedDates(bookingsMap.get(id).getStartDate(), bookingsMap.get(id).getEndDate());
		
		}catch(AccessDeniedException ade){
			System.out.println(ade.getMessage());
		}catch(UserException ue){
			System.out.println(ue.getMessage());
		}
	}
	
	/**
	 * Checks the current availability of an OfficeSpace object that can be reserved through the 
	 * OfficeSpaceManager. An OfficeSpace not part of an existing booking for the designated 
	 * start and end dates will be available.
	 * @param officeSpaceIdentifier identifier of the office space to check
	 * @return boolean indicator of the current OfficeSpace availability
	 */
	public boolean checkAvailability(String officeSpaceIdentifier, String startDate, String endDate){
		
		return OfficeSpaceManager.getInstance().retrieveOfficeSpace(officeSpaceIdentifier).checkAvailability(startDate, endDate);
	}
	
}