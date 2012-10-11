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
		FROM Album WHERE AlbumStatus = 'TRUE'
	) , GetTotalRowCount AS ( 
		SELECT MAX(RowIndex) AS TotalRowCount 
		FROM AlbumRecords 
	) 
	SELECT AlbumID, AlbumName, AlbumPrice, AlbumImage, TotalRowCount 
	FROM AlbumRecords, GetTotalRowCount 
	WHERE (RowIndex BETWEEN (@PageIndex - 1) * @PageSize + 1 AND @PageIndex*@PageSize) 
END

execute pagingShowAllAlbum 1, 1
select * from album