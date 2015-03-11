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
import java.util.Optional;

public class EditForm extends GridPane {

	private final PalletController palletController;
	private final CookieController cookieController;

    private TextField palletId;
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

		this.setVgap(4);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(45);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(55);

		this.getColumnConstraints().add(col1);
		this.getColumnConstraints().add(col2);

        Label l = new Label("pallet ID:");

        palletId = new TextField();
        palletId.setText(pallet.getPalletId().getValue().toString());
        palletId.setDisable(true);

        this.add(l, 0, 0);
        this.add(palletId, 1, 0);


        l = new Label("Cookie:");

		cookieName = new ComboBox<String>(cookieController.getObservableList());
        cookieName.getSelectionModel().select(pallet.getCookieName().getValue());
        cookieName.setDisable(true);

		this.add(l, 0, 1);
		this.add(cookieName, 1, 1);

		l = new Label("Production date:");
		productionDate = new DatePicker();
		productionDate.setMaxSize(110, 40);
		if (pallet.getProductionDate().getValue() != null) {
			productionDate.setValue(LocalDate.parse(pallet.getProductionDate().getValue()));
		}
        productionDate.setDisable(true);

		this.add(l, 0, 2);
		this.add(productionDate, 1, 2);

		l = new Label("Location:");
		location = new TextField();
		location.setText(pallet.getLocation().getValue());

		this.add(l, 0, 3);
		this.add(location, 1, 3);

		l = new Label("Blocked:");
		blocked = new CheckBox();

		this.add(l, 0, 4);
		this.add(blocked, 1, 4);

		saveButton = new Button("Update");
		saveButton.setDefaultButton(true);
		saveButton.setOnAction(new UpdateButtonEventHandler());
		this.add(saveButton, 1, 10);

		cancelButton = new Button("Cancel");
		cancelButton.setCancelButton(true);
		cancelButton.setOnAction(new CancelButtonEventHandler());
		this.add(cancelButton, 0, 10);

	}

    // Something wrong, palletId.getId() returns null.. Could try with toString().
	private class UpdateButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
                if (palletController.updateLocation(palletId.getText(), location.getText(), blocked.isSelected())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Pallet successfully updated");
                    alert.setContentText("Press OK to continue");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        modal.closeModal();

                    }
                    //JOptionPane.showMessageDialog(null, "The creation was completed");
                }


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
