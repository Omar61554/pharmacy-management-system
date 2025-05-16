package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Medicine;
import model.MedicineLiquid;
import model.MedicinePills;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the Service Page for customers to view and buy medicines.
 * Displays a searchable, sortable table of medicines with type information.
 */
public class ServicePage {
    // The root layout for this page
    private BorderPane root;
    // Table to display medicines
    private TableView<Medicine> table;
    // Observable list backing the table
    private ObservableList<Medicine> displayedMedicines;

    /**
     * Constructs the ServicePage, sets up the UI and loads medicines.
     */
    public ServicePage() {
        root = new BorderPane();

        // --- Logo at top left ---
        HBox logoBox = new HBox(AppUtils.createLogo(200, "center"));
        logoBox.setAlignment(Pos.TOP_CENTER);
        logoBox.setPadding(new Insets(0, 0, 0, 0));
        root.setTop(logoBox);

        // --- Search bar setup ---
        TextField searchField = new TextField();
        searchField.setPromptText("Search medicine by name...");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchMedicine(searchField.getText()));

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        searchBox.setAlignment(Pos.CENTER);

        // --- Table and data setup ---
        table = new TableView<>();
        displayedMedicines = FXCollections.observableArrayList();
        table.setItems(displayedMedicines);
        configureTable();

        // --- Buy button setup ---
        Button buyButton = new Button("Buy Selected");
        buyButton.setOnAction(e -> buySelectedMedicine());

        // --- Back button setup ---
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showStartPage());

        // --- Layout container for controls and table ---
        VBox container = new VBox(10, searchBox, table, buyButton, backButton);
        container.setPadding(new Insets(20));
        container.setStyle("-fx-alignment: center;");

        root.setCenter(container);

        // --- Load medicines initially ---
        showAllMedicines();
    }

    /**
     * Configures the columns of the medicine table, including type.
     */
    private void configureTable() {
        // ID Column
        TableColumn<Medicine, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        idCol.setPrefWidth(60);

        // Name Column
        TableColumn<Medicine, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        nameCol.setPrefWidth(150);

        // Price Column
        TableColumn<Medicine, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));
        priceCol.setPrefWidth(80);

        // Stock Quantity Column
        TableColumn<Medicine, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStockQuantity()));
        stockCol.setPrefWidth(80);

        // Expiry Date Column
        TableColumn<Medicine, String> expiryCol = new TableColumn<>("Expiry Date");
        expiryCol.setCellValueFactory(data -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new javafx.beans.property.SimpleStringProperty(sdf.format(data.getValue().getExpirationDate()));
        });
        expiryCol.setPrefWidth(120);

        // Type Column (Liquid or Pills)
        TableColumn<Medicine, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> {
            Medicine med = data.getValue();
            String type = "Unknown";
            if (med instanceof MedicineLiquid) {
                type = "Liquid";
            } else if (med instanceof MedicinePills) {
                type = "Pills";
            }
            return new javafx.beans.property.SimpleStringProperty(type);
        });
        typeCol.setPrefWidth(80);

        // Add all columns to the table
        table.getColumns().clear();
        table.getColumns().addAll(idCol, nameCol, priceCol, stockCol, expiryCol, typeCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Searches for medicines by name and updates the table.
     * @param keyword The search keyword.
     */
    private void searchMedicine(String keyword) {
        displayedMedicines.clear();
        if (keyword == null || keyword.trim().isEmpty()) {
            showAllMedicines();
            return;
        }
        List<Medicine> allMeds = App.medicineController.listAllMedicines();
        allMeds.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER));
        Medicine toSelect = null;
        for (Medicine med : allMeds) {
            if (med.getName() != null && med.getName().toLowerCase().contains(keyword.toLowerCase())) {
                displayedMedicines.add(med);
                if (med.getName().equalsIgnoreCase(keyword)) {
                    toSelect = med;
                }
            }
        }
        if (toSelect != null) {
            table.getSelectionModel().select(toSelect);
        } else if (!displayedMedicines.isEmpty()) {
            table.getSelectionModel().select(0);
        }
    }

    /**
     * Loads and displays all medicines in the table.
     */
    private void showAllMedicines() {
        displayedMedicines.clear();
        List<Medicine> allMeds = App.medicineController.listAllMedicines();
        allMeds.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER));
        displayedMedicines.addAll(allMeds);
    }

    /**
     * Refreshes the table data.
     */
    public void refresh() {
        showAllMedicines();
    }

    /**
     * Handles the buying of the selected medicine.
     * Decreases stock and updates the table.
     */
    private void buySelectedMedicine() {
        Medicine selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a medicine to buy.");
            return;
        }
        if (selected.getStockQuantity() <= 0) {
            showError("This medicine is out of stock.");
            return;
        }
        selected.updateQuantity(-1);
        App.medicineController.updateMedicine(selected);
        showAllMedicines();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have bought 1 unit of " + selected.getName() + ".");
        alert.setHeaderText("Purchase Successful");
        alert.showAndWait();
    }

    /**
     * Shows an error dialog with the given message.
     * @param msg The error message.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Operation Failed");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Returns the root BorderPane for this page.
     * @return The root BorderPane.
     */
    public BorderPane getRoot() {
        return root;
    }
}