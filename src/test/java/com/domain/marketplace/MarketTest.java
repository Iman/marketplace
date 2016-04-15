package com.domain.marketplace;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertTrue;

public class MarketTest {

    @Test
    public void testBid() {
        Market market = new Market();

        int userId = 123;
        List<Bid> bids = Arrays.asList(
                new Bid(1, userId, 3, 4),
                new Bid(5, userId, 7, 8),
                new Bid(9, userId, 11, 12));
        bids.forEach(bid -> market.add(bid));

        assertTrue(market.listBids(userId).equals(bids));
    }

    @Test
    public void testOffer() {
        Market market = new Market();

        int userId = 123;
        List<Offer> offers = Arrays.asList(
                new Offer(1, userId, 3, 4),
                new Offer(5, userId, 7, 8),
                new Offer(9, userId, 11, 12));
        offers.forEach(offer -> market.add(offer));

        assertTrue(market.listOffers(userId).equals(offers));
    }

    @Test
    public void testBidMatchesOffer() {
        Market market = new Market();

        int sellerId = 1;
        int buyerId = 2;
        List<Offer> offers = Arrays.asList(
                new Offer(1, sellerId, 10, 10), // Quantity not match.
                new Offer(1, sellerId, 20, 20), // Price not match.
                new Offer(1, sellerId, 20, 10) // Complete match.
        );
        offers.forEach(offer -> market.add(offer));
        Bid bid = new Bid(1, buyerId, 20, 15);
        market.add(bid);

        // Check bids and offers.
        List<Offer> remainingOffers = Arrays.asList(offers.get(0), offers.get(1));
        assertTrue(market.listOffers(sellerId).equals(remainingOffers));
        assertTrue(market.listBids(buyerId).isEmpty());

        // Check orders.
        List<Order> orders = Arrays.asList(
                new Order(buyerId, sellerId, 1, 20, 10));
        assertTrue(market.listOrdersByBuyer(buyerId).equals(orders));
        assertTrue(market.listOrdersBySeller(sellerId).equals(orders));
    }

    @Test
    public void testOfferMatchesBids() {
        Market market = new Market();

        int buyerId = 1;
        int sellerId = 2;
        List<Bid> bids = Arrays.asList(
                new Bid(1, buyerId, 10, 10), // Complete match.
                new Bid(1, buyerId, 10, 5), // Price not match.
                new Bid(1, buyerId, 20, 12), // Quantity not match.
                new Bid(1, buyerId, 5, 11) // Complete match.
        );
        bids.forEach(bid -> market.add(bid));
        Offer offer = new Offer(1, sellerId, 20, 9);
        market.add(offer);

        // Check bids and offers.
        List<Bid> remainingBids = Arrays.asList(bids.get(1), bids.get(2));
        assertTrue(market.listBids(buyerId).equals(remainingBids));
        List<Offer> remainingOffer = Arrays.asList(new Offer(1, sellerId, 5, 9));
        assertTrue(market.listOffers(sellerId).equals(remainingOffer));

        // Check orders.
        List<Order> orders = Arrays.asList(
                new Order(buyerId, sellerId, 1, 10, 9),
                new Order(buyerId, sellerId, 1, 5, 9));
        assertTrue(market.listOrdersByBuyer(buyerId).equals(orders));
        assertTrue(market.listOrdersBySeller(sellerId).equals(orders));
    }

    @Test
    public void testBidPrice() {
        Market market = new Market();

        List<Bid> bids = Arrays.asList(
                new Bid(1, 1, 1, 10),
                new Bid(1, 1, 1, 15),
                new Bid(1, 1, 1, 7),
                new Bid(1, 1, 1, 3),
                new Bid(1, 1, 1, 12)
        );
        bids.forEach(bid -> market.add(bid));

        assertTrue(market.getBidPrice() == 15);
    }

    @Test
    public void testOfferPrice() {
        Market market = new Market();

        List<Offer> offers = Arrays.asList(
                new Offer(1, 1, 1, 10),
                new Offer(1, 1, 1, 15),
                new Offer(1, 1, 1, 7),
                new Offer(1, 1, 1, 3),
                new Offer(1, 1, 1, 12)
        );
        offers.forEach(offer -> market.add(offer));

        assertTrue(market.getOfferPrice() == 3);
    }

    @Test
    public void testConcurrency() throws InterruptedException {
        Market market = new Market();

        int userId = 1;
        AtomicInteger finished = new AtomicInteger();
        List<Bid> bids = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Bid bid = new Bid(i, userId, i, i);
            bids.add(bid);
            new Thread(() ->
            {
                market.add(bid);
                finished.incrementAndGet();
            }).start();
        }

        while (finished.get() < 100) {
            Thread.sleep(100);
        }

        List<Bid> marketBids = market.listBids(userId);
        assertTrue(marketBids.size() == 100);
        assertTrue(marketBids.containsAll(bids));
    }
}