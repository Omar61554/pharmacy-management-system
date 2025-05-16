package model; 
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Order> orderHistory = new ArrayList<>();
    private boolean isPremiumMember;

    public Customer(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    @Override
    public double calculateDiscount(double originalAmount) {
        // Premium members get 15% discount, regular get 5%
        double discountRate = isPremiumMember ? 0.15 : 0.05;
        return originalAmount * (1 - discountRate);
    }

    public void setPremiumMember(boolean isPremiumMember) {
        this.isPremiumMember = isPremiumMember;
    }
    
    public void placeOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
