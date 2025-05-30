package com.pluralsight.UserInterface;

import com.pluralsight.Drinks.Drink;
import com.pluralsight.Drinks.DrinkName;
import com.pluralsight.Drinks.DrinkSize;
import com.pluralsight.Order;

import java.util.Scanner;

public class OrderDrink {
    public static void addDrink(Scanner scanner, Order order) {
        System.out.println("\n---- Add a Drink ----");

        //  Drink Size
        DrinkSize[] sizes = DrinkSize.values();
        System.out.println("Choose the drink size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d) %s\n", i + 1, formatName(sizes[i].name()));
        }

        DrinkSize selectedSize = null;
        while (selectedSize == null) {
            System.out.print("Enter the number of the size: ");
            try {
                int sizeChoice = Integer.parseInt(scanner.nextLine().trim());
                if (sizeChoice < 1 || sizeChoice > sizes.length) {
                    System.out.println("Invalid choice. Try again.");
                } else {
                    selectedSize = sizes[sizeChoice - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Drink Name
        DrinkName[] names = DrinkName.values();
        System.out.println("Choose the drink type:");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d) %s\n", i + 1, formatName(names[i].name()));
        }

        DrinkName selectedName = null;
        while (selectedName == null) {
            System.out.print("Enter the number of the drink: ");
            try {
                int nameChoice = Integer.parseInt(scanner.nextLine().trim());
                if (nameChoice < 1 || nameChoice > names.length) {
                    System.out.println("Invalid choice. Try again.");
                } else {
                    selectedName = names[nameChoice - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Add to order
        Drink drink = new Drink(selectedName, selectedSize);
        order.addItem(drink);
        System.out.println(formatName(selectedSize.name()) + " " + formatName(selectedName.name()) + " was added to your order!");
    }

    private static String formatName(String raw) {
        raw = raw.replace('_', ' ').toLowerCase();
        return raw.substring(0, 1).toUpperCase() + raw.substring(1);
    }
}
