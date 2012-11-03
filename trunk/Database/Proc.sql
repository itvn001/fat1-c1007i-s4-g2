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
CREATE PROC aShowDataStore
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
CREATE PROC aShowAllDataStore
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
CREATE PROC aShowAllDataStoreFilter
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
CREATE PROC aGetdataEditData
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

CREATE PROC aShowOrders
@ID INT
AS
SELECT OrderID,UserAccount,ShipName,ShipAddress, OrderDate, ShipStatus,FoneNumber FROM Orders,Users
WHERE Users.UserID = Orders.UserID ORDER BY Orders.OrderID DESC

EXEC aShowOrders '1'

GO
CREATE PROC aupdateStatusOrders
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
CREATE PROC aShowOrdersDetails
@ID INT
AS
SELECT Album,UnitPrice,OrderDetails.Quantity,Discount,AlbumName FROM OrderDetails,Album
WHERE OrderID = @ID AND OrderDetails.Album = Album.AlbumID

GO
CREATE VIEW aShowOrderPending
AS
SELECT  COUNT(ShipStatus) AS 'ShipStatus' FROM Orders WHERE ShipStatus = 0

GO
CREATE PROC aShowOrderPendingPr
@ID INT
AS
SELECT OrderID,UserAccount,ShipName,ShipAddress, OrderDate, ShipStatus FROM Orders,Users
WHERE Users.UserID = Orders.UserID AND Orders.ShipStatus = 0 ORDER BY Orders.UserID DESC

exec aShowOrderPendingPr '1'
GO
CREATE PROC aShowUser
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
CREATE PROC aChangePublishCategories
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
 SELECT Account,Passwords
 FROM UserAdmin
 WHERE Account = @Account 
 AND Passwords = @Password 
 
 exec aLogin 'admin','QUqujRWdjKI='
 
 SELECT * FROM  UserAdmin
 
GO
CREATE PROC aShowCommentWdata
@ID INT
AS
SELECT COUNT(FeedBackID) AS 'countcm' FROM FeedBack
WHERE AlbumID = @ID

GO
CREATE PROC aShowCommentAll
@IDAlbum INT
AS
IF @IDAlbum = 0
BEGIN
	SELECT Album.AlbumName,
		UserAccount,
		FeedBackComment,
		FeedBackDateCreate,
		FeedBack.FeedBackStatus,
		FeedBack.FeedBackID,
		FeedBack.AlbumID
		FROM FeedBack,Album,Users
		WHERE FeedBack.AlbumID = Album.AlbumID AND Users.UserID = FeedBack.UserID
		ORDER BY FeedBack.FeedBackID DESC
END
ELSE
BEGIN
	SELECT Album.AlbumName,
		UserAccount,
		FeedBackComment,
		FeedBackDateCreate,
		FeedBack.FeedBackStatus,
		FeedBack.FeedBackID,
		FeedBack.AlbumID
		FROM FeedBack,Album,Users
		 WHERE FeedBack.AlbumID = Album.AlbumID AND 
		FeedBack.AlbumID = @IDAlbum AND Users.UserID = FeedBack.UserID 
		ORDER BY FeedBack.FeedBackID DESC
END

GO
CREATE PROC aUpdateComment
@ID INT,
@Status NVARCHAR
AS
IF @Status != 'true'
BEGIN
	UPDATE FeedBack SET FeedBackStatus = 'true' WHERE FeedBackID = @ID
END

GO
CREATE PROC aDeleteComment
@ID INT
AS
DELETE FeedBack WHERE FeedBackID = @ID

GO
CREATE PROC CreateUserAccount
@UserAccount NVARCHAR(50),
@UserPassword NVARCHAR(50),
@UserName NVARCHAR(50),
@UserAge NVARCHAR(50),
@UserPhone NVARCHAR(50),
@UserSex NVARCHAR(50)
AS
	INSERT INTO Users(UserAccount,UserPassword,UserName,UserAge,UserFone,UserSex,UserStatus)
	VALUES(@UserAccount,@UserPassword,@UserName,@UserAge,@UserPhone,@UserSex,'TRUE')
	
