package model; 
import java.util.Date;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private Date expirationDate;
    private int stockQuantity;

    public Medicine(int id, String name, double price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public String getDetails() {
        return "Medicine[ID: " + id + ", Name: " + name + ", Price: $" + price + ", Exp: " + expirationDate + "]";
    }

    public boolean isExpired() {
        return expirationDate.before(new Date());
    }

    public double getPrice() {
        return price;
    }
}
