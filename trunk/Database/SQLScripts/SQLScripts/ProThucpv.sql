Use SHOPDVDS
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
CREATE PROCEDURE loadUserInforFromData
@UserId		NVARCHAR(20)
AS
SELECT * FROM Users WHERE UserAccount = @UserId

GO
CREATE PROCEDURE saveUserInforIntoData
@UserId		NVARCHAR(20),
@UserName	NVARCHAR(30),
@Phone		NVARCHAR(20),
@Address	NVARCHAR(200)
AS
UPDATE Users SET UserName = @UserName, UserFone = @Phone, Address = @Address WHERE UserAccount = @UserId

GO
CREATE PROCEDURE Ordering
@UserId		NVARCHAR(20),
@OrderDate	NVARCHAR(30),
@ShipName	NVARCHAR(30),
@ShipAddress NVARCHAR(100)
AS
INSERT INTO Orders VALUES(@UserId, @OrderDate, @ShipName, @ShipAddress, '100000', 1)

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
SELECT * FROM Orders WHERE UserId = @UserId

GO
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

EXECUTE showOrderDetailsById 7