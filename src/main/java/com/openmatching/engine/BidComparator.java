package com.openmatching.engine;

import java.util.Comparator;

public class BidComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o2.price - o1.price;
    }
}
