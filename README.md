# Deli_Java_App

# 

**SubHub** is a console-based Java application that simulates a deli sandwich ordering system. Users can build customized sandwiches, choose from signature sandwich options, and add drinks, chips, and sides to their orders. The app calculates pricing based on size, extras, and provides a printed receipt with detailed breakdowns.

---

##  Features

- **Customized Sandwiches**
  - Choose bread type, size (4", 8", 12")
  - Select meats, cheeses, regular toppings, sauces
  - Option to mark any meat or cheese as "extra" (adds additional cost)

- **Signature Sandwiches**
  - Choose from preset options like:
    - Italian Sub
    - Veggie Delight
    - Chicken Cutlet
  - Select bread type, size, and toasted or not

- **Drinks and Chips**
  - Select from predefined options 
  - Drinks vary by size (small, medium, large)
  - Chips are a fixed cost

- **Receipt Generation**
  - Each order generates a clear, itemized receipt
  - Displays ingredients with prices and extra charges
  - Automatically saved in the `receipts` folder with the timestamp `yyyyMMdd-hhmmss.txt`

---


## Extra Implemented Features
 - Users can now select from a list of Signature Sandwiches when placing an order.
 - Each order is assigned a unique, incrementing Order Number within a single session.
 - Chip and drink names have been clearly defined and included for better user selection.
 - During sandwich customization, users can opt to skip any category (e.g., meats, cheeses, sauces) by choosing "none".
 - Added color to the user interface for better visiblity of different sections
 - Allow users to choose bread type, and size for signiature sandwich

## Technology Stack and Tools

### Languages and Frameworks
- **Java**: Core programming language used to build the application.
- **Maven**: Project management and build tool used to manage dependencies and organize the project structure.

### IDE and Version Control
- **IntelliJ IDEA Community Edition**: Primary development environment used for writing and testing code.
- **Git Bash & GitHub**: Version control system used to track changes and collaborate.


## Screens
### HomeScreen and New Order Screen
[HomeScreen](Screen_Screenshots/HomeScreen.png)

[NewOrder](Screen_Screenshots/NewOrderScreen.png)

### BuildSandwich Screen
[Choosing Bread Type and Size](Screen_Screenshots/BuildingSandwichScreen-pt1.png)

[Choosing Meat, Regular Toppings, and Cheese](Screen_Screenshots/BuildingSandwichScreen-pt2.png)

[Choosing Sauce and Toasted option](Screen_Screenshots/BuildingSandwichScreen-pt3.png)

### Signature Sandwich ordering Screen
[Signature Sandwich](Screen_Screenshots/SignatureSandwichScreen.png)

### Ordering Drinks, Chips, and sides

[Sides](Screen_Screenshots/SidesScreen.png)

[Drinks](Screen_Screenshots/DrinkOrderScreen.png)

[Chips](Screen_Screenshots/ChipOrderScreen.png)

### Checkout Screen and Receipt
[CheckoutScreen](Screen_Screenshots/CheckOutScreen.png)

[Receipt](Screen_Screenshots/ReceiptScreen.png)


## Interesting Peice of Code
