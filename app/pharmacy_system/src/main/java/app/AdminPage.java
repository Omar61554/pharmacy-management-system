package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminPage {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
