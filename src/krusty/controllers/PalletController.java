package krusty.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import krusty.Database;
import krusty.models.Pallet;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class PalletController {

	private Database db;

	private ObservableList<Pallet> observableList;

	public PalletController(Database db) {
		this.db = db;

		List<Pallet> list = new ArrayList<>();
		observableList = FXCollections.observableList(list);
		resetSearch();
	}

	public ObservableList<Pallet> getObservableList() {
		return observableList;
	}

	public void resetSearch() {
		observableList.clear();
		observableList.addAll(db.getAllPallets());
	}

	public void search(String cookieName, LocalDate fromDate, LocalDate toDate) {
		observableList.clear();
		observableList.addAll(db.getPalletsForCookie(cookieName, fromDate, toDate));
	}

}
