package com.pluralsight.UserInterface;

import com.pluralsight.ColorText;
import com.pluralsight.Drinks.Drink;
import com.pluralsight.Drinks.DrinkName;
import com.pluralsight.Drinks.DrinkSize;
import com.pluralsight.Order;

import java.util.Scanner;

public class OrderDrink {
    public static void addDrink(Scanner scanner, Order order) {
        System.out.println(ColorText.YELLOW + "\n---- Add a Drink ----" + ColorText.RESET);

        //  Drink Size
        DrinkSize[] sizes = DrinkSize.values();
        System.out.println(ColorText.YELLOW + "Choose the drink size:" + ColorText.RESET);
        for (int i = 0; i < sizes.length; i++) {
            System.out.println(ColorText.CYAN + (i + 1) + ") " + formatName(sizes[i].name()) + ColorText.RESET);
        }

        DrinkSize selectedSize = null;
        while (selectedSize == null) {
            System.out.print(ColorText.YELLOW + "Enter the number of the size: " + ColorText.RESET);
            try {
                int sizeChoice = Integer.parseInt(scanner.nextLine().trim());
                if (sizeChoice < 1 || sizeChoice > sizes.length) {
                    System.out.println(ColorText.RED + "Invalid choice. Try again." + ColorText.RESET);
                } else {
                    selectedSize = sizes[sizeChoice - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println(ColorText.RED + "Please enter a valid number." + ColorText.RESET);
            }
        }

        // Drink Name
        DrinkName[] names = DrinkName.values();
        System.out.println(ColorText.YELLOW + "Choose the drink type:" + ColorText.RESET);
        for (int i = 0; i < names.length; i++) {
            System.out.println(ColorText.CYAN + (i + 1) + ") " + formatName(names[i].name()) + ColorText.RESET);
        }

        DrinkName selectedName = null;
        while (selectedName == null) {
            System.out.print(ColorText.YELLOW + "Enter the number of the drink: " + ColorText.RESET);
            try {
                int nameChoice = Integer.parseInt(scanner.nextLine().trim());
                if (nameChoice < 1 || nameChoice > names.length) {
                    System.out.println(ColorText.RED + "Invalid choice. Try again." + ColorText.RESET);
                } else {
                    selectedName = names[nameChoice - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println(ColorText.RED + "Please enter a valid number." + ColorText.RESET);
            }
        }

        // Add to order
        Drink drink = new Drink(selectedName, selectedSize);
        order.addItem(drink);
        System.out.println(ColorText.GREEN + formatName(selectedSize.name()) + " " + formatName(selectedName.name()) + " was added to your order!" + ColorText.RESET);
    }

    private static String formatName(String raw) {
        raw = raw.replace('_', ' ').toLowerCase();
        return raw.substring(0, 1).toUpperCase() + raw.substring(1);
    }
}
