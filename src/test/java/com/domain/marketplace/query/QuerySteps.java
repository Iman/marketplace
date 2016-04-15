package com.domain.marketplace.query;

import com.domain.marketplace.Bid;
import com.domain.marketplace.Market;
import com.domain.marketplace.Offer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class QuerySteps {
    private final Market market = new Market();
    private double bidPrice;
    private double offerPrice;

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

    @When("^the user query for the bid price$")
    public void queryBid() {
        bidPrice = market.getBidPrice();
    }

    @When("^the user query for the offer price$")
    public void queryOffer() {
        offerPrice = market.getOfferPrice();
    }

    @Then("^the best bid price should be (.+)$")
    public void verifyBid(double price) {
        assertTrue(bidPrice == price);
    }

    @Then("^the best offer price should be (.+)$")
    public void verifyOffer(double price) {
        assertTrue(offerPrice == price);
    }
}
