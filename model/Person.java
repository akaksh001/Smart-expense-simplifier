package model;

/**
 * Represents a person participating in the expenses.
 */
public class Person {
    private String name;
    private double balance;

    /**
     * Constructs a new Person with the given name and initial balance of 0.
     *
     * @param name the name of the person
     */
    public Person(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    /**
     * Gets the name of the person.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current balance of the person.
     * Positive balance means the person is owed money, negative means the person owes money.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Adds the specified amount to the person's balance.
     *
     * @param amount the amount to add
     */
    public void addBalance(double amount) {
        balance += amount;
    }

    /**
     * Returns a string representation of the person and their balance.
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return name + " : " + String.format("%.2f", balance);
    }
}