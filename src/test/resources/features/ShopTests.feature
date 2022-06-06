Feature: UI tests for bard shop site

  Scenario Outline: Search product by name and add it to basket
    Given I visit "bard" home page
    When I search by book name <name>
    And I add 1 book from search page
    Then I verify that 1 book with <name> is added correctly

    Examples:
      | name  |
      | роман |

  Scenario Outline: Add product and update quantity on basket page
    Given I visit "bard" home page
    When I search by book name <name>
    And I add 1 book from search page
    When I update book quantity to be 2
    Then I verify that 2 books with <name> is added correctly

    Examples:
      | name  |
      | роман |

  Scenario: Add product from new category page
    Given I visit "bard" home page
    When I visit new books category with url "/new-books/"
    Then I verify that all data in listing page
    And I add 2 books from category page
    Then I verify that 2 books is added