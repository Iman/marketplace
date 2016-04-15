Feature: Concurrent bids
  To enable the users to bid at the same time.

  Scenario: Bid at the same time.
    Given an empty market
    When 100 users bid at the same time
    Then 100 bids from different users should be added to the market