package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        scene = new Scene(new BorderPane(), 640, 480);
        primaryStage.setScene(scene);
        showStartPage();
        primaryStage.show();
    }

    public static void showStartPage() {
        StartPage startPage = new StartPage();
        scene.setRoot(startPage.getRoot());
        primaryStage.setTitle("Start Page");
    }

    public static void showLoginPage() {
        AdminPage adminPage = new AdminPage();
        scene.setRoot(adminPage.getLoginRoot());
        primaryStage.setTitle("Login Page");
    }

    public static void showAdminMainPage() {
        AdminPage adminPage = new AdminPage();
        scene.setRoot(adminPage.getMainRoot());
        primaryStage.setTitle("Admin Page");
    }

    public static void showServicePage() {
        ServicePage servicePage = new ServicePage();
        scene.setRoot(servicePage.getRoot());
        primaryStage.setTitle("Service Page");
    }

    public static void showMedicineControlPage() {
        MedicineControlPage medicineControlPage = new MedicineControlPage();
        scene.setRoot(medicineControlPage.getRoot());
        primaryStage.setTitle("Medicine Control Page");
    }

    public static void main(String[] args) {
        launch();
    }
}