package com.domain.marketplace;

public class Request {
    private final int itemId;
    private final int userId;
    private int quantity;
    private final double pricePerUnit;

    public Request(int itemId, int userId, int quantity, double pricePerUnit) {
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public int getItemId() {
        return itemId;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Request) {
            Request that = (Request) object;
            return this.itemId == that.itemId
                    && this.userId == that.userId
                    && this.quantity == that.quantity
                    && this.pricePerUnit == that.pricePerUnit;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return itemId * 11
                + userId * 13
                + quantity * 17
                + (int) pricePerUnit * 19;
    }

    @Override
    public String toString() {
        return itemId + ":" + userId + ":" + quantity + "*" + pricePerUnit;
    }
}
