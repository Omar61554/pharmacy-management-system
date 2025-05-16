package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the pharmacy system.
 * Inherits from the Person class.
 */
public class Customer extends Person {
    // List to store the order history of the customer
    private List<Order> orderHistory = new ArrayList<>();

    /**
     * Constructs a new Customer object.
     *
     * @param id The unique identifier of the customer.
     * @param name The name of the customer.
     * @param email The email address of the customer.
     * @param phoneNumber The phone number of the customer.
     */
    public Customer(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    /**
     * Adds an order to the customer's order history.
     *
     * @param order The order to be placed.
     */
    public void placeOrder(Order order) {
        orderHistory.add(order);
    }

    /**
     * Gets the order history of the customer.
     *
     * @return A list of orders placed by the customer.
     */
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
