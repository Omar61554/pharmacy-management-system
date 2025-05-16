package app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * Represents the Orders page in the pharmacy system's admin interface.
 * This page currently provides a way to navigate back to the admin main page.
 * (Further functionality for viewing/managing orders would be added here).
 */
public class OrdersPage {

    // The root layout for this page
    private BorderPane root;

    /**
     * Constructs a new OrdersPage.
     * Initializes the UI elements for the orders page.
     */
    public OrdersPage() {
        // Initialize the root BorderPane
        root = new BorderPane();
        // Add the application logo to the top-left of the page
        root.setTop(AppUtils.createLogo(100, "left"));

        // Create a VBox container to hold elements in the center
        VBox container = new VBox(20); // 20 pixels spacing between elements
        // Style the container to center its content and add padding
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Create a button to navigate back to the admin main page
        Button backButton = new Button("Back");
        // Set the action for the back button: call the showAdminMainPage method in the App class
        backButton.setOnAction(e -> App.showAdminMainPage());

        // Add the back button to the container
        container.getChildren().addAll(backButton);
        // Set the container in the center of the root layout
        root.setCenter(container);
    }

    /**
     * Gets the root layout of the Orders page.
     *
     * @return The BorderPane root.
     */
    public BorderPane getRoot() {
        return root;
    }

}
