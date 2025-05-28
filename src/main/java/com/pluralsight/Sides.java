package com.pluralsight;

import com.pluralsight.enums.SidesType;

public class Sides implements MenuItem {
    private SidesType name;

    public Sides(SidesType name) {
        this.name = name;
    }

    public SidesType getName() {
        return name;
    }

    @Override
    public double getCost() {
        return 3.00;
    }

    @Override
    public String toString() {
        return name + " Side - $" + String.format("%.2f", getCost());
    }
}
