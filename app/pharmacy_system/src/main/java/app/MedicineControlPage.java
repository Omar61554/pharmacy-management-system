package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import controller.MedicineController;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Medicine;
import model.MedicineLiquid;
import model.MedicinePills;

/**
 * Represents the Medicine Control page in the pharmacy system's admin interface.
 * This page allows administrators to add, update, delete, view, and list all medicines.
 */
public class MedicineControlPage {

    // The root layout for this page
    private BorderPane root;
    // The controller for handling medicine-related business logic
    private MedicineController medicineController = App.medicineController;

    /**
     * Constructs a new MedicineControlPage.
     * Initializes the UI elements and sets up event handlers for the control buttons.
     */
    public MedicineControlPage() {
        root = new BorderPane();
        // Add the application logo to the top-left
        root.setTop(AppUtils.createLogo(100, "left"));

        // Container for the main control buttons
        VBox container = new VBox(15); // 15 pixels spacing between buttons
        container.setStyle("-fx-alignment: center; -fx-padding: 20;"); // Center align and add padding

        // Buttons for different medicine operations
        Button addButton = new Button("Add Medicine");
        Button updateButton = new Button("Update Medicine");
        Button deleteButton = new Button("Delete Medicine");
        Button viewButton = new Button("View Medicine");
        Button showAllButton = new Button("Show All Medicines");

        // Set actions for each button to show the corresponding dialog
        addButton.setOnAction(e -> showAddDialog());
        updateButton.setOnAction(e -> showUpdateDialog());
        deleteButton.setOnAction(e -> showDeleteDialog());
        viewButton.setOnAction(e -> showViewDialog());
        showAllButton.setOnAction(e -> showAllMedicinesDialog());

        // Add buttons to the main container
        container.getChildren().addAll(addButton, updateButton, deleteButton, viewButton, showAllButton);

        // Back button to return to the admin main page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showAdminMainPage());
        // HBox to position the back button at the bottom right
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setStyle("-fx-padding: 10;"); // Add padding around the back button

        // Set the main container in the center and the back button at the bottom
        root.setCenter(container);
        root.setBottom(backBox);
    }

    /**
     * Displays a dialog for adding a new medicine.
     * Collects medicine details from the user, validates input, and adds the medicine.
     */
    private void showAddDialog() {
        Dialog<Medicine> dialog = new Dialog<>();
        dialog.setTitle("Add Medicine");
        dialog.setHeaderText("Enter medicine details:");

        // UI elements for medicine details
        Label typeLabel = new Label("Type:");
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Liquid", "Pills");
        typeCombo.setValue("Liquid"); // Default value

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        Label expLabel = new Label("Expiration Date (yyyy-MM-dd):");
        TextField expField = new TextField();
        Label stockLabel = new Label("Stock Quantity:");
        TextField stockField = new TextField();

        // Extra field that changes based on medicine type (Volume or Pill Count)
        Label extraLabel = new Label("Volume (ml):");
        TextField extraField = new TextField();
        extraField.setPromptText("Enter volume in ml"); // Initial prompt text

        // Listener to change the extra field label and prompt text based on selected type
        typeCombo.setOnAction(e -> {
            if ("Liquid".equals(typeCombo.getValue())) {
                extraLabel.setText("Volume (ml):");
                extraField.setPromptText("Enter volume in ml");
            } else {
                extraLabel.setText("Pill Count:");
                extraField.setPromptText("Enter number of pills");
            }
        });

        // Arrange UI elements in a VBox
        VBox vbox = new VBox(10, typeLabel, typeCombo, idLabel, idField, nameLabel, nameField, priceLabel, priceField, expLabel, expField, stockLabel, stockField, extraLabel, extraField);
        dialog.getDialogPane().setContent(vbox);

        // Define dialog buttons
        ButtonType addType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addType, ButtonType.CANCEL);

