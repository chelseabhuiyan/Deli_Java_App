package com.pluralsight.UserInterface;

import java.util.Scanner;
import com.pluralsight.ColorText;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderManager orderManager = new OrderManager();
        boolean running = true;

        while (running) {
            System.out.println(ColorText.YELLOW + "\n ---- SubHub Sandwich Shop ----" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "1) New Order" + ColorText.RESET);
            System.out.println(ColorText.CYAN + "0) Exit" + ColorText.RESET);
            System.out.print(ColorText.BLUE + "Select an option: " + ColorText.RESET);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    orderManager.startNewOrder(scanner);
                    break;
                case "0":
                    System.out.println(ColorText.GREEN + "Thank you for visiting SubHub Sandwich Shop! See you again!" + ColorText.RESET);
                    running = false;
                    break;
                default:
                    System.out.println(ColorText.RED + "Invalid option. Please try again." + ColorText.RESET);
            }
        }

        scanner.close();
    }
}
