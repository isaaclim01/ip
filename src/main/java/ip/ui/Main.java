package ip.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import ip.Squiddy;
import ip.exceptions.FileCorruptedException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Squiddy using FXML.
 */
public class Main extends Application {

    private Squiddy squiddy = new Squiddy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            squiddy.setIsExit(false);
            //The error handling does not work for this line
            squiddy.start();
            fxmlLoader.<MainWindow>getController().setSquiddy(squiddy);  // inject the Squiddy instance
            stage.show();
        } catch (FileNotFoundException | FileCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




