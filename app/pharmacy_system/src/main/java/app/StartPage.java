package app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StartPage {
    private BorderPane root;

    public StartPage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(300, "center"));

        VBox buttonContainer = new VBox(10);
        buttonContainer.setStyle("-fx-alignment: center; -fx-padding: 10;");

        Button adminButton = new Button("Admin");
        Button serviceButton = new Button("Service");

        adminButton.setOnAction(e -> App.showLoginPage());
        serviceButton.setOnAction(e -> App.showServicePage());

        buttonContainer.getChildren().addAll(adminButton, serviceButton);
        root.setCenter(buttonContainer);
    }

    public BorderPane getRoot() {
        return root;
    }
}