package app;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AdminPage {
    private BorderPane loginRoot;
    private BorderPane mainRoot;

    public AdminPage() {
        // Login Page
        loginRoot = new BorderPane();
        VBox logoContainer = new VBox();
        logoContainer.setStyle("-fx-alignment: center; -fx-padding: 10;");
        logoContainer.getChildren().add(AppUtils.createLogo(200, "center"));
        loginRoot.setTop(logoContainer);

        VBox container = new VBox(10);
        container.setStyle("-fx-alignment: center; -fx-padding: 20;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        Runnable loginAction = () -> {
            String enteredPassword = passwordField.getText();
            String correctPassword = "admin123";
            if (enteredPassword.equals(correctPassword)) {
                App.showAdminMainPage();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Incorrect Password");
                alert.setContentText("Please enter the correct password.");
                alert.showAndWait();
            }
        };

        loginButton.setOnAction(e -> loginAction.run());
        passwordField.setOnAction(e -> loginAction.run());
        backButton.setOnAction(e -> App.showStartPage());

        container.getChildren().addAll(passwordField, loginButton, backButton);
        loginRoot.setCenter(container);

        // Main Admin Page
        mainRoot = new BorderPane();
        mainRoot.setTop(AppUtils.createLogo(100, "left"));
        VBox adminContainer = new VBox(10);
        adminContainer.setStyle("-fx-alignment: center; -fx-padding: 20;");
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> App.showStartPage());
        adminContainer.getChildren().add(backBtn);
        mainRoot.setCenter(adminContainer);
    }

    public BorderPane getLoginRoot() {
        return loginRoot;
    }

    public BorderPane getMainRoot() {
        return mainRoot;
    }
}