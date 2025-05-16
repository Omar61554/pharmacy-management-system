package app;

import controller.MedicineController;
import dao.MedicineDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * The main application class for the Pharmacy System.
 * This class extends JavaFX's Application and manages the primary stage and scene,
 * handling navigation between different pages of the application.
 */
public class App extends Application {

    // The main scene of the application
    private static Scene scene;
    // The primary stage of the application
    private static Stage primaryStage;
    // Data Access Object for Medicine, initialized as a static final field
    public static final MedicineDAO medicineDAO = new MedicineDAO();
    // Controller for Medicine operations, initialized with the MedicineDAO
    public static final MedicineController medicineController = new MedicineController(medicineDAO);

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              the primary stage.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        // Initialize the scene with a BorderPane as the root layout and set initial dimensions
        scene = new Scene(new BorderPane(), 640, 480);
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        // Show the initial start page
        showStartPage();
        // Display the primary stage
        primaryStage.show();
    }

    /**
     * Displays the Start Page of the application.
     * Sets the root of the current scene to the StartPage's root and updates the stage title.
     */
    public static void showStartPage() {
        StartPage startPage = new StartPage();
        scene.setRoot(startPage.getRoot());
        primaryStage.setTitle("Start Page");
    }

    /**
     * Displays the Admin Login Page.
     * Sets the root of the current scene to the AdminPage's login root and updates the stage title.
     */
    public static void showLoginPage() {
        AdminPage adminPage = new AdminPage();
        scene.setRoot(adminPage.getLoginRoot());
        primaryStage.setTitle("Login Page");
    }

    /**
     * Displays the main Admin Control Page.
     * Sets the root of the current scene to the AdminPage's main root and updates the stage title.
     */
    public static void showAdminMainPage() {
        AdminPage adminPage = new AdminPage();
        scene.setRoot(adminPage.getMainRoot());
        primaryStage.setTitle("Admin Page");
    }

    /**
     * Displays the Service Page.
     * Creates a new ServicePage, refreshes its content, sets it as the scene root, and updates the stage title.
     */
    public static void showServicePage() {
        ServicePage servicePage = new ServicePage();
        servicePage.refresh(); // Refresh content before showing
        scene.setRoot(servicePage.getRoot());
        primaryStage.setTitle("Service Page");
    }

    /**
     * Displays the Medicine Control Page.
     * Creates a new MedicineControlPage, sets it as the scene root, and updates the stage title.
     */
    public static void showMedicineControlPage() {
        MedicineControlPage medicineControlPage = new MedicineControlPage();
        scene.setRoot(medicineControlPage.getRoot());
        primaryStage.setTitle("Medicine Control Page");
    }

    /**
     * Displays the Pharmacy Workers Page.
     * Creates a new PharmacyWorkersPage, sets it as the scene root, and updates the stage title.
     */
    public static void showPharmacyWorkersPage() {
        PharmacyWorkersPage workersPage = new PharmacyWorkersPage();
        scene.setRoot(workersPage.getRoot());
        primaryStage.setTitle("Pharmacy Workers");
    }

    /**
     * Displays the Orders Page.
     * Creates a new OrdersPage, sets it as the scene root, and updates the stage title.
     */
    public static void showOrdersPage() {
    OrdersPage ordersPage = new OrdersPage();
    scene.setRoot(ordersPage.getRoot());
    primaryStage.setTitle("Orders");
    }

    /**
     * The main method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application is launched
     * as a regular Java application, e.g., in IDEs.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
