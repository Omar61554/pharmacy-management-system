package app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AppUtils {
    public static ImageView createLogo(double width, String alignment) {
        Image image = new Image(App.class.getResourceAsStream("Dark_Turquoise_Simple_Medicine_Negative_Space_Logo-removebg-preview.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);

        if (alignment.equalsIgnoreCase("center")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.CENTER);
        } else if (alignment.equalsIgnoreCase("left")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.TOP_LEFT);
        } else if (alignment.equalsIgnoreCase("right")) {
            BorderPane.setAlignment(imageView, javafx.geometry.Pos.TOP_RIGHT);
        }
        return imageView;
    }
}