package com.pluralsight.Chips;

import com.pluralsight.ColorText;
import com.pluralsight.MenuItem;

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

    // Fixed cost for all chips
    public double getCost() {
        return 1.50;
    }

    @Override
    public String toString() {
        // Format enum name with spaces instead of underscores (if any)
        String formattedName = name.toString().replace('_', ' ');
        return ColorText.CYAN + formattedName + " ($" + String.format("%.2f", getCost()) + ")" + ColorText.RESET;
    }
}
