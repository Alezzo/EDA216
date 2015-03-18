package krusty.gui.search;


import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
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
					new Modal(palletController, cookieController, row.getItem());
				}
			});
			return row;
		});

		TableColumn<Pallet, Number> palletId = new TableColumn("#");
		palletId.setCellValueFactory(cell -> cell.getValue().getPalletId());
		palletId.setPrefWidth(40);

		TableColumn<Pallet, String> cookieName = new TableColumn("Cookie");
		cookieName.setCellValueFactory(cell -> cell.getValue().getCookieName());
		cookieName.setPrefWidth(110);
		
		TableColumn<Pallet, String> productionDate = new TableColumn("Production");
		productionDate.setCellValueFactory(cell -> cell.getValue().getProductionDate());
		
		TableColumn<Pallet, String> location = new TableColumn("Location");
		location.setCellValueFactory(cell -> cell.getValue().getLocation());
        location.setPrefWidth(110);
		
		TableColumn<Pallet, Boolean> isBlocked = new TableColumn("Blocked?");
		isBlocked.setCellValueFactory(cell -> cell.getValue().blocked());
		isBlocked.setCellFactory(cell -> new TableCell<Pallet, Boolean>() {
			protected void updateItem(Boolean item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setText("");
				} else {
					if (item) {
						setText("Yes");
					} else {
						setText("No");
					}
				}
			}
		});

		getColumns().addAll(palletId, cookieName, productionDate, location, isBlocked);

		setItems(palletController.getObservableList());

	}


}
