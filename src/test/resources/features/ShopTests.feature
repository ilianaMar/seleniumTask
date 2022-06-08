Feature: UI tests for bard shop site

  Scenario Outline: Search product by name and add it to basket
    Given I visit bard home page
    When I search by book name <name>
    And I add 1 book from search page
    Then I verify that 1 book with <name> is added correctly

    Examples:
      | name  |
      | роман |

  Scenario Outline: Add product and update quantity on basket page
    Given I visit bard home page
    When I search by book name <name>
    And I add 1 book from search page
    When I update book quantity to be 2
    Then I verify that 2 books with <name> is added correctly

    Examples:
      | name  |
      | роман |

  Scenario: Add product from new books category page
    Given I visit bard home page
    When I visit new books category with url "/new-books/"
    Then I verify that all data in listing page
    And I add 2 books from category page
    Then I verify that 2 books is added

  Scenario: Add product to favorite lists
    Given I visit bard home page
    When I visit  register page and create user successfully
    And I visit bard home page
    When I visit new books category with url "/best-sellers/"
    And I visit book details page of first book
    When I add to favourites this book
    Then 1 book is existing in wishlist
    And I remove 1 book from wishlist
#    Then 0 books is existing in wishlist

  Scenario: Verify when generated discount is 5 percentage
    Given I visit bard home page
    When I visit new books category with url "/new-books/"
    And I add 1 book from category page
    Then I check discount should be 1 percentage
    When I update book quantity to be 2
    Then I check discount should be 5 percentage

  Scenario: Verify when generated discount is 7 percentage
    Given I visit bard home page
    When I visit new books category with url "/new-books/"
    And I add 1 book from category page
    Then I check discount should be 1 percentage
    When I update book quantity to be 4
    Then I check discount should be 7 percentage

  Scenario: Verify when generated discount is 10 percentage
    Given I visit bard home page
    When I visit new books category with url "/new-books/"
    And I add 1 book from category page
    Then I check discount should be 1 percentage
    When I update book quantity to be 10
    Then I check discount should be 10 percentage


