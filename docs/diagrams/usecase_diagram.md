```mermaid
usecaseDiagram
    actor User

    rectangle "Smart Expense Splitter" {
        usecase "Add Member" as UC1
        usecase "Remove Member" as UC2
        usecase "Add Expense" as UC3
        usecase "Remove Expense" as UC4
        usecase "View Net Balances" as UC5
        usecase "Simplify Debts" as UC6
        usecase "Save/Load Data" as UC7
    }

    User --> UC1
    User --> UC2
    User --> UC3
    User --> UC4
    User --> UC5
    User --> UC6
    User --> UC7

    UC3 ..> UC5 : <<include>>
    UC6 ..> UC5 : <<include>>
```
