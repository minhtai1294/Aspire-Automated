
# Aspire-Automated

Aspire Automated is a project for dealing with user interface testing on Aspire application.

Before you continue, ensure you meet the following requirements:
 
* You have installed the latest version of IntelliJ IDEA Community.
* Java JDK 16 is installed.

## Technologies

* Java
* Selenium
* TestNG
* Page Object Model

## Installation

* Clone the repository using SSH: git@github.com:minhtai1294/Aspire-Automated.git
* The SSH key file is also included: prv.ppk (password is required)

## Usage

* Test script files is located in src/test/java/scripts
* Test data location: src/test/resources/testdata
* To build a test
   1. First compile the project to make sure our code is executable
   2. Add a TestNG configuration for test class (current test class is RegisterTest.java), no other inputs are required for the TestNG configuration

### Test data design
* File type: .xlsx
* Data format: all data are converted to text plain
* 1st sheet is data definitions 
* 2nd sheet is the test data map with data definitions in 1st sheet 
* Each row is a set of test data
* "End of test data" row at the end is required

### Test data usage
* Test data file name will match with class name
* Design the test data as you want and get the data from testCaseData in your test

## Some more implements can be done (but have not done)
* Junit parameters (useful when running on a test system)
* TestNG custom listeners for automation test suites (this can be integrated with database server like Mongodb)
* Report result file

