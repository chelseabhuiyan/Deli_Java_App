package com.pluralsight.Sandwich;

import com.pluralsight.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CustomizedSandwich implements MenuItem {
    private SandwichSize size;
    private BreadType bread;
    private boolean toasted;

    private List<Topping> meats = new ArrayList<>();
    private List<Topping> cheeses = new ArrayList<>();
    private List<Topping> regularToppings = new ArrayList<>();
    private List<Topping> sauces = new ArrayList<>();

    // Constructor
    public CustomizedSandwich(SandwichSize size, BreadType bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
    }

    // Method to add topping to the right list
    public void addTopping(Topping topping) {
        if (topping == null || topping.getType() == null) return;

        switch (topping.getType()) {
            case MEAT -> meats.add(topping);
            case CHEESE -> cheeses.add(topping);
            case REGULAR -> regularToppings.add(topping);
            case SAUCE -> sauces.add(topping);
        }
    }

    // Method to remove toppings
    public void removeTopping(Topping topping) {
        if (topping == null || topping.getType() == null) return;

        switch (topping.getType()) {
            case MEAT -> meats.remove(topping);
            case CHEESE -> cheeses.remove(topping);
            case REGULAR -> regularToppings.remove(topping);
            case SAUCE -> sauces.remove(topping);
        }
    }

    // Combine all toppings into one list
    public List<Topping> getAllToppings() {
        List<Topping> all = new ArrayList<>();
        all.addAll(meats);
        all.addAll(cheeses);
        all.addAll(regularToppings);
        all.addAll(sauces);
        return all;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }

    public List<Topping> getMeats()      { return meats; }
    public List<Topping> getCheeses()    { return cheeses; }
    public List<Topping> getRegularToppings() { return regularToppings; }
    public List<Topping> getSauces()     { return sauces; }

    // Implements cost calculation
    @Override
    public double getCost() {
        double basePrice = switch (size) {
            case FOUR -> 5.50;
            case EIGHT -> 7.00;
            case TWELVE -> 8.50;
        };

        double toppingCost = 0.0;
        for (Topping t : getAllToppings()) {
            toppingCost += t.getCost(size);
        }

        return basePrice + toppingCost;
    }

    // toString() override
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Customized Sandwich (").append(size).append("\", ").append(bread);
        description.append(toasted ? ", toasted):" : ", not toasted):").append("\n");

        if (!meats.isEmpty()) {
            description.append("  Meats: ")
                    .append(String.join(", ", meats.stream().map(Topping::toString).toList()))
                    .append("\n");
        }

        if (!cheeses.isEmpty()) {
            description.append("  Cheeses: ")
                    .append(String.join(", ", cheeses.stream().map(Topping::toString).toList()))
                    .append("\n");
        }

        if (!regularToppings.isEmpty()) {
            description.append("  Toppings: ")
                    .append(String.join(", ", regularToppings.stream().map(Topping::toString).toList()))
                    .append("\n");
        }

        if (!sauces.isEmpty()) {
            description.append("  Sauces: ")
                    .append(String.join(", ", sauces.stream().map(Topping::toString).toList()))
                    .append("\n");
        }

        description.append(String.format("  Total: $%.2f", getCost()));
        return description.toString();
    }
}
