package model;

/**
 * Represents a worker in the pharmacy system.
 * Inherits from the Person class.
 */
public class PharmacyWorker extends Person {
    // The role of the pharmacy worker (e.g., "Pharmacist", "Technician")
    private String role;

    /**
     * Constructs a new PharmacyWorker object.
     *
     * @param id The unique identifier of the worker.
     * @param name The name of the worker.
     * @param email The email address of the worker.
     * @param phoneNumber The phone number of the worker.
     * @param role The role of the worker in the pharmacy.
     */
    public PharmacyWorker(int id, String name, String email, String phoneNumber, String role) {
        super(id, name, email, phoneNumber);
        this.role = role;
    }

    @Override
    public double calculateDiscount(double originalAmount) {
        // Employees get 20% discount with $100 cap
        double discount = originalAmount * 0.20;
        return originalAmount - Math.min(discount, 100);
    }
    
    public void manageInventory() {
        System.out.println("Managing inventory...");
        // Placeholder for inventory management logic
    }

    /**
     * Simulates the action of assisting a customer.
     *
     * @param customer The Customer object representing the customer being assisted.
     */
    public void assistCustomer(Customer customer) {
        System.out.println("Assisting customer: " + customer.getDetails());
        // Placeholder for customer assistance logic
    }

    // You might want to add getters/setters for the 'role' field if needed
    /**
     * Gets the role of the pharmacy worker.
     * @return The worker's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the pharmacy worker.
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
