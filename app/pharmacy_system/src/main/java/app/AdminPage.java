package app;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents the Admin page of the pharmacy system application.
 * This class handles both the admin login interface and the main admin control panel.
 */
public class AdminPage {
    // Root layout for the admin login page
    private BorderPane loginRoot;
    // Root layout for the main admin control page
    private BorderPane mainRoot;

    /**
     * Constructs a new AdminPage.
     * Initializes the UI components for both the login page and the main admin page.
     */
    public AdminPage() {
        // --- Admin Login Page Setup ---
        loginRoot = new BorderPane();

        // Container for the logo at the top of the login page
        VBox logoContainer = new VBox();
        logoContainer.setStyle("-fx-alignment: center; -fx-padding: 10;");
        // Add the application logo
        logoContainer.getChildren().add(AppUtils.createLogo(200, "center"));
        loginRoot.setTop(logoContainer);

        // Container for login elements (password field and buttons)
        VBox container = new VBox(10); // 10 pixels spacing between elements
        container.setStyle("-fx-alignment: center; -fx-padding: 20;"); // Center align and add padding

        // Password field for admin login
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(200); // Limit the width of the password field

        // Login button
        Button loginButton = new Button("Login");
        // Back button to return to the start page
        Button backButton = new Button("Back");

        // Define the action to be performed on login attempt
        Runnable loginAction = () -> {
            String enteredPassword = passwordField.getText();
            // Define the correct admin password (hardcoded for this example)
            String correctPassword = "admin123"; // TODO: Implement secure password handling

            // Check if the entered password matches the correct password
            if (enteredPassword.equals(correctPassword)) {
                // If correct, show the main admin page
                App.showAdminMainPage();
            } else {
                // If incorrect, show an error alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Incorrect Password");
                alert.setContentText("Please enter the correct password.");
                alert.showAndWait(); // Show the alert and wait for user interaction
            }
        };

        // Set the action for the login button and password field (pressing Enter)
        loginButton.setOnAction(e -> loginAction.run());
        passwordField.setOnAction(e -> loginAction.run());
        // Set the action for the back button to show the start page
        backButton.setOnAction(e -> App.showStartPage());

        // Add password field and buttons to the container
        container.getChildren().addAll(passwordField, loginButton, backButton);
        // Set the container in the center of the login page layout
        loginRoot.setCenter(container);

        // --- Main Admin Page Setup ---
        mainRoot = new BorderPane();
        // Add a smaller logo to the top-left of the main admin page
        mainRoot.setTop(AppUtils.createLogo(100, "left"));

        // Container for the main admin control buttons
        VBox adminContainer = new VBox(20); // 20 pixels spacing between buttons
        adminContainer.setStyle("-fx-alignment: center; -fx-padding: 20;"); // Center align and add padding

        // Button to navigate to the Medicine Control page
        Button medicineControlBtn = new Button("Medicine Control");
        medicineControlBtn.setOnAction(e -> App.showMedicineControlPage());

        // Button to navigate to the Pharmacy Workers page
        Button workersBtn = new Button("Pharmacy Workers");
        workersBtn.setOnAction(e -> App.showPharmacyWorkersPage());

        // Button to navigate to the Orders page
        Button ordersBtn = new Button("Orders");
        ordersBtn.setOnAction(e -> App.showOrdersPage());

        // Add control buttons to the admin container
        adminContainer.getChildren().addAll(medicineControlBtn, workersBtn, ordersBtn);

        // Back button at the bottom right for the main admin page
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> App.showStartPage()); // Action to return to the start page
        HBox backBoxMain = new HBox(backBtn); // Use HBox to position the button
        backBoxMain.setAlignment(Pos.BOTTOM_RIGHT); // Align the HBox content to the bottom right
        backBoxMain.setStyle("-fx-padding: 10;"); // Add padding around the back button

        // Set the admin container in the center and the back button in the bottom of the main admin page layout
        mainRoot.setCenter(adminContainer);
        mainRoot.setBottom(backBoxMain);
    }

    /**
     * Gets the root layout for the admin login page.
     *
     * @return The BorderPane containing the login UI.
     */
    public BorderPane getLoginRoot() {
        return loginRoot;
    }

    /**
     * Gets the root layout for the main admin control page.
     *
     * @return The BorderPane containing the main admin UI.
     */
    public BorderPane getMainRoot() {
        return mainRoot;
    }
}