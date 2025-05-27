package com.pluralsight;

import com.pluralsight.enums.BreadType;
import com.pluralsight.enums.SandwichSize;
import com.pluralsight.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;

public class CustomizedSandwich extends BaseSandwich {
    private List<Topping> meats = new ArrayList<>();
    private List<Topping> cheeses = new ArrayList<>();
    private List<Topping> regularToppings = new ArrayList<>();
    private List<Topping> sauces = new ArrayList<>();


    //Constructor
    public CustomizedSandwich(SandwichSize size, BreadType bread, boolean toasted) {
        super(size, bread, toasted);
    }

    //Method to add topping to the right list
    public void addTopping(Topping topping) {
        switch (topping.getType()) { //switch uses ToppingType to decide which list the topping goes into
            case MEAT -> meats.add(topping);
            case CHEESE -> cheeses.add(topping);
            case REGULAR -> regularToppings.add(topping);
            case SAUCE -> sauces.add(topping);
        }
    }

    //helper method combines all the toppings into one list
    public List<Topping> getAllToppings() {
        List<Topping> all = new ArrayList<>();
        all.addAll(meats);
        all.addAll(cheeses);
        all.addAll(regularToppings);
        all.addAll(sauces);
        return all;
    }

    //Overrides the abstract getCost from BaseSandwich
    @Override
    public double getCost() {
        double basePrice = switch (size) {  //sets base sandwich price based on the size
            case FOUR -> 5.50;
            case EIGHT -> 7.00;
            case TWELVE -> 8.50;
        };

        double toppingCost = 0.0;

        //loops over all toppings to add up the cost based on size and extras ordered
        for (Topping t : getAllToppings()) {
            toppingCost += t.getCost(size);
        }

        return basePrice + toppingCost;
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Customized Sandwich (").append(size).append("\", ").append(bread);
        description.append(toasted ? ", toasted):" : ", not toasted):");
        description.append("\n");

        if (!meats.isEmpty()) {
            description.append("  Meats: ");
            meats.forEach(m -> description.append(m.toString()).append(", "));
            description.setLength(description.length() - 2); // remove last comma
            description.append("\n");
        }
        if (!cheeses.isEmpty()) {
            description.append("  Cheeses: ");
            cheeses.forEach(c -> description.append(c.toString()).append(", "));
            description.setLength(description.length() - 2);
            description.append("\n");
        }
        if (!regularToppings.isEmpty()) {
            description.append("  Toppings: ");
            regularToppings.forEach(r -> description.append(r.toString()).append(", "));
            description.setLength(description.length() - 2);
            description.append("\n");
        }
        if (!sauces.isEmpty()) {
            description.append("  Sauces: ");
            sauces.forEach(s -> description.append(s.toString()).append(", "));
            description.setLength(description.length() - 2);
            description.append("\n");
        }

        description.append(String.format("  Total: $%.2f", getCost()));
        return description.toString();
    }
}