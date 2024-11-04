package com.openmatching.engine;

import java.util.*;

import static com.openmatching.engine.Side.BUY;
import static com.openmatching.engine.Side.SELL;

public class OrderMatchingEnginePriorityQueue {

    static BidComparator bidComparator = new BidComparator();
    static Comparator<Order> sellComparator = bidComparator.reversed();
    static PriorityQueue<Order> buyStack = new PriorityQueue<>(bidComparator);
    static PriorityQueue<Order> sellStack = new PriorityQueue<>(sellComparator);

    public static void addOrder(Side side, int price, int qty) {
        Order order = new Order();
        order.setSide(side);
        order.setPrice(price);
        order.setQty(qty);
        order.setTimeRecv(System.nanoTime());
        addOrder(order);
    }

    static void printOrderBook(Side side, PriorityQueue<Order> orderBook) {
        System.out.println();
        System.out.println(side);
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-8s %-8s %-8s %-8s %-8s%n", "OrderID", "Price", "Qty", "Side", "CumQty", "OpenQty");
        System.out.println("---------------------------------------------------------------------------------------------------");
        orderBook.stream()
                .sorted((o1, o2) -> o1.side == BUY ? bidComparator.compare(o1, o2) : sellComparator.compare(o1, o2) )
                .forEach(System.out::println);
        System.out.println();
    }

    private static void addOrder(Order order) {
        System.out.println("Adding Order :: " + order.toCustomString());
        List<Trade> match = new ArrayList<>();
        match(order, match);
        if (!match.isEmpty()) {
            System.out.println("----------------------------------------");
            System.out.println("Trades :: ");
            match.forEach(System.out::println);
            System.out.println("----------------------------------------");
        }
        if (order.getOpenQty() > 0) {
            if (order.getSide() == BUY) {
                buyStack.add(order);
            } else {
                sellStack.add(order);
            }
        }
    }

    public static void match(Order order, List<Trade> trades) {

        if (order.getSide() == BUY) {
            if (!sellStack.isEmpty() && sellStack.peek().price <= order.getPrice()) {
                Order sellOrder = sellStack.peek();
                int matchPrice = sellOrder.price;
                int remainingQty = order.getOpenQty();
                if (remainingQty > 0) {
                    int qty = sellOrder.getOpenQty();
                    if (qty <= remainingQty) {
                        Trade trade = new Trade(order.clone(), sellOrder.clone(), qty, matchPrice);
                        sellOrder.addCumQty(qty);
                        order.addCumQty(qty);
                        trades.add(trade);
                        sellStack.poll();
                    } else {
                        Trade trade = new Trade(order.clone(), sellOrder.clone(), remainingQty, matchPrice);
                        sellOrder.addCumQty(remainingQty);
                        order.addCumQty(remainingQty);
                        trades.add(trade);
                    }
                }
            }
            if (order.getOpenQty() > 0 && !sellStack.isEmpty() && sellStack.peek().price <= order.getPrice()) {
                match(order, trades);
            }
        } else {
            if (!buyStack.isEmpty() && buyStack.peek().price >= order.getPrice()) {
                Order buyOrder = buyStack.peek();
                int matchPrice = buyOrder.price;
                int remainingQty = order.getOpenQty();
                if (remainingQty > 0) {
                    int qty = buyOrder.getOpenQty();
                    if (qty <= remainingQty) {
                        Trade trade = new Trade(order.clone(), buyOrder.clone(), qty, matchPrice);
                        buyOrder.addCumQty(qty);
                        order.addCumQty(qty);
                        trades.add(trade);
                        buyStack.poll();
                    } else {
                        Trade trade = new Trade(order.clone(), buyOrder.clone(), remainingQty, matchPrice);
                        buyOrder.addCumQty(remainingQty);
                        order.addCumQty(remainingQty);
                        trades.add(trade);
                    }
                }
            }
            if (order.getOpenQty() > 0 && !buyStack.isEmpty() && buyStack.peek().price >= order.getPrice()) {
                match(order, trades);
            }
        }
    }

}