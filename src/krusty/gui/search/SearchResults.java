package krusty.gui.search;


import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import krusty.KrustyKookies;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.gui.production.Modal;
import krusty.models.Pallet;


public class SearchResults extends TableView {

	private PalletController palletController;
	private CookieController cookieController;

	public SearchResults(PalletController controller, CookieController cookieController) {
		this.palletController = controller;
		this.cookieController = cookieController;

		init();
	}

	private void init() {

		this.setRowFactory(tv -> {
			TableRow<Pallet> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {

					// Create the dialog Stage.
					Stage dialogStage = new Stage();
					dialogStage.setTitle("Edit pallet");
					dialogStage.initModality(Modality.WINDOW_MODAL);
					dialogStage.initOwner(KrustyKookies.primaryStage);
					Scene scene = new Scene(new Modal(palletController, cookieController, row.getItem()), 300, 200);
					dialogStage.setScene(scene);

					// Show the dialog and wait until the user closes it
					dialogStage.showAndWait();
				}
			});
			return row;
		});

		TableColumn<Pallet, Number> palletId = new TableColumn("#");
		palletId.setCellValueFactory(cell -> cell.getValue().getPalletId());
		palletId.setPrefWidth(40);

		TableColumn<Pallet, String> cookieName = new TableColumn("Cookie");
		cookieName.setCellValueFactory(cell -> cell.getValue().getCookieName());
		cookieName.setPrefWidth(90);

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

		setItems(palletController.getObservableList());

	}


}
