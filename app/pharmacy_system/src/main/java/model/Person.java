package model;

/**
 * Abstract class representing a generic person in the pharmacy system.
 * This class serves as a base for specific types of people like Customers or Employees.
 */
public abstract class Person {
    // Unique identifier for the person
    protected int id;
    // Name of the person
    protected String name;
    // Email address of the person
    protected String email;
    // Phone number of the person
    protected String phoneNumber;

    /**
     * Constructs a new Person object.
     *
     * @param id The unique identifier of the person.
     * @param name The name of the person.
     * @param email The email address of the person.
     * @param phoneNumber The phone number of the person.
     */
    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract double calculateDiscount(double originalAmount);
    
    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}
