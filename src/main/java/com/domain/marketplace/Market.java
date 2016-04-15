package com.domain.marketplace;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Market {
    private final ConcurrentLinkedQueue<Bid> bids = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Offer> offers = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();

    public synchronized void add(Bid bid) {
        bids.add(bid);
        new CopyOnWriteArrayList<>(offers).stream().anyMatch(offer -> this.check(offer, bid));
    }

    public synchronized void add(Offer offer) {
        offers.add(offer);
        new CopyOnWriteArrayList<>(bids).stream().anyMatch(bid -> this.check(offer, bid) && offer.getQuantity() == 0);
    }

    private boolean check(Offer offer, Bid bid) {
        int itemId = offer.getItemId();
        int quantity = bid.getQuantity();
        int remainingQuantity = offer.getQuantity() - quantity;
        double pricePerUnit = offer.getPricePerUnit();

        // A bid and offer match when:
        //    The bid item ID and sell item ID are the same
        //    The bid price is greater than or equal to the offer price
        //    The offer quantity is greater than or equal to the bid quantity
        if (bid.getItemId() == itemId
                && bid.getPricePerUnit() >= pricePerUnit
                && quantity > 0
                && remainingQuantity >= 0) {
            // When a bid and offer match:
            // An order is created. An order consists of a buyer ID, a seller ID, an item ID, a quantity, and a price per unit.
            // The price of the order is the minimum of the matched bid and offer price
            Order order = new Order(bid.getUserId(), offer.getUserId(), itemId, quantity, pricePerUnit);
            orders.add(order);

            // The bid is removed from the marketplace
            bids.remove(bid);

            // The offer quantity is reduced by the amount of the matching bid.
            offer.setQuantity(remainingQuantity);
            // If the offer quantity is reduce to 0, it is removed from the marketplace
            if (remainingQuantity == 0) {
                offers.remove(offer);
            }

            return true;
        }
        return false;
    }

    public synchronized List<Bid> listBids(int userId) {
        return bids.stream().filter(bid -> bid.getUserId() == userId).collect(Collectors.toList());
    }

    public synchronized List<Offer> listOffers(int userId) {
        return offers.stream().filter(offer -> offer.getUserId() == userId).collect(Collectors.toList());
    }

    public synchronized List<Order> listOrdersByBuyer(int buyerId) {
        return orders.stream().filter(order -> order.getBuyerId() == buyerId).collect(Collectors.toList());
    }

    public synchronized List<Order> listOrdersBySeller(int sellerId) {
        return orders.stream().filter(order -> order.getSellerId() == sellerId).collect(Collectors.toList());
    }

    public synchronized double getBidPrice() {
        Optional<Bid> bid = bids.stream().max(RequestPriceComparator.INSTANCE);
        return bid.isPresent() ? bid.get().getPricePerUnit() : -Double.MAX_VALUE;
    }

    public synchronized double getOfferPrice() {
        Optional<Offer> offer = offers.stream().min(RequestPriceComparator.INSTANCE);
        return offer.isPresent() ? offer.get().getPricePerUnit() : Double.MAX_VALUE;
    }
}
