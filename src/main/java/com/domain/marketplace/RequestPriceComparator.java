package com.domain.marketplace;

import java.util.Comparator;

public enum RequestPriceComparator implements Comparator<Request> {
    INSTANCE;

    @Override
    public int compare(Request request1, Request request2) {
        double difference = request1.getPricePerUnit() - request2.getPricePerUnit();
        return (difference > 0 ? 1 : (difference == 0 ? 0 : -1));
    }
}
