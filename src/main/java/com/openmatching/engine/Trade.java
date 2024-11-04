package com.openmatching.engine;

import java.util.concurrent.atomic.AtomicInteger;

public record Trade(Order buySide, Order sellSide, int tradeQty, int tradePrice, int tradeId) {

    private static final AtomicInteger tradeCount = new AtomicInteger(1);

    public Trade(Order buySide, Order sellSide, int tradeQty, int tradePrice) {
        this(buySide, sellSide, tradeQty, tradePrice, tradeCount.getAndIncrement());
    }

}
