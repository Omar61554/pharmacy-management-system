package app;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;


public class OrdersPage {

    private BorderPane root;

    public OrdersPage() {
        root = new BorderPane();
        root.setTop(AppUtils.createLogo(100, "left"));

        VBox container = new VBox(20);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showAdminMainPage());

        container.getChildren().addAll(backButton);
        root.setCenter(container);
    }

    public BorderPane getRoot() {
        return root;
    }
    
}
