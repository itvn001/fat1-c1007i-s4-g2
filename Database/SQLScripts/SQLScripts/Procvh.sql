USE SHOPDVDS
--
GO
alter VIEW aShowCategories
AS
SELECT CateID,CateTypeID,CateName,CateStatus FROM Categories
--
GO
alter PROC aShowAlbum
@CateID INT
AS
IF @CateID = 0
BEGIN
	SELECT AlbumID,
		CateID,
		AlbumName,
		AlbumPrice,
		AlbumDateCreate,
		AlbumStatus
		FROM Album
END
ELSE IF @CateID != 0
BEGIN
SELECT AlbumID,
		CateID,
		AlbumName,
		AlbumPrice,
		AlbumDateCreate,
		AlbumStatus
		FROM Album WHERE CateID = @CateID
END
exec aShowAlbum '1'
GO
-----
ALTER PROC aShowEditAlbum
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
		Quantity
		FROM Album,Categories
		WHERE AlbumID = @AlbumID AND Album.CateID = Categories.CateID
---
GO
CREATE PROC aShowDataStore
@AlbumID INT
AS
SELECT DataID,
		AlbumID,
		SupID,
		DataName,
		DataPath,
		DataDescription,
		DataPublic,
		DataStatus
		FROM DataStore
WHERE AlbumID = @AlbumID		