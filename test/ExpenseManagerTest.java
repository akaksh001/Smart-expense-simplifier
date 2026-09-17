package test;

import model.ExpenseManager;
import model.Person;
import model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Test class for ExpenseManager.
 */
public class ExpenseManagerTest {

    private ExpenseManager manager;

    @BeforeEach
    public void setUp() {
        manager = new ExpenseManager();
    }

    @Test
    @DisplayName("Test adding a member successfully")
    public void testAddMember() {
        assertTrue(manager.addMember("Alice"), "Should return true for new member");
        assertEquals(1, manager.getMembers().size(), "Members list size should be 1");
        assertEquals("Alice", manager.getMembers().get(0).getName(), "Member name should be Alice");
    }

    @Test
    @DisplayName("Test adding a duplicate member")
    public void testAddDuplicateMember() {
        manager.addMember("Bob");
        assertFalse(manager.addMember("Bob"), "Should return false for exact duplicate member");
        assertFalse(manager.addMember("bob"), "Should return false for case-insensitive duplicate member");
        assertEquals(1, manager.getMembers().size(), "Members list size should still be 1");
    }

    @Test
    @DisplayName("Test removing a member successfully")
    public void testRemoveMember() {
        manager.addMember("Alice");
        assertTrue(manager.removeMember("Alice"), "Should return true when removing existing member");
        assertEquals(0, manager.getMembers().size(), "Members list should be empty");
        assertFalse(manager.removeMember("Alice"), "Should return false when member is already removed");
    }

    @Test
    @DisplayName("Test adding an expense updates balances")
    public void testAddExpense() {
        manager.addMember("Alice");
        manager.addMember("Bob");
        
        manager.addExpense("Alice", 100.0, "Dinner");
        
        ArrayList<Expense> expenses = manager.getExpenses();
        assertEquals(1, expenses.size(), "Expenses list should contain 1 expense");
        assertEquals(100.0, expenses.get(0).getAmount(), "Expense amount should be 100.0");
        
        ArrayList<Person> members = manager.getMembers();
        Person alice = members.stream().filter(p -> p.getName().equals("Alice")).findFirst().get();
        Person bob = members.stream().filter(p -> p.getName().equals("Bob")).findFirst().get();
        
        // Alice paid 100. Her share is 50. Alice balance = 100 - 50 = +50.
        // Bob paid 0. His share is 50. Bob balance = -50.
        assertEquals(50.0, alice.getBalance(), 0.01, "Alice balance should be 50.0");
        assertEquals(-50.0, bob.getBalance(), 0.01, "Bob balance should be -50.0");
    }

    @Test
    @DisplayName("Test removing an expense updates balances")
    public void testRemoveExpense() {
        manager.addMember("Alice");
        manager.addMember("Bob");
        
        manager.addExpense("Alice", 100.0, "Dinner");
        Expense expense = manager.getExpenses().get(0);
        
        assertTrue(manager.removeExpense(expense.getId()), "Should return true for removing existing expense");
        assertEquals(0, manager.getExpenses().size(), "Expenses list should be empty");
        
        ArrayList<Person> members = manager.getMembers();
        Person alice = members.stream().filter(p -> p.getName().equals("Alice")).findFirst().get();
        Person bob = members.stream().filter(p -> p.getName().equals("Bob")).findFirst().get();
        
        // Balances should revert to 0
        assertEquals(0.0, alice.getBalance(), 0.01, "Alice balance should revert to 0.0");
        assertEquals(0.0, bob.getBalance(), 0.01, "Bob balance should revert to 0.0");
    }

    @Test
    @DisplayName("Test getting members list")
    public void testGetMembers() {
        manager.addMember("Alice");
        manager.addMember("Bob");
        ArrayList<Person> members = manager.getMembers();
        assertEquals(2, members.size(), "Should return list of 2 members");
    }

    @Test
    @DisplayName("Test getting expenses list")
    public void testGetExpenses() {
        manager.addMember("Alice");
        manager.addExpense("Alice", 50.0, "Lunch");
        ArrayList<Expense> expenses = manager.getExpenses();
        assertEquals(1, expenses.size(), "Should return list of 1 expense");
        assertEquals("Lunch", expenses.get(0).getDescription(), "Expense description should be Lunch");
    }

    @Test
    @DisplayName("Verify balances after complex expense splitting")
    public void testExpenseSplitCalculation() {
        manager.addMember("Alice");
        manager.addMember("Bob");
        manager.addMember("Charlie");
        
        manager.addExpense("Alice", 90.0, "Tickets"); // Alice paid 90, share 30. Balances: Alice +60, Bob -30, Charlie -30
        manager.addExpense("Bob", 30.0, "Snacks");    // Bob paid 30, share 10. Balances: Alice +50, Bob -10, Charlie -40
        
        ArrayList<Person> members = manager.getMembers();
        Person alice = members.stream().filter(p -> p.getName().equals("Alice")).findFirst().get();
        Person bob = members.stream().filter(p -> p.getName().equals("Bob")).findFirst().get();
        Person charlie = members.stream().filter(p -> p.getName().equals("Charlie")).findFirst().get();
        
        assertEquals(50.0, alice.getBalance(), 0.01, "Alice balance should be 50.0");
        assertEquals(-10.0, bob.getBalance(), 0.01, "Bob balance should be -10.0");
        assertEquals(-40.0, charlie.getBalance(), 0.01, "Charlie balance should be -40.0");
    }
}
