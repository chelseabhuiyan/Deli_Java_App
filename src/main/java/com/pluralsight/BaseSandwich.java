package com.pluralsight;

import com.pluralsight.enums.SandwichSize;
import com.pluralsight.enums.BreadType;

public abstract class BaseSandwich implements MenuItem {
    protected SandwichSize size;
    protected BreadType bread;
    protected boolean toasted;

    //constructor
    public BaseSandwich(SandwichSize size, BreadType bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
    }

    //Getters
    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }

    // These must be implemented by subclasses
    public abstract double getCost();
    public abstract String toString();
}


