package com.domain.marketplace;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequestTest {

    @Test
    public void testConstructor() {
        int itemId = 123;
        int userId = 456;
        int quantity = 789;
        double pricePerUnit = 0.123;
        Request request = new Request(itemId, userId, quantity, pricePerUnit);

        assertTrue(request.getItemId() == itemId);
        assertTrue(request.getUserId() == userId);
        assertTrue(request.getQuantity() == quantity);
        assertTrue(request.getPricePerUnit() == pricePerUnit);
    }

    @Test
    public void testEquals() {
        Request request = new Request(1, 2, 3, 4);
        Request request2 = new Request(1, 2, 3, 4);
        assertTrue(request.equals(request2));

        Request request3 = new Request(1, 2, 3, 5);
        assertTrue(!request.equals(request3));
    }

}