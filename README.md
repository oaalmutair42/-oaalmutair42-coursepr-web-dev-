## README


How to clone:
  
    Using command line that have git clone command and by using either https url of ssh from repository. 

How to clone the code from the repository:

    * $ git clone https://gitlab.csc.tntech.edu/csc2310-fa21-students/yourid/yourid-coursepr.git

How to compile the program:
 
    * Using intellij idea is built in and debug the code. 

How to run the program:

    * 1. Run the web server for the application using mvnw, as follows: $ ./mvnw spring-boot:run 
    * 2. Run the web application in a browser using the following url: http://localhost:8080

The structure of the directories in the source:

      * src has two folders in it the first one is the main sources classes code that fetch the url and save and load courses using jsoup and maven. The second folder is for testing and making sure all the codes in main sources working without any error. There are some error path tests too.
      * src has JSON file to load if the courses in the file and if not it will save the new course in that file.

List of dependencies:

    * Programs (maven, jsoup, JSON,)
    * Edit the dependencies using pom.xml