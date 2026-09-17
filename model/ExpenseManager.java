package model;

import java.util.*;

/**
 * Manages the members, expenses, and their balances.
 */
public class ExpenseManager {

    private ArrayList<Person> members;
    private ArrayList<Expense> expenses;

    /**
     * Constructs a new ExpenseManager with empty members and expenses lists.
     */
    public ExpenseManager() {
        members = new ArrayList<>();
        expenses = new ArrayList<>();
    }

    /**
     * Adds a new member to the manager.
     *
     * @param name the name of the member to add
     * @return true if the member was added, false if a member with the same name already exists
     */
    public boolean addMember(String name) {
        for (Person p : members) {
            if (p.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        members.add(new Person(name));
        return true;
    }

    /**
     * Removes a member from the manager.
     * 
     * @param name the name of the member to remove
     * @return true if the member was removed, false otherwise
     */
    public boolean removeMember(String name) {
        return members.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    /**
     * Adds a new expense and updates the balances of all members.
     *
     * @param payer       the name of the person who paid
     * @param amount      the amount paid
     * @param description the description of the expense
     */
    public void addExpense(String payer,
                           double amount,
                           String description) {

        expenses.add(new Expense(payer, amount, description));

        int totalMembers = members.size();
        if (totalMembers == 0) return;
        
        double share = amount / totalMembers;

        for (Person p : members) {
            if (p.getName().equalsIgnoreCase(payer)) {
                p.addBalance(amount - share);
            } else {
                p.addBalance(-share);
            }
        }
    }
    
    /**
     * Removes an expense by its ID and reverts the balances of all members.
     * 
     * @param id the ID of the expense to remove
     * @return true if the expense was removed, false otherwise
     */
    public boolean removeExpense(int id) {
        Expense toRemove = null;
        for (Expense e : expenses) {
            if (e.getId() == id) {
                toRemove = e;
                break;
            }
        }
        
        if (toRemove != null) {
            int totalMembers = members.size();
            if (totalMembers > 0) {
                double share = toRemove.getAmount() / totalMembers;
                for (Person p : members) {
                    if (p.getName().equalsIgnoreCase(toRemove.getPaidBy())) {
                        p.addBalance(-(toRemove.getAmount() - share));
                    } else {
                        p.addBalance(share);
                    }
                }
            }
            expenses.remove(toRemove);
            return true;
        }
        return false;
    }

    /**
     * Gets the list of members.
     *
     * @return the list of members
     */
    public ArrayList<Person> getMembers() {
        return members;
    }

    /**
     * Gets the list of expenses.
     * 
     * @return the list of expenses
     */
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    /**
     * Displays the current balances of all members.
     */
    public void showBalances() {
        System.out.println("\nCurrent Balances:");

        for (Person p : members) {
            System.out.println(p);
        }
    }
    
    /**
     * Saves the members and expenses data to a file.
     * 
     * @param filePath the file path to save to
     */
    public void saveData(String filePath) {
        FileManager.saveToFile(members, expenses, filePath);
    }
    
    /**
     * Loads the members and expenses data from a file.
     * 
     * @param filePath the file path to load from
     */
    public void loadData(String filePath) {
        this.members = FileManager.loadMembers(filePath);
        this.expenses = FileManager.loadExpenses(filePath);
    }
}
