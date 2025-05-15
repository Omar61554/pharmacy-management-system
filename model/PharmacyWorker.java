package model; 
public class PharmacyWorker extends Person {
    private String role;

    public PharmacyWorker(int id, String name, String email, String phoneNumber, String role) {
        super(id, name, email, phoneNumber);
        this.role = role;
    }

    public void manageInventory() {
        System.out.println("Managing inventory...");
    }

    public void assistCustomer(Customer customer) {
        System.out.println("Assisting customer: " + customer.getDetails());
    }
}
