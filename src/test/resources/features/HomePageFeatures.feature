@Regression
Feature:
  Background:
    Given User is on login page


  Scenario: user adds items to cart
    And User enters user name standard_user
    And user enters password secret_sauce
    And user clicks on login button
    When User adds items to cart
