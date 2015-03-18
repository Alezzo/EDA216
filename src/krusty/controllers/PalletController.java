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

    public boolean register(String cookieName, LocalDate productionDate, String location) {
        if(db.registerNewPallet(cookieName, productionDate, location)) {
            Pallet lastPallet = observableList.get(observableList.size() - 1);
            observableList.add(new Pallet(lastPallet.getPalletId().getValue() + 1, cookieName, productionDate.toString(), location, false));
            return true;
        } else {
            return false;
        }
    }

    public void deletePallet(String id) {
        if(db.deletePallet(id)) {
            Pallet p = findPalletById(Integer.parseInt(id));
            observableList.removeAll(p);
        }
    }

    public boolean editPalletInformation(String id, String location, boolean blocked) {
        if(db.editPalletInformation(id, location, blocked)) {

	        // Update the local pallet
	        Pallet p = findPalletById(Integer.parseInt(id));

	        if (p != null) {
		        p.getLocation().setValue(location);
		        p.blocked().set(blocked);
	        }

            return true;
        } else {
            return false;
        }
    }

	public boolean blockAll() {

		Integer[] ids = new Integer[observableList.size()];

		int i = 0;
		for (Pallet p : observableList) {
			ids[i++] = p.getPalletId().getValue();
			p.blocked().set(true);
		}

		return db.blockAllPallets(ids);

	}

	private Pallet findPalletById(int id) {

		for (Pallet p : observableList) {
			if (p.getPalletId().getValue().equals(id)) {
				return p;
			}
		}

		return null;
	}
}
