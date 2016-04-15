package com.domain.marketplace;

public class Order {
    private final int buyerId;
    private final int sellerId;
    private final int itemId;
    private final int quantity;
    private final double pricePerUnit;

    public Order(int buyerId, int sellerId, int itemId, int quantity, double pricePerUnit) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Order) {
            Order that = (Order) object;
            return this.buyerId == that.buyerId
                    && this.sellerId == that.sellerId
                    && this.itemId == that.itemId
                    && this.quantity == that.quantity
                    && this.pricePerUnit == that.pricePerUnit;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return buyerId * 11
                + sellerId * 13
                + itemId * 17
                + quantity * 19
                + (int) pricePerUnit * 23;
    }

    @Override
    public String toString() {
        return buyerId + "<-" + sellerId + ":" + itemId + ":" + quantity + "*" + pricePerUnit;
    }
}
