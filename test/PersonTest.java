package test;

import model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Person model.
 */
public class PersonTest {
    
    @Test
    @DisplayName("Test Person constructor")
    public void testConstructor() {
        Person person = new Person("Alice");
        assertEquals("Alice", person.getName(), "Name should be Alice");
        assertEquals(0.0, person.getBalance(), "Initial balance should be 0.0");
    }

    @Test
    @DisplayName("Test addBalance with positive and negative amounts")
    public void testAddBalance() {
        Person person = new Person("Bob");
        person.addBalance(100.0);
        assertEquals(100.0, person.getBalance(), "Balance should be 100.0");
        
        person.addBalance(-50.0);
        assertEquals(50.0, person.getBalance(), "Balance should be 50.0");
        
        person.addBalance(-100.0);
        assertEquals(-50.0, person.getBalance(), "Balance should be -50.0");
    }

    @Test
    @DisplayName("Test getName")
    public void testGetName() {
        Person person = new Person("Charlie");
        assertEquals("Charlie", person.getName(), "Name should match the constructor argument");
    }

    @Test
    @DisplayName("Test toString format")
    public void testToString() {
        Person person = new Person("Dave");
        person.addBalance(15.5);
        String expected = "Dave : 15.50";
        assertEquals(expected, person.toString(), "toString should format balance to 2 decimal places");
    }
}
