package com.pluralsight;

import com.pluralsight.enums.BreadType;
import com.pluralsight.enums.SandwichSize;
import com.pluralsight.enums.ToppingName;
import com.pluralsight.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;

public class SignatureSandwich extends BaseSandwich {
    private final String name; //name of the signature sandwich
    private final List<Topping> toppings = new ArrayList<>(); //a list holds all the toppings in the signature sandwich

    public SignatureSandwich(String name, SandwichSize size, BreadType bread, boolean toasted) {
        super(size, bread, toasted);  ////Calls the BaseSandwich to set the bread type, size, and toasted
        this.name = name;

        // Initialize toppings based on the sandwich name
        switch (name.toLowerCase()) {
            case "italian sub" -> initItalianSub();
            case "veggie delight" -> initVeggieDelight();
            case "chicken cutlet" -> initChickenCutlet();
            default -> throw new IllegalArgumentException("Unknown signature sandwich: " + name);
        }
    }

    private void initItalianSub() {
        // Add meats
        toppings.add(new Topping(ToppingName.HAM, ToppingType.MEAT, false));
        toppings.add(new Topping(ToppingName.SALAMI, ToppingType.MEAT, false));
        toppings.add(new Topping(ToppingName.BACON, ToppingType.MEAT, false));

        // Cheeses
        toppings.add(new Topping(ToppingName.PROVOLONE, ToppingType.CHEESE, false));

        // Regular Toppings
        toppings.add(new Topping(ToppingName.LETTUCE, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.TOMATOES, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.ONIONS, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.PEPPERS, ToppingType.REGULAR, false));

        // Sauces
        toppings.add(new Topping(ToppingName.RANCH, ToppingType.SAUCE, false));
    }

    private void initVeggieDelight() {
        // No meats
        // Cheeses
        toppings.add(new Topping(ToppingName.SWISS, ToppingType.CHEESE, false));

        // Regular Toppings
        toppings.add(new Topping(ToppingName.LETTUCE, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.TOMATOES, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.ONIONS, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.CUCUMBERS, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.PEPPERS, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.PICKLES, ToppingType.REGULAR, false));

        // Sauces
        toppings.add(new Topping(ToppingName.MUSTARD, ToppingType.SAUCE, false));
    }

    private void initChickenCutlet() {
        // Meats
        toppings.add(new Topping(ToppingName.CHICKEN, ToppingType.MEAT, false));

        // Cheeses
        toppings.add(new Topping(ToppingName.PROVOLONE, ToppingType.CHEESE, false));

        // Regular Toppings
        toppings.add(new Topping(ToppingName.LETTUCE, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.TOMATOES, ToppingType.REGULAR, false));
        toppings.add(new Topping(ToppingName.MAYO, ToppingType.SAUCE, false));  // mayo as sauce

        // You can add more toppings or sauces as desired
    }

    //Getters
    public String getName() {
        return name; //returns sandwich name
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings); //returns copy of the topping list
    }

    @Override
    public double getCost() {
        // Base prices same as CustomizedSandwich for consistency
        double basePrice = switch (size) {
            case FOUR -> 5.50;
            case EIGHT -> 7.00;
            case TWELVE -> 8.50;
        };

        double toppingCost = 0.0;
        for (Topping t : toppings) {
            toppingCost += t.getCost(size);
        }

        return basePrice + toppingCost;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append(name).append(" (").append(size).append("\", ").append(bread);
        description.append(toasted ? ", toasted):" : ", not toasted):").append("\n");

        // Group toppings by type for nicer formatting
        StringBuilder meats = new StringBuilder();
        StringBuilder cheeses = new StringBuilder();
        StringBuilder regulars = new StringBuilder();
        StringBuilder sauces = new StringBuilder();

        for (Topping t : toppings) {
            switch (t.getType()) {
                case MEAT -> meats.append(t.toString()).append(", ");
                case CHEESE -> cheeses.append(t.toString()).append(", ");
                case REGULAR -> regulars.append(t.toString()).append(", ");
                case SAUCE -> sauces.append(t.toString()).append(", ");
            }
        }

        if (meats.length() > 0) {
            description.append("  Meats: ").append(meats, 0, meats.length() - 2).append("\n");
        }
        if (cheeses.length() > 0) {
            description.append("  Cheeses: ").append(cheeses, 0, cheeses.length() - 2).append("\n");
        }
        if (regulars.length() > 0) {
            description.append("  Toppings: ").append(regulars, 0, regulars.length() - 2).append("\n");
        }
        if (sauces.length() > 0) {
            description.append("  Sauces: ").append(sauces, 0, sauces.length() - 2).append("\n");
        }

        description.append(String.format("  Total: $%.2f", getCost()));

        return description.toString();
    }
}
