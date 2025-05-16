package app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Represents the Pharmacy Workers page in the pharmacy system's admin interface.
 * This page provides navigation options related to managing pharmacy workers.
 */
public class PharmacyWorkersPage {

    // The root layout for this page
    private BorderPane root;

    /**
     * Constructs a new PharmacyWorkersPage.
     * Initializes the UI elements and sets up event handlers for the control buttons.
     */
    public PharmacyWorkersPage() {
        root = new BorderPane();
        // Add the application logo to the top-left
        root.setTop(AppUtils.createLogo(100, "left"));

        // Container for the main control buttons related to workers
        VBox container = new VBox(20); // 20 pixels spacing between buttons
        container.setStyle("-fx-alignment: center; -fx-padding: 20;"); // Center align and add padding

        // Button to navigate back to the admin main page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> App.showAdminMainPage()); // Set action to show admin main page

        // Add the back button to the container
        container.getChildren().addAll(backButton);
        // Set the container in the center of the layout
        root.setCenter(container);
    }

    /**
     * Gets the root layout of the Pharmacy Workers page.
     *
     * @return The BorderPane root.
     */
    public BorderPane getRoot() {
        return root;
    }

}
