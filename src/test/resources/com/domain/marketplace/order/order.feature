Feature: Order
  To create orders automatically if user's bid or offer matches.


  Scenario: A bid matches an offer

    Given an offer of item 1, user 1, quantity 10, and price 10.00 per unit
    And a bid of item 1, user 2, quantity 5, and price 15.00 per unit

    When the user list all bids of user 2
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1		 | 1	  |	5		 | 10.00		|

    When the user list all orders of buyer 2
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of seller 1
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|


  Scenario: A bid matches the earliest offer of two

    Given an offer of item 1, user 1, quantity 10, and price 10.00 per unit
    And an offer of item 1, user 3, quantity 10, and price 10.00 per unit
    And a bid of item 1, user 2, quantity 5, and price 15.00 per unit

    When the user list all bids of user 2
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1		 | 1	  |	5		 | 10.00		|

    When the user list all offers of user 3
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1		 | 3	  |	10		 | 10.00		|

    When the user list all orders of buyer 2
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of seller 1
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of seller 3
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |


  Scenario: An offer matches two bids

    Given a bid of item 1, user 2, quantity 5, and price 15.00 per unit
    And a bid of item 1, user 3, quantity 5, and price 12.00 per unit
    And an offer of item 1, user 1, quantity 15, and price 10.00 per unit

    When the user list all bids of user 2
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all bids of user 3
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1		 | 1	  |	5		 | 10.00		|

    When the user list all orders of buyer 2
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of buyer 3
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 3		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of seller 1
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|
      | 3		  | 1		 | 1	  | 5		 | 10.00		|


  Scenario: An offer matches the earliest bid of two

    Given a bid of item 1, user 2, quantity 5, and price 15.00 per unit
    And a bid of item 1, user 3, quantity 5, and price 12.00 per unit
    And an offer of item 1, user 1, quantity 5, and price 10.00 per unit

    When the user list all bids of user 2
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all bids of user 3
    Then the below bids should be returned
      | itemId | userId | quantity | pricePerUnit |
      | 1		 | 3	  |	5		 | 12.00		|

    When the user list all offers of user 1
    Then the below offers should be returned
      | itemId | userId | quantity | pricePerUnit |

    When the user list all orders of buyer 2
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|

    When the user list all orders of buyer 3
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |

    When the user list all orders of seller 1
    Then the below orders should be returned
      | buyerId | sellerId | itemId | quantity | pricePerUnit |
      | 2		  | 1		 | 1	  | 5		 | 10.00		|