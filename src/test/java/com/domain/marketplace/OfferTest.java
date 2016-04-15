package com.domain.marketplace;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OfferTest {

    @Test
    public void testOffer() {
        int itemId = 123;
        int userId = 456;
        int quantity = 789;
        double pricePerUnit = 0.123;
        Offer offer = new Offer(itemId, userId, quantity, pricePerUnit);

        assertTrue(offer.getItemId() == itemId);
        assertTrue(offer.getUserId() == userId);
        assertTrue(offer.getQuantity() == quantity);
        assertTrue(offer.getPricePerUnit() == pricePerUnit);
    }

    @Test
    public void testEquals() {
        Offer offer = new Offer(1, 2, 3, 4);
        Offer offer2 = new Offer(1, 2, 3, 4);
        assertTrue(offer.equals(offer2));

        Bid bid = new Bid(1, 2, 3, 4);
        assertTrue(!offer.equals(bid));
    }

    @Test
    public void testSetQuantity() {
        Offer offer = new Offer(1, 2, 3, 4);
        assertTrue(offer.getQuantity() == 3);

        offer.setQuantity(123);
        assertTrue(offer.getQuantity() == 123);
    }
}