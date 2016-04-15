Feature: Query price
  To enable the user to query the best bid or offer price.

  Scenario: Query the highest bid price.
    Given a bid of item 1, user 1, quantity 10, and price 10.00 per unit
    And a bid of item 1, user 2, quantity 10, and price 12.00 per unit
    And a bid of item 1, user 3, quantity 10, and price 8.00 per unit
    When the user query for the bid price
    Then the best bid price should be 12.00

  Scenario: Query the lowest offer price.
    Given an offer of item 1, user 1, quantity 10, and price 10.00 per unit
    And an offer of item 1, user 2, quantity 10, and price 12.00 per unit
    And an offer of item 1, user 3, quantity 10, and price 8.00 per unit
    When the user query for the offer price
    Then the best offer price should be 8.00