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
    private boolean isPremiumMember;

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

<<<<<<< HEAD
    /**
     * Adds an order to the customer's order history.
     *
     * @param order The order to be placed.
     */
=======
    @Override
    public double calculateDiscount(double originalAmount) {
        // Premium members get 15% discount, regular get 5%
        double discountRate = isPremiumMember ? 0.15 : 0.05;
        return originalAmount * (1 - discountRate);
    }

    public void setPremiumMember(boolean isPremiumMember) {
        this.isPremiumMember = isPremiumMember;
    }
    
>>>>>>> dd421fb1c781900ae90ec8b2a485b8c164849b0a
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
