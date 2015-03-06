package krusty.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import krusty.Database;
import krusty.models.Pallet;

import java.util.ArrayList;
import java.util.List;

public class PalletController {

	private Database db;

	private ObservableList<Pallet> observableList;

	public PalletController(Database db) {
		this.db = db;

		List<Pallet> list = new ArrayList<Pallet>();
		observableList = FXCollections.observableList(list);
		// TODO: Add listeners for updating the database!
		// http://docs.oracle.com/javafx/2/collections/jfxpub-collections.htm
		// Example 4

		// Load the default data..
		resetSearch();
	}

	public ObservableList<Pallet> getObservableList() {
		return observableList;
	}

	public void resetSearch() {
		Pallet[] pallets = db.getAllPallets();
		observableList.removeAll();
		observableList.addAll(pallets);
	}

	public void search() {
		// TODO: Implement search to db, should update observableList..
	}

}
