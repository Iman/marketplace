package com.domain.marketplace;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BidTest {

    @Test
    public void testBid() {
        int itemId = 123;
        int userId = 456;
        int quantity = 789;
        double pricePerUnit = 0.123;
        Bid bid = new Bid(itemId, userId, quantity, pricePerUnit);

        assertTrue(bid.getItemId() == itemId);
        assertTrue(bid.getUserId() == userId);
        assertTrue(bid.getQuantity() == quantity);
        assertTrue(bid.getPricePerUnit() == pricePerUnit);
    }

    @Test
    public void testEquals() {
        Bid bid = new Bid(1, 2, 3, 4);
        Bid bid2 = new Bid(1, 2, 3, 4);
        assertTrue(bid.equals(bid2));

        Offer offer = new Offer(1, 2, 3, 4);
        assertFalse(bid.equals(offer));
    }
}