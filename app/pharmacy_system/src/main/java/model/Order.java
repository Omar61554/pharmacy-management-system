package model;
import java.util.Date;
import java.util.List;

/**
 * Represents an order placed by a customer in the pharmacy system.
 */
public class Order {
    // Unique identifier for the order
    private int id;
    // The customer who placed the order
    private Customer customer;
    // List of medicines included in the order
    private List<Medicine> medicines;
    // Date when the order was placed
    private Date orderDate;

    /**
     * Constructs a new Order object.
     *
     * @param id The unique identifier of the order.
     * @param customer The customer who placed the order.
     * @param medicines The list of medicines in the order.
     * @param orderDate The date the order was placed.
     */
    public Order(int id, Customer customer, List<Medicine> medicines, Date orderDate) {
        this.id = id;
        this.customer = customer;
        this.medicines = medicines;
        this.orderDate = orderDate;
    }

    /**
     * Calculates the total price of all medicines in the order.
     *
     * @return The total price of the order.
     */
    public double calculateTotal() {
        double total = 0;
        for (Medicine med : medicines) {
            total += med.getPrice();
        }
        return total;
    }

    /**
     * Generates a detailed string representation of the order.
     * Includes order ID, date, customer details, list of medicines, and total price.
     *
     * @return A string containing the order details.
     */
    public String getOrderDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id)
          .append("\nDate: ").append(orderDate)
          .append("\nCustomer: ").append(customer.getDetails())
          .append("\nMedicines:\n");
        for (Medicine med : medicines) {
            sb.append("- ").append(med.getDetails()).append("\n");
        }
        sb.append("Total: $").append(calculateTotal());
        return sb.toString();
    }
}
