# EDA216

Project for the course [EDA216 - Database Technology](http://cs.lth.se/eda216/).

To start the program use `java -jar eda216.jar`.

As per requirement there is currently no GUI for viewing ingredient usage, the query ```select * from Ingredient_storage;``` can be used.

![Screenshot](/doc/screenshot.png?raw=true "Screenshot")

## Database Schema:

![UML](/doc/model.png?raw=true "UML Diagram")

```sql
create table Customer (
	customerName VARCHAR(45), 
	address VARCHAR(45),
	primary key(customerName)
);

create table `Order` (
	orderId INT auto_increment,
	customerName VARCHAR(45),
	orderDate DATETIME,
	primary key(orderId),
	foreign key(customerName) references Customer(customerName)
);

create table Cookie (
	cookieName VARCHAR(45),
	primary key(cookieName)
);

create table Order_Cookie (
	orderId INT, 
	cookieName VARCHAR(45), 
	palletAmount INT, 
	primary key(orderId, cookieName), 
	foreign key(orderId) references `Order`(orderId), 
	foreign key(cookieName) references Cookie(cookieName)
);

create table Pallet (
	palletId INT auto_increment,
	cookieName VARCHAR(45),
	orderId INT,
	productionDate DATE,
	deliveryDate DATE,
	location VARCHAR(45),
	isBlocked BOOLEAN,
	primary key(palletId),
	foreign key(cookieName) references Cookie(cookieName),
	foreign key(orderId) references `Order`(orderId)
);

create table Ingredient_Storage (
	ingredientName VARCHAR(45),
	amountLeft INT,
	primary key(ingredientName)
);

create table Cookie_Ingredient (
	cookieName VARCHAR(45),
	ingredientName VARCHAR(45),
	amount INT,
	primary key(cookieName, ingredientName),
	foreign key(cookieName) references Cookie(cookieName),
	foreign key(ingredientName)
		references Ingredient_Storage(ingredientName)
);

create table Ingredient_Delivery (
	ingredientName VARCHAR(45), 
	deliveryDate DATETIME, 
	amount INT, 
	primary key(ingredientName, deliveryDate), 
	foreign key(ingredientName)
		references Ingredient_Storage(ingredientName)
);
```
