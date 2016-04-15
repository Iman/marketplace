Feature: Bid
  To enable the user to bid and query bids.

  Scenario: Add a bid and list
    Given a bid of item 1, user 1, quantity 10, and price 10.00 per unit
    When the user list all bids of user 1
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1      | 1      | 10       | 10.00        |

  Scenario: Add bids and list
    Given a bid of item 1, user 1, quantity 10, and price 10.00 per unit
    And a bid of item 2, user 1, quantity 10, and price 10.00 per unit
    And a bid of item 2, user 2, quantity 10, and price 10.00 per unit
    When the user list all bids of user 1
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1      | 1      | 10       | 10.00        |
      | 2      | 1      | 10       | 10.00        |
    When the user list all bids of user 2
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 2      | 2      | 10       | 10.00        |