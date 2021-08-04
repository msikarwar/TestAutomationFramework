@feature
Feature: Create a new User from admin Login

@NewUser
Scenario: Create New User
#Login to application as Admi
Given user login to the "OrangeHRM" application in "Chrome" browser
When user enters "userName" into username element
When user enters "password" into username element
And user clicks on submit button
#Create New User
#And user clicks on "admit" button on HomePage