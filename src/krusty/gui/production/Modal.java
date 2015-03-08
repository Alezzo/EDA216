package krusty.gui.production;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.models.Pallet;

public class Modal extends BorderPane {

	public Modal(PalletController palletController, CookieController cookieController, Pallet pallet) {

		Form form = new Form(palletController, cookieController);
		form.setPallet(pallet);

		BorderPane.setMargin(form, new Insets(10, 10, 10, 10));

		this.setCenter(form);
	}
}
