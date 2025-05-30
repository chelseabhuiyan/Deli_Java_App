package com.pluralsight.UserInterface;

import com.pluralsight.Drinks.Drink;
import com.pluralsight.Order;
import com.pluralsight.Drinks.DrinkName;
import com.pluralsight.Drinks.DrinkSize;

import java.util.Scanner;

public class OrderDrink {
    public static void addDrink(Scanner scanner, Order order) {
        System.out.println("\n----Add a Drink ----");

        // Select Size
        System.out.println("Choose the drink size:");
        for (DrinkSize size : DrinkSize.values()) {
            System.out.println("- " + size);
        }
        DrinkSize selectedSize = null;
        while (selectedSize == null) {
            try {
                selectedSize = DrinkSize.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size. Try again:");
            }
        }

        // Select Name
        System.out.println("Choose drink type:");
        for (DrinkName name : DrinkName.values()) {
            System.out.println("- " + name);
        }
        DrinkName selectedName = null;
        while (selectedName == null) {
            try {
                selectedName = DrinkName.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid drink name. Try again:");
            }
        }

        // Create and add to order
        Drink drink = new Drink(selectedName, selectedSize);
        order.addItem(drink);
        System.out.println("The drink was added to your order!");
    }
}
