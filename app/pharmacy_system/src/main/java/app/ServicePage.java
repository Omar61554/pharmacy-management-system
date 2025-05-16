package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Medicine;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

public class ServicePage {
    private BorderPane root;
    private TableView<Medicine> table;
    private ObservableList<Medicine> displayedMedicines;

    public ServicePage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search medicine by name...");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchMedicine(searchField.getText()));

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-alignment: center;");

        // Table setup
        table = new TableView<>();
        configureTable();

        displayedMedicines = FXCollections.observableArrayList();
        table.setItems(displayedMedicines);

        // Buy button
        Button buyButton = new Button("Buy Selected");
        buyButton.setOnAction(e -> buySelectedMedicine());

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showStartPage());

        VBox container = new VBox(10, searchBox, table, buyButton, backButton);
        container.setPadding(new Insets(20));
        container.setStyle("-fx-alignment: center;");

        root.setCenter(container);

        // Load medicines initially
        showAllMedicines();
    }

    private void configureTable() {
        TableColumn<Medicine, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Medicine, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));

        TableColumn<Medicine, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStockQuantity()));

        TableColumn<Medicine, String> expiryCol = new TableColumn<>("Expiry Date");
        expiryCol.setCellValueFactory(data -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new javafx.beans.property.SimpleStringProperty(sdf.format(data.getValue().getExpirationDate()));
        });

        nameCol.setPrefWidth(150);
        priceCol.setPrefWidth(80);
        stockCol.setPrefWidth(80);
        expiryCol.setPrefWidth(120);

        table.getColumns().addAll(nameCol, priceCol, stockCol, expiryCol);
    }

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

    private void showAllMedicines() {
        displayedMedicines.clear();
        List<Medicine> allMeds = App.medicineController.listAllMedicines();
        allMeds.sort(Comparator.comparing(Medicine::getName, String.CASE_INSENSITIVE_ORDER));
        displayedMedicines.addAll(allMeds);
    }

    // Call this method whenever the page is shown to refresh data
    public void refresh() {
        showAllMedicines();
    }

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

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Operation Failed");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public BorderPane getRoot() {
        return root;
    }
}