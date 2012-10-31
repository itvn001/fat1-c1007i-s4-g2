USE SHOPDVDS
--
GO
CREATE VIEW aShowCategories
AS
SELECT CateID,CateTypeID,CateName,CateStatus FROM Categories
--
GO
CREATE PROC aShowAlbum
@CateID INT
AS
IF @CateID = 0
BEGIN
	SELECT  AlbumID,
		CateID,
		AlbumName,
		AlbumPrice,
		AlbumDateCreate,
		AlbumImage,
		AlbumStatus,
		Quantity
		FROM Album ORDER BY AlbumID DESC
END
IF @CateID != 0
BEGIN
SELECT AlbumID,
		CateID,
		AlbumName,
		AlbumPrice,
		AlbumDateCreate,
		AlbumImage,
		AlbumStatus,
		Quantity
		FROM Album WHERE CateID = @CateID ORDER BY AlbumID DESC
END
exec aShowAlbum '1'
GO
-----
CREATE PROC aShowEditAlbum
@AlbumID INT
AS
SELECT AlbumID,
		Album.CateID,
		AlbumName,
		Categories.CateName,
		AlbumPrice,
		AlbumDateCreate,
		AlbumStatus,
		AlbumImage,
		Quantity,
		AlbumDetails
		FROM Album,Categories
		WHERE AlbumID = @AlbumID AND Album.CateID = Categories.CateID
---
GO
ALTER PROC aShowDataStore
@AlbumID INT
AS
SELECT	DataID,
		AlbumID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus,
		DataImage
		FROM DataStore WHERE AlbumID = @AlbumID	 ORDER BY DataID DESC
--
GO
ALTER PROC aShowAllDataStore
@ID int
AS
SELECT	DataID,
		Album.AlbumName,
		SupID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus,
		DataImage
		FROM DataStore,Album 
		WHERE DataStore.AlbumID = 1 ORDER BY DataID DESC
GO
ALTER PROC aShowAllDataStoreFilter
@ID int
AS
IF @ID = 0
BEGIN
	SELECT	DataID,
		Album.AlbumName,
		SupID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus,
		DataImage
		FROM DataStore,Album 
		WHERE DataStore.AlbumID = Album.AlbumID ORDER BY DataID DESC
END
ELSE
BEGIN
 SELECT	DataID,
		Album.AlbumName,
		SupID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus,
		DataImage
		FROM DataStore,Album 
		WHERE DataStore.AlbumID = Album.AlbumID 
		AND Album.AlbumID = @ID ORDER BY DataID DESC	
END
	
	exec aShowAllDataStoreFilter '0'
GO
CREATE PROC aInsertAlbum
	@CateID INT,
	@NameAlbum NVARCHAR(50),
	@AlbumPrice DECIMAL,
	@Quantity INT,
	@AlbumImage NVARCHAR(100),
	@DetailsAlbum NVARCHAR(MAX)
	AS
	INSERT INTO Album(CateID,AlbumName,AlbumPrice,AlbumDateCreate,AlbumStatus,AlbumImage,Quantity,AlbumDetails)
	VALUES(@CateID,@NameAlbum,@AlbumPrice,GETDATE(),'TRUE',@AlbumImage,@Quantity,@DetailsAlbum)
--
GO
CREATE PROC SetPublisAlbum
	@AlbumID INT
	AS
	IF (SELECT Album.AlbumStatus FROM Album WHERE Album.AlbumID = @AlbumID) = 'true'
	BEGIN
		UPDATE Album SET AlbumStatus = 'false' WHERE AlbumID = @AlbumID
	END
	ELSE IF(SELECT Album.AlbumStatus FROM Album WHERE Album.AlbumID = @AlbumID) = 'false'
	BEGIN
		UPDATE Album SET AlbumStatus = 'true' WHERE AlbumID = @AlbumID
	END
--
GO
CREATE PROC aAddDataStoreToList
@AlbumID INT,
@DataID INT
AS
UPDATE DataStore SET AlbumID = @AlbumID WHERE DataID = @DataID
--
GO
CREATE PROC aAddListAlbumToStore
@DataID INT
AS
UPDATE DataStore SET AlbumID = 1 WHERE DataID = @DataID
--
GO
CREATE PROC aUpdateAlbumInfo
@AlbumID INT,
@NameAlbum NVARCHAR(50),
@AlbumPrice DECIMAL,
@AlbumQuantity INT,
@DetailsAlbum NVARCHAR(MAX)
AS
UPDATE Album SET AlbumName = @NameAlbum,
				AlbumPrice = @AlbumPrice,
				Quantity = @AlbumQuantity,
				AlbumDetails = @DetailsAlbum
				WHERE AlbumID = @AlbumID
--
GO
CREATE PROC aShowAlbumCategories
@ID INT
AS
SELECT AlbumID,AlbumName FROM Album ORDER BY AlbumID DESC

EXEC aShowAlbumCategories '1'

GO
CREATE PROC aInserData
@CateAlbumID INT,
@DataName NVARCHAR(100),
@DataPath NVARCHAR(100),
@DataPublish BIT,
@DataImage NVARCHAR(200)
AS
INSERT INTO DataStore(AlbumID,DataName,DataPath,DataDescription,DataPublic,DataStatus,DataImage)
VALUES (@CateAlbumID,@DataName,@DataPath,'',@DataPublish,'true',@DataImage)

