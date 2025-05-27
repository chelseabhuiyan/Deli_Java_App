package com.pluralsight;

import com.pluralsight.enums.SidesType;


public class Sides {
    private SidesType type;

    // Constructor
    public Sides(SidesType type) {
        this.type = type;
    }

    // Getter
    public SidesType getType() {
        return type;
    }

    // Since sides are included, the cost is always zero
    public double getCost() {
        return 0.0;
    }

    @Override
    public String toString() {
        return type.toString().replace('_', ' ');
    }
}
