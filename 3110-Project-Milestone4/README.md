# 3110-Project: Simulation of a file-sharing social network

Team "3110-Project" Milestone 3: Strategies & Code smells

/*READ ME*\

Project Members:

Hasan Issa (100921446): search strategies for distance and similarities
Obinna Elobi (100953254): GUI, implementation of MVC pattern
Craig Isesele (100950074): search strategies for likes and follows, ability to set strategy, changed strategies from methods into separate classes, readme
Kariharan Thilagakumar (100922048): tests

-The source files for the project can be found in the src folder

Adding JFreeChart libraries
-Right click on the project, select 'build path' then select 'Configure build path...'
-Click on the Libraries tab and click on 'Add External Jars'
-Navigate to the Jar Files folder select 'jcommon-1.0.23.jar' and 'jfreechart-1.0.19.jar'
-You may also choose to highlight all the jar files instead

Things added
-Put everything in 'project' package to avoid use of a default package

Unzipping jar folder
-Create a new folder
-Copy 'jar.exe' and 'jli.dll' from your jdk folder (probably in program files) to this folder
-Also copy the jar file to this folder
-Open your command line and use the cd command to get to the location of the folder you created
-type jar xf 'jarname.jar'

Documentation
-There is a jfreechart zip file which is necessary for running the gui located in the doc folder
-Unzip the file and follow the instructions at http://www.jfree.org/phpBB2/viewtopic.php?t=20743
-The User Manual and javadoc files are also in the doc folder

Comments
-Fixed milestone 2 bugs

Running the Program
- when SimulationController is run, the first window comes up, input the number of producers, consumers and cycles you want
- you then get a pop up for each producer and consumer to input their taste *If left blank program continues and that user will have no taste*
- Activity Feed displays user activity each cycle and graph updates with payoff for each cycle. 
- The userID and taste of the user that just ran in the cycle will be displayed. 
- Select a search strategy to use from the dropdown menu and type in the number of documents you want to search for
- When step through is pressed a random user is picked in the social network and they act accordingly
- If a producer is selected a pop up will appear so you can choose the like strategy for the producer
- If liking a different taste you will have to input a taste to like to continue the program
- This is done until the number of cycles specified at the start is reached.