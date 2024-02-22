
Feature: UsedCars DropDown Automation 

  @sanity
  Scenario: Details of Usedcar Brands available in chennai
    Given Hover on the Usedcar dropdown
    When Click on the chennai location
    Then Collect all the brand available
    And Click on Home button
