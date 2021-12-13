Feature: Cart operations
  Scenario: Adding and deleting items

    Given I open 'http://localhost/litecart/'
    When I add '3' items to cart
    Then cart has '3' items
    When I open cart
    Then there are added items
    When I delete all items
    Then cart is empty

