package model; 
import java.util.Date;
import java.io.Serializable;

public class MedicinePills implements Serializable, Medicine {
    private static final long serialVersionUID = 1L;  // Add this version UID
    
    private int id;
    private String name;
    private double price;
    private Date expirationDate;
    private int stockQuantity;
    private int pillCount;

    public MedicinePills(int id, String name, double price, Date expirationDate, int stockQuantity, int pillCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
        this.pillCount = pillCount;
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

    public int gettPillCount()  {
        return pillCount;
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

    public Date getDate() {
        return expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