--///////////////////////////////////////////////////////////////////////////////////
GO
Create View showAllCategory
as
Select * from Categories

GO
CREATE PROCEDURE pagingShowAllAlbum
@PageIndex INT, 
@PageSize INT 
AS 
BEGIN
	WITH AlbumRecords AS ( 
		SELECT ROW_NUMBER() OVER (ORDER BY AlbumDateCreate) AS RowIndex, 
			AlbumID, AlbumName, AlbumImage, AlbumPrice 
		FROM Album WHERE AlbumStatus = 'TRUE' AND AlbumID != 1 
	) , GetTotalRowCount AS ( 
		SELECT MAX(RowIndex) AS TotalRowCount 
		FROM AlbumRecords 
	) 
	SELECT AlbumID, AlbumName, AlbumPrice, AlbumImage, TotalRowCount 
	FROM AlbumRecords, GetTotalRowCount 
	WHERE (RowIndex BETWEEN (@PageIndex - 1) * @PageSize + 1 AND @PageIndex*@PageSize) 
END

GO 
CREATE PROCEDURE pagingShowAllAlbumById
@PageIndex INT, 
@PageSize INT,
@pId INT
AS 
BEGIN
	WITH AlbumRecords AS ( 
		SELECT ROW_NUMBER() OVER (ORDER BY AlbumDateCreate) AS RowIndex, 
			AlbumID, AlbumName, AlbumImage, AlbumPrice 
		FROM Album WHERE AlbumStatus = 'TRUE' AND CateID = @pId AND AlbumID != 1
	) , GetTotalRowCount AS ( 
		SELECT MAX(RowIndex) AS TotalRowCount 
		FROM AlbumRecords 
	) 
	SELECT AlbumID, AlbumName, AlbumPrice, AlbumImage, TotalRowCount 
	FROM AlbumRecords, GetTotalRowCount 
	WHERE (RowIndex BETWEEN (@PageIndex - 1) * @PageSize + 1 AND @PageIndex*@PageSize) 
END

GO
CREATE PROCEDURE showInforAlbum
@AlbumID	INT
AS
SELECT Album.AlbumID, Album.CateID, Album.AlbumName, Album.AlbumPrice, Album.AlbumDateCreate, Album.AlbumStatus, Album.AlbumImage, Album.Quantity, Categories.CateName, Album.AlbumDetails
FROM Album left join Categories on Album.CateID = Categories.CateID WHERE AlbumID = @AlbumID


GO
CREATE PROCEDURE listDataSotre
@AlbummID	INT
AS
SELECT * FROM DataStore WHERE AlbumID = @AlbummID


GO
CREATE PROCEDURE getSupplierID
@ALbumID	INT
AS
SELECT SupID FROM Album WHERE AlbumID = @ALbumID


GO
CREATE PROCEDURE showNameSupplier
@SupplierId	INT
AS
SELECT SupName FROM Supplier WHERE SupID = @SupplierId

GO
CREATE PROCEDURE getAlbumById
@AlbumId	INT
AS
SELECT * FROM Album WHERE AlbumID = @AlbumId

GO
--change UserAccount = UserId
CREATE PROCEDURE loadUserInforFromData
@UserId		NVARCHAR(20)
AS
SELECT * FROM Users WHERE UserID = @UserId

GO
--change UserAccount = UserId
CREATE PROCEDURE saveUserInforIntoData
@UserId		NVARCHAR(20),
@UserName	NVARCHAR(30),
@Phone		NVARCHAR(20),
@Address	NVARCHAR(200)
AS
UPDATE Users SET UserName = @UserName, UserFone = @Phone, Address = @Address WHERE UserID = @UserId

GO

CREATE PROCEDURE Ordering
@UserId		NVARCHAR(20),
@OrderDate	NVARCHAR(30),
@ShipName	NVARCHAR(30),
@ShipAddress NVARCHAR(100)
AS
INSERT INTO Orders(UserID,OrderDate,ShipName,ShipAddress,ShipStatus) 
VALUES(@UserId, GETDATE(), @ShipName, @ShipAddress,0)

