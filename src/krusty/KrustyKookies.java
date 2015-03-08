package krusty;

import javafx.application.Application;
import javafx.stage.Stage;
import krusty.gui.KrustyGUI;

import javax.swing.*;

/**
 * KrustyKookies is the main class for the movie ticket booking
 * application. It creates a database object and the GUI to
 * interface to the database.
 */
public class KrustyKookies extends Application {

    public static void main(String[] args) {
        launch();
        //new KrustyGUI(db);
    }

	@Override
	public void start(Stage stage) throws Exception {
		Database db = new Database();

		if (!db.openConnection("db129", "kmg335rz")) {
			JOptionPane.showMessageDialog(null, "Unable to connect to the database!");
			System.exit(0);
		}

		KrustyGUI gui = new KrustyGUI(db, stage);
	}
}
