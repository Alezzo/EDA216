package krusty.models;

import javafx.beans.property.*;

public class Pallet {

	private IntegerProperty palletId;
	private StringProperty cookieName;
	private IntegerProperty orderId;
	private StringProperty productionDate;
	private StringProperty deliveryDate;
	private StringProperty location;
	private BooleanProperty isBlocked;

	public Pallet(Integer palletId, String cookieName, String productionDate, String location, boolean isBlocked) {
		this.palletId = new SimpleIntegerProperty(palletId);
		this.cookieName = new SimpleStringProperty(cookieName);
		this.productionDate = new SimpleStringProperty(productionDate);
		this.location = new SimpleStringProperty(location);
		this.isBlocked = new SimpleBooleanProperty(isBlocked);
	}

	public IntegerProperty getPalletId() {
		return palletId;
	}
	
	public StringProperty getCookieName() {
		return cookieName;
	}
	
	public StringProperty getProductionDate() {
		return productionDate;
	}
	
	public StringProperty getLocation() {
		return location;
	}

    public BooleanProperty blocked() { return isBlocked;}
}