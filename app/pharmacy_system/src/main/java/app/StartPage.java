package app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Represents the initial start page of the pharmacy system application.
 * This page provides options to navigate to the Admin Login page or the Service Page.
 */
public class StartPage {

    // The root layout for this page
    private BorderPane root;

    /**
     * Constructs a new StartPage.
     * Initializes the UI elements and sets up event handlers for the navigation buttons.
     */
    public StartPage() {
        // Initialize the root BorderPane
        root = new BorderPane();
        // Add the application logo to the top-left of the page
        root.setTop(AppUtils.createLogo(300, "center"));

        // Create a VBox container to hold the navigation buttons
        VBox container = new VBox(20); // 20 pixels spacing between buttons
        // Style the container to center its content and add padding
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Create the button to navigate to the Admin Login page
        Button adminLoginButton = new Button("Admin Login");
        // Set the action for the admin login button: call the showLoginPage method in the App class
        adminLoginButton.setOnAction(e -> App.showLoginPage());

        // Create the button to navigate to the Service Page
        Button servicePageButton = new Button("Service Page");
        // Set the action for the service page button: call the showServicePage method in the App class
        servicePageButton.setOnAction(e -> App.showServicePage());

        // Add the buttons to the container
        container.getChildren().addAll(adminLoginButton, servicePageButton);
        // Set the container in the center of the root layout
        root.setCenter(container);
    }

    /**
     * Gets the root layout of the Start page.
     *
     * @return The BorderPane root.
     */
    public BorderPane getRoot() {
        return root;
    }
}
