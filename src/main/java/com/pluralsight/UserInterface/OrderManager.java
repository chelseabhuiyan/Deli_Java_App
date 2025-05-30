package com.pluralsight.UserInterface;

import com.pluralsight.ColorText;
import com.pluralsight.Order;

import java.util.Scanner;

public class OrderManager {
    private Order currentOrder;

    public void startNewOrder(Scanner scanner) {
        currentOrder = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println(ColorText.YELLOW + "\n---- New Order ----" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "1) Add Sandwich" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "2) Add Signature Sandwich" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "3) Add Drink" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "4) Add Chips" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "5) Checkout" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "0) Cancel Order" + ColorText.RESET);
            System.out.print(ColorText.BLUE + "Select an option: " + ColorText.RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    BuildSandwich.createSandwich(scanner, currentOrder);
                    break;
                case "2":
                    OrderSignatureSandwich.addSignatureSandwich(scanner, currentOrder);
                    break;
                case "3":
                    OrderDrink.addDrink(scanner, currentOrder);
                    break;
                case "4":
                    OrderChips.addChip(scanner, currentOrder);
                    break;
                case "5":
                    checkoutOrder(scanner);
                    ordering = false;
                    break;
                case "0":
                    System.out.println(ColorText.RED + "Order canceled." + ColorText.RESET);
                    ordering = false;
                    break;
                default:
                    System.out.println(ColorText.RED + "Invalid option. Try again." + ColorText.RESET);
            }
        }
    }

    private void checkoutOrder(Scanner scanner) {
        System.out.println(ColorText.YELLOW + "\n--- Checkout ---" + ColorText.RESET);
        System.out.println(currentOrder);  // calls toString()

        System.out.print(ColorText.BLUE + "Confirm order? (YES/NO): " + ColorText.RESET);
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("YES")) {
            currentOrder.saveReceipt();
            System.out.println(ColorText.GREEN + "Order confirmed. Receipt saved." + ColorText.RESET);
        } else {
            System.out.println(ColorText.RED + "Order not confirmed. Returning to home screen." + ColorText.RESET);
        }
    }
}
