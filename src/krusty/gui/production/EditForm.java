package krusty.gui.production;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import krusty.controllers.CookieController;
import krusty.controllers.PalletController;
import krusty.models.Pallet;

import java.time.LocalDate;

public class EditForm extends GridPane {

	private final PalletController palletController;
	private final CookieController cookieController;

	private ComboBox<String> cookieName;
	private DatePicker productionDate;
	private TextField location;
	private CheckBox blocked;

	private Button saveButton;
	private Button cancelButton;

	private Pallet pallet = null;

	private Modal modal;

	public EditForm(PalletController palletController, CookieController cookieController, Pallet pallet, Modal modal) {
		this.palletController = palletController;
		this.cookieController = cookieController;
		this.pallet = pallet;
		this.modal = modal;

		this.setVgap(5);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(75);

		this.getColumnConstraints().add(col1);
		this.getColumnConstraints().add(col2);

		Label l = new Label("Cookie:");

		cookieName = new ComboBox<String>(cookieController.getObservableList());
        cookieName.getSelectionModel().select(pallet.getCookieName().getValue());

		this.add(l, 0, 0);
		this.add(cookieName, 1, 0);

		l = new Label("Production date:");
		productionDate = new DatePicker();
		productionDate.setMaxSize(110, 40);
		if (pallet.getProductionDate().getValue() != null) {
			productionDate.setValue(LocalDate.parse(pallet.getProductionDate().getValue()));
		}


		this.add(l, 0, 1);
		this.add(productionDate, 1, 1);

		l = new Label("Location:");
		location = new TextField();
		location.setText(pallet.getLocation().getValue());

		this.add(l, 0, 2);
		this.add(location, 1, 2);

		l = new Label("Blocked:");
		blocked = new CheckBox();

		this.add(l, 0, 3);
		this.add(blocked, 1, 3);

		saveButton = new Button("Update");
		saveButton.setDefaultButton(true);
		saveButton.setOnAction(new UpdateButtonEventHandler());
		this.add(saveButton, 1, 10);

		cancelButton = new Button("Cancel");
		cancelButton.setCancelButton(true);
		cancelButton.setOnAction(new CancelButtonEventHandler());
		this.add(cancelButton, 0, 10);

	}

	private class UpdateButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {



			// TODO: Call palletController.create(<data>).
			// TODO: Also check if the pallet is new or not, possible by setting this.pallet in setPallet, and if null create else update.
			// Show confirmation and reset form fields, preferable using the cancelButton.
		}
	}

    private class CancelButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            modal.closeModal();
        }
    }

}
