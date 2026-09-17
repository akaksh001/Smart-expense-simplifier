package model;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Utility class for data persistence using simple CSV format.
 */
public class FileManager {

    /**
     * Saves members and expenses to the specified file path.
     *
     * @param members the list of members
     * @param expenses the list of expenses
     * @param filePath the file path to save to
     */
    public static void saveToFile(ArrayList<Person> members, ArrayList<Expense> expenses, String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("---MEMBERS---");
            for (Person p : members) {
                writer.println(p.getName() + "," + p.getBalance());
            }
            writer.println("---EXPENSES---");
            for (Expense e : expenses) {
                writer.println(e.getId() + "," + e.getPaidBy() + "," + e.getAmount() + "," + e.getDescription() + "," + e.getCategory().name() + "," + e.getTimestamp());
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Loads members from the specified file path.
     *
     * @param filePath the file path to load from
     * @return the list of members, or empty list if an error occurs
     */
    public static ArrayList<Person> loadMembers(String filePath) {
        ArrayList<Person> members = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return members;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean readingMembers = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals("---MEMBERS---")) {
                    readingMembers = true;
                    continue;
                } else if (line.equals("---EXPENSES---")) {
                    break;
                }
                
                if (readingMembers && !line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        Person p = new Person(parts[0]);
                        p.addBalance(Double.parseDouble(parts[1]));
                        members.add(p);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }
        return members;
    }

    /**
     * Loads expenses from the specified file path.
     *
     * @param filePath the file path to load from
     * @return the list of expenses, or empty list if an error occurs
     */
    public static ArrayList<Expense> loadExpenses(String filePath) {
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return expenses;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean readingExpenses = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals("---EXPENSES---")) {
                    readingExpenses = true;
                    continue;
                }
                
                if (readingExpenses && !line.trim().isEmpty()) {
                    String[] parts = line.split(",", 6);
                    if (parts.length >= 4) {
                        int id = Integer.parseInt(parts[0]);
                        String paidBy = parts[1];
                        double amount = Double.parseDouble(parts[2]);
                        String desc = parts[3];
                        ExpenseCategory category = ExpenseCategory.OTHER;
                        if (parts.length >= 5) {
                            category = ExpenseCategory.fromString(parts[4]);
                        }
                        Expense expense = new Expense(paidBy, amount, desc, category);
                        if (parts.length >= 6) {
                            expense.setTimestamp(LocalDateTime.parse(parts[5]));
                        }
                        expense.setId(id);
                        expenses.add(expense);
                        
                        // Update Expense auto-increment ID to prevent collisions
                        if (id >= Expense.getNextId()) {
                            Expense.setNextId(id + 1);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
