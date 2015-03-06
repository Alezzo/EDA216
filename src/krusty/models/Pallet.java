package krusty.models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pallet {

	private IntegerProperty id;
	private StringProperty cookieName;

	public Pallet(Integer id, String cookieName) {
		this.id = new SimpleIntegerProperty(id);
		this.cookieName = new SimpleStringProperty(cookieName);
	}

	public IntegerProperty getId() {
		return id;
	}

	public StringProperty getCookieName() {
		return cookieName;
	}




}
