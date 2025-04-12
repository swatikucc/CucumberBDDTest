Feature: Login functionality

  Scenario: Valid user login
    Given User is on the login page
    When User enters valid credentials
    Then User is redirected to the home page

  Scenario: Invalid user login
    Given User is on the login page
    When User enters invalid credentials
    Then Error message is displayed
