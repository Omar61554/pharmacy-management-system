package model; 
public class PharmacyWorker extends Person {
    private String role;

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
    }

    public void assistCustomer(Customer customer) {
        System.out.println("Assisting customer: " + customer.getDetails());
    }
}
