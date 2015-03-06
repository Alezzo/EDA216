package krusty.gui;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import krusty.Database;


public class SearchResults extends TableView {

	private Database db;

	private TableView table = new TableView();

	public SearchResults(Database db) {
		this.db = db;

		init();
	}

	private void init() {

		TableColumn id = new TableColumn("#");
		id.setMaxWidth(40);
		TableColumn cookieName = new TableColumn("Cookie");
		TableColumn order = new TableColumn("Order");
		TableColumn productionDate = new TableColumn("Production");
		TableColumn deliveryDate = new TableColumn("Delivery");
		TableColumn blocked = new TableColumn("Blocked?");
		TableColumn location = new TableColumn("Location");

		getColumns().addAll(id, cookieName, order, productionDate, deliveryDate, blocked, location);

	}


}
