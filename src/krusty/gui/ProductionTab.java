package krusty.gui;


import javafx.scene.control.Tab;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.gui.production.Form;

public class ProductionTab extends Tab {

	public ProductionTab(PalletController palletController, CookieController cookieController) {
		this.setText("Production");

		this.setContent(new Form(palletController, cookieController));
	}
}
