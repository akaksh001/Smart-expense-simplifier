# Smart Expense Splitter

## Project Overview
The **Smart Expense Splitter** is a Java-based desktop application designed to simplify the process of tracking and splitting shared expenses among a group of people. Whether it's for a trip, sharing rent, or splitting dinner bills, the application calculates who owes whom and optimizes the debts to minimize the total number of transactions required to settle up.

## Features
* **Member Management:** Add members to the expense group.
* **Expense Tracking:** Record expenses paid by a specific member.
* **Debt Simplification:** Automatically calculates net balances and uses a greedy algorithm to simplify debts (minimizing the number of transactions).
* **Graphical User Interface:** Easy-to-use Swing-based GUI for adding members, expenses, and viewing settlements.
* **Console Support:** Command-line functionality for quick interactions.

## Tech Stack
* **Language:** Java 17+
* **UI Framework:** Java Swing
* **Testing:** JUnit 5 (Planned/Supported)
* **Logging:** java.util.logging

## Setup Instructions
1. Ensure you have Java Development Kit (JDK) 17 or higher installed.
2. Clone this repository.
3. Compile the Java files:
   ```bash
   javac -d out src/model/*.java src/gui/*.java src/Main.java
   ```
4. Run the application:
   ```bash
   java -cp out gui.MainFrame
   ```
   *Alternatively, run `Main` for console mode.*

## Screenshots
*(Add screenshots of the GUI showing member addition, expense entry, and settlement calculation here)*

## Testing Instructions
1. Compile the test files using JUnit 5.
2. Run tests to verify the debt simplification logic and data models.
