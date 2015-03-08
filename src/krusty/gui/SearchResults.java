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

		TableColumn<Pallet, Number> palletId = new TableColumn("#");
		palletId.setCellValueFactory(cell -> cell.getValue().getPalletId());
		palletId.setMaxWidth(40);

		TableColumn<Pallet, String> cookieName = new TableColumn("Cookie");
		cookieName.setCellValueFactory(cell -> cell.getValue().getCookieName());

		TableColumn<Pallet, Number> orderId = new TableColumn("Order");
		orderId.setCellValueFactory(cell -> cell.getValue().getOrderId());
		
		TableColumn<Pallet, String> productionDate = new TableColumn("Production");
		productionDate.setCellValueFactory(cell -> cell.getValue().getProductionDate());
		
		TableColumn<Pallet, String> deliveryDate = new TableColumn("Delivery");
		deliveryDate.setCellValueFactory(cell -> cell.getValue().getDeliveryDate());
		
		TableColumn<Pallet, String> location = new TableColumn("Location");
		location.setCellValueFactory(cell -> cell.getValue().getLocation());
		
		TableColumn<Pallet, String> isBlocked = new TableColumn("Blocked?");
		isBlocked.setCellValueFactory(cell -> cell.getValue().isBlocked());

		getColumns().addAll(palletId, cookieName, orderId, productionDate, deliveryDate, location, isBlocked);

		setItems(controller.getObservableList());

	}


}
