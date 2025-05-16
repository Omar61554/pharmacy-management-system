package model;

import java.io.Serializable;
import java.util.Date;

public interface Medicine extends Serializable {
    // Common getters
    int getId();
    String getName();
    double getPrice();
    Date getExpirationDate();
    int getStockQuantity();
    
    // Common setters
    void setId(int id);
    
    // Common methods
    void updateQuantity(int quantity);
    String getDetails();
    boolean isExpired();
}