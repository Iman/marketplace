package com.domain.marketplace.request;

import com.domain.marketplace.Bid;
import com.domain.marketplace.Market;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BidSteps {
    private final Market market = new Market();
    private List<Bid> bids;

    @Given("^a bid of item (\\d+), user (\\d+), quantity (\\d+), and price (.+) per unit$")
    public void add(int itemId, int userId, int quantity, double pricePerUnit) {
        Bid bid = new Bid(itemId, userId, quantity, pricePerUnit);
        market.add(bid);
    }

    @When("^the user list all bids of user (\\d+)$")
    public void list(int userId) {
        bids = market.listBids(userId);
    }

    @Then("^the below bids should be returned$")
    public void verify(List<Bid> bids) {
        assertTrue(this.bids.equals(bids));
    }
}
