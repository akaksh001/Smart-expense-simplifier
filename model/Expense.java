package model;

import java.time.LocalDateTime;

/**
 * Represents an expense paid by a person.
 */
public class Expense {
    private static int nextId = 1;
    
    private int id;
    private String paidBy;
    private double amount;
    private String description;
    private ExpenseCategory category;
    private LocalDateTime timestamp;

    /**
     * Constructs a new Expense with the given details, defaulting to OTHER category.
     *
     * @param paidBy      the name of the person who paid
     * @param amount      the amount paid
     * @param description the description of the expense
     */
    public Expense(String paidBy, double amount, String description) {
        this(paidBy, amount, description, ExpenseCategory.OTHER);
    }

    /**
     * Constructs a new Expense with the given details and category.
     *
     * @param paidBy      the name of the person who paid
     * @param amount      the amount paid
     * @param description the description of the expense
     * @param category    the category of the expense
     */
    public Expense(String paidBy, double amount, String description, ExpenseCategory category) {
        this.id = nextId++;
        this.paidBy = paidBy;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the unique ID of the expense.
     *
     * @return the ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the unique ID of the expense.
     *
     * @param id the ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the next available ID.
     * 
     * @return the next available ID
     */
    public static int getNextId() {
        return nextId;
    }
    
    /**
     * Sets the next available ID.
     * 
     * @param id the next available ID
     */
    public static void setNextId(int id) {
        nextId = id;
    }

    /**
     * Gets the name of the person who paid.
     *
     * @return the payer's name
     */
    public String getPaidBy() {
        return paidBy;
    }

    /**
     * Gets the amount paid.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the description of the expense.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the category of the expense.
     *
     * @return the category
     */
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     * Gets the timestamp when the expense was created.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Sets the timestamp of the expense.
     * 
     * @param timestamp the timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a string representation of the expense.
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "Expense #" + id + " [" + category.getDisplayName() + "] " + description + 
               " : " + paidBy + " paid " + amount + " on " + timestamp.toLocalDate();
    }
}
