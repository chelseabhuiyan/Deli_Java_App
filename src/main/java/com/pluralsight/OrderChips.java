package com.pluralsight;

import com.pluralsight.enums.ChipName;

import java.util.Scanner;

public class OrderChips {
    public static void addChip(Scanner scanner, Order order) {
        System.out.println("\n---- Add Chips ----");

        // Display available chip options
        System.out.println("Choose chip type:");
        for (ChipName chip : ChipName.values()) {
            System.out.println("- " + chip);
        }

        ChipName selectedChip = null;
        while (selectedChip == null) {
            try {
                selectedChip = ChipName.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid chip name. Try again:");
            }
        }

        // Create and add chip to order
        Chip chip = new Chip(selectedChip);
        order.addChip(chip);
        System.out.println("Chips were added to your order!");
    }
}
