
package model; 
import java.util.Date;
import java.io.Serializable;

public class MedicineLiquid implements Serializable {
    private static final long serialVersionUID = 1L;  // Add this version UID
    
    private int id;
    private String name;
    private double price;
    private Date expirationDate;
    private int stockQuantity;
    protected int pillCount;
    private int volume;

    // public MedicinePills(String name, double price, Date expirationDate, int stockQuantity) {
    //     this.id = 0;
    //     this.name = name;
    //     this.price = price;
    //     this.expirationDate = expirationDate;
    //     this.stockQuantity = stockQuantity;
    // }
    
    MedicineLiquid(int id, String name, double price, Date expirationDate, int stockQuantity, int volume) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
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

}