GO
CREATE PROC aUpdateUseGuest
@DataID INT
AS
IF(SELECT DataPublic FROM DataStore WHERE DataID = @DataID) = 'true'
BEGIN
	UPDATE DataStore SET DataPublic = 'false' WHERE DataID = @DataID
END
ELSE
BEGIN
	UPDATE DataStore SET DataPublic = 'true' WHERE DataID = @DataID
END

GO
ALTER PROC aGetdataEditData
@DataID INT
AS
SELECT DataID,
		Album.AlbumName,
		SupID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus,
		DataImage
		FROM Album,DataStore
		WHERE DataID = @DataID AND DataStore.AlbumID = Album.AlbumID
		
GO
CREATE PROC aUpdateDataStore
@DataID INT,
@DataName NVARCHAR(100),
@DataImage NVARCHAR(200),
@DataPath NVARCHAR(100)
AS
UPDATE DataStore SET DataName = @DataName,
					DataImage = @DataImage,
					DataPath = @DataPath
					WHERE DataID = @DataID
GO

ALTER PROC aShowOrders
@ID INT
AS
SELECT OrderID,UserAccount,ShipName,ShipAddress, OrderDate, ShipStatus,FoneNumber FROM Orders,Users
WHERE Users.UserID = Orders.UserID ORDER BY Orders.OrderID DESC

EXEC aShowOrders '1'

GO
alter PROC aupdateStatusOrders
@ID INT,
@Status INT
AS
IF @Status = 0
BEGIN
	UPDATE Orders SET ShipStatus = 1 WHERE OrderID = @ID
END
ELSE IF @Status = 1
BEGIN
	UPDATE Orders SET ShipStatus = 2 WHERE OrderID = @ID
END

GO
ALTER PROC aShowOrdersDetails
@ID INT
AS
SELECT Album,UnitPrice,OrderDetails.Quantity,Discount,AlbumName FROM OrderDetails,Album
WHERE OrderID = @ID AND OrderDetails.Album = Album.AlbumID

GO
alter VIEW aShowOrderPending
AS
SELECT  COUNT(ShipStatus) AS 'ShipStatus' FROM Orders WHERE ShipStatus = 0

SELECT * FROM aShowOrderPending

GO
ALTER PROC aShowOrderPendingPr
@ID INT
AS
SELECT OrderID,UserAccount,ShipName,ShipAddress, OrderDate, ShipStatus FROM Orders,Users
WHERE Users.UserID = Orders.UserID AND Orders.ShipStatus = 0 ORDER BY Orders.UserID DESC

exec aShowOrderPendingPr '1'
GO
ALTER PROC aShowUser
@ID INT
AS
SELECT	UserID,
		UserAccount,
		UserName,
		UserSex,
		DateCreate
		FROM Users ORDER BY UserID DESC
		
		EXEC aShowUser '1'
GO
CREATE PROC aShowUserDetails
@ID INT
AS
SELECT UserAccount,
		UserName,
		UserAge,
		UserFone,
		UserSex,
		DateCreate
		FROM Users WHERE UserID = @ID
GO
CREATE PROC aShowCategories_main
@IDType INT
AS
IF @IDType = 0
BEGIN
	SELECT CateID,CateType.CateTypeName,CateName,CateStatus
	FROM Categories,CateType WHERE Categories.CateTypeID = 
	CateType.CateTypeID
END
ELSE 
BEGIN
	SELECT CateID,CateType.CateTypeName,CateName,CateStatus
	FROM Categories,CateType WHERE Categories.CateTypeID =
	 CateType.CateTypeID AND Categories.CateTypeID = @IDType
END

exec aShowCategories_main 1

GO
CREATE PROC aShowCateType
@IS INT
AS
SELECT CateTypeID,CateTypeName FROM CateType

GO
alter PROC aChangePublishCategories
@ID INT
AS
IF(SELECT CateStatus FROM Categories WHERE CateID = @ID) = 'true'
BEGIN
	UPDATE Categories SET CateStatus = 'false' WHERE CateID = @ID
END
ELSE IF(SELECT CateStatus FROM Categories WHERE CateID = @ID) = 'false'
BEGIN
	UPDATE Categories SET CateStatus = 'true' WHERE CateID = @ID
END

exec aChangePublishCategories '1'

GO
CREATE PROC aInsertCategories
@CateType INT,
@CateName NVARCHAR(35),
@CateStatus BIT
AS
INSERT INTO Categories(CateTypeID,CateName,CateStatus) VALUES(@CateType,@CateName,@CateStatus)

GO
CREATE PROC aUpdateCategories
@ID INT,
@CateType INT,
@CateName NVARCHAR(35),
@CateStatus BIT
AS
UPDATE Categories SET CateTypeID = @CateType,
						CateName = @CateName,
						CateStatus = @CateStatus
						WHERE CateID = @ID
						
GO
CREATE PROC aLogin
@Account NVARCHAR(25),
@Password NVARCHAR(100)
AS
SELECT Account,Passwords,Permision.PerName
 FROM UserAdmin,Permision
 WHERE Permision.PerID = UserAdmin.PerID 
 AND Account = @Account AND Passwords = @Password 
 
GO
ALTER PROC aShowCommentWdata
@ID INT
AS
SELECT COUNT(FeedBackID) AS 'countcm' FROM FeedBack
WHERE AlbumID = @ID

EXEC aShowCommentWdata '1'
 