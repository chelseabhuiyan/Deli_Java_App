package com.pluralsight.Drinks;

import com.pluralsight.MenuItem;

public class Drink implements MenuItem {
    private DrinkName name;
    private DrinkSize size;

    public Drink(DrinkName name, DrinkSize size) {
        this.name = name;
        this.size = size;
    }

    public DrinkName getName() {
        return name;
    }

    public void setName(DrinkName name) {
        this.name = name;
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    // Returns the cost based on drink size
    public double getCost() {
        return switch (size) {
            case SMALL -> 1.50;
            case MEDIUM -> 2.00;
            case LARGE -> 2.50;
        };
    }

    @Override
    public String toString() {
        return size + " " + name.toString().replace('_', ' ') + " ($" + String.format("%.2f", getCost()) + ")";
    }
}
