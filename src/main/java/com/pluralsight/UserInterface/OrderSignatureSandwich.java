package com.pluralsight.UserInterface;

import com.pluralsight.Order;
import com.pluralsight.Sandwich.*;

import java.util.Scanner;

public class OrderSignatureSandwich {
    public static void addSignatureSandwich(Scanner scanner, Order order) {
        System.out.println("\n---- Choose a Signature Sandwich ----");

        System.out.println("Available options:");
        System.out.println("1. Italian Sub");
        System.out.println("2. Veggie Delight");
        System.out.println("3. Chicken Cutlet");

        String name = null;
        while (name == null) {
            System.out.print("Enter the number of your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> name = "Italian Sub";
                case "2" -> name = "Veggie Delight";
                case "3" -> name = "Chicken Cutlet";
                default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        // Select size
        SandwichSize size = null;
        while (size == null) {
            System.out.println("Choose a size: FOUR, EIGHT, TWELVE");
            try {
                size = SandwichSize.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size. Try again.");
            }
        }

        // Select bread
        BreadType bread = null;
        while (bread == null) {
            System.out.println("Choose a bread: WHITE, WHEAT, RYE, WRAP");
            try {
                bread = BreadType.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid bread type. Try again.");
            }
        }

        // Toasted?
        System.out.print("Would you like it toasted? (yes/no): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        // Build and add sandwich
        SignatureSandwich sandwich = new SignatureSandwich(name, size, bread, toasted);
        order.addItem(sandwich);
        System.out.println("Signature sandwich added to your order!");
    }
}
