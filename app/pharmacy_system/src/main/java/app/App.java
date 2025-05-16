package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene; // Single scene for all pages
    private static Stage primaryStage; // Reference to the primary stage

@Override
public void start(Stage stage) {
    primaryStage = stage;
    // Create the initial scene with an empty root
    scene = new Scene(new BorderPane(), 640, 480);
    primaryStage.setScene(scene);
    showStartPage();
    primaryStage.show();
}


     private void showStartPage() {
        // Create the start page
        BorderPane startPage = new BorderPane();
        startPage.setTop(createLogo(400, "center")); // Add the logo to the top-left

        VBox buttonContainer = new VBox(10);
        buttonContainer.setStyle("-fx-alignment: center; -fx-padding: 10;");

        Button adminButton = new Button("Admin");
        Button serviceButton = new Button("Service");

        adminButton.setOnAction(event -> showLoginPage());
        serviceButton.setOnAction(event -> showServicePage());

        buttonContainer.getChildren().addAll(adminButton, serviceButton);
        startPage.setCenter(buttonContainer);

        updateScene(startPage, "Start Page");
    }

    private void showLoginPage() {
        // Create the login page
        BorderPane loginPage = new BorderPane();

        // Center the logo on the login page
        VBox logoContainer = new VBox();
        logoContainer.setStyle("-fx-alignment: center; -fx-padding: 10;"); // Reduced padding
        logoContainer.getChildren().add(createLogo(200, "center")); // Centered logo with size 200
        loginPage.setTop(logoContainer);

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        // Define the login action
        Runnable loginAction = () -> {
            String enteredPassword = passwordField.getText();
            String correctPassword = "admin123";

            if (enteredPassword.equals(correctPassword)) {
                showAdminPage();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Incorrect Password");
                alert.setContentText("Please enter the correct password.");
                alert.showAndWait();
            }
        };

        // Add functionality to the login button
        loginButton.setOnAction(event -> loginAction.run());

        // Add functionality to the password field for Enter key
        passwordField.setOnAction(event -> loginAction.run());

        // Add functionality to the back button
        backButton.setOnAction(event -> showStartPage()); // Go back to the start page

        container.getChildren().addAll(passwordField, loginButton, backButton);
        loginPage.setCenter(container);

        updateScene(loginPage, "Login Page");
    }

    private void showAdminPage() {
        // Create the primary page
        BorderPane AdminPage = new BorderPane();
        AdminPage.setTop(createLogo(100, "left")); // Add the logo to the top-left

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showStartPage()); // Go back to the start page

        container.getChildren().add(backButton);
        AdminPage.setCenter(container);

        updateScene(AdminPage, "Primary Page");
    }

    private void showServicePage() {
        // Create the service page
        BorderPane servicePage = new BorderPane();
        servicePage.setTop(createLogo(100, "left")); // Add the logo to the top-left

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> showStartPage()); // Go back to the start page

        container.getChildren().add(backButton);
        servicePage.setCenter(container);

        updateScene(servicePage, "Service Page");
    }

   

    private void updateScene(BorderPane root, String title) {
        // Update the scene's root and the window title
        scene.setRoot(root);
        primaryStage.setTitle(title);
    }

    private ImageView createLogo(double width, String alignment) {
        // Create the logo
        Image image = new Image(App.class.getResourceAsStream("Dark_Turquoise_Simple_Medicine_Negative_Space_Logo-removebg-preview.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width); // Set the width of the logo
        imageView.setPreserveRatio(true);

        // Set alignment based on the parameter
        if (alignment.equalsIgnoreCase("center")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.CENTER);
        } else if (alignment.equalsIgnoreCase("left")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.TOP_LEFT);
        } else if (alignment.equalsIgnoreCase("right")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.TOP_RIGHT);
        }

        return imageView;
    }

    public static void main(String[] args) {
        launch();
    }
}