Feature: NewBikes DropDown Automation

  @sanity
  Scenario: Details of Upcoming Bikes
    Given automate homepage element
    When hover on the Newbikes Dropdown
    And click on the Upcoming Bikes
    And select manufacturer as Honda
    Then clicked on view more
    And collect all the details of upcoming bikes