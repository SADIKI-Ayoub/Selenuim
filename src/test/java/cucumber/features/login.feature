Feature: Login Feature

  @smoke
  Scenario Outline: I login the website with invalid username and invalid password-1 for parallel testing
    Given Browser is open
    And I am on the login page
    When I try to login with "<username>" and "<password>"
    Then I verify invalid login message
    Examples:
      | username               | password |
      | sadiki@ayoub.com       | 11223344 |
