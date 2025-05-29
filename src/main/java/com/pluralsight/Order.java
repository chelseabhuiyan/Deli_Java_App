package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final LocalDateTime orderTime = LocalDateTime.now(); // Track when order was created

    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    public double getTotalCost() {
        return items.stream().mapToDouble(MenuItem::getCost).sum();
    }

    // Helper method to reuse formatted timestamp
    public String getFormattedTimestamp() {
        return orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order Receipt\n");
        receipt.append("Timestamp: ").append(getFormattedTimestamp()).append("\n");
        receipt.append("-------------\n");

        for (MenuItem item : items) {
            receipt.append(item.toString()).append("\n");
        }

        receipt.append(String.format("\nTotal: $%.2f\n", getTotalCost()));
        return receipt.toString();
    }

    public void saveReceipt() {
        String folderName = "receipts";
        File folder = new File(folderName);
        if (!folder.exists()) folder.mkdirs();

        String filename = folderName + "/" + getFormattedTimestamp() + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(getReceipt());
        } catch (IOException e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return getReceipt();
    }
}
