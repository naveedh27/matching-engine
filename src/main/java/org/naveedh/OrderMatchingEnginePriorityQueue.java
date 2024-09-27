package org.naveedh;

import java.util.*;
import java.util.stream.Stream;

import static org.naveedh.Side.BUY;
import static org.naveedh.Side.SELL;

public class OrderMatchingEnginePriorityQueue {

    static BidComparator bidComparator = new BidComparator();
    static Comparator<Integer> sellComparator = bidComparator.reversed();
    static PriorityQueue<Integer> buyStack = new PriorityQueue<>(bidComparator);
    static PriorityQueue<Integer> sellStack = new PriorityQueue<>(sellComparator);

    static HashMap<Integer, TreeMap<Integer, Order>> buyPriceMap = new HashMap<>();
    static HashMap<Integer, TreeMap<Integer, Order>> sellPriceMap = new HashMap<>();

    public static void main(String[] args) {


        Order b1 = new Order();
        b1.setPrice(100);
        b1.setQty(4);
        b1.setSide(BUY);
        b1.setTimeRecv(System.nanoTime());

        Order b2 = new Order();
        b2.setPrice(110);
        b2.setQty(1);
        b2.setSide(BUY);
        b2.setTimeRecv(System.nanoTime());

        Order s1 = new Order();
        s1.setPrice(150);
        s1.setQty(4);
        s1.setSide(SELL);
        s1.setTimeRecv(System.nanoTime());

        Order s2 = new Order();
        s2.setPrice(155);
        s2.setQty(1);
        s2.setSide(SELL);
        s2.setTimeRecv(System.nanoTime());


        Stream.of(b1, b2, s1, s2)
                .forEach(OrderMatchingEnginePriorityQueue::addOrder);

        System.out.println(buyStack);
        System.out.println(sellStack);

        System.out.println(buyPriceMap);
        System.out.println(sellPriceMap);

        Order h = new Order();
        h.setSide(BUY);
        h.setQty(2);
        h.setPrice(200);
        h.setTimeRecv(System.nanoTime());

        addOrder(h);

        Order l = new Order();
        l.setSide(SELL);
        l.setQty(2);
        l.setPrice(100);
        l.setTimeRecv(System.nanoTime());

        addOrder(l);

        System.out.println(buyStack);
        System.out.println(sellStack);

        System.out.println(buyPriceMap);
        System.out.println(sellPriceMap);


    }

    public static void addOrder(Order order) {
        List<Trade> match = new ArrayList<>();
        match(order, match);
        if(!match.isEmpty()) {
            System.out.println("----------------------------------------");
            System.out.println("Trades :: ");
            match.forEach(System.out::println);
            System.out.println("----------------------------------------");
        }
        if (order.getOpenQty() > 0) {
            if (order.getSide() == BUY) {
                buyStack.add(order.getPrice());
                TreeMap<Integer, Order> orderTreeMap = buyPriceMap.compute(order.getPrice(), (k, v) -> v == null ? new TreeMap<>() : v);
                orderTreeMap.put(order.getOrderId(), order);
            } else {
                sellStack.add(order.getPrice());
                TreeMap<Integer, Order> orderTreeMap = sellPriceMap.compute(order.getPrice(), (k, v) -> v == null ? new TreeMap<>() : v);
                orderTreeMap.put(order.getOrderId(), order);
            }
        }
    }

    public static List<Trade> match(Order order, List<Trade> trades) {

        if (order.getSide() == BUY) {
            if (!sellStack.isEmpty() && sellStack.peek() <= order.getPrice()) {
                Integer matchPrice = sellStack.peek();
                int remainingQty = order.getOpenQty();
                List<Integer> deleteList = new ArrayList<>();
                TreeMap<Integer, Order> sellOrderMap = sellPriceMap.get(matchPrice);
                for (Order sellOrder : sellOrderMap.values()) {
                    if (remainingQty > 0) {
                        int qty = sellOrder.getOpenQty();
                        int price = sellOrder.getPrice();
                        if (qty <= remainingQty) {
                            Trade trade = new Trade(order, sellOrder, qty, price);
                            sellOrder.addCumQty(qty);
                            order.addCumQty(qty);
                            trades.add(trade);
                            deleteList.add(sellOrder.getOrderId());
                            remainingQty -= qty;
                        } else {
                            Trade trade = new Trade(order, sellOrder, remainingQty, price);
                            sellOrder.addCumQty(remainingQty);
                            order.addCumQty(remainingQty);
                            trades.add(trade);
                            remainingQty = 0;
                        }
                    }
                }
                deleteList.forEach(sellOrderMap::remove);
                if (sellOrderMap.isEmpty()) {
                    sellStack.poll();
                    sellPriceMap.remove(matchPrice);
                }
                if (order.getOpenQty() > 0 && !sellStack.isEmpty() && sellStack.peek() <= order.getPrice()) {
                    match(order, trades);
                }
            }

        } else {
            if (!buyStack.isEmpty() && buyStack.peek() >= order.getPrice()) {
                Integer matchPrice = buyStack.peek();
                int remainingQty = order.getOpenQty();
                List<Integer> deleteList = new ArrayList<>();
                TreeMap<Integer, Order> buyOrderMap = buyPriceMap.get(matchPrice);
                for (Order buyOrder : buyOrderMap.values()) {
                    if (remainingQty > 0) {
                        int qty = buyOrder.getOpenQty();
                        int price = buyOrder.getPrice();
                        if (qty <= remainingQty) {
                            Trade trade = new Trade(buyOrder, order, qty, price);
                            buyOrder.addCumQty(qty);
                            order.addCumQty(qty);
                            trades.add(trade);
                            deleteList.add(buyOrder.getOrderId());
                            remainingQty -= qty;
                        } else {
                            Trade trade = new Trade(buyOrder, order, remainingQty, price);
                            buyOrder.addCumQty(remainingQty);
                            order.addCumQty(remainingQty);
                            trades.add(trade);
                            remainingQty = 0;
                        }
                    }
                }
                deleteList.forEach(buyOrderMap::remove);
                if (buyOrderMap.isEmpty()) {
                    buyStack.poll();
                    buyPriceMap.remove(matchPrice);
                }
                if (order.getOpenQty() > 0 && !buyStack.isEmpty() && buyStack.peek() >= order.getPrice()) {
                    match(order, trades);
                }
            }
        }

        return trades;
    }

}