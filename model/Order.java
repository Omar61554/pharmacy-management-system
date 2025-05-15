package model; 
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Medicine> medicines;
    private Date orderDate;

    public Order(int id, Customer customer, List<Medicine> medicines, Date orderDate) {
        this.id = id;
        this.customer = customer;
        this.medicines = medicines;
        this.orderDate = orderDate;
    }

    public double calculateTotal() {
        double total = 0;
        for (Medicine med : medicines) {
            total += med.getPrice();
        }
        return total;
    }

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
