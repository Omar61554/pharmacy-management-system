package model; 
import java.util.Date;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private Date expirationDate;
    private int stockQuantity;

    public Medicine(String name, double price, Date expirationDate, int stockQuantity) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }
    
    public Medicine(int id, String name, double price, Date expirationDate, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public void updateQuantity(int quantity){
        if (this.stockQuantity + quantity >= 0){
            this.stockQuantity += quantity;}
        else {
            System.out.println("There are no enough stock");
        }
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getQuantity() {
        return stockQuantity;
    }
}
