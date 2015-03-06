package krusty.gui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import krusty.Database;
import krusty.controllers.PalletController;

public class PalletListPane extends JFXPanel {

	private Database db;

	private BorderPane pane;

	public PalletListPane(Database db) {
		this.db = db;

		init();
	}

	private void init() {

		pane = new BorderPane();

		PalletController palletController = new PalletController(db);

		pane.setTop(new SearchBox(db, palletController));

		pane.setCenter(new SearchResults(palletController));

		Platform.runLater(this::createScene);
	}

	private void createScene() {
		Scene scene = new Scene(pane);
		setScene(scene);
	}


}
