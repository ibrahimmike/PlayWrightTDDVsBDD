@Regression
Feature: Login
 Background:
 Given User is on login page
 Scenario: Correct Login

  And User enters user name standard_user
  And user enters password secret_sauce
  And user clicks on login button
  Then Home page is loaded

 Scenario Outline: Different error login

   And User enters user name <userName>
   And user enters password <password>
   And user clicks on login button
   Then The error message is showing '<errorMessage>'
   Examples:
    | userName        | password     | errorMessage                                                              |
    | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
    | ibrahim         | secret_sauce | Epic sadface: Username and password do not match any user in this service |

  Scenario Outline: All users categories login

   And User enters user name <userName>
   And user enters password <password>
   And user clicks on login button
   Then Home page is loaded
   Examples:
    | userName                | password     |
    | standard_user           | secret_sauce |
    | performance_glitch_user | secret_sauce |
    | error_user              | secret_sauce |
    | problem_user            | secret_sauce |
    | visual_user             | secret_sauce |














