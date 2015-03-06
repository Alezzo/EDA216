package krusty.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import krusty.Database;


public class SearchBox extends GridPane {

	private Database db;


	public SearchBox(Database db) {
		this.db = db;

		init();
	}

	private void init() {

		this.setAlignment(Pos.CENTER);
		this.setHgap(5);
		this.setMinHeight(55);

		Label fromDateLabel = new Label("From date");
		DatePicker fromDatePicker = new DatePicker();
		fromDatePicker.setMaxSize(120, 40);

		this.add(fromDateLabel, 0, 0);
		this.add(fromDatePicker, 0, 1);

		Label toDateLabel = new Label("To date");
		DatePicker toDatePicker = new DatePicker();
		toDatePicker.setMaxSize(120, 40);

		this.add(toDateLabel, 1, 0);
		this.add(toDatePicker, 1, 1);


		Label cookieLabel = new Label("Cookie");

		String cookies[] = db.getCookieNames();
		ObservableList obList = FXCollections.observableArrayList(cookies);
		obList.add(0, "Any cookie");

		ComboBox cookieName = new ComboBox(obList);
		cookieName.getSelectionModel().select("Any cookie");

		this.add(cookieLabel, 2, 0);
		this.add(cookieName, 2, 1);


		Button button = new Button("Search");
		this.add(button, 3, 1);

	}


}
