package com.pluralsight.UserInterface;

import com.pluralsight.Chips.Chip;
import com.pluralsight.Chips.ChipName;
import com.pluralsight.ColorText;
import com.pluralsight.Order;

import java.util.Scanner;

public class OrderChips {
    public static void addChip(Scanner scanner, Order order) {
        System.out.println(ColorText.YELLOW + "\n---- Add Chips ----" + ColorText.RESET);

        ChipName[] chipOptions = ChipName.values();

        // Display options as numbered list
        for (int i = 0; i < chipOptions.length; i++) {
            String formattedName = chipOptions[i].name().replace('_', ' ');
            System.out.println(ColorText.CYAN + (i + 1) + ") " + formattedName + ColorText.RESET);
        }

        System.out.print(ColorText.YELLOW + "Enter the number of the chip: " + ColorText.RESET);
        int choice = -1;

        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < 1 || choice > chipOptions.length) {
                    System.out.print(ColorText.RED + "Invalid choice. Please try again: " + ColorText.RESET);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print(ColorText.RED + "Please enter a valid number: " + ColorText.RESET);
            }
        }

        ChipName selectedChip = chipOptions[choice - 1];
        Chip chip = new Chip(selectedChip);
        order.addItem(chip);
        System.out.println(ColorText.GREEN + formattedName(selectedChip) + " was added to your order!" + ColorText.RESET);
    }

    private static String formattedName(ChipName name) {
        String raw = name.name().replace('_', ' ').toLowerCase();
        return Character.toUpperCase(raw.charAt(0)) + raw.substring(1);
    }
}
