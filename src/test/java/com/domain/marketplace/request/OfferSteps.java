package com.domain.marketplace.request;

import com.domain.marketplace.Market;
import com.domain.marketplace.Offer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OfferSteps {
    private final Market market = new Market();
    private List<Offer> offers;

    @Given("^an offer of item (\\d+), user (\\d+), quantity (\\d+), and price (.+) per unit$")
    public void add(int itemId, int userId, int quantity, double pricePerUnit) {
        Offer offer = new Offer(itemId, userId, quantity, pricePerUnit);
        market.add(offer);
    }

    @When("^the user list all offers of user (\\d+)$")
    public void list(int userId) {
        offers = market.listOffers(userId);
    }

    @Then("^the below offers should be returned$")
    public void verify(List<Offer> offers) {
        assertTrue(this.offers.equals(offers));
    }
}
