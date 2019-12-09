RUN: DriverCode
-------------
BRIEF SUMMARY
-------------
This program allows the user to select, insert, update, and delete tables and their values in a music database. The music database holds songs, artists, albums, labels, playlists, concerts, and genres. The database is connected to using DatabaseConnection.java, the music database is interacted with using the user interface defined in DriverCode.java, and the functions of the user interface are defined in Query.java. Other files include an ER diagram and a Data Dictionary.





-----------------------------------
FOLDERS, FILES, AND THEIR FUNCTIONS
-----------------------------------
1. Deliverables     //Folder holding the non-active code and pdf files
  A. Data Dictionary.pdf      //Describes tables, their associated variables, variable data types, and descriptions
  B. ER Diagram.png      //Visual representation of data tables, and the means by which they are connected
  C. MusicDatabaseDataDump20191207.sql      //Populates the database
  D. MusicDatabaseStructureDump20191207.sql     //Creates the database
2. Src      //Folder holding main code functions and definitions
  A. Main     //Consists of database connection, executable code and function definitions
    a. DatabaseConnection.java      //Establishes connection to the database, contains login data
    b. DriverCode.java      //Contains user interface for the database
    c. Query.java     //Function definitions of functions called in DriverCode.java
3. .gitIgnore     //Tells git which files to ignore when pushing and pulling
4. Music Database.iml     //Intellij created file that stores Java development module
5. README.md      //This file, describes files, functions of user interface, and ...


---------------------------------
BASIC FUNCTIONS OF USER INTERFACE
---------------------------------
1. Search
  A. Search songs
  B. Search artists
  C. Search albums
  D. Search labels
  E. Search playlists
  F. Search concerts
  G. Search genres
  
2. Get All (Prints all relevant data of selected category)
  A. Get songs
  B. Get artists
  C. Get albums
  D. Get labels
  E. Get playlists
  F. Get concerts
  G. Get genres
  H. Get members of an artist
  
3. Insert
  A. Create a playlist name if it does not exist, but does not insert songs
  B. Insert songs into an existing playlist
  C. Create a concert, and then add the name, then the date and time, then the location

4. Delete
  A. Get rid of playlist table
  B. Get rid of concert table
  
  

--------------

--------------
