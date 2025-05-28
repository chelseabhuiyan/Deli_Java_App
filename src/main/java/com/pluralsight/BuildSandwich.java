package com.pluralsight;

import com.pluralsight.enums.*;
import java.util.*;
import java.util.stream.Collectors;

public class BuildSandwich {
    public static void createSandwich(Scanner scanner, Order order) {
        System.out.println("\n-- Build Your Own Sandwich --");

        SandwichSize size = promptSize(scanner);
        BreadType bread = promptBread(scanner);
        CustomizedSandwich sandwich = new CustomizedSandwich(size, bread, false); // default toasted = false

        // Regular Toppings
        List<ToppingName> regularToppings = getToppingsByType(ToppingType.REGULAR);
        addToppings(scanner, sandwich, regularToppings, "regular");

        // Meats
        List<ToppingName> meats = getToppingsByType(ToppingType.MEAT);
        addToppings(scanner, sandwich, meats, "meat");

        // Cheeses
        List<ToppingName> cheeses = getToppingsByType(ToppingType.CHEESE);
        addToppings(scanner, sandwich, cheeses, "cheese");

        // Sauces
        List<ToppingName> sauces = getToppingsByType(ToppingType.SAUCE);
        addToppings(scanner, sandwich, sauces, "sauce");

        // Toasted?
        System.out.print("Would you like it toasted? (YES/NO): ");
        String toastedInput = scanner.nextLine().trim().toUpperCase();
        if (toastedInput.equals("YES")) {
            sandwich.setToasted(true);
        }

        order.addSandwich(sandwich);
        System.out.println("Sandwich added to your order!");
    }

    private static SandwichSize promptSize(Scanner scanner) {
        System.out.println("Choose sandwich size:");
        for (SandwichSize size : SandwichSize.values()) {
            System.out.println("- " + size);
        }
        return SandwichSize.valueOf(scanner.nextLine().trim().toUpperCase());
    }

    private static BreadType promptBread(Scanner scanner) {
        System.out.println("Choose bread type:");
        for (BreadType bread : BreadType.values()) {
            System.out.println("- " + bread);
        }
        return BreadType.valueOf(scanner.nextLine().trim().toUpperCase());
    }

    private static List<ToppingName> getToppingsByType(ToppingType type) {
        return Arrays.stream(ToppingName.values())
                .filter(name -> name.getType() == type)
                .collect(Collectors.toList());
    }

    private static void addToppings(Scanner scanner, CustomizedSandwich sandwich, List<ToppingName> options, String category) {
        System.out.println("Choose " + category + " toppings (comma-separated, e.g. HAM,CHEESE):");
        System.out.println("Options: " + options.stream().map(Enum::name).collect(Collectors.joining(", ")));
        String input = scanner.nextLine().trim().toUpperCase();
        String[] selected = input.split(",");

        for (String name : selected) {
            try {
                ToppingName topping = ToppingName.valueOf(name);
                ToppingType type = topping.getType();

                boolean isExtra = false;
                if (type == ToppingType.MEAT || type == ToppingType.CHEESE) {
                    System.out.print("Do you want extra " + topping + "? (YES/NO): ");
                    String extraInput = scanner.nextLine().trim().toUpperCase();
                    isExtra = extraInput.equals("YES");
                }

                sandwich.addTopping(new Topping(topping, type, isExtra));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid topping: " + name);
            }
        }
    }
}
