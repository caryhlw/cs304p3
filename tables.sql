/* still need to do the following:
 - Dependencies
 - Cascades
 - Some attributes probably don't need 'not null', 
 	but I added them for now						
*/

DROP SEQUENCE upc_counter;
DROP SEQUENCE receipt_counter;
DROP SEQUENCE retid_counter;
DROP SEQUENCE shipment_counter;

DROP TABLE HasSong;
DROP TABLE LeadSinger;
DROP TABLE PurchaseItem;
DROP TABLE ShipItem;
DROP TABLE ReturnItem;
DROP TABLE Return;
DROP TABLE Shipment;
DROP TABLE Purchase;
DROP TABLE Customer;
DROP TABLE Item;



CREATE SEQUENCE upc_counter
START WITH 100000;

CREATE SEQUENCE receipt_counter
START WITH 100000;

CREATE SEQUENCE retid_counter
START WITH 100000;

CREATE SEQUENCE shipment_counter
START WITH 1000000;

create table Item 
	( upc integer not null PRIMARY KEY,
	title varchar(30) not null,
	type varchar(3) not null,
	category varchar(20),
	company varchar(30) not null,
	year integer not null,
	sellPrice float not null,
	quantity integer not null );
	
create table Customer
	( cid varchar(30) not null PRIMARY KEY,
	password varchar(25) not null, 
	name varchar(30) not null,
	address varchar(30) not null,
	phone char(12) not null);

create table Shipment
	( sid integer not null PRIMARY KEY,	
	supName varchar(30) not null,
	sdate date not null );

create table LeadSinger
	( upc integer not null,
	name varchar(30) not null,
	PRIMARY KEY (upc, name),
	foreign key (upc) references Item 
		ON DELETE CASCADE );
	
create table HasSong
	( upc integer not null,
	title varchar(30) not null, 
	PRIMARY KEY(upc, title),
	foreign key(upc) references Item );
	

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
	cid varchar(30),
	card# integer,
	expire date,
	expectedDate date,
	deliveredDate date,
	foreign key(cid) references Customer );
	
create table PurchaseItem
	( receiptID integer not null,
	upc integer not null,
	quantity integer not null,
	PRIMARY KEY(receiptID, upc),
	foreign key(upc) references Item,
	foreign key(receiptID) references Purchase );

create table Return
	( retid integer not null PRIMARY KEY,
	returnDate date not null,
	receiptID integer not null,
	name varchar(30) default 'Future Shop',
	foreign key(receiptID) references Purchase );

create table ReturnItem
	( retid integer not null,
	upc integer not null,
	quantity integer not null,
	PRIMARY KEY(retid, upc),
	foreign key(retid) references Return,
	foreign key(upc) references Item );
	


insert into Item	
values (upc_counter.nextval, 'OK Computer', 'cd', 'rock', 'Capitol Records', 1997, 10.99, 1000);
insert into LeadSinger
values (upc_counter.currval, 'Thom Yorke');
insert ALL
into HasSong values (upc_counter.currval, 'Airbag')
into HasSong values (upc_counter.currval, 'Paranoid Android')
into HasSong values (upc_counter.currval, 'Subterranean Homesick Alien')
into HasSong values (upc_counter.currval, 'Exit Music (For a Film)')
into HasSong values (upc_counter.currval, 'Let Down')
into HasSong values (upc_counter.currval, 'Karma Police')
into HasSong values (upc_counter.currval, 'Fitter Happier')
into HasSong values (upc_counter.currval, 'Electioneering')
into HasSong values (upc_counter.currval, 'Climbing Up the Walls')
into HasSong values (upc_counter.currval, 'No Surprises')
into HasSong values (upc_counter.currval, 'Lucky')
into HasSong values (upc_counter.currval, 'The Tourist')
select * from dual;

insert into Item
values (upc_counter.nextval, 'Teenage Dream', 'cd', 'pop', 'Capitol Records', 2010, 6.99, 50);
insert into LeadSinger
values(upc_counter.currval, 'Katy Perry');

insert ALL
into HasSong values (upc_counter.currval, 'Teenage Dream')
into HasSong values (upc_counter.currval, 'Last Friday Night (T.G.I.F.)')
into HasSong values (upc_counter.currval, 'California Gurls')
into HasSong values (upc_counter.currval, 'Firework')
into HasSong values (upc_counter.currval, 'Peacock')
into HasSong values (upc_counter.currval, 'Circle the Drain')
into HasSong values (upc_counter.currval, 'The One That Got Away')
into HasSong values (upc_counter.currval, 'Who Am I Living For?')
into HasSong values (upc_counter.currval, 'Pearl')
into HasSong values (upc_counter.currval, 'Hummingbird Heartbeat')
into HasSong values (upc_counter.currval, 'Not Like the Movies')
select * from dual;

insert into Item
values (upc_counter.nextval, 'Kill Bill: Vol. 1', 'dvd', null, 'Miramax Films', 2003, 9.99, 0);

insert ALL
into Customer values ('blau', 'abc123', 'Brandon Lau', '1428 Bramwell Road', '604-992-2219')
into Customer values ('cwong', 'qwerty', 'Cary Wong', 'Somewhere in Kerrisdale', '604-123-4567')
into Customer values ('bigfoig', 'itypeslow', 'Mike Estepho', 'Richmond?', '778-999-9999')
into Customer values ('akon', 'solonely', 'Akom V', 'Richmond', '778-111-1111')
select * from dual;


insert into Purchase values (receipt_counter.nextval, '23-jun-2011', 'akon',
							51911000, '1-jun-2013', null, null);

insert into PurchaseItem values (receipt_counter.currval, 100000, 3);
insert into PurchaseItem values (receipt_counter.currval, 100002, 1);

insert into Return values (retid_counter.nextval, '29-jun-2011', receipt_counter.currval,
							default);
insert into ReturnItem values (retid_counter.currval, 100000, 2);

insert into Shipment values (shipment_counter.nextval, 'Big Phat Records', '14-may-2010');
insert into ShipItem values (shipment_counter.currval, 100000, 6.99, 100);