        // Add an event filter to the "Add" button for input validation BEFORE the dialog closes
        Button addBtn = (Button) dialog.getDialogPane().lookupButton(addType);
        addBtn.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            // Check for empty fields
            if (idField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty() ||
                expField.getText().trim().isEmpty() ||
                stockField.getText().trim().isEmpty() ||
                extraField.getText().trim().isEmpty()) {
                showError("All fields must be filled.");
                event.consume(); // Consume the event to prevent the dialog from closing
                return;
            }
            // Check data types and date validity
            int id = -1;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (NumberFormatException e) {
                showError("ID must be an integer.");
                event.consume();
                return;
            }
            // Check for unique ID
            if (medicineController.getMedicine(id) != null) {
                showError("ID already exists. Please enter a unique ID.");
                event.consume();
                return;
            }
            try {
                Double.parseDouble(priceField.getText());
            } catch (NumberFormatException e) {
                showError("Price must be a number.");
                event.consume();
                return;
            }
            try {
                int stock = Integer.parseInt(stockField.getText());
                if (stock < 0) {
                    showError("Stock quantity must be a non-negative integer.");
                    event.consume();
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Stock quantity must be an integer.");
                event.consume();
                return;
            }
            try {
                Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(expField.getText());
                // Check if expiration date is before today
                if (exp.before(new Date())) {
                    showError("Expiration date cannot be before today.");
                    event.consume();
                }
            } catch (Exception ex) {
                showError("Invalid date format. Please use yyyy-MM-dd.");
                event.consume();
                return;
            }
            try {
                Integer.parseInt(extraField.getText());
            } catch (NumberFormatException e) {
                // Error message depends on the selected type
                showError(("Liquid".equals(typeCombo.getValue()) ? "Volume" : "Pill count") + " must be an integer.");
                event.consume();
            }
        });

        // Define how the dialog result is converted to a Medicine object
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addType) {
                try {
                    // Parse input fields (validation already done by event filter)
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(expField.getText());
                    int stock = Integer.parseInt(stockField.getText());
                    int extra = Integer.parseInt(extraField.getText());
                    String type = typeCombo.getValue();

                    // Create the appropriate Medicine subtype based on the selected type
                    if ("Liquid".equals(type)) {
                        return new MedicineLiquid(id, name, price, exp, stock, extra);
                    } else {
                        return new MedicinePills(id, name, price, exp, stock, extra);
                    }
                } catch (Exception ex) {
                    // This catch block should ideally not be reached if the event filter works correctly,
                    // but it's kept as a fallback.
                    // showError("An unexpected error occurred."); // Avoid showing error twice
                }
            }
            return null; // Return null if Cancel is clicked or validation failed
        });

        // Show the dialog and process the result if a Medicine object is returned
        dialog.showAndWait().ifPresent(this::addMedicine);
    }

    /**
     * Displays a dialog for updating an existing medicine.
     * Prompts for the medicine ID, retrieves the medicine, displays its details for editing,
     * validates input, and updates the medicine.
     */
    private void showUpdateDialog() {
        // First, ask for the ID of the medicine to update
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Update Medicine");
        idDialog.setHeaderText("Enter Medicine ID to update:");
        idDialog.setContentText("ID:");
        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                // Retrieve the medicine using the entered ID
                Medicine oldMed = medicineController.getMedicine(id);
                if (oldMed == null) {
                    // Show error if medicine is not found
                    showError("Medicine not found.");
                    return;
                }

                // If medicine is found, display a dialog to edit its details
                Dialog<Medicine> dialog = new Dialog<>();
                dialog.setTitle("Update Medicine");
                dialog.setHeaderText("Edit medicine details:");

                // Text fields pre-populated with current medicine details
                TextField nameField = new TextField(oldMed.getName());
                TextField priceField = new TextField(String.valueOf(oldMed.getPrice()));
                // Format the expiration date for display
                TextField expField = new TextField(new SimpleDateFormat("yyyy-MM-dd").format(oldMed.getExpirationDate()));
                TextField stockField = new TextField(String.valueOf(oldMed.getStockQuantity()));

                // Arrange fields in a VBox
                VBox vbox = new VBox(10,
                        new Label("Name:"), nameField,
                        new Label("Price:"), priceField,
                        new Label("Expiration Date (yyyy-MM-dd):"), expField,
                        new Label("Stock Quantity:"), stockField);
                dialog.getDialogPane().setContent(vbox);

                // Define dialog buttons
                ButtonType updateType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(updateType, ButtonType.CANCEL);

                // Define how the dialog result is converted to an updated Medicine object
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == updateType) {
                        try {
                            // Parse updated input fields
                            String name = nameField.getText();
                            double price = Double.parseDouble(priceField.getText());
                            Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(expField.getText());
                            int stock = Integer.parseInt(stockField.getText());

                            // Validate expiration date
                            if (exp.before(new Date())) {
                                showError("Expiration date cannot be before today.");
                                return null; // Return null if validation fails
                            }

                            // Create a new Medicine object with updated details, keeping the original type
                            if (oldMed instanceof MedicineLiquid) {
                                // Keep the original volume for liquid medicine
                                int volume = ((MedicineLiquid) oldMed).getVolume();
                                return new MedicineLiquid(id, name, price, exp, stock, volume);
                            } else if (oldMed instanceof MedicinePills) {
                                // Keep the original pill count for pill medicine
                                int pillCount = ((MedicinePills) oldMed).gettPillCount();
                                return new MedicinePills(id, name, price, exp, stock, pillCount);
                            } else {
                                // Should not happen if medicine types are handled correctly
                                return null;
                            }
                        } catch (Exception ex) {
                            // Handle parsing or date format errors
                            showError("Invalid input. Please check your entries.");
                            return null; // Return null on error
                        }
                    }
                    return null; // Return null if Cancel is clicked
                });

                // Show the update dialog and process the result if an updated Medicine object is returned
                dialog.showAndWait().ifPresent(this::updateMedicine);

            } catch (NumberFormatException ex) {
                // Handle invalid ID input
                showError("Invalid ID.");
            }
        });
    }

    /**
     * Displays a dialog for deleting a medicine.
     * Prompts for the medicine ID and deletes the medicine if found.
     */
    private void showDeleteDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Medicine");
        dialog.setHeaderText("Enter Medicine ID to delete:");
        dialog.setContentText("ID:");
        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                // Call the delete medicine method with the entered ID
                deleteMedicine(id);
            } catch (NumberFormatException ex) {
                // Handle invalid ID input
                showError("Invalid ID.");
            }
        });
    }

    /**
     * Displays a dialog for viewing details of a specific medicine.
     * Prompts for the medicine ID and displays its details if found.
     */
    private void showViewDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Medicine");
        dialog.setHeaderText("Enter Medicine ID to view:");
        dialog.setContentText("ID:");
        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                // Call the view medicine method with the entered ID
                viewMedicine(id);
            } catch (NumberFormatException ex) {
                // Handle invalid ID input
                showError("Invalid ID.");
            }
        });
    }

    /**
     * Displays a dialog showing a list of all medicines.
     * Retrieves all medicines and presents their details in a non-editable text area.
     */
    private void showAllMedicinesDialog() {
        // Retrieve the list of all medicines
        List<Medicine> medicines = medicineController.listAllMedicines();
        if (medicines.isEmpty()) {
            // Show message if no medicines are found
            showError("No medicines found.");
            return;
        }
        // Build a string containing details of all medicines
        StringBuilder sb = new StringBuilder();
        for (Medicine med : medicines) {
            sb.append("ID: ").append(med.getId())
              .append(", Name: ").append(med.getName())
              .append(", Price: ").append(med.getPrice())
              .append(", Expiration: ").append(med.getExpirationDate())
              .append(", Stock: ").append(med.getStockQuantity());
            // Append type-specific details (Volume or Pills)
            if (med instanceof MedicineLiquid) {
                sb.append(", Volume: ").append(((MedicineLiquid) med).getVolume()).append("ml");
            } else if (med instanceof MedicinePills) {
                sb.append(", Pills: ").append(((MedicinePills) med).gettPillCount());
            }
            sb.append("\n"); // New line for the next medicine
        }

        // Display the list in an information alert with a TextArea
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Medicines");
        alert.setHeaderText("List of Medicines");
        TextArea area = new TextArea(sb.toString());
        area.setEditable(false); // Make the text area read-only
        area.setWrapText(true); // Enable text wrapping
        alert.getDialogPane().setContent(area); // Set the text area as the dialog content
        alert.showAndWait(); // Show the alert
    }

    /**
     * Displays an error alert with a specified message.
     *
     * @param msg The error message to display.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Adds a medicine using the MedicineController and shows a success or error message.
     *
     * @param medicine The Medicine object to add.
     */
    public void addMedicine(Medicine medicine) {
        if (medicine == null) return; // Do nothing if medicine object is null
        boolean success = medicineController.addMedicine(medicine);
        if (success) {
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Added");
            alert.setContentText("The medicine has been added successfully.");
            alert.showAndWait();
        } else {
            // Show error message if adding failed
            showError("There was an error adding the medicine. Please try again.");
        }
    }

    /**
     * Updates a medicine using the MedicineController and shows a success or error message.
     *
     * @param medicine The Medicine object with updated details.
     */
    public void updateMedicine(Medicine medicine) {
        if (medicine == null) return; // Do nothing if medicine object is null
        boolean success = medicineController.updateMedicine(medicine);
        if (success) {
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Updated");
            alert.setContentText("The medicine has been updated successfully.");
            alert.showAndWait();
        } else {
            // Show error message if updating failed
            showError("There was an error updating the medicine. Please try again.");
        }
    }

    /**
     * Deletes a medicine using the MedicineController based on its ID and shows a success or error message.
     *
     * @param medicineId The ID of the medicine to delete.
     */
    public void deleteMedicine(int medicineId) {
        boolean success = medicineController.deleteMedicine(medicineId);
        if (success) {
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Deleted");
            alert.setContentText("The medicine has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show error message if deleting failed (e.g., medicine not found)
            showError("There was an error deleting the medicine. Please try again. (Medicine not found?)");
        }
    }

    /**
     * Views details of a medicine using the MedicineController based on its ID and displays them.
     *
     * @param medicineId The ID of the medicine to view.
     */
    public void viewMedicine(int medicineId) {
        Medicine medicine = medicineController.getMedicine(medicineId);
        if (medicine != null) {
            // Build a string with medicine details
            StringBuilder details = new StringBuilder(
                "ID: " + medicine.getId() +
                "\nName: " + medicine.getName() +
                "\nPrice: " + medicine.getPrice() +
                "\nExpiration: " + medicine.getExpirationDate() +
                "\nStock: " + medicine.getStockQuantity()
            );
            // Append type-specific details
            if (medicine instanceof MedicineLiquid) {
                details.append("\nVolume: ").append(((MedicineLiquid) medicine).getVolume()).append("ml");
            } else if (medicine instanceof MedicinePills) {
                details.append("\nPills: ").append(((MedicinePills) medicine).gettPillCount());
            }
            // Display details in an information alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Medicine Details");
            alert.setHeaderText("Medicine Found");
            alert.setContentText(details.toString());
            alert.showAndWait();
        } else {
            // Show error if medicine is not found
            showError("No medicine found with the given ID.");
        }
    }

    /**
     * Gets the root layout of the Medicine Control page.
     *
     * @return The BorderPane root.
     */
    public BorderPane getRoot() {
        return root;
    }
}
