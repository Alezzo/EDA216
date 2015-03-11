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

	public Pallet(Integer palletId, String cookieName, Integer orderId, String productionDate, String deliveryDate, String location, boolean isBlocked) {
		this.palletId = new SimpleIntegerProperty(palletId);
		this.cookieName = new SimpleStringProperty(cookieName);
		this.orderId = new SimpleIntegerProperty(orderId);
		this.productionDate = new SimpleStringProperty(productionDate);
		this.deliveryDate = new SimpleStringProperty(deliveryDate);
		this.location = new SimpleStringProperty(location);
		this.isBlocked = new SimpleBooleanProperty(isBlocked);
	}

	public IntegerProperty getPalletId() {
		return palletId;
	}
	
	public StringProperty getCookieName() {
		return cookieName;
	}

	public IntegerProperty getOrderId() {
		return orderId;
	}
	
	public StringProperty getProductionDate() {
		return productionDate;
	}

    public StringProperty getDeliveryDate() {
        return deliveryDate;
    }
	
	public StringProperty getLocation() {
		return location;
	}

    public BooleanProperty blocked() { return isBlocked;}
	
	public StringProperty isBlocked() {
		if(isBlocked.getValue()){
			return new SimpleStringProperty("Yes");
		}
		return new SimpleStringProperty("No");
	}
}