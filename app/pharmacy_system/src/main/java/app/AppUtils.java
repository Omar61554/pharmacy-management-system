package app;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Utility class for common application functions, such as creating UI elements.
 */
public class AppUtils {

    /**
     * Creates an ImageView containing the application logo.
     *
     * @param size The desired size (width and height) of the logo.
     * @param alignment The alignment of the logo within its container ("left", "center", "right").
     * @return An HBox containing the ImageView of the logo, aligned as specified.
     */
    public static HBox createLogo(int size, String alignment) {
        // Load the logo image from the resources folder
        Image logoImage = new Image(App.class.getResourceAsStream("Dark_Turquoise_Simple_Medicine_Negative_Space_Logo-removebg-preview.png"));
        // Create an ImageView to display the image
        ImageView logoView = new ImageView(logoImage);
        // Set the desired size for the ImageView
        logoView.setFitWidth(size);
        logoView.setFitHeight(size);
        // Preserve the aspect ratio of the image
        logoView.setPreserveRatio(true);

        // Create an HBox to hold the logo and control its alignment
        HBox logoBox = new HBox(logoView);

        // Set the alignment of the HBox based on the input string
        switch (alignment.toLowerCase()) {
            case "left":
                logoBox.setAlignment(Pos.TOP_LEFT);
                break;
            case "center":
                logoBox.setAlignment(Pos.TOP_CENTER);
                break;
            case "right":
                logoBox.setAlignment(Pos.TOP_RIGHT);
                break;
            default:
                // Default to left alignment if the string is not recognized
                logoBox.setAlignment(Pos.TOP_LEFT);
                break;
        }

        // Return the HBox containing the logo
        return logoBox;
    }
}
