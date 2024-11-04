package com.openmatching.engine;

import java.util.concurrent.atomic.AtomicInteger;

public class Order implements Cloneable{
    int orderId;
    int qty;
    int price;
    Side side;
    long timeRecv;
    int cumQty;
    int openQty;
    private static final AtomicInteger orderCount = new AtomicInteger(1);


    public Order(Side side, int price, int qty) {
        this();
        this.side = side;
        this.price = price;
        this.qty = qty;
    }

    public Order() {
        this.orderId = orderCount.getAndIncrement();
        this.timeRecv = System.nanoTime();
    }

    public long getTime() {
        return timeRecv;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
        this.openQty =qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public long getTimeRecv() {
        return timeRecv;
    }

    public void setTimeRecv(long timeRecv) {
        this.timeRecv = timeRecv;
    }

    public int getCumQty() {
        return cumQty;
    }

    public void setCumQty(int cumQty) {
        this.cumQty = cumQty;
    }

    public void addCumQty(int cumQty) {
        this.cumQty += cumQty;
        this.openQty -= cumQty;
    }

    public int getOpenQty() {
        return openQty;
    }


    @Override
    public String toString() {
        return String.format("%-8d %-8d %-8d %-8s %-8d %-8d", orderId, price, qty, side, cumQty, openQty);
    }

    public String toCustomString() {
        return "Order{" +
                "side=" + side +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }


    @Override
    public Order clone() {
        try {
            Order clone = (Order) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
