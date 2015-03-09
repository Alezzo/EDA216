package krusty.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public boolean create(String cookieName, LocalDate productionDate, String location) {
        if(db.createNewPallet(cookieName, productionDate, location)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateLocation(String id, String location) {
        if(db.updateLocationOfPallet(id, location)) {
            return true;
        } else {
            return false;
        }
    }
}
