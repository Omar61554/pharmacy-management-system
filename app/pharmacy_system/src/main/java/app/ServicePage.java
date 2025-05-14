package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class ServicePage {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}