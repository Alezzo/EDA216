package krusty.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
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

	public void search(String cookieName, LocalDate fromDate, LocalDate toDate, CheckBox isBlocked) {
		observableList.clear();
		observableList.addAll(db.getPalletsForCookie(cookieName, fromDate, toDate, isBlocked));
	}

    public boolean create(String cookieName, LocalDate productionDate, String location, boolean blocked) {
        if(db.createNewPallet(cookieName, productionDate, location, blocked)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editPalletInformation(String id, String location, boolean blocked) {
        if(db.editPalletInformation(id, location, blocked)) {
            return true;
        } else {
            return false;
        }
    }
}
