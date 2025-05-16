package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a medicine item in the pharmacy system.
 * This interface defines the common properties and behaviors for all medicine types.
 */
public interface Medicine extends Serializable {
    // Common getters

    /**
     * Gets the unique identifier of the medicine.
     * @return The medicine's ID.
     */
    int getId();

    /**
     * Gets the name of the medicine.
     * @return The medicine's name.
     */
    String getName();

    /**
     * Gets the price of the medicine.
     * @return The medicine's price.
     */
    double getPrice();

    /**
     * Gets the expiration date of the medicine.
     * @return The medicine's expiration date.
     */
    Date getExpirationDate();

    /**
     * Gets the current stock quantity of the medicine.
     * @return The stock quantity.
     */
    int getStockQuantity();

    // Common setters

    /**
     * Sets the unique identifier of the medicine.
     * @param id The ID to set.
     */
    void setId(int id);

    // Common methods

    /**
     * Updates the stock quantity of the medicine.
     * @param quantity The amount to add or remove from the current stock.
     */
    void updateQuantity(int quantity);

    /**
     * Gets a detailed description of the medicine.
     * @return A string containing the medicine's details.
     */
    String getDetails();

    /**
     * Checks if the medicine has expired based on the current date.
     * @return true if the medicine is expired, false otherwise.
     */
    boolean isExpired();
}