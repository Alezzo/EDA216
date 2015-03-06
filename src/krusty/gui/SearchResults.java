package krusty.gui;


import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import krusty.Database;
import krusty.controllers.PalletController;
import krusty.models.Pallet;


public class SearchResults extends TableView {

	private PalletController controller;

	private TableView table = new TableView();

	public SearchResults(PalletController controller) {
		this.controller = controller;

		init();
	}

	private void init() {

		TableColumn<Pallet, Number> id = new TableColumn("#");
		id.setCellValueFactory(cell -> cell.getValue().getId());
		id.setMaxWidth(40);

		TableColumn<Pallet, String> cookieName = new TableColumn("Cookie");
		cookieName.setCellValueFactory(cell -> cell.getValue().getCookieName());

		TableColumn order = new TableColumn("Order");
		TableColumn productionDate = new TableColumn("Production");
		TableColumn deliveryDate = new TableColumn("Delivery");
		TableColumn blocked = new TableColumn("Blocked?");
		TableColumn location = new TableColumn("Location");

		getColumns().addAll(id, cookieName, order, productionDate, deliveryDate, blocked, location);

		setItems(controller.getObservableList());

	}


}
