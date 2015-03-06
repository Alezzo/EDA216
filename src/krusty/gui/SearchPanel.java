package krusty.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import krusty.Database;


public class SearchPanel extends JFXPanel {

	private Database db;

	private Button testButton;
	private TextField testTextField;
	private Label testLabel;
	private GridPane pane;

	public SearchPanel(Database db) {
		this.db = db;

		init();
	}

	private void init() {

		pane = new GridPane();
		pane.setHgap(5);
		pane.setMinHeight(55);

		Label fromDateLabel = new Label("From date");
		DatePicker fromDatePicker = new DatePicker();
		fromDatePicker.setMaxSize(120, 40);

		pane.add(fromDateLabel, 0, 0);
		pane.add(fromDatePicker, 0, 1);

		Label toDateLabel = new Label("To date");
		DatePicker toDatePicker = new DatePicker();
		toDatePicker.setMaxSize(120, 40);

		pane.add(toDateLabel, 1, 0);
		pane.add(toDatePicker, 1, 1);


		Label cookieLabel = new Label("Cookie");

		String cookies[] = db.getCookieNames();
		ObservableList obList = FXCollections.observableArrayList(cookies);
		obList.add(0, "Any cookie");

		ComboBox cookieName = new ComboBox(obList);
		cookieName.getSelectionModel().select("Any cookie");

		pane.add(cookieLabel, 2, 0);
		pane.add(cookieName, 2, 1);


		Button button = new Button("Search");
		pane.add(button, 3, 1);

		Platform.runLater(this::createScene);
	}

	private void createScene() {
		Scene scene = new Scene(pane);
		setScene(scene);
	}

	public Button getTestButton() {
		return testButton;
	}

	public TextField getTestTextField() {
		return testTextField;
	}

	public Label getTestLabel() {
		return testLabel;
	}

}
