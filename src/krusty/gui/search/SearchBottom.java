package krusty.gui.search;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import krusty.controllers.PalletController;

public class SearchBottom extends BorderPane {

	private PalletController palletController;

	public SearchBottom(PalletController palletController) {
		this.palletController = palletController;

		this.setPadding(new Insets(5, 5, 5, 5));

		Button button = new Button("Block all search results");
		button.setStyle("-fx-base: #f3622d;");
		button.setOnAction(new BlockAllButtonActionHandler());

		this.setRight(button);
	}


	private class BlockAllButtonActionHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			palletController.blockAll();
		}
	}
}
