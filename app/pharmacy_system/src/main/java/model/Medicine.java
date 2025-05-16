package model;
import java.util.Date;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private Date expirationDate; 
    private int stockQuantity;

    public Medicine() {}

    public Medicine(int id, String name, double price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void getQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

    public String getDetails() {
        return "Medicine[ID: " + id + ", Name: " + name + ", Price: $" + price + ", Exp: " + expirationDate + "]";
    }

    public boolean isExpired() {
        return expirationDate.before(new Date());
    }
}