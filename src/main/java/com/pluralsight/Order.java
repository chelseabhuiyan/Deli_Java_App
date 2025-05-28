package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final LocalDateTime orderTime = LocalDateTime.now(); //  Track when order was created

    private List<BaseSandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chip> chips = new ArrayList<>();
    private List<Sides> sides = new ArrayList<>();

    public void addSandwich(BaseSandwich sandwich) {
        if (sandwich != null) sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink) {
        if (drink != null) drinks.add(drink);
    }

    public void addChip(Chip chip) {
        if (chip != null) chips.add(chip);
    }

    public void addSide(Sides side) {
        if (side != null) sides.add(side);
    }

    public double getTotalCost() {
        double total = 0.0;
        for (BaseSandwich s : sandwiches) total += s.getCost();
        for (Drink d : drinks) total += d.getCost();
        for (Chip c : chips) total += c.getCost();
        for (Sides s : sides) total += s.getCost();
        return total;
    }

    //  Helper method to reuse formatted timestamp
    public String getFormattedTimestamp() {
        return orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order Receipt\n");
        receipt.append("Timestamp: ").append(getFormattedTimestamp()).append("\n"); //  Add timestamp to receipt
        receipt.append("-------------\n");

        if (!sandwiches.isEmpty()) {
            receipt.append("Sandwiches:\n");
            sandwiches.forEach(s -> receipt.append(s.toString()).append("\n"));
        }

        if (!drinks.isEmpty()) {
            receipt.append("Drinks:\n");
            drinks.forEach(d -> receipt.append(d.toString()).append("\n"));
        }

        if (!chips.isEmpty()) {
            receipt.append("Chips:\n");
            chips.forEach(c -> receipt.append(c.toString()).append("\n"));
        }

        if (!sides.isEmpty()) {
            receipt.append("Sides:\n");
            sides.forEach(s -> receipt.append(s.toString()).append("\n"));
        }

        receipt.append(String.format("\nTotal: $%.2f\n", getTotalCost()));
        return receipt.toString();
    }

    public void saveReceipt() {
        String folderName = "receipts";
        File folder = new File(folderName);
        if (!folder.exists()) folder.mkdir();

        String filename = folderName + "/" + getFormattedTimestamp() + ".txt"; //saves the receipt wit the time stamp

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(getReceipt());
        } catch (IOException e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }

    public void clear() {
        sandwiches.clear();
        drinks.clear();
        chips.clear();
        sides.clear();
    }

    @Override
    public String toString() {
        return getReceipt();
    }
}
