CREATE DATABASE Registrations
GO

USE Registrations 
GO

CREATE TABLE Register
(
	username varchar(50) PRIMARY KEY,
	password varchar(50),
	lastname nvarchar(50),
	isAdmin bit
)
GO

INSERT Register VALUES ('TQP', '123', 'TRUONG QUANG PHIEN', 1)
INSERT Register VALUES ('PT', '123', 'PHIEN TRUONGG', 0)
GO

CREATE TABLE Book
(
	ID nvarchar(20) PRIMARY KEY,
	name nvarchar(50)
)
GO

INSERT Book VALUES ('B01' , 'JAVA')
INSERT Book VALUES ('B02' , 'PASCAL')
INSERT Book VALUES ('B03' , 'C++')
INSERT Book VALUES ('B04' , 'C#')
INSERT Book VALUES ('B05' , 'PYTHON')
INSERT Book VALUES ('B06' , 'BOOSTRAP')

CREATE TABLE Orders
(
	OrderID int PRIMARY KEY IDENTITY,
	Name varchar(50),
	Address varchar(50),
	RegDate datetime DEFAULT GetDate()
)

CREATE TABLE OrderDetails
(
	ID int PRIMARY KEY IDENTITY,
	Book_ID nvarchar(20) FOREIGN KEY REFERENCES Book(ID),
	Quantity int,
	OrderID int NOT NULL FOREIGN KEY REFERENCES Orders(OrderID)
)

INSERT INTO Orders(Name,Address)
	OUTPUT inserted.OrderID
	VALUES ('PHUONG', '218')

Select * from Orders
SELECT * FROM OrderDetails
