# Project Report: Smart Expense Splitter

## 1. Title Page
**Project Title:** Smart Expense Splitter
**Course:** Object-Oriented Programming (VITyarthi)
**Language:** Java

## 2. Abstract
The Smart Expense Splitter is a Java application designed to calculate and simplify shared expenses among a group. When individuals share costs for events or living arrangements, tracking debts can become tedious and convoluted. This software solves the problem by recording expenses, calculating equal shares, tracking continuous net balances, and employing a greedy algorithm to reduce the total number of transactions needed to settle debts.

## 3. Introduction
Managing finances in a group setting usually results in multiple overlapping debts. The objective of this project is to create an intuitive application that mathematically simplifies these transactions. It tracks members, logs expenses, and automatically determines who owes whom in the most efficient manner. 

## 4. Problem Statement
*(See `statement.md` for full details)*
In group dynamics, calculating shared expenses manually leads to errors and excessive peer-to-peer transactions. The system requires an automated way to log expenses, distribute costs evenly, and calculate the minimal number of payments to settle all outstanding balances.

## 5. System Requirements
- **Hardware:** Standard PC/Laptop with minimum 2GB RAM.
- **Software:** Java Development Kit (JDK) 17 or higher.
- **Libraries:** Java Swing (built-in).

## 6. Architecture & Design
The software follows an MVC-like architecture.
- **Model:** Contains data structures (`Person`, `Expense`, `ExpenseCategory`) and business logic (`ExpenseManager`, `DebtSimplifier`).
- **View/Controller:** The `MainFrame` handles user interactions, input validation, and rendering state from the Model.
- **Utils:** Contains cross-cutting features like `FileManager` and `AppLogger`.

## 7. Use Case Diagram
*(Refer to `docs/diagrams/usecase_diagram.md`)*
Key use cases include adding members, logging expenses, and viewing simplified settlements.

## 8. Class Diagram
*(Refer to `docs/diagrams/class_diagram.md`)*
Demonstrates OOP principles like encapsulation in `Person` and `Expense`, and relationships such as `ExpenseManager` composing `Person` and `Expense` objects.

## 9. Sequence Diagram
*(Refer to `docs/diagrams/sequence_diagram.md`)*
Shows the lifecycle of adding an expense: User -> MainFrame -> ExpenseManager -> Person objects updating balances.

## 10. Activity / Workflow Diagram
*(Refer to `docs/diagrams/workflow_diagram.md`)*
Details the conditional logic of validating inputs, distributing costs, and simplifying debts using the greedy two-pointer approach.

## 11. Implementation Details
The core logic resides in `DebtSimplifier`, which uses two lists (debtors and creditors). It iteratively matches the largest available debtor with a creditor, minimizing the transaction value by the minimum of their respective absolute balances, effectively reducing the net transactions needed.

## 12. Testing
Testing focuses on verifying the mathematical accuracy of the debt settlement algorithm. Scenarios such as circular debts (A owes B, B owes C, C owes A) were verified to ensure the system simplifies it to zero or optimal direct transactions.

## 13. Screenshots
*(To be added by the user: Include visual evidence of the working GUI.)*

## 14. Conclusion
The Smart Expense Splitter successfully demonstrates fundamental OOP concepts such as classes, access modifiers, encapsulation, and GUI integration. The system effectively tracks and simplifies group expenses, eliminating the confusion of shared payments.

## 15. Future Enhancements
- Integration with an actual database (e.g., SQLite) instead of text files.
- Support for custom expense splits (e.g., percentages or exact amounts).
- Exporting settlement reports to PDF or CSV.
- Mobile application adaptation.
