package com.pluralsight;

import com.pluralsight.enums.ChipName;

public class Chip implements MenuItem {
    private ChipName name;

    public Chip(ChipName name) {
        this.name = name;
    }

    public ChipName getName() {
        return name;
    }

    public void setName(ChipName name) {
        this.name = name;
    }

    // Fixed cost for all chips (can be changed if needed)
    public double getCost() {
        return 1.50;
    }

    @Override
    public String toString() {
        // Format enum name with spaces instead of underscores (if any)
        return name.toString().replace('_', ' ') + " ($" + String.format("%.2f", getCost()) + ")";
    }
}
