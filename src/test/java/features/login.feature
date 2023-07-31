Feature: Login
  As a user I should be able to log in to the app

  Scenario: Login with valid credentials
  As a user I should be able to login using valid username and password

    Given I am on the sauce demo page
    When I enter my username "standard_user"
    And I enter my password "secret_sauce"
    And I click on the login button
    Then I should be logged in
    And I should be able to see products

  Scenario: Login with invalid username
  As a user I should not be able to login using invalid username

    Given I am on the sauce demo page
    When I enter my username "standard_user2"
    And I enter my password "secret_sauce"
    And I click on the login button
    Then I should get an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Login with invalid password
  As a user I should not be able to login using invalid password

    Given I am on the sauce demo page
    When I enter my username "standard_user"
    And I enter my password "secret_sauce2"
    And I click on the login button
    Then I should get an error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Login with invalid username and password
  As a user I should not be able to login using invalid username and password

    Given I am on the sauce demo page
    When I enter my username "standard_user2"
    And I enter my password "secret_sauce2"
    And I click on the login button
    Then I should get an error message "Epic sadface: Username and password do not match any user in this service"


  Scenario Outline: Login with invalid credentials
  As a user I should not be able to login using invalid username and/or password

    Given I am on the sauce demo page
    When I enter my username "<username>"
    And I enter my password "<password>"
    And I click on the login button
    Then I should get an error message "<errorMessage>"

    Examples:
      | username       | password      | errorMessage                                                              |
      | standard_user2 | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
      | standard_user  | secret_sauce2 | Epic sadface: Username and password do not match any user in this service |
      | standard_user2 | secret_sauce2 | Epic sadface: Username and password do not match any user in this service |
      |                | secret_sauce  | Epic sadface: Username is required                                        |
      | standard_user  |               | Epic sadface: Password is required                                        |
      |                |               | Epic sadface: Username is required                                        |