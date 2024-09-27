package org.naveedh;

import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    int orderId;
    int qty;
    int price;
    Side side;
    long timeRecv;
    int cumQty;
    int openQty;
    private static final AtomicInteger orderCount = new AtomicInteger(1);


    public Order() {
        this.orderId = orderCount.getAndIncrement();
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
        return "Order{" +
                "orderId=" + orderId +
                ", qty=" + qty +
                ", price=" + price +
                ", side=" + side +
                ", timeRecv=" + timeRecv +
                ", cumQty=" + cumQty +
                ", openQty=" + openQty +
                '}';
    }
}
