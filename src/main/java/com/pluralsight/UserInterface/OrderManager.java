package com.pluralsight.UserInterface;

import com.pluralsight.Order;

import java.util.Scanner;

public class OrderManager {
    private Order currentOrder;

    public void startNewOrder(Scanner scanner) {
        currentOrder = new Order();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n---- New Order ----");
            System.out.println("1) Add Sandwich");
            System.out.println("2. Add Signature Sandwich");
            System.out.println("3) Add Drink");
            System.out.println("4) Add Chips");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    BuildSandwich.createSandwich(scanner, currentOrder); // adds item using addItem()
                    break;
                case "2":
                    OrderSignatureSandwich.addSignatureSandwich(scanner, currentOrder);
                    break;
                case "3":
                    OrderDrink.addDrink(scanner, currentOrder); // adds item using addItem()
                    break;
                case "4":
                    OrderChips.addChip(scanner, currentOrder); // adds item using addItem()
                    break;
                case "5":
                    checkoutOrder(scanner);
                    ordering = false;
                    break;
                case "0":
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void checkoutOrder(Scanner scanner) {
        System.out.println("\n--- Checkout ---");
        System.out.println(currentOrder); // calls toString() -> formatted receipt

        System.out.print("Confirm order? (YES/NO): ");
        String confirm = scanner.nextLine().trim().toUpperCase();

        if (confirm.equals("YES")) {
            currentOrder.saveReceipt();
            System.out.println("Order confirmed. Receipt saved.");
        } else {
            System.out.println("Order not confirmed. Returning to home screen.");
        }
    }
}
