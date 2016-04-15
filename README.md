# Pre-Interview Exercise

## Overview
Implementation of a simple marketplace which brings together buyers and sellers of goods.

## Requirement
The marketplace works as follows:

- Sellers offer items for sale. An offer consists of an item ID, a user ID, a quantity and a price per unit the seller is willing to sell at
- Buyers bid for items. A bid consists of an item ID, user ID, a quantity and a price per unit the buyer is willing to pay
- Sellers can enter offers even if there are no buyers for that item in the market
- Buyers can enter bids even if there are no sellers in the market for that item
- A bid and offer match when:
    - The bid item ID and sell item ID are the same
    - The bid price is greater than or equal to the offer price
    - The offer quantity is greater than or equal to the bid quantity
- If a bid matches many offers it should be matched against the first received offer
- If an offer matches many bids it should be matched against the first received bid
- When a bid and offer match:
    - An order is created. An order consists of a buyer ID, a seller ID, an item ID, a quantity, and a price per unit. The price of the order is the minimum of the matched bid and offer price
    - The bid is removed from the marketplace
    - The offer quantity is reduced by the amount of the matching bid. If the offer quantity is reduce to, it is removed from the marketplace
- Bids and offers stay in the marketplace until they are matched

As a first step we have been asked to implement this marketplace, providing the following API:

- Add a bid
- Add an offer
- List the bids for a given buyer user ID
- List the offers for a given seller user ID
- List the orders for a given seller user ID
- List the orders for a given buyer user ID
- Retrieve the current bid price for a given item ID (i.e. the highest price of all bids for that item)
- Retrieve the current offer price for a given item ID (i.e. the lowest price of all offers for that item)

### Example:
![alt text](https://raw.githubusercontent.com/Iman/marketplace/master/data-sample.png "Sample")

## Prerequisites
- Java 8
- maven 3

## Build
```mvn clean install```

## Test
```mvn clean test```

## BDD Test (Cucumber)
```mvn clean verify -Dit.test=request/RequestRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"```
```mvn clean verify -Dit.test=query/QueryRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"```
```mvn clean verify -Dit.test=order/OrderRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"```
```mvn clean verify -Dit.test=concurrency/ConcurrencyRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"```