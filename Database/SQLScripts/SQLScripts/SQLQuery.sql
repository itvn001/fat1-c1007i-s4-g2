CREATE DATABASE SHOPDVDS
--
GO 
--
USE SHOPDVDS
--
	-- Cate Type Table 
	-- Values Song, Game, Movie
--
GO
CREATE TABLE CateType
(
	CateTypeID INT PRIMARY KEY IDENTITY(1,1),
	CateTypeName NVARCHAR(10)
)
--
	-- Categories Table
--
GO
CREATE TABLE Categories
(
	CateID INT PRIMARY KEY IDENTITY(1,1),
	CateTypeID INT REFERENCES CateType(CateTypeID), --
	CateName NVARCHAR(35),
	CateStatus BIT DEFAULT 'TRUE'
)
--
	-- Article Table
--
GO
CREATE TABLE Article
(
	ArticleID INT PRIMARY KEY IDENTITY(1,1),
	ArticleTitle NVARCHAR(100),
	ArticleImage NVARCHAR(100),
	ArticleNText NVARCHAR(MAX),
	ArticleStatus BIT DEFAULT 'TRUE'
)
--
	-- Album Table
--
GO
CREATE TABLE Album -- Name DVDS
(
	AlbumID INT PRIMARY KEY IDENTITY(1,1),
	CateID INT REFERENCES Categories(CateID),
	AlbumName NVARCHAR(50),
	AlbumPrice DECIMAL,
	AlbumDateCreate NVARCHAR(30),
	AlbumStatus BIT DEFAULT 'TRUE'
)
--
	-- Suppier Table
--
GO
CREATE TABLE Supplier
(
	SupID INT PRIMARY KEY IDENTITY(1,1),
	SupName NVARCHAR(50),
	SupAddress NVARCHAR(50)
)
--
	-- Dvds Table
--
GO
CREATE TABLE DataStore
(
	DataID INT PRIMARY KEY IDENTITY(1,1),
	AlbumID INT REFERENCES Album(AlbumID),
	SupID INT REFERENCES Supplier(SupID),
	DataName NVARCHAR(100),
	DataPath NVARCHAR(100),
	DataDescription NVARCHAR(MAX),
	DataPublic BIT DEFAULT 'TRUE', -- for guest/ user
	DataStatus BIT DEFAULT 'TRUE'
)
--
	-- Users Table
--
GO
CREATE TABLE Users
(
	UserID INT PRIMARY KEY IDENTITY(1,1),
	UserAccount NVARCHAR(20),
	UserPassword NVARCHAR(70),
	UserName NVARCHAR(30),
	UserAge NVARCHAR(5),
	UserFone NVARCHAR(20),
	UserSex NVARCHAR(10),
	UserStatus BIT DEFAULT 'TRUE'	
)
--
	-- User Collection Cate
--
GO
CREATE TABLE  CollectionCate
(
	CollecCateID INT PRIMARY KEY IDENTITY(1,1),
	CollectCateName NVARCHAR(50),
	UserID INT REFERENCES Users(UserID),
	DateCreate NVARCHAR(30),
)
--
	-- User Collection Table
--
GO
CREATE TABLE CollectionUser
(
	CollectID INT PRIMARY KEY IDENTITY(1,1),
	CollectionCate INT REFERENCES CollectionCate(CollecCateID),
	DataID INT REFERENCES DataStore(DataID),
	AlbumID INT REFERENCES Album(AlbumID)
)
--
	-- FeedBack Table
--
GO
CREATE TABLE FeedBack
(
	FeedBackID INT PRIMARY KEY IDENTITY(1,1),
	AlbumID INT REFERENCES Album(AlbumID),
	UserID INT REFERENCES Users(UserID),
	FeedBackComment NVARCHAR(MAX),
	FeedBackDateCreate NVARCHAR(30),
	FeedBackStatus BIT DEFAULT 'TRUE'
)
--
	-- Order Table
--
GO
CREATE TABLE Orders
(
	OrderID INT PRIMARY KEY IDENTITY(1,1),
	UserID INT REFERENCES Users(UserID),
	OrderDate NVARCHAR(30),
	ShipName NVARCHAR(30),
	ShipAddress NVARCHAR(100),
	ShipPostalCode NVARCHAR(20),
	ShipStatus INT
)
--
	-- Order Details Table
--
GO
CREATE TABLE OrderDetails
(
	OrderID INT REFERENCES Orders(OrderID),
	Album INT REFERENCES Album(ALbumID),
	UnitPrice DECIMAL,
	Quantity INT,
	Discount DECIMAL
)
--
	-- Permision Table
--
GO
CREATE TABLE Permision
(
	PerID INT PRIMARY KEY IDENTITY(1,1),
	PerName NVARCHAR(20),
)
--
	-- UserAdmin Table
--
GO
CREATE TABLE UserAdmin
(
	UserAdminID INT PRIMARY KEY IDENTITY(1,1),
	Account NVARCHAR(25),
	Passwords  NVARCHAR(100),
	PerID INT REFERENCES Permision(PerID),
	Name NVARCHAR(50),
	Email NVARCHAR(50)
)
-- Insert into database
-- Insert into Cate Type
GO
INSERT INTO CateType VALUES('Music'),('Game'),('Movie')

-- Insert into Categories
GO
INSERT INTO Categories VALUES(1,'Music FIFA','true')
INSERT INTO Categories VALUES(2,'ACTION','true')
INSERT INTO Categories VALUES(3,'COMEDY','true')
