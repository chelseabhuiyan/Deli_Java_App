package com.pluralsight.UserInterface;

import com.pluralsight.Sandwich.*;
import com.pluralsight.Order;
import com.pluralsight.Sides;
import com.pluralsight.SidesType;

import java.util.*;
import java.util.stream.Collectors;

public class BuildSandwich {
    public static void createSandwich(Scanner scanner, Order order) {
        System.out.println("\n-- Build Your Own Sandwich --");

        SandwichSize size = promptSize(scanner);
        BreadType bread = promptBread(scanner);
        CustomizedSandwich sandwich = new CustomizedSandwich(size, bread, false); // default toasted = false

        // Toppings by category
        addToppings(scanner, sandwich, getToppingsByType(ToppingType.REGULAR), "regular");
        addToppings(scanner, sandwich, getToppingsByType(ToppingType.MEAT), "meat");
        addToppings(scanner, sandwich, getToppingsByType(ToppingType.CHEESE), "cheese");
        addToppings(scanner, sandwich, getToppingsByType(ToppingType.SAUCE), "sauce");

        // Toasted option
        System.out.println("Would you like it toasted?");
        System.out.println("1) Yes\n2) No");
        System.out.print("Enter your choice: ");
        String toastedInput = scanner.nextLine().trim();
        if (toastedInput.equals("1")) {
            sandwich.setToasted(true);
        }

        order.addItem(sandwich);
        System.out.println("Sandwich added to your order!");

        // Add side
        System.out.print("Would you like to add a side?\n1) Yes\n2) No\nEnter your choice: ");
        String sideAnswer = scanner.nextLine().trim();

        if (sideAnswer.equals("1")) {
            SidesType[] sidesOptions = SidesType.values();
            System.out.println("Available sides:");
            for (int i = 0; i < sidesOptions.length; i++) {
                System.out.println((i + 1) + ") " + sidesOptions[i]);
            }

            System.out.print("Choose a side by number: ");
            try {
                int sideChoice = Integer.parseInt(scanner.nextLine().trim());
                if (sideChoice >= 1 && sideChoice <= sidesOptions.length) {
                    SidesType selectedSide = sidesOptions[sideChoice - 1];
                    order.addItem(new Sides(selectedSide));
                    System.out.println("Side added to your order!");
                } else {
                    System.out.println("Invalid side number. Skipping...");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Skipping side...");
            }
        }
    }

    private static SandwichSize promptSize(Scanner scanner) {
        SandwichSize[] sizes = SandwichSize.values();
        System.out.println("Choose sandwich size:");
        for (int i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i]);
        }

        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= sizes.length) {
                    return sizes[choice - 1];
                }
            } catch (NumberFormatException e) {}
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static BreadType promptBread(Scanner scanner) {
        BreadType[] breads = BreadType.values();
        System.out.println("Choose bread type:");
        for (int i = 0; i < breads.length; i++) {
            System.out.println((i + 1) + ") " + breads[i]);
        }

        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= breads.length) {
                    return breads[choice - 1];
                }
            } catch (NumberFormatException e) {}
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static List<ToppingName> getToppingsByType(ToppingType type) {
        return Arrays.stream(ToppingName.values())
                .filter(name -> name.getType() == type)
                .collect(Collectors.toList());
    }

    private static void addToppings(Scanner scanner, CustomizedSandwich sandwich, List<ToppingName> options, String category) {
        System.out.println("Choose " + category + " toppings:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }
        System.out.println("0) None");
        System.out.print("Enter your choices (comma-separated numbers): ");
        String input = scanner.nextLine().trim();
        String[] selectedIndices = input.split(",");

        for (String indexStr : selectedIndices) {
            try {
                int index = Integer.parseInt(indexStr.trim());

                if (index == 0) continue; // skip if they chose "none"
                if (index < 1 || index > options.size()) {
                    System.out.println("Invalid option: " + index);
                    continue;
                }

                ToppingName topping = options.get(index - 1);
                ToppingType type = topping.getType();
                boolean isExtra = false;

                if (type == ToppingType.MEAT || type == ToppingType.CHEESE) {
                    System.out.print("Do you want extra " + topping + "? (YES/NO): ");
                    String extraInput = scanner.nextLine().trim().toUpperCase();
                    isExtra = extraInput.equals("YES");
                }

                sandwich.addTopping(new Topping(topping, type, isExtra));

            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + indexStr);
            }
        }
    }
}
