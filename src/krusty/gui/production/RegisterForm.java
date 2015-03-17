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

public class RegisterForm extends GridPane {

	private final PalletController palletController;
	private final CookieController cookieController;

	private ComboBox<String> cookieName;
	private DatePicker productionDate;
	private TextField location;

	private Button saveButton;
	private Button cancelButton;

	private Pallet pallet = null;

	public RegisterForm(PalletController palletController, CookieController cookieController) {
		this.palletController = palletController;
		this.cookieController = cookieController;

		//this.setMaxSize(200, 200);
		this.setVgap(4);

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);

		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(75);

		this.getColumnConstraints().add(col1);
		this.getColumnConstraints().add(col2);

		Label l = new Label("Cookie:");

		cookieName = new ComboBox<String>(cookieController.getObservableList());

        cookieController.getObservableList().add(0, "Choose a cookie");
        cookieName.getSelectionModel().select("Choose a cookie");

		this.add(l, 0, 0);
		this.add(cookieName, 1, 0);

		l = new Label("Production date:");
		productionDate = new DatePicker();
		productionDate.setMaxSize(115, 40);
		productionDate.setValue(LocalDate.now());

		this.add(l, 0, 1);
		this.add(productionDate, 1, 1);

		l = new Label("Location:");
		location = new TextField();

		this.add(l, 0, 2);
		this.add(location, 1, 2);

		saveButton = new Button("Register");
		saveButton.setDefaultButton(true);
		saveButton.setOnAction(new RegisterButtonEventHandler());
		this.add(saveButton, 1, 10);

		cancelButton = new Button("Clear");
		cancelButton.setCancelButton(true);
		cancelButton.setOnAction(new ClearButtonEventHandler());
		this.add(cancelButton, 0, 10);

	}

	public void setPallet(Pallet pallet) {
		this.pallet = pallet;
		saveButton.setText("Update");

		this.cookieName.setValue(pallet.getCookieName().getValue());
		this.productionDate.setValue(LocalDate.parse(pallet.getProductionDate().getValue()));
		this.location.setText(pallet.getLocation().getValue());
	}

	private class RegisterButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
            if (pallet == null) {
                if (cookieName.getValue() == "Choose a cookie") {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Something wrong");
                    alert.setContentText("Did you forget to choose a cookie?");

                    alert.showAndWait();
                }
				else if (palletController.register(cookieName.getValue(), productionDate.getValue(), location.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Pallet successfully created");
                    alert.setContentText("Press OK to continue");

                    alert.showAndWait();

                    cookieName.getSelectionModel().select(0);
                    productionDate.setValue(LocalDate.now());
                    location.setText("");
				}
			}
		}
	}

    private class ClearButtonEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            cookieName.getSelectionModel().select(0);
            productionDate.setValue(LocalDate.now());
            location.setText("");
        }
    }
}
