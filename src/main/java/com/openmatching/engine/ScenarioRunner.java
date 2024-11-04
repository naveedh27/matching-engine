package com.openmatching.engine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static com.openmatching.engine.OrderMatchingEnginePriorityQueue.addOrder;
import static com.openmatching.engine.OrderMatchingEnginePriorityQueue.buyStack;
import static com.openmatching.engine.OrderMatchingEnginePriorityQueue.printOrderBook;
import static com.openmatching.engine.OrderMatchingEnginePriorityQueue.sellStack;
import static com.openmatching.engine.Side.BUY;
import static com.openmatching.engine.Side.SELL;


public class ScenarioRunner {

    public static void main(String[] args) {
        try {
            Path resourcesDir = Paths.get(ScenarioRunner.class.getClassLoader().getResource("").toURI());

            try (Stream<Path> paths = Files.walk(resourcesDir)) {
                paths.filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().startsWith("scenario"))
                        .peek(p -> System.out.println("Running Scenario :: " + p.getFileName()))
                        .forEach(ScenarioRunner::processFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    static void processFile(Path filePath) {
        System.out.println("Processing file: " + filePath.getFileName());

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                // Split the line by commas
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String sideStr = parts[0].trim();
                    String priceStr = parts[1].trim();
                    String qtyStr = parts[2].trim();

                    // Parse the side
                    Side side;
                    if (sideStr.equalsIgnoreCase("BUY")) {
                        side = Side.BUY;
                    } else if (sideStr.equalsIgnoreCase("SELL")) {
                        side = Side.SELL;
                    } else {
                        System.out.println("Invalid side: " + sideStr);
                        continue; // Skip to the next line
                    }

                    // Parse price and quantity
                    try {
                        int price = Integer.parseInt(priceStr);
                        int qty = Integer.parseInt(qtyStr);

                        // Call the dummy addOrder method
                        addOrder(side, price, qty);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            printOrderBook(BUY, buyStack);
            printOrderBook(SELL, sellStack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
