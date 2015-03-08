package krusty.gui.production;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;

import java.time.LocalDate;

public class Form extends GridPane {

	private final PalletController palletController;
	private final CookieController cookieController;

	private ComboBox<String> cookieName;
	private DatePicker productionDate;
	private TextField location;
	private CheckBox blocked;

	public Form(PalletController palletController, CookieController cookieController) {
		this.palletController = palletController;
		this.cookieController = cookieController;

		//this.setMaxSize(200, 200);
		this.setVgap(5);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(75);

		this.getColumnConstraints().add(col1);
		this.getColumnConstraints().add(col2);

		Label l = new Label("Cookie:");

		cookieName = new ComboBox<String>(cookieController.getObservableList());

		this.add(l, 0, 0);
		this.add(cookieName, 1, 0);

		l = new Label("Production date:");
		productionDate = new DatePicker();
		productionDate.setMaxSize(110, 40);
		productionDate.setValue(LocalDate.now());

		this.add(l, 0, 1);
		this.add(productionDate, 1, 1);

		l = new Label("Location:");
		location = new TextField();

		this.add(l, 0, 2);
		this.add(location, 1, 2);

		l = new Label("Blocked:");
		blocked = new CheckBox();

		this.add(l, 0, 3);
		this.add(blocked, 1, 3);

		Button button = new Button("Create");
		button.setDefaultButton(true);
		//button.setOnAction(new SearchButtonEventHandler());
		this.add(button, 1, 10);

		Button cancelButton = new Button("Cancel");
		cancelButton.setCancelButton(true);
		//cancelButton.setOnAction(new ClearButtonEventHandler());
		this.add(cancelButton, 0, 10);

	}

	private class CreateButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			// TODO: Call palletController.create(<data>).
			// Show confirmation and reset form fields, preferable using the cancelButton.
		}
	}

}