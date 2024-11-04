Simple Java Order Matching Engine
=================================

Overview
--------

This project is a simple Java program that simulates an order matching engine, similar to those used in financial exchanges. It uses priority queues to manage buy and sell orders, matching them based on price and time priority. The engine processes orders, executes trades when possible, and maintains an order book that reflects the current state of all open orders.

Features
--------

-   **Order Submission**: Submit buy or sell orders with specified prices and quantities.
-   **Order Matching**: Automatically matches buy and sell orders based on price and time priority.
-   **Order Book Management**: Maintains an up-to-date order book displaying all open orders.
-   **Priority Queues**: Utilizes Java's `PriorityQueue` to efficiently manage and prioritize orders.
-   **Trade Execution**: Executes trades when matching orders are found, updating cumulative and open quantities.
-   **Simplified Simulation**: Ideal for educational purposes to understand the basics of order matching engines.

Data Structures Used
--------------------

-   **PriorityQueue**:
    -   **Buy Orders**: Implemented as a max-heap priority queue, where orders with higher prices have higher priority.
    -   **Sell Orders**: Implemented as a min-heap priority queue, where orders with lower prices have higher priority.
-   **Order Class**: Represents an individual order with attributes:

    - **orderId** (`int`): A unique identifier for the order, automatically assigned.
    - **qty** (`int`): The total quantity of the order.
    - **price** (`int`): The price at which the order is placed.
    - **side** (`Side`): The side of the order, either `BUY` or `SELL`.
    - **timeRecv** (`long`): The timestamp when the order was received, used for time priority.
    - **cumQty** (`int`): Cumulative quantity that has been filled.
    - **openQty** (`int`): Remaining quantity not yet filled.
    - **orderCount** (`AtomicInteger`): A static counter used to generate unique order IDs.

How It Works
------------

1.  **Adding Orders**:

    -   Users submit orders using the `addOrder(Side, Price, Quantity)` method.
    -   The engine attempts to match the new order with existing orders on the opposite side of the order book.
2.  **Order Matching Logic**:

    -   **Price Priority**: Orders are matched starting with the best available prices.
        -   For **buy orders**, higher prices have priority.
        -   For **sell orders**, lower prices have priority.
    -   **Time Priority**: If multiple orders have the same price, the earliest entered order has priority.
3.  **Trade Execution**:

    -   When a match is found, the engine executes the trade up to the available quantities.
    -   Updates the **CumQty** and **OpenQty** for both orders involved.
    -   Removes orders from the order book when they are fully filled.
4.  **Order Book Snapshot**:

    -   The engine maintains an order book that displays all open buy and sell orders.
    -   Orders are sorted based on price and time priority.

### Usage

-   The program can be run interactively or configured to read commands from a file.
-   **Adding Orders**:
    -   **Buy Order**: `addOrder(BUY, price, quantity);`
    -   **Sell Order**: `addOrder(SELL, price, quantity);`
-   The engine will automatically process the order, attempt to match it, and update the order book.

Example Scenario
----------------
**Order Matching Engine - Simple Scenario**

This project simulates a simple order matching engine for educational purposes. The matching engine processes buy and sell orders based on price and time priority, executing trades when possible.

### Example Scenario with 5 Orders

#### Initial Order Book

| Side   | Price   | Quantity   |
|--------|---------|------------|
| ------ | ------- | ---------- |


1.  **Buy Order: 50 units at price 100**

    -   **Order Book After Order 1:**

        | Side | Price | Quantity |
        |------|-------|----------|
        | Buy  | 100   | 50       |

   2.  **Sell Order: 30 units at price 90**

       -   Matches with the existing buy order at price 100. 30 units are traded, and 20 units remain in the buy order.

       -   **Order Book After Order 2:**

       | Side | Price | Quantity |
       |------|-------|----------|
       | Buy  | 100   | 20       |

3.  **Sell Order: 25 units at price 95**

    -   Matches partially with the remaining buy order (20 units at price 100). 20 units are traded, and 5 units are added to the sell order book.

    -   **Order Book After Order 3:**

    | Side | Price | Quantity |
    |------|-------|----------|
    | Sell | 95    | 5        |

4.  **Buy Order: 40 units at price 92**

    -   Added to the buy order book as it cannot match with the existing sell order (5 units at price 95).

    -   **Order Book After Order 4:**

    | Side | Price | Quantity |
    |------|-------|----------|
    | Buy  | 92    | 40       |
    | Sell | 95    | 5        |

5.  **Sell Order: 50 units at price 90**

    -   Matches with the buy order (40 units at price 92). 40 units are traded, and 10 units remain in the sell order book.

    -   **Order Book After Order 5:**

    | Side | Price | Quantity |
    |------|-------|----------|
    | Sell | 95    | 5        |
    | Sell | 90    | 10       |

### Final Order Book Snapshot

| Side | Price | Quantity |
|------|-------|----------|
| Sell | 95    | 5        |
| Sell | 90    | 10       |

This scenario illustrates the core functionality of an order matching engine, including price and time priority, and how orders are partially or fully matched based on available market conditions.

Customization
-------------

-   **Adjust Matching Logic**: Modify the matching algorithm to handle different types of orders (e.g., limit orders, market orders).
-   **Add Features**: 
     - Implement order cancellations, order modifications, or support for different order types.
     - Add Multi Instrument Support
-   **Performance Optimization**: 
     - Use high-performant data structure

Limitations
-----------

-   **Simplified Logic**: This engine does not handle all the complexities of a real-world order matching system.
-   **No Persistence**: Orders are not saved to a database; they exist only during the program's execution.
-   **Concurrency**: The engine is not designed to handle concurrent order submissions.

Contributing
------------

Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.


Contact
-------

For questions or support, please create a new discussion

Acknowledgments
---------------

-   Inspired by basic trading and matching engine principles.
-   Utilizes Java's built-in data structures for simplicity and efficiency.

* * * * *

Thank you for your interest in this project! Enjoy exploring the basics of an order matching engine.