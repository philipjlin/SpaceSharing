package squaredesk.provider;

/**
 * The Rate class keeps tracks of the different rates to rent an office space as determined by the provider.
 * Hourly and daily rates are included. 
 * 
 * @author Philip Lin
 */
public class Rate {
	
	private int hourlyRate;
	private int dailyRate;
	
	/**
	 * Constructor
	 */
	public Rate(){
		
		dailyRate = 0;
		hourlyRate = 0;
	}
	
	/**
	 * Returns the daily rental rate for an office space.
	 */
	public int getDailyRate(){ 
		
		return dailyRate; 
	}
	
	/**
	 * Sets the daily rental rate for an office space.
	 */
	public void setDailyRate(int dailyRate){
		
		this.dailyRate = dailyRate;
	}
	
	/**
	 * Returns the hourly rental rate for an office space.
	 */
	public int getHourlyRate(){ 
		
		return hourlyRate; 
	}
	
	/**
	 * Sets the hourly rental rate for an office space.
	 */
	public void setHourlyRate(int hourlyRate){
		
		this.hourlyRate = hourlyRate;
	}

}