package com.pluralsight;

import com.pluralsight.enums.SandwichSize;
import com.pluralsight.enums.ToppingName;
import com.pluralsight.enums.ToppingType;

public class Topping {
    private ToppingName name;
    private ToppingType type;
    private boolean isExtra;

    //Constructor
    public Topping(ToppingName name, ToppingType type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    //Getters
    public ToppingName getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }

    public double getCost(SandwichSize size) {
        double cost = 0.0;

        if (type == ToppingType.MEAT) {
            //Cost of each sandwich with extra meat
            if (isExtra) {
                if (size == SandwichSize.FOUR) cost = 0.50;
                else if (size == SandwichSize.EIGHT) cost = 1.00;
                else if (size == SandwichSize.TWELVE) cost = 1.50;
            } else { //Cost of each sandwich without extra meat
                if (size == SandwichSize.FOUR) cost = 1.00;
                else if (size == SandwichSize.EIGHT) cost = 2.00;
                else if (size == SandwichSize.TWELVE) cost = 3.00;
            }
        } else if (type == ToppingType.CHEESE) {
            //Cost of each sandwich with extra cheese
            if (isExtra) {
                if (size == SandwichSize.FOUR) cost = 0.30;
                else if (size == SandwichSize.EIGHT) cost = 0.60;
                else if (size == SandwichSize.TWELVE) cost = 0.90;
            } else { //Cost of each sandwich without extra cheese
                if (size == SandwichSize.FOUR) cost = 0.75;
                else if (size == SandwichSize.EIGHT) cost = 1.50;
                else if (size == SandwichSize.TWELVE) cost = 2.25;
            }
        } else {
            // Regular toppings and sauces are free
            cost = 0.0;
        }

        return cost;
    }

    @Override
    public String toString() {
        // Format enum name nicely
        String formattedName = name.toString().replace('_', ' ').toLowerCase();
        formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);

        return (isExtra ? "Extra " : "") + formattedName;
    }
}