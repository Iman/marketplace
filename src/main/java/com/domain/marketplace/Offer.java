package com.domain.marketplace;


public class Offer extends Request {
    public Offer(int itemId, int userId, int quantity, double pricePerUnit) {
        super(itemId, userId, quantity, pricePerUnit);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Offer && super.equals(object);
    }
}
