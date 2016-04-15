package com.domain.marketplace;

public class Bid extends Request {
    public Bid(int itemId, int userId, int quantity, double pricePerUnit) {
        super(itemId, userId, quantity, pricePerUnit);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Bid && super.equals(object);
    }
}