GO
CREATE PROCEDURE OrderingDetail
@OrderId	INT,
@ALbumID	INT,
@Price		DECIMAL,
@Quantity	INT
AS
INSERT INTO OrderDetails VALUES( @OrderId, @ALbumID, @Price, @Quantity, 0)

GO
CREATE VIEW returnLastOrderId
AS
SELECT TOP 1 OrderId FROM Orders ORDER BY OrderId DESC

GO

CREATE PROCEDURE listOrderByUserId
@UserId		INT
AS
SELECT * FROM Orders WHERE UserId = @UserId ORDER BY Orders.OrderID DESC

GO
--change
CREATE PROCEDURE listOrderDetailByOrderId
@OrderId	INT
AS
SELECT OD.UnitPrice, OD.Quantity FROM OrderDetails AS OD WHERE OD.OrderID = @OrderId

GO
CREATE PROCEDURE showOrderDetailsById
@OrderId	INT
AS
SELECT OD.Album, OD.UnitPrice, OD.Quantity, OD.Discount, A.AlbumName FROM OrderDetails AS OD
INNER JOIN Album AS A ON OD.Album = A.AlbumID WHERE OD.OrderID = @OrderId

GO
CREATE PROCEDURE changeStatusOrder
@OrderId	INT
AS
UPDATE Orders SET ShipStatus = 3 WHERE OrderID = @OrderId AND ShipStatus = 0

GO
CREATE PROCEDURE showCollectionCateById
@UserId		INT
AS
SELECT * FROM CollectionCate WHERE UserID = @UserId

GO
CREATE PROCEDURE addDataStoteIntoMyList
@CollectionId	INT,
@DataId			INT
AS
INSERT INTO CollectionUser  VALUES(@CollectionId, @DataId, NULL)

GO
CREATE PROCEDURE checkExistDataStore
@CollectionId	INT,
@DataId			INT
AS
SELECT * FROM CollectionUser WHERE CollectionCate = @CollectionId AND DataID = @DataId

GO
CREATE PROCEDURE insertNewPlaylist
@CateName		NVARCHAR(50),
@UserId			INT
AS
INSERT INTO CollectionCate VALUES(@CateName, @UserId, GETDATE())

GO
CREATE PROCEDURE listCollectionCateNew
@UserId			INT
AS
SELECT TOP 1 * FROM CollectionCate WHERE UserID = @UserId ORDER BY DateCreate DESC

/*Co the la moi chua chac *_^ */
GO
CREATE PROCEDURE showInforFromData
@UserId			INT
AS
SELECT * FROM Users WHERE UserID = @UserId

GO
CREATE PROCEDURE deleteCollectionCateById
@CollectionId	INT
AS
DELETE FROM CollectionUser WHERE CollectionCate = @CollectionId
DELETE FROM CollectionCate WHERE CollecCateID = @CollectionId

GO
CREATE PROCEDURE showDataStoreById
@DataStoreId	INT
AS
SELECT * FROM DataStore WHERE DataID = @DataStoreId

GO
CREATE PROCEDURE listCollectionUserByPlaylistId
@CollectionUserId	INT
AS
SELECT * FROM CollectionUser WHERE CollectionCate = @CollectionUserId

GO
CREATE PROCEDURE listCollectionCateById
@CollectionCateId	INT,
@UserId				INT
AS
SELECT * FROM CollectionCate WHERE CollecCateID = @CollectionCateId AND UserID = @UserId

GO
CREATE PROCEDURE checkLogin
@UserName			NVARCHAR(20),
@Pass				NVARCHAR(70)
AS
SELECT * FROM Users WHERE UserAccount = @UserName AND UserPassword = @Pass

GO
CREATE PROCEDURE deleteDataStoreInPlaylist
@PlaylistId			INT,
@DataStoreId		INT
AS
DELETE FROM CollectionUser WHERE CollectionCate = @PlaylistId AND DataID = @DataStoreId

EXECUTE checkLogin 'vanthuc', 'u+ebIiMqslo='