package app;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import controller.MedicineController;
import model.Medicine;
import dao.MedicineDAO;

public class MedicineControlPage {

    private BorderPane root;

    public MedicineControlPage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showAdminMainPage());

        HBox backBox = new HBox(backButton);
        backBox.setAlignment(Pos.BOTTOM_RIGHT);
        backBox.setStyle("-fx-padding: 10;");

        root.setBottom(backBox);
        root.setCenter(container);
    }

    public BorderPane getRoot() {
        return root;
    }
    
}
