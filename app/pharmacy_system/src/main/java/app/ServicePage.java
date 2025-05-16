package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Medicine;
// import service.MedicineService;

// import java.text.SimpleDateFormat;
// import java.util.List;

public class ServicePage {
    private BorderPane root;
    // private MedicineService medicineService;
    private TableView<Medicine> table;
    private ObservableList<Medicine> displayedMedicines;

    public ServicePage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        // medicineService = new MedicineService();

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search medicine by name...");

        Button searchButton = new Button("Search");
        // searchButton.setOnAction(e -> searchMedicine(searchField.getText()));

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        searchBox.setStyle("-fx-alignment: center;");

        // Table setup
        table = new TableView<>();
        configureTable();

        displayedMedicines = FXCollections.observableArrayList();
        table.setItems(displayedMedicines);

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showStartPage());

        VBox container = new VBox(10, searchBox, table, backButton);
        container.setPadding(new Insets(20));
        container.setStyle("-fx-alignment: center;");

        root.setCenter(container);

        // showAllMedicines();
    }

    private void configureTable() {
        TableColumn<Medicine, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Medicine, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()));

        TableColumn<Medicine, Number> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()));

        TableColumn<Medicine, String> expiryCol = new TableColumn<>("Expiry Date");
        // expiryCol.setCellValueFactory(data -> {
        //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //     return new javafx.beans.property.SimpleStringProperty(sdf.format(data.getValue().getExpirationDate()));
        // });

        // TableColumn<Medicine, String> locationCol = new TableColumn<>("Location");
        // locationCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLocation()));

        nameCol.setPrefWidth(150);
        priceCol.setPrefWidth(80);
        stockCol.setPrefWidth(80);
        expiryCol.setPrefWidth(120);
        //locationCol.setPrefWidth(120);

        //table.getColumns().addAll(nameCol, priceCol, stockCol, expiryCol, locationCol);
    }

    // private void searchMedicine(String keyword) {
    //     displayedMedicines.clear();

    //     if (keyword == null || keyword.trim().isEmpty()) {
    //         showAllMedicines();
    //         return;
    //     }

        // List<Medicine> allMeds = medicineService.fetchAllMedicines();
        // for (Medicine med : allMeds) {
        //     if (med.getName().toLowerCase().contains(keyword.toLowerCase())) {
        //         displayedMedicines.add(med);
        //     }
        // }
    // }

    // private void showAllMedicines() {
    //     displayedMedicines.clear();
    //     displayedMedicines.addAll(medicineService.fetchAllMedicines());
    // }

    public BorderPane getRoot() {
        return root;
    }
}
