package krusty.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import krusty.Database;

import java.util.ArrayList;
import java.util.List;

public class CookieController {

	private Database db;

	private ObservableList<String> observableList;

	public CookieController(Database db) {
		this.db = db;

		List<String> list = new ArrayList<>();
		observableList = FXCollections.observableList(list);
		getAllCookies();
	}

	public ObservableList<String> getObservableList() {
		return observableList;
	}

	private void getAllCookies() {
		observableList.clear();
		observableList.addAll(db.getCookieNames());
	}
}
