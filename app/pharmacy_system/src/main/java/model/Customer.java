package model; 
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    public void placeOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
