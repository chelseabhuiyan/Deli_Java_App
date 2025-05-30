package com.pluralsight.UserInterface;

import com.pluralsight.ColorText;
import com.pluralsight.Order;
import com.pluralsight.Sandwich.*;

import java.util.Scanner;

public class OrderSignatureSandwich {
    public static void addSignatureSandwich(Scanner scanner, Order order) {
        System.out.println(ColorText.CYAN + "\n---- Choose a Signature Sandwich ----" + ColorText.RESET);

        System.out.println(ColorText.YELLOW + "Available options:" + ColorText.RESET);
        System.out.println("1. Italian Sub");
        System.out.println("2. Veggie Delight");
        System.out.println("3. Chicken Cutlet");

        String name = null;
        while (name == null) {
            System.out.print(ColorText.CYAN + "Enter the number of your choice: " + ColorText.RESET);
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> name = "Italian Sub";
                case "2" -> name = "Veggie Delight";
                case "3" -> name = "Chicken Cutlet";
                default -> System.out.println(ColorText.RED + "Invalid choice. Please enter 1, 2, or 3." + ColorText.RESET);
            }
        }

        // Select size using numbers
        SandwichSize[] sizes = SandwichSize.values();
        System.out.println(ColorText.CYAN + "Choose a size:" + ColorText.RESET);
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i]);
        }

        SandwichSize size = null;
        while (size == null) {
            System.out.print(ColorText.CYAN + "Enter the number of the size: " + ColorText.RESET);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= sizes.length) {
                    size = sizes[choice - 1];
                } else {
                    System.out.println(ColorText.RED + "Invalid number. Try again." + ColorText.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ColorText.RED + "Please enter a valid number." + ColorText.RESET);
            }
        }

        // Select bread using numbers
        BreadType[] breads = BreadType.values();
        System.out.println(ColorText.CYAN + "Choose a bread type:" + ColorText.RESET);
        for (int i = 0; i < breads.length; i++) {
            System.out.println((i + 1) + ") " + breads[i]);
        }

        BreadType bread = null;
        while (bread == null) {
            System.out.print(ColorText.CYAN + "Enter the number of the bread type: " + ColorText.RESET);
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= breads.length) {
                    bread = breads[choice - 1];
                } else {
                    System.out.println(ColorText.RED + "Invalid number. Try again." + ColorText.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ColorText.RED + "Please enter a valid number." + ColorText.RESET);
            }
        }

        // Toasted?
        System.out.print(ColorText.CYAN + "Would you like it toasted? (yes/no): " + ColorText.RESET);
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("yes");

        // Build and add sandwich
        SignatureSandwich sandwich = new SignatureSandwich(name, size, bread, toasted);
        order.addItem(sandwich);
        System.out.println(ColorText.GREEN + "Signature sandwich added to your order!" + ColorText.RESET);
    }
}
