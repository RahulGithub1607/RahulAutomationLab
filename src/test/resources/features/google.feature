Feature: Google Search

  Scenario: Search using Google
    Given user is on Google homepage
    When user searches for "Selenium WebDriver"
    Then page title should contain "Selenium"
