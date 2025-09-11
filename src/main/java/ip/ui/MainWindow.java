package ip.ui;

import ip.Squiddy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DefaultUser.jpg"));
    private Image squiddyImage = new Image(this.getClass().getResourceAsStream("/images/Squiddy.jpg"));

    private Squiddy squiddy = new Squiddy();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSquiddy(Squiddy s) {
        squiddy = s;
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String squiddyText = squiddy.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getSquiddyDialog(squiddyText, squiddyImage)
        );
        userInput.clear();
        if (squiddy.getIsExit()) {
            Platform.exit();
        }
    }
}
