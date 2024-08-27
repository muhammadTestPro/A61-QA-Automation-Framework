Feature: Login feature

  Scenario: Login Scenario
    Given I open Login Page
    When I enter email "demo@testpro.io"
    And I enter Password "te$t$tudent"
    And I submit
    Then I am logged in

  Scenario Outline: Negative Login Scenario
    Given I open Login Page
    When I enter email "<email>"
    And I enter Password "<password>"
    And I submit
    Then I should not get logged in

    Examples:
      | email               | password    |
      | invalid@testpro.io  | te$t$tudent |
      | valid@testpro.io    | invalidPass |
      | valid@testpro.io    |             |
      |                     | te$t$tudent |