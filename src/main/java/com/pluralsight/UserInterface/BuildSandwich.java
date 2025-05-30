package com.pluralsight.UserInterface;

import com.pluralsight.Sandwich.*;
import com.pluralsight.Order;
import com.pluralsight.Sides;
import com.pluralsight.SidesType;
import com.pluralsight.ColorText;

import java.util.*;
import java.util.stream.Collectors;

    public class BuildSandwich {
        public static void createSandwich(Scanner scanner, Order order) {
            System.out.println(ColorText.YELLOW + "\n-- Build Your Own Sandwich --" + ColorText.RESET);

            SandwichSize size = promptSize(scanner);
            BreadType bread = promptBread(scanner);
            CustomizedSandwich sandwich = new CustomizedSandwich(size, bread, false);

            addToppings(scanner, sandwich, getToppingsByType(ToppingType.REGULAR), "regular");
            addToppings(scanner, sandwich, getToppingsByType(ToppingType.MEAT), "meat");
            addToppings(scanner, sandwich, getToppingsByType(ToppingType.CHEESE), "cheese");
            addToppings(scanner, sandwich, getToppingsByType(ToppingType.SAUCE), "sauce");

            System.out.println(ColorText.YELLOW + "Would you like it toasted?" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "1) Yes\n2) No" + ColorText.RESET);
            System.out.print(ColorText.YELLOW + "Enter your choice: " + ColorText.RESET);
            String toastedInput = scanner.nextLine().trim();
            if (toastedInput.equals("1")) sandwich.setToasted(true);

            order.addItem(sandwich);
            System.out.println(ColorText.GREEN + "Sandwich added to your order!" + ColorText.RESET);

            System.out.print(ColorText.YELLOW + "Would you like to add a side?\n" +
                    ColorText.CYAN + "1) Yes\n2) No\n" + ColorText.YELLOW + "Enter your choice: " + ColorText.RESET);
            String sideAnswer = scanner.nextLine().trim();

            if (sideAnswer.equals("1")) {
                SidesType[] sidesOptions = SidesType.values();
                System.out.println(com.pluralsight.ColorText.YELLOW + "Available sides:" + com.pluralsight.ColorText.RESET);
                for (int i = 0; i < sidesOptions.length; i++) {
                    String formatted = sidesOptions[i].name().replace('_', ' ').toLowerCase();
                    formatted = formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
                    System.out.println(com.pluralsight.ColorText.CYAN + (i + 1) + ") " + formatted + com.pluralsight.ColorText.RESET);
                }

                System.out.print("Choose a side by number: ");
                try {
                    int sideChoice = Integer.parseInt(scanner.nextLine().trim());
                    if (sideChoice >= 1 && sideChoice <= sidesOptions.length) {
                        SidesType selectedSide = sidesOptions[sideChoice - 1];
                        order.addItem(new Sides(selectedSide));
                        System.out.println(com.pluralsight.ColorText.GREEN + "Side added to your order!" + com.pluralsight.ColorText.RESET);
                    } else {
                        System.out.println(com.pluralsight.ColorText.RED + "Invalid side number. Skipping..." + com.pluralsight.ColorText.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(com.pluralsight.ColorText.RED + "Invalid input. Skipping side..." + com.pluralsight.ColorText.RESET);
                }
            }

        }

        private static SandwichSize promptSize(Scanner scanner) {
            SandwichSize[] sizes = SandwichSize.values();
            System.out.println(ColorText.YELLOW + "Choose sandwich size:" + ColorText.RESET);
            for (int i = 0; i < sizes.length; i++) {
                System.out.println(ColorText.CYAN + (i + 1) + ") " + sizes[i] + ColorText.RESET);
            }

            while (true) {
                System.out.print(ColorText.YELLOW + "Enter your choice: " + ColorText.RESET);
                try {
                    int choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice >= 1 && choice <= sizes.length) {
                        return sizes[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                System.out.println(ColorText.RED + "Invalid choice. Please try again." + ColorText.RESET);
            }
        }

        private static BreadType promptBread(Scanner scanner) {
            BreadType[] breads = BreadType.values();
            System.out.println(ColorText.YELLOW + "Choose bread type:" + ColorText.RESET);
            for (int i = 0; i < breads.length; i++) {
                System.out.println(ColorText.CYAN + (i + 1) + ") " + breads[i] + ColorText.RESET);
            }

            while (true) {
                System.out.print(ColorText.YELLOW + "Enter your choice: " + ColorText.RESET);
                try {
                    int choice = Integer.parseInt(scanner.nextLine().trim());
                    if (choice >= 1 && choice <= breads.length) {
                        return breads[choice - 1];
                    }
                } catch (NumberFormatException ignored) {}
                System.out.println(ColorText.RED + "Invalid choice. Please try again." + ColorText.RESET);
            }
        }

        private static List<ToppingName> getToppingsByType(ToppingType type) {
            return Arrays.stream(ToppingName.values())
                    .filter(name -> name.getType() == type)
                    .collect(Collectors.toList());
        }

        private static void addToppings(Scanner scanner, CustomizedSandwich sandwich, List<ToppingName> options, String category) {
            System.out.println(ColorText.YELLOW + "Choose " + category + " toppings:" + ColorText.RESET);
            for (int i = 0; i < options.size(); i++) {
                System.out.println(ColorText.CYAN + (i + 1) + ") " + options.get(i) + ColorText.RESET);
            }
            System.out.println(ColorText.CYAN + "0) None" + ColorText.RESET);
            System.out.print(ColorText.YELLOW + "Enter your choices (comma-separated numbers): " + ColorText.RESET);
            String input = scanner.nextLine().trim();
            String[] selectedIndices = input.split(",");

            for (String indexStr : selectedIndices) {
                try {
                    int index = Integer.parseInt(indexStr.trim());
                    if (index == 0) continue;
                    if (index < 1 || index > options.size()) {
                        System.out.println(ColorText.RED + "Invalid option: " + index + ColorText.RESET);
                        continue;
                    }

                    ToppingName topping = options.get(index - 1);
                    ToppingType type = topping.getType();
                    boolean isExtra = false;

                    if (type == ToppingType.MEAT || type == ToppingType.CHEESE) {
                        System.out.print(ColorText.YELLOW + "Do you want extra " + topping + "? (YES/NO): " + ColorText.RESET);
                        String extraInput = scanner.nextLine().trim().toUpperCase();
                        isExtra = extraInput.equals("YES");
                    }

                    sandwich.addTopping(new Topping(topping, type, isExtra));

                } catch (NumberFormatException e) {
                    System.out.println(ColorText.RED + "Invalid input: " + indexStr + ColorText.RESET);
                }
            }
        }
    }
