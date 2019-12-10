-------------
BRIEF SUMMARY
-------------
This program allows the user to select, insert, update, and delete values in a music database. The music database holds songs, artists, albums, labels, playlists, concerts, and genres. The database is connected to using DatabaseConnection.java, the music database is interacted with using the user interface defined in DriverCode.java, and the functions of the user interface are defined in Query.java. Other files include an ER diagram and a Data Dictionary.


---------------------------
INSTRUCTIONS TO RUN PROGRAM
---------------------------
In GitHub, go to deliverables folder, download jar file.
Open command line and change directory to where the jar file has been downloaded.
Run command "java -jar Music\ Database.jar", which will open the user interface.
Must have Java version 11+ ( openjdk-11-jre-headless )
From there, available functions will be listed, and are ready for use.
Functions can be selected by typing in their corresponding number.


-----------------------------------
FOLDERS, FILES, AND THEIR FUNCTIONS
-----------------------------------
1. Deliverables     //Folder holding the non-active code and pdf files
	- Data Dictionary.pdf      //Describes tables, their associated variables, variable data types, and descriptions
	- ER Diagram.png      //Visual representation of data tables, and the means by which they are connected
	- Music Database.jar     //Executable file that contains all files on GitHub
	- MusicDatabaseDataDump20191207.sql      //Populates the database
	- MusicDatabaseStructureDump20191207.sql     //Creates the database
2. Src      //Folder holding source code functions and definitions 
	- Main     //Consists of database connection, executable code and function definitions
		- DatabaseConnection.java      //Establishes connection to the database, contains login data
		- DriverCode.java      //Contains user interface for the database
		- Query.java     //Function definitions of functions called in DriverCode.java
3. .gitIgnore     //Tells git which files to ignore when pushing and pulling
4. Music Database.iml     //Intellij created file that stores Java development module
5. README.md      //This file, describes files, functions of user interface, and ...


---------------------------------
BASIC FUNCTIONS OF USER INTERFACE
---------------------------------
1. Search (Search by keyword / Case Insensitive search)
  - Search songs
  - Search artists
  - Search albums
  - Search labels
  - Search playlists
  - Search concerts
  - Search genres
  
2. Get All (Prints all relevant data of selected category)
  - Get songs
  - Get artists
  - Get albums
  - Get labels
  - Get playlists
  - Get concerts
  - Get genres
  - Get members of an artist
  
3. Insert (Search by keyword, then validation is CASE SENSITIVE)
  - Create a playlist name if it does not exist, but does not insert songs
  - Insert songs into an existing playlist
  - Create a concert, and then add the name, then the date and time, then the location

4. Delete (Search by keyword, then validation is CASE SENSITIVE)
  - Get rid of playlist table
  - Get rid of concert table
5. Update
	-Update the name of concert
	-Update the date of concert
	-Update the location of concert
