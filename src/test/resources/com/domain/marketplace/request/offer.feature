Feature: Offer
  To enable the user to offer and query offers.

  Scenario: Add an offer and list
    Given an offer of item 1, user 1, quantity 10, and price 10.00 per unit
    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1      | 1      | 10       | 10.00        |

  Scenario: Add offers and list
    Given an offer of item 1, user 1, quantity 10, and price 10.00 per unit
    And an offer of item 2, user 1, quantity 10, and price 10.00 per unit
    And an offer of item 2, user 2, quantity 10, and price 10.00 per unit
    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1      | 1      | 10       | 10.00        |
      | 2      | 1      | 10       | 10.00        |
    When the user list all offers of user 2
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 2      | 2      | 10       | 10.00        |