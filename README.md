# WebScraper - Navya Poosa

## WebScraper - Automate a website and fetch data to store in a JSON file format

## Installation Instructions:
Set up a local environment -> Install IDE, Java, Gradle, Git, TestNG to run the project.
Set up a TestNG framework -> Modify Build.Gradle file, Introduce testng.xml file

Instructions to Integrate:
Add TestNG Dependency to build.gradle
Create Folder Structure Create a test folder under src. The Test folder should contain the following subfolders one after the other. src->test->java->demo Inside the demo folder, create a TestCases.java file. In this file we write all our testcases.
Configure TestNG with test Task Create a folder under test, and parallel to the java folder, named “resources”. Within the build.gradle file, configure the test task to use TestNG and specify the location of your testng.xml file. This is how you instruct Gradle to use TestNG for running tests instead of the default JUnit. Ensure the path to testng.xml is correct. You might need to adjust the path based on where your testng.xml file is located in your project structure.
Creating the testng.xml File Create a testng.xml file in the specified directory, typically under src/test/resources/ in a standard project structure. This XML file is where you define your test suites and specify which test classes or
methods to run
Running the Tests With the dependency added and the testng.xml file configured, you can now run your TestNG tests through Gradle by executing the following command in your terminal or command prompt: "./gradlew test" This command will run all tests specified in your testng.xml file using TestNG.

## Usage and Examples:
usage scenarios to showcase how the project works.

Scenario 1:
Go to the "https://www.scrapethissite.com/pages/" website and click on “Hockey Teams: Forms, Searching and Pagination”
Iterate through the table and collect the Team Name, Year and Win % for the teams with Win % less than 40% (0.40)
Iterate through 4 pages of this data and store it in a ArrayList<HashMap>
Convert the ArrayList<HashMap> object to a JSON file named `hockey-team-data.json`. 
Each Hashmap object should contain: 
Epoch Time of Scrape
Team Name
Year
Win %
Store the file in the output folder in the root directory. Assert using TestNG that the file is present and not empty.

Scenario 2:
Go to the website "https://www.scrapethissite.com/pages/" and click on “Oscar Winning Films”
Click on each year present on the screen and find the top 5 movies on the list - store in an ArrayList<HashMap>. 
Keep a Boolean variable “isWinner” which will be true only for the best picture winner of that year.
Keep a variable to maintain the year from which the data is scraped
Convert the ArrayList<HashMap> object to a JSON file named `oscar-winner-data.json`. Each HashMap object should contain:
Epoch Time of Scrape
Year
Title
Nomination
Awards
isWinner
Store the file in the output folder in the root directory. Assert using TestNG that the file is present and not empty


# to run the project
to run the project use ./gradlew test 


 
