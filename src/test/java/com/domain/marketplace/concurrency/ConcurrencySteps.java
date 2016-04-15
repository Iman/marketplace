package com.domain.marketplace.concurrency;

import com.domain.marketplace.Bid;
import com.domain.marketplace.Market;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

public class ConcurrencySteps {
    private final Market market = new Market();

    @Given("^an empty market$")
    public void initialize() {

    }

    @When("^(\\d+) users bid at the same time$")
    public void bid(int userNumber) throws InterruptedException {
        AtomicInteger unfinished = new AtomicInteger(userNumber);
        for (int i = 0; i < userNumber; i++) {
            Bid bid = new Bid(i, i, i, i);
            new Thread(() ->
            {
                market.add(bid);
                unfinished.decrementAndGet();
            }).start();
        }

        while (unfinished.get() > 0) {
            Thread.sleep(100);
        }
    }

    @Then("^(\\d+) bids from different users should be added to the market$")
    public void verify(int userNumber) {
        for (int i = 0; i < userNumber; i++) {
            List<Bid> bids = market.listBids(i);
            assertTrue(bids.size() == 1);

            Bid bid = bids.get(0);
            assertTrue(bid.getItemId() == i);
            assertTrue(bid.getUserId() == i);
            assertTrue(bid.getQuantity() == i);
            assertTrue(bid.getPricePerUnit() == i);
        }
    }
}
