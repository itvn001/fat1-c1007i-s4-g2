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
exec aShowAlbum '0'

SELECT * FROM Album