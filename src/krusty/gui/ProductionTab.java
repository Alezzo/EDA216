package krusty.gui;


import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.gui.production.CreateForm;

public class ProductionTab extends Tab {

	public ProductionTab(PalletController palletController, CookieController cookieController) {
		this.setText("Register Pallet");

		CreateForm form = new CreateForm(palletController, cookieController);

		BorderPane p = new BorderPane();
		BorderPane.setMargin(form, new Insets(10, 10, 10, 10));
		p.setCenter(form);

		this.setContent(p);
	}
}
