# Smart Expense Splitter

![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)

## Project Overview

The **Smart Expense Splitter** is a comprehensive, Java-based desktop application meticulously designed to solve the common problem of tracking and splitting shared expenses. Whether it's for a group trip, sharing apartment rent and utilities, or splitting dinner bills, calculating who owes whom can quickly become a complex mathematical challenge. 

This application takes the hassle out of expense sharing. It calculates individual balances in real-time and, most importantly, employs a greedy debt simplification algorithm to optimize and minimize the total number of monetary transactions required for everyone to settle up.

## Key Features

* **Comprehensive Member Management:** 
  * Add multiple members to the expense group.
  * Delete members if they are no longer part of the group.
* **Detailed Expense Tracking:** 
  * Record expenses with precise details: Payer, Amount, Description, and Category.
  * Supported categories include: Food, Transport, Entertainment, Utilities, and Other.
  * View a complete, uneditable history of all expenses logged.
  * Delete erroneous expenses and have balances automatically recalculate.
* **Intelligent Debt Simplification:** 
  * Automatically calculates net balances for all members (positive balance = owed money, negative balance = owes money).
  * Uses an optimized mathematical algorithm to simplify debts, ensuring the group settles up with the absolute minimum number of transactions.
* **Data Persistence:**
  * Save your group's members and expenses to a local file (`data.dat`).
  * Load your saved data in future sessions to pick up exactly where you left off.
* **Robust Form Validation:**
  * Input validation prevents empty names, negative amounts, and invalid characters.
* **Professional Graphical User Interface:** 
  * An intuitive, easy-to-use Java Swing-based GUI.
  * Organized layout with clear input controls, member lists, balance tables, and a dedicated settlement view.
* **Logging System:**
  * Integrated `java.util.logging` to track all major application events and errors (logs saved to `logs/app.log`).

## Technologies & Stack

* **Programming Language:** Java 17+
* **User Interface:** Java Swing (AWT/Swing)
* **Testing:** JUnit 5 (Jupiter API)
* **Logging:** `java.util.logging` (JUL)
* **Architecture:** MVC-inspired modular design with separate models, utility classes, and GUI components.

## Screenshots

### Application Interface
![Main Window - Input and Balances](screenshot1.png)

### Expense History & Settlement
![Expense History and Debt Simplification](screenshot2.png)

## Installation & Setup

1. **Prerequisites:** 
   Ensure you have the Java Development Kit (JDK) 17 or higher installed on your machine.
2. **Clone the Repository:**
   ```bash
   git clone https://github.com/akaksh001/Smart-expense-simplifier.git
   cd Smart-expense-simplifier
   ```
3. **Compile the Source Code:**
   Compile the main application classes and place them in the `out` directory:
   ```bash
   javac -d out Main.java model\*.java gui\MainFrame.java util\AppLogger.java
   ```
4. **Run the Graphical Application:**
   ```bash
   java -cp out gui.MainFrame
   ```
   *(Alternatively, run `java -cp out Main` for the console-only mode).*

## Testing Instructions

The project includes comprehensive automated tests for the core models and mathematical algorithms using JUnit 5.

1. **Download JUnit 5 Console Standalone:**
   Ensure the `junit-platform-console-standalone-1.10.2.jar` is inside the `lib/` folder.
2. **Compile the Tests:**
   ```bash
   javac -d out -cp "lib\junit-platform-console-standalone-1.10.2.jar;." Main.java model\*.java util\AppLogger.java test\*.java
   ```
3. **Run the Test Suite:**
   ```bash
   java -jar lib\junit-platform-console-standalone-1.10.2.jar --class-path out --scan-classpath
   ```
   *You should see 16/16 tests passing successfully.*

## Future Enhancements
* Unequal expense splitting (e.g., splitting by percentages or specific amounts).
* Exporting settlement reports to PDF or Excel.
* Multi-currency support with real-time conversion rates.

---
*Developed as part of the VITyarthi Flipped Course Evaluation.*
