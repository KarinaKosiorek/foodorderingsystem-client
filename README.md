# foodorderingsystem-client
This is command-line client for restaurant ordering system service (see https://github.com/KarinaKosiorek/foodorderingsystem).
It connects to the server where the service is hosted and sends HTTP GET request with specified user actions in parameters.

How to run:
java -jar fosclient.jar
(there is fosclient.jar in this repository, located in /foodorderingsystem-client/target/ directory)

It is possible to define web configuration file for client.
File must be named "config.properties".
File must be in the same directory as .jar file.
File should include two lines:
host=[host where foodorderingsystem service is hosted]
port=[port to connect to the server where foodorderingsystem service is hosted]

Example 1:
host=foodorderingsystem.com
port=8080

Example 2:
host=localhost
port=8080

If there is no "config.properties" file, host and port will get default values:
host=localhost
port=8080


Below there is help text that will be printed when the client will be executed without input arguments:

Run options for fosclient:
---------------------------------------------------------------------------------------------
-help											
	: prints the help

-host [host]									
	: food ordering system host

-port [port]									
	: port  

-menu											
	: listing whole menu

-cuisines										
	: listing cuisines

-cuisine [cuisineID]							
	: listing main courses for specified cuisine

-drinks											
	: listing drinks

-desserts										
	: listing desserts

-order											
	: ordering lunch (main course + dessert) and/or drink with lemon and/or ice cubes optionally
	With the option -order, user also need to specify at least one of the following options:

		-lunch [maincourseID#dessertID]					
			: ordered lunch with main course and dessert
	
		-drink [drinkID]				
			: ordered drink
			
		-address [address]								
			: client address			

		-phone [phone_number]
			: client phone number

	If option -drink is used, user may optionally add:
		
		-lemon											
			: lemon for ordered drink
		-icecubes	
			: ice cubes for ordered drink

---------------------------------------------------------------------------------------------
EXAMPLES:
---------------------------------------------------------------------------------------------
fosclient -host foodorderingsystem.com -port 8080 -menu		
	: lists menu from food ordering system hosted on foodorderingsystem.com host on port 8080
	 
fosclient -cuisine 2							
	: lists main courses for cuisine with ID = 2
	
fosclient -order -lunch 1#3 -drink 1 -lemon		
	: orders lunch with main course with ID = 1 and dessert with ID = 3, and drink with ID = 2 with lemon

fosclient -order -lunch 1#1						
	: orders lunch with main course with ID = 1 and dessert with ID = 1, no drink
	
fosclient -order -drink 2 -icecubes -lemon				
	: orders drink with ID = 2 with ice cubes and lemon, no lunch

