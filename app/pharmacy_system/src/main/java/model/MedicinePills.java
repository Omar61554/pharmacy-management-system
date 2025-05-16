package model;
import java.util.Date;

/**
 * Represents a medicine in pill form in the pharmacy system.
 * Implements the Medicine interface.
 */
public class MedicinePills implements Medicine {
    private static final long serialVersionUID = 1L;  // Add this version UID

    // Unique identifier for the medicine
    private int id;
    // Name of the medicine
    private String name;
    // Price of the medicine
    private double price;
    // Expiration date of the medicine
    private Date expirationDate;
    // Current stock quantity of the medicine
    private int stockQuantity;
    // Number of pills per unit (e.g., per bottle or box)
    private int pillCount;

    /**
     * Constructs a new MedicinePills object.
     *
     * @param id The unique identifier of the medicine.
     * @param name The name of the medicine.
     * @param price The price of the medicine.
     * @param expirationDate The expiration date of the medicine.
     * @param stockQuantity The initial stock quantity.
     * @param pillCount The number of pills per unit.
     */
    public MedicinePills(int id, String name, double price, Date expirationDate, int stockQuantity, int pillCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
        this.pillCount = pillCount;
    }

    /**
     * Updates the stock quantity of the medicine.
     * Adds the specified quantity to the current stock, ensuring the stock does not go below zero.
     *
     * @param quantity The amount to add or remove from the current stock.
     */
    public void updateQuantity(int quantity){
        if (this.stockQuantity + quantity >= 0){
            this.stockQuantity += quantity;}
        else {
            System.out.println("There are no enough stock");
        }
    }

    /**
     * Gets a detailed description of the medicine.
     *
     * @return A string containing the medicine's ID, Name, Price, and Expiration Date.
     */
    public String getDetails() {
        return "Medicine[ID: " + id + ", Name: " + name + ", Price: $" + price + ", Exp: " + expirationDate + "]";
    }

    /**
     * Checks if the medicine has expired based on the current date.
     *
     * @return true if the medicine is expired, false otherwise.
     */
    public boolean isExpired() {
        return expirationDate.before(new Date());
    }

    /**
     * Gets the price of the medicine.
     *
     * @return The medicine's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the number of pills per unit.
     *
     * @return The pill count.
     */
    public int gettPillCount()  {
        return pillCount;
    }

    /**
     * Gets the name of the medicine.
     *
     * @return The medicine's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique identifier of the medicine.
     *
     * @return The medicine's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the medicine.
     *
     * @param id The ID to set.
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the current stock quantity of the medicine.
     *
     * @return The stock quantity.
     */
    public int getQuantity() {
        return stockQuantity;
    }

    /**
     * Gets the expiration date of the medicine.
     *
     * @return The medicine's expiration date.
     */
    public Date getDate() {
        return expirationDate;
    }

    /**
     * Gets the current stock quantity of the medicine.
     *
     * @return The stock quantity.
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Gets the expiration date of the medicine.
     *
     * @return The medicine's expiration date.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }
}
