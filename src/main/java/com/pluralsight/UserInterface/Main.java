package com.pluralsight.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderManager orderManager = new OrderManager();
        boolean running = true;

        while (running) {
            System.out.println("\n ----DELI-cious Sandwich Shop---- ");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    orderManager.startNewOrder(scanner);
                    break;
                case "0":
                    System.out.println("Thank you for visiting DELI-cious Sandwich Shop! See you again!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
