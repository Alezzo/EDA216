package krusty.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import krusty.Database;
import krusty.controllers.PalletController;
import krusty.gui.search.SearchBox;
import krusty.gui.search.SearchResults;

public class PalletListTab extends Tab {

	public PalletListTab(Database db) {

		BorderPane pane = new BorderPane();
		PalletController palletController = new PalletController(db);
		SearchBox searchBox = new SearchBox(db, palletController);

		pane.setTop(searchBox);
		BorderPane.setMargin(searchBox, new Insets(0, 0, 5, 0));

		pane.setCenter(new SearchResults(palletController));

		this.setText("Pallets");
		this.setContent(pane);
	}


}
