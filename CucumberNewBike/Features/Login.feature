Feature: Login to zigwheel.com 

  @sanity
  Scenario: Validate the Error Message
    Given navigate to home page
    When click on login/signUp
    And click on google
    And navigate to new window
    Then Enter the Email and click on next btn
