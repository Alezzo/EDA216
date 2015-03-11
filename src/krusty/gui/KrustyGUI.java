package krusty.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import krusty.Database;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;

public class KrustyGUI {


	private Database db;
	private Stage stage;

	public KrustyGUI(Database db, Stage stage) {
		this.db = db;
		this.stage = stage;

		stage.setTitle("Krusty Kookies");

		Group root = new Group();
		Scene scene = new Scene(root, 700, 500, Color.WHITE);

		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		BorderPane mainPane = new BorderPane();

		CookieController cookieController = new CookieController(db);
		PalletController palletController = new PalletController(db);

		//Create Tabs
		tabPane.getTabs().add(new PalletListTab(palletController, cookieController));
		tabPane.getTabs().add(new ProductionTab(palletController, cookieController));

		mainPane.setCenter(tabPane);

		mainPane.prefHeightProperty().bind(scene.heightProperty());
		mainPane.prefWidthProperty().bind(scene.widthProperty());

		root.getChildren().add(mainPane);
		stage.setScene(scene);

		stage.show();
	}

}
