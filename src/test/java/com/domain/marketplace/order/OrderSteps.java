package com.domain.marketplace.order;

import com.domain.marketplace.Bid;
import com.domain.marketplace.Market;
import com.domain.marketplace.Offer;
import com.domain.marketplace.Order;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OrderSteps {
    private final Market market = new Market();
    private List<Bid> bids;
    private List<Offer> offers;
    private List<Order> orders;

    @Given("^a bid of item (\\d+), user (\\d+), quantity (\\d+), and price (.+) per unit$")
    public void addBid(int itemId, int userId, int quantity, double pricePerUnit) {
        Bid bid = new Bid(itemId, userId, quantity, pricePerUnit);
        market.add(bid);
    }

    @Given("^an offer of item (\\d+), user (\\d+), quantity (\\d+), and price (.+) per unit$")
    public void addOffer(int itemId, int userId, int quantity, double pricePerUnit) {
        Offer offer = new Offer(itemId, userId, quantity, pricePerUnit);
        market.add(offer);
    }

    @When("^the user list all bids of user (\\d+)$")
    public void listBids(int userId) {
        bids = market.listBids(userId);
    }

    @When("^the user list all offers of user (\\d+)$")
    public void listOffers(int userId) {
        offers = market.listOffers(userId);
    }

    @When("^the user list all orders of buyer (\\d+)$")
    public void listOrdersByBuyer(int buyerId) {
        orders = market.listOrdersByBuyer(buyerId);
    }

    @When("^the user list all orders of seller (\\d+)$")
    public void listOrdersBySeller(int sellerId) {
        orders = market.listOrdersBySeller(sellerId);
    }

    @Then("^the below bids should be returned$")
    public void verifyBids(List<Bid> bids) {
        assertTrue(this.bids.equals(bids));
    }

    @Then("^the below offers should be returned$")
    public void verifyOffers(List<Offer> offers) {
        assertTrue(this.offers.equals(offers));
    }

    @Then("^the below orders should be returned$")
    public void verifyOrders(List<Order> orders) {
        assertTrue(this.orders.equals(orders));
    }
}
