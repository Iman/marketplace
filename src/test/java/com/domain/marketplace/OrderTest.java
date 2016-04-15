package com.domain.marketplace;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderTest {
    @Test
    public void testCreateOrder() {
        int buyerId = 111;
        int sellerId = 222;
        int itemId = 333;
        int quantity = 444;
        double pricePerUnit = 555.666;
        Order order = new Order(buyerId, sellerId, itemId, quantity, pricePerUnit);

        assertTrue(order.getBuyerId() == buyerId);
        assertTrue(order.getSellerId() == sellerId);
        assertTrue(order.getItemId() == itemId);
        assertTrue(order.getQuantity() == quantity);
        assertTrue(order.getPricePerUnit() == pricePerUnit);
    }

    @Test
    public void testEquals() {
        Order order = new Order(1, 2, 3, 4, 5);
        Order order2 = new Order(1, 2, 3, 4, 5);
        assertTrue(order.equals(order2));

        Order order3 = new Order(1, 2, 3, 4, 6);
        assertTrue(!order.equals(order3));
    }
}