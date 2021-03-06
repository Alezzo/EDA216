package krusty;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import krusty.gui.KrustyGUI;

import javax.swing.*;
import java.util.Locale;

/**
 * KrustyKookies is the main class for the movie ticket booking
 * application. It creates a database object and the GUI to
 * interface to the database.
 */
public class KrustyKookies extends Application {

	public static Stage primaryStage;

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "GB"));

        launch();
        //new KrustyGUI(db);
    }

	@Override
	public void start(Stage stage) throws Exception {
		KrustyKookies.primaryStage = stage;

		Database db = new Database();

		if (!db.openConnection("db129", "kmg335rz")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something wrong");
            alert.setContentText("Unable to connect to database");

            alert.showAndWait();

			System.exit(0);
		}

		KrustyGUI gui = new KrustyGUI(db, stage);
	}
}
