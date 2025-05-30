package com.pluralsight.UserInterface;

import com.pluralsight.Chips.Chip;
import com.pluralsight.Chips.ChipName;
import com.pluralsight.Order;

import java.util.Scanner;

public class OrderChips {
    public static void addChip(Scanner scanner, Order order) {
        System.out.println("\n---- Add Chips ----");

        ChipName[] chipOptions = ChipName.values();

        // Display options as numbered list
        for (int i = 0; i < chipOptions.length; i++) {
            String formattedName = chipOptions[i].name().replace('_', ' ');
            System.out.printf("%d) %s\n", i + 1, formattedName);
        }

        System.out.print("Enter the number of the chip: ");
        int choice = -1;

        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 1 || choice > chipOptions.length) {
                    System.out.print("Invalid choice. Please try again: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }

        ChipName selectedChip = chipOptions[choice - 1];
        Chip chip = new Chip(selectedChip);
        order.addItem(chip);
        System.out.println(formattedName(selectedChip) + " was added to your order!");
    }

    private static String formattedName(ChipName name) {
        return name.name().replace('_', ' ').toLowerCase()
                .replaceFirst(Character.toString(name.name().charAt(0)), Character.toString(Character.toUpperCase(name.name().charAt(0))));
    }
}
