Feature: Google Search

  Scenario Outline: Search using Google
    Given user is on Google homepage
    When user searches for <query>
    Then page title should contain <expectedTitle>
    Examples:
      | query                | expectedTitle |
      | "Selenium WebDriver" | "Selenium"    |
