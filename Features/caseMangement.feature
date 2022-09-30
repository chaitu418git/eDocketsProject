@caseManagement
Feature: eDockets Cloud Project

Background: User is Logged In
 Given user is on Edockets Login Page
 And login details are
 |username|password|
 |ppalle@alncorp.com|P@ssw0rdS|
 When user enters the login details
 And clicks on Login Button
 Then user should be able to login to Edockets Application

Scenario: Verify Login and Logout Functionality of Edockets Application
#Given user is on Edockets Login Page
#And login details are
#|username|password|
#|ppalle@alncorp.com|P@ssw0rdS|
#When user enters the login details
#And clicks on Login Button
#Then user should be able to login to Edockets Application
When user clicks on Logout Button
Then user should be navigated to Login Page

Scenario: Verify Client Creation Functionality
Given user clicks on Create Button
And user clicks on Client Button
And user enters the client details
When user clicks on Save Button
Then user able to view "Client added successfully." message in popup
And user clicks on Ok Button
Then verify the client name which was created 

