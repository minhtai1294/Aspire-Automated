
# Aspire-Automated

Aspire Automated is a project for dealing with user interface testing on Aspire application.

Before you continue, ensure you meet the following requirements:
 
* You have installed the latest version of IntelliJ IDEA Community or any IDE.
* Java JDK 16 is installed. https://www.oracle.com/java/technologies/javase-downloads.html
* Have lasted Chrome dirver and put it in path of environment variables
* (Otional) Install maven and put in path environment variables. This is for running from execution file. Here is a guide: https://mkyong.com/maven/how-to-install-maven-in-windows/

## Technologies

* Java
* Selenium
* TestNG
* Page Object Model

## Installation

* Clone the repository using SSH link: git@github.com:minhtai1294/Aspire-Automated.git
* The SSH key file is also included: prv.ppk (password is required)

## Usage

* Test script files is located in src/test/java/scripts
* Test data location: src/test/resources/testdata

## Test suite
* I break down the process into 4 test areas following the process from 1st register page > additional details page > business details page > identity verification page. (In reallity the tests should be smaller because there are also small pages in each listed areas)

### How to run
   1. First compile the project to make sure our code is executable
   2. Run
* Way 1: Run the 'execution test.bat' (you need to install Maven and set path for MAVEN_HOME in environment variables)
* Way 2: Run the testng.xml file in project
* Way 3: To run invidual test class. Add a TestNG configuration for a test class (currently there's only package scripts.testsuites.registerpagetest), no other inputs are required for the TestNG configuration

### Test data design
* File type: .xlsx
* Data format: all data are converted to text plain
* 1st sheet is data definitions 
* 2nd sheet is the test data map with data definitions in 1st sheet 
* Each row is a set of test data
* "End of test data" row at the end is required

### Test data usage
#### Important: Test data file name will match with test method name
* Design the test data as you want and get the data from testCaseData in your test

## Some more implements can be done (but have not done, just my thinking)
* Junit parameters (this is useful when running on test system)
* TestNG custom listeners for automation test suites (this can be integrated with database server like MongoDB)
* Report result file

