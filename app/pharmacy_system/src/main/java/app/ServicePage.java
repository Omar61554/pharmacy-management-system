package app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ServicePage {
    private BorderPane root;

    public ServicePage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showStartPage());

        container.getChildren().add(backButton);
        root.setCenter(container);
    }

    public BorderPane getRoot() {
        return root;
    }
}