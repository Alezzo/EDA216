package krusty.gui.production;


import javafx.scene.layout.GridPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;

public class Form extends GridPane {

	private final PalletController palletController;
	private final CookieController cookieController;

	public Form(PalletController palletController, CookieController cookieController) {
		this.palletController = palletController;
		this.cookieController = cookieController;


	}



}
