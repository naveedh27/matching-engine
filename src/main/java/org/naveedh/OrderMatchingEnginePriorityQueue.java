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

    static TreeMap<Integer, TreeMap<Integer, Order>> buyOrderBook = new TreeMap<>();
    static TreeMap<Integer, TreeMap<Integer, Order>> sellOrderBook = new TreeMap<>();

    public static void main(String[] args) {

       /* addOrder(BUY, 150, 20);
        addOrder(BUY, 100, 50);

        addOrder(SELL, 200, 25);
        addOrder(SELL, 250, 100);

        System.out.println(buyStack);
        System.out.println(sellStack);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

        addOrder(BUY, 210, 25);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

        addOrder(SELL, 50, 100);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

        addOrder(BUY, 150, 20);
        addOrder(BUY, 100, 50);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);*/

        //scenario1();
        //scenario2();
        //scenario3();
        //scenario4();
        scenario5();

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

    }

    private static void scenario5() {

        addOrder(BUY, 100, 100);
        addOrder(BUY, 105, 100);
        addOrder(BUY, 110, 100);
        addOrder(BUY, 115, 100);
        addOrder(BUY, 120, 100);

        addOrder(SELL, 100, 385);

        addOrder(BUY, 100, 500);

        addOrder(SELL, 100, 1850);


    }
    private static void scenario4() {
        addOrder(BUY, 100, 50);    // Order 1
        addOrder(BUY, 105, 60);    // Order 2
        addOrder(BUY, 102, 40);    // Order 3
        addOrder(BUY, 110, 70);    // Order 4
        addOrder(BUY, 108, 80);    // Order 5
        addOrder(BUY, 99,  90);    // Order 6
        addOrder(BUY, 103, 30);    // Order 7
        addOrder(BUY, 101, 20);    // Order 8
        addOrder(BUY, 107, 50);    // Order 9
        addOrder(BUY, 104, 60);    // Order 10
        addOrder(BUY, 106, 40);    // Order 11
        addOrder(BUY, 109, 55);    // Order 12
        addOrder(BUY, 100, 65);    // Order 13
        addOrder(BUY, 111, 45);    // Order 14
        addOrder(BUY, 112, 35);    // Order 15
        addOrder(BUY, 113, 25);    // Order 16
        addOrder(BUY, 114, 15);    // Order 17
        addOrder(BUY, 115, 20);    // Order 18
        addOrder(BUY, 116, 30);    // Order 19
        addOrder(BUY, 117, 40);    // Order 20
        addOrder(BUY, 118, 50);    // Order 21
        addOrder(BUY, 119, 60);    // Order 22
        addOrder(BUY, 120, 70);    // Order 23
        addOrder(BUY, 121, 80);    // Order 24
        addOrder(BUY, 122, 90);    // Order 25

// SELL Orders
        addOrder(SELL, 105, 55);   // Order 26
        addOrder(SELL, 103, 65);   // Order 27
        addOrder(SELL, 108, 75);   // Order 28
        addOrder(SELL, 110, 85);   // Order 29
        addOrder(SELL, 112, 95);   // Order 30
        addOrder(SELL, 99,  50);   // Order 31
        addOrder(SELL, 100, 60);   // Order 32
        addOrder(SELL, 104, 70);   // Order 33
        addOrder(SELL, 106, 80);   // Order 34
        addOrder(SELL, 107, 90);   // Order 35
        addOrder(SELL, 109, 40);   // Order 36
        addOrder(SELL, 111, 30);   // Order 37
        addOrder(SELL, 113, 20);   // Order 38
        addOrder(SELL, 114, 10);   // Order 39
        addOrder(SELL, 115, 25);   // Order 40
        addOrder(SELL, 116, 35);   // Order 41
        addOrder(SELL, 117, 45);   // Order 42
        addOrder(SELL, 118, 55);   // Order 43
        addOrder(SELL, 119, 65);   // Order 44
        addOrder(SELL, 120, 75);   // Order 45
        addOrder(SELL, 121, 85);   // Order 46
        addOrder(SELL, 122, 95);   // Order 47
        addOrder(SELL, 123, 105);  // Order 48
        addOrder(SELL, 124, 115);  // Order 49
        addOrder(SELL, 125, 125);  // Order 50
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
    }
    private static void scenario3() {
        addOrder(BUY, 100, 50);    // Order 1
        addOrder(SELL, 105, 30);   // Order 2
        addOrder(BUY, 102, 40);    // Order 3
        addOrder(SELL, 101, 20);   // Order 4
        addOrder(BUY, 103, 60);    // Order 5
        addOrder(SELL, 100, 70);   // Order 6
        addOrder(SELL, 99, 50);    // Order 7
        addOrder(BUY, 98, 80);     // Order 8
        addOrder(SELL, 97, 30);    // Order 9
        addOrder(BUY, 105, 40);    // Order 10
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
    }

    private static void scenario2() {
        addOrder(BUY, 100, 50);
        addOrder(SELL, 105, 30);
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
        addOrder(BUY, 110, 40);
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
        addOrder(SELL, 100, 20);
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
        addOrder(BUY, 105, 30);
        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
        addOrder(SELL, 105, 25);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);
    }

    private static void scenario1() {
        addOrder(BUY, 100, 50);
        addOrder(SELL, 90, 30);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

        addOrder(SELL, 95, 25);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);


        addOrder(BUY, 92, 40);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);

        addOrder(SELL, 90, 50);

        printOrderBook(BUY, buyOrderBook);
        printOrderBook(SELL, sellOrderBook);


        System.out.println(buyStack);
        System.out.println(sellStack);
    }

    private static void addOrder(Side side, int price, int qty) {
        Order order = new Order();
        order.setSide(side);
        order.setPrice(price);
        order.setQty(qty);
        order.setTimeRecv(System.nanoTime());
        addOrder(order);
    }

    private static void printOrderBook(Side side, TreeMap<Integer, TreeMap<Integer, Order>> orderBook) {
        System.out.println();
        System.out.println(side);
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-8s %-8s %-8s %-8s %-8s%n", "OrderID", "Price", "Qty", "Side", "CumQty", "OpenQty");
        System.out.println("---------------------------------------------------------------------------------------------------");
        Collection<TreeMap<Integer, Order>> sortedOrderBook = side == SELL ? orderBook.descendingMap().values() : orderBook.values();
        sortedOrderBook.stream()
                .map(TreeMap::values)
                .flatMap(Collection::stream)
                .forEach(System.out::println);
        System.out.println();
    }

    public static void addOrder(Order order) {
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
                buyStack.add(order.getPrice());
                TreeMap<Integer, Order> orderTreeMap = buyOrderBook.compute(order.getPrice(), (k, v) -> v == null ? new TreeMap<>() : v);
                orderTreeMap.put(order.getOrderId(), order);
            } else {
                sellStack.add(order.getPrice());
                TreeMap<Integer, Order> orderTreeMap = sellOrderBook.compute(order.getPrice(), (k, v) -> v == null ? new TreeMap<>() : v);
                orderTreeMap.put(order.getOrderId(), order);
            }
        }
    }

    public static void match(Order order, List<Trade> trades) {

        if (order.getSide() == BUY) {
            if (!sellStack.isEmpty() && sellStack.peek() <= order.getPrice()) {
                Integer matchPrice = sellStack.peek();
                int remainingQty = order.getOpenQty();
                TreeMap<Integer, Order> sellOrderMap = sellOrderBook.get(matchPrice);
                Iterator<Map.Entry<Integer, Order>> sellOrders = sellOrderMap.entrySet().iterator();
                while (sellOrders.hasNext()) {
                    Order sellOrder = sellOrders.next().getValue();
                    if (remainingQty > 0) {
                        int qty = sellOrder.getOpenQty();
                        int price = sellOrder.getPrice();
                        if (qty <= remainingQty) {
                            Trade trade = new Trade(order.clone(), sellOrder.clone(), qty, price);
                            sellOrder.addCumQty(qty);
                            order.addCumQty(qty);
                            trades.add(trade);
                            remainingQty -= qty;
                            sellOrders.remove();
                        } else {
                            Trade trade = new Trade(order.clone(), sellOrder.clone(), remainingQty, price);
                            sellOrder.addCumQty(remainingQty);
                            order.addCumQty(remainingQty);
                            trades.add(trade);
                            remainingQty = 0;
                        }
                    } else {
                        break;
                    }
                }
                if (sellOrderMap.isEmpty()) {
                    sellStack.poll();
                    sellOrderBook.remove(matchPrice);
                }
                if (order.getOpenQty() > 0 && !sellStack.isEmpty() && sellStack.peek() <= order.getPrice()) {
                    match(order, trades);
                }
            }

        } else {
            if (!buyStack.isEmpty() && buyStack.peek() >= order.getPrice()) {
                Integer matchPrice = buyStack.peek();
                int remainingQty = order.getOpenQty();
                TreeMap<Integer, Order> buyOrderMap = buyOrderBook.get(matchPrice);
                Iterator<Map.Entry<Integer, Order>> buyOrders = buyOrderMap.entrySet().iterator();
                while (buyOrders.hasNext()) {
                    Order buyOrder = buyOrders.next().getValue();
                    if (remainingQty > 0) {
                        int qty = buyOrder.getOpenQty();
                        int price = buyOrder.getPrice();
                        if (qty <= remainingQty) {
                            Trade trade = new Trade(buyOrder, order, qty, price);
                            buyOrder.addCumQty(qty);
                            order.addCumQty(qty);
                            trades.add(trade);
                            remainingQty -= qty;
                            buyOrders.remove();
                        } else {
                            Trade trade = new Trade(buyOrder, order, remainingQty, price);
                            buyOrder.addCumQty(remainingQty);
                            order.addCumQty(remainingQty);
                            trades.add(trade);
                            remainingQty = 0;
                        }
                    } else {
                        break;
                    }
                }
                if (buyOrderMap.isEmpty()) {
                    buyStack.poll();
                    buyOrderBook.remove(matchPrice);
                }
                if (order.getOpenQty() > 0 && !buyStack.isEmpty() && buyStack.peek() >= order.getPrice()) {
                    match(order, trades);
                }
            }
        }
    }

}