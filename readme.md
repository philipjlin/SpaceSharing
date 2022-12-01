# Space Sharing Application


## Respository
<https://github.com/philipjlin/SpaceSharing>


## Description
SquareBNB is an application that is based on popular home-sharing application AirBNB. The concept of the application is to provide a means for individuals to rent out places that they have as office space to people looking for those types of spaces. The providers can make money from their unused space, and people looking for a place to work have an alternative to traditional office spaces, bookstores, coffee shops or their own homes.

Office space providers need a way to list their available spaces for rent to prospective renters, while renters need a way to search for office spaces that suit their working needs. SquareBNB provides a solution for this by connecting providers and renters through a searchable database of listings. In addition to giving providers a way to list their spaces, and renters a way to search those listings, ShareDesk also takes care of reservations through an authenticated online system.

Authentication is provided to so that users can only access methods that they are authorized to access - this adds a layer of security to the application.


## Design Documents
The functionality and structure of each service layer in the application is described in design documents included in the project.
* Authentication Service Design Document - describes authentication for users.
* Provider Service Design Document - defines functionality for providers looking to list spaces to rent out.
* Renter Service Design Document - defines functionality for renters looking to find spaces to rent.
* Knowledge Engine - Component used to store information about listed spaces and query the database of spaces for specific results based on a set of criteria.

UML diagrams, created in Astah were used to provide visual representations of the relationships of classes that were a part of each services.


## Input Files
* inputQueries.txt - input queries to test on the knowledge engine.
* inputTriples.txt - sample data to populate the database of shared spaces.
* authentication.csv - defines users, roles, and authentication/authorization information.


## Output Files
Sample output for various use cases acheived when running the application are provided in the Sample Output folder.


## Run Instructions
Program runs with commands:

javac squaredesk/provider/*.java squaredesk/renter/*.java squaredesk/authentication/*.java test/*.java knkowledgeengine/*.java <br>

java -cp . cscie97.asn4.test.TestDriver authentication.csv <br>
java -cp . cscie97.asn4.test.TestDriver2 authentication.csv <br>
java -cp . cscie97.asn4.test.TestDriver3 authentication.csv <br>
java -cp . cscie97.asn4.test.TestDriver4 authentication.csv <br>
