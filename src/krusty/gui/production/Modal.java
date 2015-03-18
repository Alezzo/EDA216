package krusty.gui.production;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import krusty.KrustyKookies;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.models.Pallet;

public class Modal {

	private Stage stage;

	public Modal(PalletController palletController, CookieController cookieController, Pallet pallet) {

		BorderPane pane = new BorderPane();

		EditForm form = new EditForm(palletController, cookieController, pallet, this);

		BorderPane.setMargin(form, new Insets(10, 10, 10, 10));

		pane.setCenter(form);

		// Create the dialog Stage.
		stage = new Stage();
		stage.setTitle("Edit pallet");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(KrustyKookies.primaryStage);
		Scene scene = new Scene(pane, 300, 240);
		stage.setScene(scene);

		// Show the dialog and wait until the user closes it
		stage.showAndWait();
	}

	public void closeModal() {
		stage.close();
	}

}
