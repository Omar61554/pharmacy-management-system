package app;

import controller.MedicineController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Medicine;
import model.MedicineLiquid;
import model.MedicinePills;

import java.util.List;

/**
 * Represents the Service page of the pharmacy system.
 * This page allows users (customers) to view available medicines and their details.
 */
public class ServicePage {

    // The root layout for this page
    private BorderPane root;
    // TableView to display the list of medicines
    private TableView<Medicine> medicineTable;
    // ObservableList to hold the data for the TableView
    private ObservableList<Medicine> medicineData;
    // The controller for handling medicine-related business logic
    private MedicineController medicineController = App.medicineController;

    /**
     * Constructs a new ServicePage.
     * Initializes the UI elements, sets up the medicine table, and loads initial data.
     */
    public ServicePage() {
        root = new BorderPane();
        // Add the application logo to the top-left
        root.setTop(AppUtils.createLogo(100, "left"));

        // Container for the main content (table and controls)
        VBox container = new VBox(10); // 10 pixels spacing
        container.setStyle("-fx-padding: 20;"); // Add padding

        // Initialize the TableView
        medicineTable = new TableView<>();
        // Initialize the ObservableList
        medicineData = FXCollections.observableArrayList();
        // Set the data source for the table
        medicineTable.setItems(medicineData);

        // --- Define Table Columns ---

        // ID Column
        TableColumn<Medicine, Integer> idColumn = new TableColumn<>("ID");
        // Associate this column with the "id" property of the Medicine object
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Name Column
        TableColumn<Medicine, String> nameColumn = new TableColumn<>("Name");
        // Associate this column with the "name" property
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Price Column
        TableColumn<Medicine, Double> priceColumn = new TableColumn<>("Price");
        // Associate this column with the "price" property
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Expiration Date Column
        TableColumn<Medicine, String> expColumn = new TableColumn<>("Expiration Date");
        // Use a custom cell value factory to format the Date object
        expColumn.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getExpirationDate();
            // Format the date as a string (e.g., yyyy-MM-dd)
            return new javafx.beans.property.SimpleStringProperty(
                    new java.text.SimpleDateFormat("yyyy-MM-dd").format(date));
        });

        // Stock Quantity Column
        TableColumn<Medicine, Integer> stockColumn = new TableColumn<>("Stock");
        // Associate this column with the "stockQuantity" property
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        // Type Column (Displays "Liquid" or "Pills")
        TableColumn<Medicine, String> typeColumn = new TableColumn<>("Type");
        // Use a custom cell value factory to determine the type string
        typeColumn.setCellValueFactory(cellData -> {
            Medicine medicine = cellData.getValue();
            if (medicine instanceof MedicineLiquid) {
                return new javafx.beans.property.SimpleStringProperty("Liquid");
            } else if (medicine instanceof MedicinePills) {
                return new javafx.beans.property.SimpleStringProperty("Pills");
            }
            return new javafx.beans.property.SimpleStringProperty("Unknown"); // Fallback
        });

        // Specific Detail Column (Displays Volume for Liquid, Pill Count for Pills)
        TableColumn<Medicine, String> specificColumn = new TableColumn<>("Specific Detail");
        // Use a custom cell value factory to get the type-specific detail
        specificColumn.setCellValueFactory(cellData -> {
            Medicine medicine = cellData.getValue();
            if (medicine instanceof MedicineLiquid) {
                // Display volume for liquid medicine
                return new javafx.beans.property.SimpleStringProperty(
                        ((MedicineLiquid) medicine).getVolume() + " ml");
            } else if (medicine instanceof MedicinePills) {
                // Display pill count for pill medicine
                return new javafx.beans.property.SimpleStringProperty(
                        ((MedicinePills) medicine).gettPillCount() + " pills");
            }
            return new javafx.beans.property.SimpleStringProperty(""); // Empty string for unknown types
        });


        // Add all defined columns to the table
        medicineTable.getColumns().addAll(idColumn, nameColumn, priceColumn, expColumn, stockColumn, typeColumn, specificColumn);

        // Add the table to the main container
        container.getChildren().add(medicineTable);

        // Back button to return to the start page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showStartPage()); // Set action to show start page

        // HBox to position the back button at the bottom right
        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setStyle("-fx-padding: 10;"); // Add padding around the back button

        // Set the main container in the center and the back button at the bottom
        root.setCenter(container);
        root.setBottom(backBox);

        // Load initial data into the table
        loadMedicineData();
    }

    /**
     * Loads medicine data from the controller and populates the TableView.
     */
    private void loadMedicineData() {
        // Clear existing data in the observable list
        medicineData.clear();
        // Get the list of all medicines from the controller
        List<Medicine> medicines = medicineController.listAllMedicines();
        // Add all retrieved medicines to the observable list
        medicineData.addAll(medicines);
    }

    /**
     * Refreshes the data displayed in the medicine table.
     * This method should be called when the ServicePage is shown to ensure the data is up-to-date.
     */
    public void refresh() {
        loadMedicineData();
    }

    /**
     * Gets the root layout of the Service page.
     *
     * @return The BorderPane root.
     */
    public BorderPane getRoot() {
        return root;
    }
}
