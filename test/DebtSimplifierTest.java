package test;

import model.DebtSimplifier;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Test class for DebtSimplifier.
 */
public class DebtSimplifierTest {

    private ArrayList<Person> members;

    @BeforeEach
    public void setUp() {
        members = new ArrayList<>();
    }

    @Test
    @DisplayName("Test simplify with no debts")
    public void testSimplifyWithNoDebts() {
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        members.add(alice);
        members.add(bob);
        
        String result = DebtSimplifier.simplify(members);
        assertTrue(result.contains("All balances are settled!"), "Should indicate that all balances are settled");
    }

    @Test
    @DisplayName("Test simplify with two members")
    public void testSimplifyWithTwoMembers() {
        Person alice = new Person("Alice");
        alice.addBalance(50.0);
        Person bob = new Person("Bob");
        bob.addBalance(-50.0);
        
        members.add(alice);
        members.add(bob);
        
        String result = DebtSimplifier.simplify(members);
        assertTrue(result.contains("Bob pays ₹50.00 to Alice"), "Should contain the correct settlement string");
        
        // Ensure balances are updated properly
        assertEquals(0.0, alice.getBalance(), 0.01, "Alice balance should be 0 after simplification");
        assertEquals(0.0, bob.getBalance(), 0.01, "Bob balance should be 0 after simplification");
    }

    @Test
    @DisplayName("Test simplify with three members complex scenario")
    public void testSimplifyWithThreeMembers() {
        Person alice = new Person("Alice");
        alice.addBalance(100.0);   // Creditor
        Person bob = new Person("Bob");
        bob.addBalance(-40.0);     // Debtor
        Person charlie = new Person("Charlie");
        charlie.addBalance(-60.0); // Debtor
        
        members.add(alice);
        members.add(bob);
        members.add(charlie);
        
        String result = DebtSimplifier.simplify(members);
        assertTrue(result.contains("Bob pays ₹40.00 to Alice"), "Bob should pay Alice 40.00");
        assertTrue(result.contains("Charlie pays ₹60.00 to Alice"), "Charlie should pay Alice 60.00");
        
        // Ensure balances are updated properly
        assertEquals(0.0, alice.getBalance(), 0.01, "Alice balance should be 0");
        assertEquals(0.0, bob.getBalance(), 0.01, "Bob balance should be 0");
        assertEquals(0.0, charlie.getBalance(), 0.01, "Charlie balance should be 0");
    }

    @Test
    @DisplayName("Test simplify returns string containing expected names")
    public void testSimplifyReturnsString() {
        Person dave = new Person("Dave");
        dave.addBalance(-10.0);
        Person eve = new Person("Eve");
        eve.addBalance(10.0);
        
        members.add(dave);
        members.add(eve);
        
        String result = DebtSimplifier.simplify(members);
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Dave"), "Result should mention Dave");
        assertTrue(result.contains("Eve"), "Result should mention Eve");
    }
}
