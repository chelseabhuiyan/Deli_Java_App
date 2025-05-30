package com.pluralsight.Sandwich;

public enum ToppingName {
    // Meats
    STEAK(ToppingType.MEAT),
    HAM(ToppingType.MEAT),
    SALAMI(ToppingType.MEAT),
    ROAST_BEEF(ToppingType.MEAT),
    CHICKEN(ToppingType.MEAT),
    BACON(ToppingType.MEAT),

    // Cheeses
    AMERICAN(ToppingType.CHEESE),
    PROVOLONE(ToppingType.CHEESE),
    CHEDDAR(ToppingType.CHEESE),
    SWISS(ToppingType.CHEESE),

    // Regular toppings
    LETTUCE(ToppingType.REGULAR),
    PEPPERS(ToppingType.REGULAR),
    ONIONS(ToppingType.REGULAR),
    TOMATOES(ToppingType.REGULAR),
    JALAPENOS(ToppingType.REGULAR),
    CUCUMBERS(ToppingType.REGULAR),
    PICKLES(ToppingType.REGULAR),
    GUACAMOLE(ToppingType.REGULAR),
    MUSHROOMS(ToppingType.REGULAR),

    // Sauces
    MAYO(ToppingType.SAUCE),
    MUSTARD(ToppingType.SAUCE),
    KETCHUP(ToppingType.SAUCE),
    RANCH(ToppingType.SAUCE),
    THOUSAND_ISLANDS(ToppingType.SAUCE),
    VINAIGRETTE(ToppingType.SAUCE);

    private final ToppingType type;

    ToppingName(ToppingType type) {
        this.type = type;
    }

    public ToppingType getType() {
        return type;
    }
}
