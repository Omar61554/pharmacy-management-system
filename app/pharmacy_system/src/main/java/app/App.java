package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        // Create the UI components
        VBox container = new VBox(10); // VBox with spacing of 10
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginButton = new Button("Login");

        // Add functionality to the login button
        loginButton.setOnAction(event -> {
            String enteredPassword = passwordField.getText();
            String correctPassword = "admin123"; // Replace with your desired password

            if (enteredPassword.equals(correctPassword)) {
                showPrimaryScene(stage);
            } else {
                // Show an alert if the password is incorrect
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Incorrect Password");
                alert.setContentText("Please enter the correct password.");
                alert.showAndWait();
            }
        });

        // Add components to the container
        container.getChildren().addAll(passwordField, loginButton);

        // Set the scene
        scene = new Scene(container, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    private void showPrimaryScene(Stage stage) {
        // Create the primary scene UI
        VBox primaryContainer = new VBox(10);
        primaryContainer.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> stage.setScene(scene)); // Go back to the login scene

        primaryContainer.getChildren().add(backButton);

        Scene primaryScene = new Scene(primaryContainer, 640, 480);
        stage.setScene(primaryScene);
        stage.setTitle("Primary Page");
    }

    public static void main(String[] args) {
        launch();
    }
}