package com.pluralsight;

import com.pluralsight.Sandwich.CustomizedSandwich;
import com.pluralsight.Sandwich.SignatureSandwich;
import com.pluralsight.Sandwich.Topping;
import com.pluralsight.Sandwich.SandwichSize;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Order {
    private static int orderCounter = 1;
    private final int orderNumber;
    private final LocalDateTime orderTime = LocalDateTime.now();
    private final List<MenuItem> items = new ArrayList<>();

    public Order() {
        this.orderNumber = orderCounter++;
    }

    public void addItem(MenuItem item) {
        if (item != null) items.add(item);
    }

    public double getTotalCost() {
        return items.stream().mapToDouble(MenuItem::getCost).sum();
    }

    private String getFormattedTimestamp() {
        return orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
    }

    public String getReceipt() {
        StringBuilder r = new StringBuilder();
        r.append("===== Order #").append(orderNumber).append(" =====\n")
                .append("Time: ").append(getFormattedTimestamp()).append("\n\n");

        for (MenuItem item : items) {
            if (item instanceof CustomizedSandwich cs) {
                // Base line
                double basePrice = switch (cs.getSize()) {
                    case FOUR   -> 5.50;
                    case EIGHT  -> 7.00;
                    case TWELVE -> 8.50;
                };
                r.append("Customized Sandwich (")
                        .append(cs.getSize()).append("\", ")
                        .append(cs.getBread())
                        .append(cs.isToasted() ? ", toasted" : "")
                        .append(") - $")
                        .append(String.format("%.2f", basePrice))
                        .append("\n");

                // Meats
                if (!cs.getMeats().isEmpty()) {
                    r.append("Meats:\n");
                    for (Topping t : cs.getMeats()) {
                        double cost = t.getCost(cs.getSize());
                        r.append("  - ").append(t.getName())
                                .append(" - $").append(String.format("%.2f", cost))
                                .append("\n");
                        if (t.isExtra()) {
                            double extra = cost - baseMeatCost(t, cs.getSize());
                            r.append("    + Extra meat - $")
                                    .append(String.format("%.2f", extra))
                                    .append("\n");
                        }
                    }
                }

                // Cheeses
                if (!cs.getCheeses().isEmpty()) {
                    r.append("Cheeses:\n");
                    for (Topping t : cs.getCheeses()) {
                        double cost = t.getCost(cs.getSize());
                        r.append("  - ").append(t.getName())
                                .append(" - $").append(String.format("%.2f", cost))
                                .append("\n");
                        if (t.isExtra()) {
                            double extra = cost - baseCheeseCost(t, cs.getSize());
                            r.append("    + Extra cheese - $")
                                    .append(String.format("%.2f", extra))
                                    .append("\n");
                        }
                    }
                }

                // Regular toppings & sauces
                if (!cs.getRegularToppings().isEmpty()) {
                    r.append("Toppings (Included): ")
                            .append(
                                    cs.getRegularToppings().stream()
                                            .map(t -> t.getName().toString())      // map to String
                                            .collect(Collectors.joining(", "))
                            )
                            .append("\n");
                }
                if (!cs.getSauces().isEmpty()) {
                    r.append("Sauces (Included): ")
                            .append(
                                    cs.getSauces().stream()
                                            .map(t -> t.getName().toString())      // map to String
                                            .collect(Collectors.joining(", "))
                            )
                            .append("\n");
                }


                r.append("\n");
            }
            else if (item instanceof SignatureSandwich ss) {
                // You could expand similarly for signature sandwiches...
                r.append(ss.toString()).append("\n\n");
            }
            else {
                // Drinks, chips, sides
                r.append(item.toString()).append("\n\n");
            }
        }

        r.append("------------------------------\n")
                .append(String.format("Total: $%.2f\n", getTotalCost()))
                .append("--------------------------\n");

        return r.toString();
    }

    public void saveReceipt() {
        String folderName = "receipts";
        File folder = new File(folderName);
        if (!folder.exists()) folder.mkdirs();

        // Format: yyyyMMdd-HHmmss.txt
        String filename = getFormattedTimestamp() + ".txt";
        File file = new File(folder, filename);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(getReceipt());
        } catch (IOException e) {
            System.out.println("Failed to save receipt: " + e.getMessage());
        }
    }

    public void clear() {
        items.clear();
    }

    // Helpers to compute base cost (no extra) for meats/cheeses
    private double baseMeatCost(Topping t, SandwichSize size) {
        return new Topping(t.getName(), t.getType(), false).getCost(size);
    }
    private double baseCheeseCost(Topping t, SandwichSize size) {
        return new Topping(t.getName(), t.getType(), false).getCost(size);
    }

    @Override
    public String toString() {
        return getReceipt();
    }
}
