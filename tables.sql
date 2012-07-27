/* still need to do the following:
 - Dependencies
 - Cascades
 - Some attributes probably don't need 'not null', 
 	but I added them for now						
*/

create table Item 
	( upc integer not null PRIMARY KEY,
	title varchar(50) not null,
	type varchar(20) not null,
	category varchar(20) not null,
	company varchar(50) not null,
	year integer not null,
	sellPrice float not null,
	quantity integer not null );

CREATE SEQUENCE upc_counter
START WITH 100000000000;

create table LeadSinger
	( upc integer not null,
	name varchar(50) not null,
	PRIMARY KEY (upc, name),
	foreign key (upc) references Item );
	
create table HasSong
	( upc integer not null,
	title varchar(50) not null, 
	PRIMARY KEY(upc, title),
	foreign key(upc) references Item );
	
create table Shipment
	( sid integer not null PRIMARY KEY,	
	supName varchar(50) not null,
	date date not null );

CREATE SEQUENCE shipment_counter
START WITH 1000000

create table ShipItem
	( sid integer not null,
	upc integer not null,
	supPrice float not null,
	quantity integer not null, 
	PRIMARY KEY(sid, upc),
	foreign key(sid) references Shipment,
	foreign key(upc) references Item );
	
create table Purchase
	( receiptID integer not null PRIMARY KEY,
	purchaseDate date not null,
	cid integer not null,
	card# integer not null,
	expire date not null,
	expectedDate date not null,
	deliveredDate date,
	foreign key(cid) references Customer );

CREATE SEQUENCE receipt_counter
START WITH 10000000
	
create table PurchaseItem
	( receiptID integer not null,
	upc integer not null,
	quantity integer not null,
	PRIMARY KEY(receiptID, upc),
	foreign key(upc) references Item,
	foregin key(receiptID) references Purchase );
	
create table Customer
	( cid integer not null PRIMARY KEY,
	password varchar(25) not null, 
	name varchar(50) not null,
	address varchar(50) not null,
	phone integer not null);

create table Return
	( retid integer not null PRIMARY KEY,
	returnDate date not null,
	receiptID integer not null,
	name varchar(50) not null,
	foreign key(receiptID) references Purchase,	
	foreign key(name) references Customer );

create table ReturnItem
	( retid integer not null,
	upc integer not null,
	quantity integer not null,
	PRIMARY KEY(retid, upc),
	foreign key(retid) references Return,
	foreign key(upc) references Item );
	
