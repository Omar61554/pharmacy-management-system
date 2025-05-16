package app;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.MedicineController;
import model.Medicine;
import dao.MedicineDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicineControlPage {

    private BorderPane root;
    private MedicineController medicineController = new MedicineController(new MedicineDAO());

    public MedicineControlPage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        VBox container = new VBox(15);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button addButton = new Button("Add Medicine");
        Button updateButton = new Button("Update Medicine");
        Button deleteButton = new Button("Delete Medicine");
        Button viewButton = new Button("View Medicine");

        addButton.setOnAction(e -> showAddDialog());
        updateButton.setOnAction(e -> showUpdateDialog());
        deleteButton.setOnAction(e -> showDeleteDialog());
        viewButton.setOnAction(e -> showViewDialog());

        container.getChildren().addAll(addButton, updateButton, deleteButton, viewButton);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showAdminMainPage());
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setStyle("-fx-padding: 10;");

        root.setCenter(container);
        root.setBottom(backBox);
    }

   // Replace your showAddDialog() with this:
private void showAddDialog() {
    Dialog<Medicine> dialog = new Dialog<>();
    dialog.setTitle("Add Medicine");
    dialog.setHeaderText("Enter medicine details:");

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

    VBox vbox = new VBox(10, idLabel, idField, nameLabel, nameField, priceLabel, priceField, expLabel, expField, stockLabel, stockField);
    dialog.getDialogPane().setContent(vbox);

    ButtonType addType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addType, ButtonType.CANCEL);

    Button addBtn = (Button) dialog.getDialogPane().lookupButton(addType);
    addBtn.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
        // Check for empty fields
        if (idField.getText().trim().isEmpty() ||
            nameField.getText().trim().isEmpty() ||
            priceField.getText().trim().isEmpty() ||
            expField.getText().trim().isEmpty() ||
            stockField.getText().trim().isEmpty()) {
            showError("All fields must be filled.");
            event.consume();
            return;
        }
        // Check data types and date validity
        try {
            Integer.parseInt(idField.getText());
        } catch (NumberFormatException e) {
            showError("ID must be an integer.");
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
            if (exp.before(new Date())) {
                showError("Expiration date cannot be before today.");
                event.consume();
            }
        } catch (Exception ex) {
            showError("Invalid date format. Please use yyyy-MM-dd.");
            event.consume();
        }
    });

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addType) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(expField.getText());
                int stock = Integer.parseInt(stockField.getText());
                return new Medicine(id, name, price, exp, stock);
            } catch (Exception ex) {
                // Already handled by event filter
            }
        }
        return null;
    });

    dialog.showAndWait().ifPresent(this::addMedicine);
}

    

    private void showUpdateDialog() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Update Medicine");
        idDialog.setHeaderText("Enter Medicine ID to update:");
        idDialog.setContentText("ID:");
        idDialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                Medicine oldMed = medicineController.getMedicine(id);
                if (oldMed == null) {
                    showError("Medicine not found.");
                    return;
                }
                Dialog<Medicine> dialog = new Dialog<>();
                dialog.setTitle("Update Medicine");
                dialog.setHeaderText("Edit medicine details:");

                TextField nameField = new TextField(oldMed.getName());
                TextField priceField = new TextField(String.valueOf(oldMed.getPrice()));
                TextField expField = new TextField(new SimpleDateFormat("yyyy-MM-dd").format(oldMed.getExpirationDate()));
                TextField stockField = new TextField(String.valueOf(oldMed.getStockQuantity()));

                VBox vbox = new VBox(10,
                        new Label("Name:"), nameField,
                        new Label("Price:"), priceField,
                        new Label("Expiration Date (yyyy-MM-dd):"), expField,
                        new Label("Stock Quantity:"), stockField);
                dialog.getDialogPane().setContent(vbox);

                ButtonType updateType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(updateType, ButtonType.CANCEL);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == updateType) {
                        try {
                            String name = nameField.getText();
                            double price = Double.parseDouble(priceField.getText());
                            Date exp = new SimpleDateFormat("yyyy-MM-dd").parse(expField.getText());
                            int stock = Integer.parseInt(stockField.getText());
                            if (exp.before(new Date())) {
                                showError("Expiration date cannot be before today.");
                                return null;
                            }
                            return new Medicine(id, name, price, exp, stock);
                        } catch (Exception ex) {
                            showError("Invalid input. Please check your entries.");
                        }
                    }
                    return null;
                });

                dialog.showAndWait().ifPresent(this::updateMedicine);

            } catch (NumberFormatException ex) {
                showError("Invalid ID.");
            }
        });
    }

    private void showDeleteDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Medicine");
        dialog.setHeaderText("Enter Medicine ID to delete:");
        dialog.setContentText("ID:");
        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                deleteMedicine(id);
            } catch (NumberFormatException ex) {
                showError("Invalid ID.");
            }
        });
    }

    private void showViewDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("View Medicine");
        dialog.setHeaderText("Enter Medicine ID to view:");
        dialog.setContentText("ID:");
        dialog.showAndWait().ifPresent(idStr -> {
            try {
                int id = Integer.parseInt(idStr);
                viewMedicine(id);
            } catch (NumberFormatException ex) {
                showError("Invalid ID.");
            }
        });
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void addMedicine(Medicine medicine) {
        if (medicine == null) return;
        boolean success = medicineController.addMedicine(medicine);
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Added");
            alert.setContentText("The medicine has been added successfully.");
            alert.showAndWait();
        } else {
            showError("There was an error adding the medicine. Please try again.");
        }
    }

    public void updateMedicine(Medicine medicine) {
        if (medicine == null) return;
        boolean success = medicineController.updateMedicine(medicine);
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Updated");
            alert.setContentText("The medicine has been updated successfully.");
            alert.showAndWait();
        } else {
            showError("There was an error updating the medicine. Please try again.");
        }
    }

    public void deleteMedicine(int medicineId) {
        boolean success = medicineController.deleteMedicine(medicineId);
        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Medicine Deleted");
            alert.setContentText("The medicine has been deleted successfully.");
            alert.showAndWait();
        } else {
            showError("There was an error deleting the medicine. Please try again.");
        }
    }

    public void viewMedicine(int medicineId) {
        Medicine medicine = medicineController.getMedicine(medicineId);
        if (medicine != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Medicine Details");
            alert.setHeaderText("Medicine Found");
            alert.setContentText(
                    "ID: " + medicine.getId() +
                    "\nName: " + medicine.getName() +
                    "\nPrice: " + medicine.getPrice() +
                    "\nExpiration: " + medicine.getExpirationDate() +
                    "\nStock: " + medicine.getStockQuantity()
            );
            alert.showAndWait();
        } else {
            showError("No medicine found with the given ID.");
        }
    }

    public BorderPane getRoot() {
        return root;
    }
}