package krusty.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import krusty.Database;
import krusty.controllers.PalletController;


public class SearchBox extends GridPane {

	private Database db;
	private PalletController controller;
	private ComboBox<String> selectedCookie;
	private DatePicker fromDatePicker;
	private DatePicker toDatePicker;

	public SearchBox(Database db, PalletController controller) {
		this.db = db;
		this.controller = controller;

		init();
	}

	private void init() {

		this.setAlignment(Pos.CENTER);
		this.setHgap(5);
		this.setMinHeight(55);

		Label fromDateLabel = new Label("From date");
		fromDatePicker = new DatePicker();
		fromDatePicker.setMaxSize(120, 40);

		this.add(fromDateLabel, 0, 0);
		this.add(fromDatePicker, 0, 1);

		Label toDateLabel = new Label("To date");
		toDatePicker = new DatePicker();
		toDatePicker.setMaxSize(120, 40);

		this.add(toDateLabel, 1, 0);
		this.add(toDatePicker, 1, 1);


		Label cookieLabel = new Label("Cookie");

		String cookies[] = db.getCookieNames();
		ObservableList<String> obList = FXCollections.observableArrayList(cookies);
		obList.add(0, "Any cookie");

		selectedCookie = new ComboBox<String>(obList);
		selectedCookie.getSelectionModel().select("Any cookie");
		

		this.add(cookieLabel, 2, 0);
		this.add(selectedCookie, 2, 1);


		Button button = new Button("Search");
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, new SearchButtonEventHandler());
		this.add(button, 3, 1);

	}

	private class SearchButtonEventHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			System.out.println(selectedCookie.getValue());
			controller.search(selectedCookie.getValue(), fromDatePicker.getValue(), toDatePicker.getValue());
		}
	}


}
