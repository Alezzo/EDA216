package krusty.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.gui.search.SearchBottom;
import krusty.gui.search.SearchBox;
import krusty.gui.search.SearchResults;

public class PalletListTab extends Tab {

	public PalletListTab(PalletController palletController, CookieController cookieController) {

		BorderPane pane = new BorderPane();
		SearchBox searchBox = new SearchBox(palletController, cookieController);

		pane.setTop(searchBox);
		BorderPane.setMargin(searchBox, new Insets(0, 0, 5, 0));

		pane.setCenter(new SearchResults(palletController, cookieController));
		pane.setBottom(new SearchBottom(palletController));

		this.setText("Pallets");
		this.setContent(pane);
	}
}
