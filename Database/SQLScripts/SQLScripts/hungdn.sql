USE SHOPDVDS
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
