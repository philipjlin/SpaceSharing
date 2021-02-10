package squaredesk.authentication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
public class CSVReader{
	
	String fileName;
	String line;
	String delimiter = ",";
	String[] values;
	BufferedReader br;
	
	private Map<String, Service> servicesMap = new HashMap<String, Service>();
	private Map<String, Role> rolesMap = new HashMap<String, Role>();
	private Map<String, Permission> permissionsMap = new HashMap<String, Permission>();
	private Map<String, RegisteredUser> registeredUsersMap = new HashMap<String, RegisteredUser>();

	/**
	 * Constructor
	 * @param fileName of CSV file with Authentication Service information
	 */
	public CSVReader(String fileName){
		
		this.fileName = fileName;
	}
	
	/**
	 * Uses the CSV file to create Service objects.
	 * Adds created service objects to a map of service objects.
	 */
	public void createServicesFromFile(){
				
		try {
					
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine()) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("define_service") ){
					
					Service serviceToAdd = new Service();
					serviceToAdd.setIdentifier(values[1]);
					serviceToAdd.setName(values[2]);
					serviceToAdd.setDescription(values[3]);
					
					servicesMap.put(serviceToAdd.getIdentifier(), serviceToAdd);
				}		
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Uses the CSV file to create Role objects.
	 * Adds created role objects to a map of role objects.
	 */
	public void createRolesFromFile(){
				
		try {
					
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine()) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("define_role") ){
					
					Role roleToAdd = new Role();
					roleToAdd.setIdentifier(values[1]);
					roleToAdd.setName(values[2]);
					roleToAdd.setDescription(values[3]);
					
					rolesMap.put(roleToAdd.getIdentifier(), roleToAdd);
				}		
			}

		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}	
	}
	
	/**
	 * Uses the CSV file to create Permission objects.
	 * Adds created permission objects to a map of permission objects.
	 */
	public void createPermissionsFromFile(){
				
		try {
				
			br = new BufferedReader(new FileReader(fileName));

			while( (line = br.readLine()) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("define_permission") ){
					
					Permission permissionToAdd = new Permission();
					permissionToAdd.setIdentifier(values[2]);
					permissionToAdd.setName(values[3]);
					permissionToAdd.setDescription(values[4]);
					
					permissionsMap.put(permissionToAdd.getIdentifier(), permissionToAdd);
				}		
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Uses the CSV file to create RegisteredUser objects.
	 * Adds created registered user objects to a map of registered user objects.
	 */
	public void createUsersFromFile(){
				
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine()) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("create_user") ){
					
					RegisteredUser userToAdd = new RegisteredUser();
					userToAdd.setIdentifier(values[1]);
					userToAdd.setName(values[2]);
					userToAdd.setUser(values[3]);
					userToAdd.setPassword(values[4]);
					
					registeredUsersMap.put(userToAdd.getIdentifier(), userToAdd);
				}		
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}	
	}
	
	/**
	 * Uses the CSV file to create a Map containing service identifiers mapped
	 * to a list of permission identifiers for the service.
	 */
	public void addPermissionsToServicesFromFile(){
		
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine() ) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("define_permission") )
					servicesMap.get(values[1]).addPermission(permissionsMap.get(values[2]));
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}	
	}
	
	/**
	 * Uses the CSV file to create a Map containing role identifiers mapped
	 * to a list of permission identifiers for the role.
	 */
	public void addPermissionsToRolesFromFile(){
				
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine() ) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("add_entitlement_to_role") )
					rolesMap.get(values[1]).addPermission(permissionsMap.get(values[2]));
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}
	} 
	
	
	/**
	 * Uses the CSV file to create a Map containing user identifiers mapped
	 * to a list of role identifiers for the user.
	 */
	public void addRolesToUsersFromFile(){
				
		try {
			
			br = new BufferedReader(new FileReader(fileName));
			
			while( (line = br.readLine() ) != null ){
				
				values = line.split(delimiter);
				for( int i = 0 ; i < values.length ; i++ )
					values[i] = values[i].trim();
				
				if( values[0].equals("add_role_to_user") )
					registeredUsersMap.get(values[1]).getRoles().add(rolesMap.get(values[2]));
			}
			
		}catch( FileNotFoundException e ){
			e.printStackTrace();
		}catch( IOException ioe){
			ioe.printStackTrace();
		}	
	} 
	
	/*
	 * Getters
	 */
	public Map<String, Service> getServicesMap(){
		return servicesMap;
	}
	
	public Map<String, Role> getRolesMap(){
		return rolesMap;
	}
	
	public Map<String, Permission> getPermissionsMap(){
		return permissionsMap;
	}
	
	public Map<String, RegisteredUser> getRegisteredUsersMap(){
		return registeredUsersMap;
	}
	
}