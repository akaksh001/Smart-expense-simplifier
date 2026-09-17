# Problem Statement: Smart Expense Splitter

## Problem Context
When traveling in a group, living with roommates, or organizing a joint event, expenses are frequently shared. As the number of participants and transactions grows, keeping track of who paid for what and manually calculating who owes whom becomes increasingly complex and error-prone. 

Without a systematic computational approach:
- Individuals struggle to maintain fair ledgers.
- Debt resolution becomes highly inefficient, often resulting in "circular debts" (e.g., A owes B, B owes C, C owes A).
- People end up making multiple, redundant transactions to settle debts, which wastes time and can incur unnecessary digital transfer fees.
- Disputes and friction can arise due to lack of transparency and inaccurate manual tracking.

## Scope of the Project
The **Smart Expense Splitter** application is built to handle group expense tracking and debt settlement efficiently and transparently. It provides a localized software solution where users can input group members, log shared expenses across multiple categories, and view real-time net balances. 

The core of the application lies in its algorithmic approach to resolving debts: it eliminates redundant transactions and computes a simplified settlement plan using a greedy algorithm. The project encompasses a graphical user interface (GUI) for ease of use, robust data validation, and file-based data persistence.

## Target Users
The system is designed for any group of individuals needing to track and settle shared costs:
1. **Roommates:** For equitably splitting recurring costs like rent, groceries, and utilities over time.
2. **Travel Groups & Friends:** For tracking shared meals, transportation, and accommodation during trips.
3. **Event Organizers:** For splitting the costs of organizing parties, meetups, or collaborative projects among contributors.
4. **Colleagues:** For managing shared team lunches or office supplies.

## High-Level Features
1. **Dynamic Group Management:** Users can add and remove participants at any time. The system actively prevents duplicate entries to ensure data integrity.
2. **Comprehensive Expense Logging:** Users can record expenses by specifying the payer, exact amount, description, and categorized type (Food, Transport, Utilities, etc.).
3. **Real-time Equitable Splitting:** The system automatically distributes the recorded cost equally among all group members, instantly updating their individual net balances.
4. **Expense History & Auditing:** A complete, uneditable history table provides full transparency of all transactions logged, with the ability to delete incorrect entries safely.
5. **Net Balance Dashboard:** Users can check their current financial standing at a glance. A positive balance indicates they are owed money; a negative balance indicates they owe money.
6. **Algorithmic Debt Simplification:** A mathematical algorithm computes the most efficient, minimized set of transactions required to settle all outstanding debts across the group.
7. **Data Persistence:** Built-in save and load functionality allowing the group's ledger to be stored securely and retrieved across different sessions.
