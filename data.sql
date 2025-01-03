USE [SaigonWaterBusFinal]
GO
/****** Object:  User [vinh1]    Script Date: 2024-12-25 11:50:30 AM ******/
CREATE USER [vinh1] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[vinh1]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [vinh1]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [vinh1]
GO
/****** Object:  Schema [vinh1]    Script Date: 2024-12-25 11:50:30 AM ******/
CREATE SCHEMA [vinh1]
GO
/****** Object:  Table [dbo].[account]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[dia_chi] [nvarchar](max) NULL,
	[email] [varchar](255) NULL,
	[ho_ten] [nvarchar](max) NULL,
	[id_facebook] [nvarchar](max) NULL,
	[password] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[sodt] [nvarchar](max) NULL,
	[status] [bit] NOT NULL,
	[auth_sdt] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[chuyen]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[chuyen](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[co_dinh] [bit] NULL,
	[ghe_trong] [int] NULL,
	[gio_den] [nvarchar](255) NULL,
	[gio_di] [nvarchar](255) NULL,
	[ngay_khoi_hanh] [date] NULL,
	[status] [bit] NULL,
	[id_tai_xe] [bigint] NULL,
	[id_tuyen] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hoa_don]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hoa_don](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[ngay_tao] [date] NULL,
	[so_luong_ghe] [int] NULL,
	[so_luong_ve] [int] NULL,
	[status] [bit] NULL,
	[ten_ghe] [varchar](255) NULL,
	[thanh_tien] [float] NULL,
	[id_account] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tai_xe]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tai_xe](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[dia_chi] [nvarchar](255) NULL,
	[ho_ten] [nvarchar](255) NULL,
	[sodt] [nvarchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tau]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tau](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[bien_so_tau] [nvarchar](255) NULL,
	[so_luong_ghe] [int] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tuyen]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tuyen](
	[id] [nvarchar](255) NOT NULL,
	[ben_den] [nvarchar](255) NULL,
	[ben_di] [nvarchar](255) NULL,
	[ben_dung] [nvarchar](255) NULL,
	[status] [bit] NULL,
	[ten_tuyen] [nvarchar](255) NULL,
	[id_tau] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ve]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ve](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[gia_ve] [float] NULL,
	[status] [bit] NULL,
	[ten_ghe] [varchar](255) NULL,
	[id_chuyen] [bigint] NULL,
	[id_hoa_don] [bigint] NULL,
	[row_number] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (1, N'Gò Vấp', N'kuteboylove19992@gmail.com', N'Nguyễn Hoàng Vinh', N'3492228344389833', N'$2a$10$5HyTPAx1P1Br2kQzJ5yOXe0GGYjpC3Tsk9WRVoVkK02Yig7fcmTDO', N'ADMIN', N'0393211895', 1, 1)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (3, N'Quảng Nam oki', N'nguyenvinhdev260399@gmail.com', N'Nguyễn User', N'3492228344389833', N'$2a$10$i4TVke8uPkn.dh3ISxTtjON.Dz/LrbwdfjEtrA6xEmEc6K/JxGxrS', N'USER', N'0332312887', 1, NULL)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (11, N'Hà Nội New', N'vipchipcoi1230@gmail.com', N'Nguyễn Hoàng Vinh', N'3492228344389833', N'$2a$10$5HyTPAx1P1Br2kQzJ5yOXe0GGYjpC3Tsk9WRVoVkK02Yig7fcmTDO', N'USER', N'0393211895', 1, 1)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (13, NULL, N'phuongvu.xu@gmail.com', N'Nguyễn phương', NULL, N'$2a$10$1KeizgoZw1OQNLwerrBX8eTTXAgRWbeMSnch2mJdQ6T1DM/RfZocu', N'USER', NULL, 0, NULL)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (14, NULL, N'phuongvttps26491@fpt.edu.vn', N'Phương', NULL, N'$2a$10$nSzpzvSJYXIpMuzvIjYsa.gRvwT4q7FhNgHYzud9uNMhn9y1xRkF.', N'ADMIN', NULL, 0, NULL)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (15, NULL, N'diemtran147604@gmail.com', N'Nguyễn Hoàng Vinh', NULL, N'$2a$10$ACLM6.mhYz4LYiQ/Qt2IwejCJNZLoQqGcC5PiAGk5.uuMz6k.PKlW', N'USER', NULL, 0, NULL)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (16, N'Gò vấp', N'vinhnhps26792@fpt.edu.vn', N'Nguyễn Hoàng Vinh', NULL, N'$2a$10$k9wab5YnC47fU.y2bXygcueeYhppsYWepv8NOzxgMhisq1RN.tv.S', N'USER', NULL, 1, NULL)
INSERT [dbo].[account] ([id], [dia_chi], [email], [ho_ten], [id_facebook], [password], [role], [sodt], [status], [auth_sdt]) VALUES (17, NULL, N'hoangvinh260399@gmail.com', N'Nguyễn vinh', NULL, N'$2a$10$GEdOzQk.oi1i8swC8HnB7OZbA.NyO2NRIyMDUmKLBqmQtCIP9Ocn2', N'USER', NULL, 0, NULL)
SET IDENTITY_INSERT [dbo].[account] OFF
GO
SET IDENTITY_INSERT [dbo].[chuyen] ON 

INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (11, 1, 34, N'08:30', N'07:30', CAST(N'2024-05-23' AS Date), 1, 6, N'TD01')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (12, 1, 34, N'10:30', N'09:30', CAST(N'2024-05-23' AS Date), 1, 6, N'TV01')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (13, 1, 34, N'16:30', N'15:30', CAST(N'2024-05-23' AS Date), 1, 7, N'TD02')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (14, 1, 34, N'18:06', N'17:30', CAST(N'2024-05-23' AS Date), 1, 7, N'TV02')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (15, 1, 34, N'08:30', N'07:30', CAST(N'2024-05-24' AS Date), 1, 8, N'TD03')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (16, 1, 34, N'10:30', N'09:30', CAST(N'2024-05-24' AS Date), 1, 8, N'TV03')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (17, 1, 34, N'16:30', N'15:30', CAST(N'2024-05-24' AS Date), 1, 9, N'TD04')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (18, 1, 34, N'18:30', N'17:30', CAST(N'2024-05-24' AS Date), 1, 9, N'TV04')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (19, 1, 34, N'08:30', N'07:30', CAST(N'2024-05-22' AS Date), 1, 10, N'TD05')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (20, 1, 34, N'10:30', N'09:30', CAST(N'2024-05-22' AS Date), 1, 10, N'TV05')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (21, 1, 34, N'16:30', N'15:30', CAST(N'2024-05-22' AS Date), 1, 11, N'TD06')
INSERT [dbo].[chuyen] ([id], [co_dinh], [ghe_trong], [gio_den], [gio_di], [ngay_khoi_hanh], [status], [id_tai_xe], [id_tuyen]) VALUES (22, 1, 34, N'18:30', N'17:30', CAST(N'2024-05-22' AS Date), 1, 11, N'TV06')
SET IDENTITY_INSERT [dbo].[chuyen] OFF
GO
SET IDENTITY_INSERT [dbo].[hoa_don] ON 

INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (4, CAST(N'2024-02-19' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (5, CAST(N'2024-02-19' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (6, CAST(N'2024-02-19' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (7, CAST(N'2024-02-19' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (8, CAST(N'2024-02-20' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (9, CAST(N'2024-02-20' AS Date), 4, NULL, NULL, N'C09,C10,C11,C12', 60000, 13)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (10, CAST(N'2024-02-20' AS Date), 3, NULL, NULL, N'A01,A02,A03', 45000, 1)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (11, CAST(N'2024-02-21' AS Date), 4, NULL, NULL, N'B05,B06,B07,B08', 60000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (12, CAST(N'2024-02-22' AS Date), 2, NULL, NULL, N'A03,A04', 30000, NULL)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (13, CAST(N'2024-02-23' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (14, CAST(N'2024-02-23' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (15, CAST(N'2024-02-24' AS Date), 2, NULL, NULL, N'B05,B06', 30000, 1)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (16, CAST(N'2024-02-23' AS Date), 3, NULL, NULL, N'A01,A02,B06', 45000, 1)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (17, CAST(N'2024-02-25' AS Date), 2, NULL, NULL, N'A03,B07', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (18, CAST(N'2024-02-23' AS Date), 2, NULL, NULL, N'A03,A04', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (19, CAST(N'2024-02-26' AS Date), 2, NULL, NULL, N'A03,B07', 750000, 14)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (20, CAST(N'2024-02-23' AS Date), 5, NULL, NULL, N'A01,A02,A03,A04,B05', 75000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (21, CAST(N'2024-02-02' AS Date), 4, NULL, NULL, N'A02,A03,B06,B07', 60000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (22, CAST(N'2024-02-24' AS Date), 4, NULL, NULL, N'A02,A03,B06,B07', 60000, NULL)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (23, CAST(N'2024-02-25' AS Date), 4, NULL, NULL, N'A01,A02,A03,A04', 60000, 1)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (24, CAST(N'2024-02-25' AS Date), 3, NULL, NULL, N'A01,A02,A03', 45000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (25, CAST(N'2024-02-25' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (26, CAST(N'2024-02-25' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (27, CAST(N'2024-02-25' AS Date), 2, NULL, NULL, N'A01,A02', 30000, 3)
INSERT [dbo].[hoa_don] ([id], [ngay_tao], [so_luong_ghe], [so_luong_ve], [status], [ten_ghe], [thanh_tien], [id_account]) VALUES (28, CAST(N'2024-04-11' AS Date), 2, NULL, NULL, N'533,534', 30000, 1)
SET IDENTITY_INSERT [dbo].[hoa_don] OFF
GO
SET IDENTITY_INSERT [dbo].[tai_xe] ON 

INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (6, N'Quận 12', N'Nguyễn Văn An', N'0123584697', 1)
INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (7, N'Quận 2', N'Vũ Dung Hòa', N'0351264798', 1)
INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (8, N'Quận 11', N'Trần Văn Siêu', N'0416387921', 1)
INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (9, N'Quận 7', N'Nguyễn Phúc Long', N'0456327891', 1)
INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (10, N'Quận 5', N'Hoàng Tuấn Minh', N'0419786323', 1)
INSERT [dbo].[tai_xe] ([id], [dia_chi], [ho_ten], [sodt], [status]) VALUES (11, N'Quận 8', N'Lại Văn Sâm', N'0486321791', 1)
SET IDENTITY_INSERT [dbo].[tai_xe] OFF
GO
SET IDENTITY_INSERT [dbo].[tau] ON 

INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (1, N'SG10001', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (2, N'SG10002', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (3, N'SG10003', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (4, N'SG10004', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (5, N'SG10005', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (6, N'SG10006', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (7, N'SG10007', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (8, N'SG10008', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (9, N'SG10009', 34, 1)
INSERT [dbo].[tau] ([id], [bien_so_tau], [so_luong_ghe], [status]) VALUES (10, N'SG10010', 34, 1)
SET IDENTITY_INSERT [dbo].[tau] OFF
GO
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD01', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 01', 1)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD02', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 02', 2)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD03', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 03', 3)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD04', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 04', 4)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD05', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 05', 5)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TD06', N'Linh Đông', N'Bạch Đằng', N'Bình An, Thanh Đa, Hiệp Bình Chánh, Linh Đông', 1, N'Bạch Đằng - Linh Đông 06', 6)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV01', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 01', 1)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV02', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 02', 2)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV03', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 03', 3)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV04', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 04', 4)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV05', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 05', 5)
INSERT [dbo].[tuyen] ([id], [ben_den], [ben_di], [ben_dung], [status], [ten_tuyen], [id_tau]) VALUES (N'TV06', N'Bạch Đằng', N'Linh Đông', N'Hiệp Bình Chánh, Thanh Đa, Bình An, Bạch Đằng ', 1, N'Linh Đông - Bạch Đằng 06', 6)
GO
SET IDENTITY_INSERT [dbo].[ve] ON 

INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (511, 15000, 0, N'A01', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (512, 15000, 0, N'A02', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (513, 15000, 0, N'A03', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (514, 15000, 0, N'A04', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (515, 15000, 0, N'B05', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (516, 15000, 0, N'B06', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (517, 15000, 0, N'B07', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (518, 15000, 0, N'B08', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (519, 15000, 0, N'C09', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (520, 15000, 0, N'C10', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (521, 15000, 0, N'C11', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (522, 15000, 0, N'C12', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (523, 15000, 0, N'D13', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (524, 15000, 0, N'D14', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (525, 15000, 0, N'D15', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (526, 15000, 0, N'D16', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (527, 15000, 0, N'E17', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (528, 15000, 0, N'E18', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (529, 15000, 0, N'E19', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (530, 15000, 0, N'E20', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (531, 15000, 0, N'F21', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (532, 15000, 0, N'F22', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (533, 15000, 0, N'F23', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (534, 15000, 0, N'F24', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (535, 15000, 0, N'G25', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (536, 15000, 0, N'G26', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (537, 15000, 0, N'G27', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (538, 15000, 0, N'G28', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (539, 15000, 0, N'H29', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (540, 15000, 0, N'H30', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (541, 15000, 0, N'H31', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (542, 15000, 0, N'H32', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (543, 15000, 0, N'I33', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (544, 15000, 0, N'I34', 11, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (545, 15000, 0, N'A01', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (546, 15000, 0, N'A02', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (547, 15000, 0, N'A03', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (548, 15000, 0, N'A04', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (549, 15000, 0, N'B05', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (550, 15000, 0, N'B06', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (551, 15000, 0, N'B07', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (552, 15000, 0, N'B08', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (553, 15000, 0, N'C09', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (554, 15000, 0, N'C10', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (555, 15000, 0, N'C11', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (556, 15000, 0, N'C12', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (557, 15000, 0, N'D13', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (558, 15000, 0, N'D14', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (559, 15000, 0, N'D15', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (560, 15000, 0, N'D16', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (561, 15000, 0, N'E17', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (562, 15000, 0, N'E18', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (563, 15000, 0, N'E19', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (564, 15000, 0, N'E20', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (565, 15000, 0, N'F21', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (566, 15000, 0, N'F22', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (567, 15000, 0, N'F23', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (568, 15000, 0, N'F24', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (569, 15000, 0, N'G25', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (570, 15000, 0, N'G26', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (571, 15000, 0, N'G27', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (572, 15000, 0, N'G28', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (573, 15000, 0, N'H29', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (574, 15000, 0, N'H30', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (575, 15000, 0, N'H31', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (576, 15000, 0, N'H32', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (577, 15000, 0, N'I33', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (578, 15000, 0, N'I34', 12, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (579, 15000, 0, N'A01', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (580, 15000, 0, N'A02', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (581, 15000, 0, N'A03', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (582, 15000, 0, N'A04', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (583, 15000, 0, N'B05', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (584, 15000, 0, N'B06', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (585, 15000, 0, N'B07', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (586, 15000, 0, N'B08', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (587, 15000, 0, N'C09', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (588, 15000, 0, N'C10', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (589, 15000, 0, N'C11', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (590, 15000, 0, N'C12', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (591, 15000, 0, N'D13', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (592, 15000, 0, N'D14', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (593, 15000, 0, N'D15', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (594, 15000, 0, N'D16', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (595, 15000, 0, N'E17', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (596, 15000, 0, N'E18', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (597, 15000, 0, N'E19', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (598, 15000, 0, N'E20', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (599, 15000, 0, N'F21', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (600, 15000, 0, N'F22', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (601, 15000, 0, N'F23', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (602, 15000, 0, N'F24', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (603, 15000, 0, N'G25', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (604, 15000, 0, N'G26', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (605, 15000, 0, N'G27', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (606, 15000, 0, N'G28', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (607, 15000, 0, N'H29', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (608, 15000, 0, N'H30', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (609, 15000, 0, N'H31', 13, NULL, NULL)
GO
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (610, 15000, 0, N'H32', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (611, 15000, 0, N'I33', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (612, 15000, 0, N'I34', 13, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (613, 15000, 0, N'A01', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (614, 15000, 0, N'A02', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (615, 15000, 0, N'A03', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (616, 15000, 0, N'A04', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (617, 15000, 0, N'B05', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (618, 15000, 0, N'B06', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (619, 15000, 0, N'B07', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (620, 15000, 0, N'B08', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (621, 15000, 0, N'C09', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (622, 15000, 0, N'C10', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (623, 15000, 0, N'C11', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (624, 15000, 0, N'C12', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (625, 15000, 0, N'D13', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (626, 15000, 0, N'D14', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (627, 15000, 0, N'D15', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (628, 15000, 0, N'D16', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (629, 15000, 0, N'E17', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (630, 15000, 0, N'E18', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (631, 15000, 0, N'E19', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (632, 15000, 0, N'E20', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (633, 15000, 0, N'F21', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (634, 15000, 0, N'F22', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (635, 15000, 0, N'F23', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (636, 15000, 0, N'F24', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (637, 15000, 0, N'G25', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (638, 15000, 0, N'G26', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (639, 15000, 0, N'G27', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (640, 15000, 0, N'G28', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (641, 15000, 0, N'H29', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (642, 15000, 0, N'H30', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (643, 15000, 0, N'H31', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (644, 15000, 0, N'H32', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (645, 15000, 0, N'I33', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (646, 15000, 0, N'I34', 14, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (647, 15000, 0, N'A01', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (648, 15000, 0, N'A02', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (649, 15000, 0, N'A03', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (650, 15000, 0, N'A04', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (651, 15000, 0, N'B05', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (652, 15000, 0, N'B06', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (653, 15000, 0, N'B07', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (654, 15000, 0, N'B08', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (655, 15000, 0, N'C09', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (656, 15000, 0, N'C10', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (657, 15000, 0, N'C11', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (658, 15000, 0, N'C12', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (659, 15000, 0, N'D13', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (660, 15000, 0, N'D14', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (661, 15000, 0, N'D15', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (662, 15000, 0, N'D16', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (663, 15000, 0, N'E17', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (664, 15000, 0, N'E18', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (665, 15000, 0, N'E19', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (666, 15000, 0, N'E20', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (667, 15000, 0, N'F21', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (668, 15000, 0, N'F22', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (669, 15000, 0, N'F23', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (670, 15000, 0, N'F24', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (671, 15000, 0, N'G25', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (672, 15000, 0, N'G26', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (673, 15000, 0, N'G27', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (674, 15000, 0, N'G28', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (675, 15000, 0, N'H29', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (676, 15000, 0, N'H30', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (677, 15000, 0, N'H31', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (678, 15000, 0, N'H32', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (679, 15000, 0, N'I33', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (680, 15000, 0, N'I34', 15, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (681, 15000, 0, N'A01', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (682, 15000, 0, N'A02', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (683, 15000, 0, N'A03', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (684, 15000, 0, N'A04', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (685, 15000, 0, N'B05', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (686, 15000, 0, N'B06', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (687, 15000, 0, N'B07', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (688, 15000, 0, N'B08', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (689, 15000, 0, N'C09', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (690, 15000, 0, N'C10', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (691, 15000, 0, N'C11', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (692, 15000, 0, N'C12', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (693, 15000, 0, N'D13', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (694, 15000, 0, N'D14', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (695, 15000, 0, N'D15', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (696, 15000, 0, N'D16', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (697, 15000, 0, N'E17', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (698, 15000, 0, N'E18', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (699, 15000, 0, N'E19', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (700, 15000, 0, N'E20', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (701, 15000, 0, N'F21', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (702, 15000, 0, N'F22', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (703, 15000, 0, N'F23', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (704, 15000, 0, N'F24', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (705, 15000, 0, N'G25', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (706, 15000, 0, N'G26', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (707, 15000, 0, N'G27', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (708, 15000, 0, N'G28', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (709, 15000, 0, N'H29', 16, NULL, NULL)
GO
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (710, 15000, 0, N'H30', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (711, 15000, 0, N'H31', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (712, 15000, 0, N'H32', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (713, 15000, 0, N'I33', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (714, 15000, 0, N'I34', 16, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (715, 15000, 0, N'A01', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (716, 15000, 0, N'A02', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (717, 15000, 0, N'A03', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (718, 15000, 0, N'A04', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (719, 15000, 0, N'B05', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (720, 15000, 0, N'B06', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (721, 15000, 0, N'B07', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (722, 15000, 0, N'B08', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (723, 15000, 0, N'C09', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (724, 15000, 0, N'C10', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (725, 15000, 0, N'C11', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (726, 15000, 0, N'C12', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (727, 15000, 0, N'D13', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (728, 15000, 0, N'D14', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (729, 15000, 0, N'D15', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (730, 15000, 0, N'D16', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (731, 15000, 0, N'E17', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (732, 15000, 0, N'E18', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (733, 15000, 0, N'E19', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (734, 15000, 0, N'E20', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (735, 15000, 0, N'F21', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (736, 15000, 0, N'F22', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (737, 15000, 0, N'F23', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (738, 15000, 0, N'F24', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (739, 15000, 0, N'G25', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (740, 15000, 0, N'G26', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (741, 15000, 0, N'G27', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (742, 15000, 0, N'G28', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (743, 15000, 0, N'H29', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (744, 15000, 0, N'H30', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (745, 15000, 0, N'H31', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (746, 15000, 0, N'H32', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (747, 15000, 0, N'I33', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (748, 15000, 0, N'I34', 17, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (749, 15000, 0, N'A01', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (750, 15000, 0, N'A02', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (751, 15000, 0, N'A03', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (752, 15000, 0, N'A04', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (753, 15000, 0, N'B05', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (754, 15000, 0, N'B06', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (755, 15000, 0, N'B07', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (756, 15000, 0, N'B08', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (757, 15000, 0, N'C09', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (758, 15000, 0, N'C10', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (759, 15000, 0, N'C11', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (760, 15000, 0, N'C12', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (761, 15000, 0, N'D13', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (762, 15000, 0, N'D14', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (763, 15000, 0, N'D15', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (764, 15000, 0, N'D16', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (765, 15000, 0, N'E17', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (766, 15000, 0, N'E18', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (767, 15000, 0, N'E19', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (768, 15000, 0, N'E20', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (769, 15000, 0, N'F21', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (770, 15000, 0, N'F22', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (771, 15000, 0, N'F23', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (772, 15000, 0, N'F24', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (773, 15000, 0, N'G25', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (774, 15000, 0, N'G26', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (775, 15000, 0, N'G27', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (776, 15000, 0, N'G28', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (777, 15000, 0, N'H29', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (778, 15000, 0, N'H30', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (779, 15000, 0, N'H31', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (780, 15000, 0, N'H32', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (781, 15000, 0, N'I33', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (782, 15000, 0, N'I34', 18, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (783, 15000, 1, N'A01', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (784, 15000, 1, N'A02', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (785, 15000, 1, N'A03', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (786, 15000, 1, N'A04', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (787, 15000, 1, N'B05', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (788, 15000, 1, N'B06', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (789, 15000, 1, N'B07', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (790, 15000, 1, N'B08', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (791, 15000, 1, N'C09', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (792, 15000, 1, N'C10', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (793, 15000, 1, N'C11', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (794, 15000, 1, N'C12', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (795, 15000, 1, N'D13', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (796, 15000, 1, N'D14', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (797, 15000, 1, N'D15', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (798, 15000, 1, N'D16', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (799, 15000, 1, N'E17', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (800, 15000, 1, N'E18', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (801, 15000, 1, N'E19', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (802, 15000, 1, N'E20', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (803, 15000, 1, N'F21', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (804, 15000, 1, N'F22', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (805, 15000, 1, N'F23', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (806, 15000, 1, N'F24', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (807, 15000, 1, N'G25', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (808, 15000, 1, N'G26', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (809, 15000, 1, N'G27', 19, NULL, NULL)
GO
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (810, 15000, 1, N'G28', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (811, 15000, 1, N'H29', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (812, 15000, 1, N'H30', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (813, 15000, 1, N'H31', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (814, 15000, 1, N'H32', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (815, 15000, 1, N'I33', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (816, 15000, 1, N'I34', 19, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (817, 15000, 1, N'A01', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (818, 15000, 1, N'A02', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (819, 15000, 1, N'A03', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (820, 15000, 1, N'A04', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (821, 15000, 1, N'B05', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (822, 15000, 1, N'B06', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (823, 15000, 1, N'B07', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (824, 15000, 1, N'B08', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (825, 15000, 1, N'C09', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (826, 15000, 1, N'C10', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (827, 15000, 1, N'C11', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (828, 15000, 1, N'C12', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (829, 15000, 1, N'D13', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (830, 15000, 1, N'D14', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (831, 15000, 1, N'D15', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (832, 15000, 1, N'D16', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (833, 15000, 1, N'E17', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (834, 15000, 1, N'E18', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (835, 15000, 1, N'E19', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (836, 15000, 1, N'E20', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (837, 15000, 1, N'F21', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (838, 15000, 1, N'F22', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (839, 15000, 1, N'F23', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (840, 15000, 1, N'F24', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (841, 15000, 1, N'G25', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (842, 15000, 1, N'G26', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (843, 15000, 1, N'G27', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (844, 15000, 1, N'G28', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (845, 15000, 1, N'H29', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (846, 15000, 1, N'H30', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (847, 15000, 1, N'H31', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (848, 15000, 1, N'H32', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (849, 15000, 1, N'I33', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (850, 15000, 1, N'I34', 20, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (851, 15000, 1, N'A01', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (852, 15000, 1, N'A02', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (853, 15000, 1, N'A03', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (854, 15000, 1, N'A04', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (855, 15000, 1, N'B05', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (856, 15000, 1, N'B06', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (857, 15000, 1, N'B07', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (858, 15000, 1, N'B08', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (859, 15000, 1, N'C09', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (860, 15000, 1, N'C10', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (861, 15000, 1, N'C11', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (862, 15000, 1, N'C12', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (863, 15000, 1, N'D13', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (864, 15000, 1, N'D14', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (865, 15000, 1, N'D15', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (866, 15000, 1, N'D16', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (867, 15000, 1, N'E17', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (868, 15000, 1, N'E18', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (869, 15000, 1, N'E19', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (870, 15000, 1, N'E20', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (871, 15000, 1, N'F21', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (872, 15000, 1, N'F22', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (873, 15000, 1, N'F23', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (874, 15000, 1, N'F24', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (875, 15000, 1, N'G25', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (876, 15000, 1, N'G26', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (877, 15000, 1, N'G27', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (878, 15000, 1, N'G28', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (879, 15000, 1, N'H29', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (880, 15000, 1, N'H30', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (881, 15000, 1, N'H31', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (882, 15000, 1, N'H32', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (883, 15000, 1, N'I33', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (884, 15000, 1, N'I34', 21, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (885, 15000, 1, N'A01', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (886, 15000, 1, N'A02', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (887, 15000, 1, N'A03', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (888, 15000, 1, N'A04', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (889, 15000, 1, N'B05', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (890, 15000, 1, N'B06', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (891, 15000, 1, N'B07', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (892, 15000, 1, N'B08', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (893, 15000, 1, N'C09', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (894, 15000, 1, N'C10', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (895, 15000, 1, N'C11', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (896, 15000, 1, N'C12', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (897, 15000, 1, N'D13', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (898, 15000, 1, N'D14', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (899, 15000, 1, N'D15', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (900, 15000, 1, N'D16', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (901, 15000, 1, N'E17', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (902, 15000, 1, N'E18', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (903, 15000, 1, N'E19', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (904, 15000, 1, N'E20', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (905, 15000, 1, N'F21', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (906, 15000, 1, N'F22', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (907, 15000, 1, N'F23', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (908, 15000, 1, N'F24', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (909, 15000, 1, N'G25', 22, NULL, NULL)
GO
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (910, 15000, 1, N'G26', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (911, 15000, 1, N'G27', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (912, 15000, 1, N'G28', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (913, 15000, 1, N'H29', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (914, 15000, 1, N'H30', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (915, 15000, 1, N'H31', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (916, 15000, 1, N'H32', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (917, 15000, 1, N'I33', 22, NULL, NULL)
INSERT [dbo].[ve] ([id], [gia_ve], [status], [ten_ghe], [id_chuyen], [id_hoa_don], [row_number]) VALUES (918, 15000, 1, N'I34', 22, NULL, NULL)
SET IDENTITY_INSERT [dbo].[ve] OFF
GO
ALTER TABLE [dbo].[chuyen]  WITH CHECK ADD  CONSTRAINT [FK65qqnr22j3jf5j8odksb5y1gy] FOREIGN KEY([id_tai_xe])
REFERENCES [dbo].[tai_xe] ([id])
GO
ALTER TABLE [dbo].[chuyen] CHECK CONSTRAINT [FK65qqnr22j3jf5j8odksb5y1gy]
GO
ALTER TABLE [dbo].[chuyen]  WITH CHECK ADD  CONSTRAINT [FK70vtexqi2y8kf1mfiu3jp5rqm] FOREIGN KEY([id_tuyen])
REFERENCES [dbo].[tuyen] ([id])
GO
ALTER TABLE [dbo].[chuyen] CHECK CONSTRAINT [FK70vtexqi2y8kf1mfiu3jp5rqm]
GO
ALTER TABLE [dbo].[hoa_don]  WITH CHECK ADD  CONSTRAINT [FKlvh1gc7oa3w69lja4t58ylytr] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[hoa_don] CHECK CONSTRAINT [FKlvh1gc7oa3w69lja4t58ylytr]
GO
ALTER TABLE [dbo].[tuyen]  WITH CHECK ADD  CONSTRAINT [FKhxuqsr1ixhsldibhe8hvku7me] FOREIGN KEY([id_tau])
REFERENCES [dbo].[tau] ([id])
GO
ALTER TABLE [dbo].[tuyen] CHECK CONSTRAINT [FKhxuqsr1ixhsldibhe8hvku7me]
GO
ALTER TABLE [dbo].[ve]  WITH CHECK ADD  CONSTRAINT [FKb9j8ckgi7ja4gcnohbs0gesgr] FOREIGN KEY([id_hoa_don])
REFERENCES [dbo].[hoa_don] ([id])
GO
ALTER TABLE [dbo].[ve] CHECK CONSTRAINT [FKb9j8ckgi7ja4gcnohbs0gesgr]
GO
ALTER TABLE [dbo].[ve]  WITH CHECK ADD  CONSTRAINT [FKjoeit2n2oe6ixotg27el33rb4] FOREIGN KEY([id_chuyen])
REFERENCES [dbo].[chuyen] ([id])
GO
ALTER TABLE [dbo].[ve] CHECK CONSTRAINT [FKjoeit2n2oe6ixotg27el33rb4]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD CHECK  (([role]='USER' OR [role]='ADMIN'))
GO
/****** Object:  StoredProcedure [dbo].[sp_InsertVeTheoSoLuongGhe]    Script Date: 2024-12-25 11:50:30 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_InsertVeTheoSoLuongGhe]
(
    @id_chuyen bigint,
    @gia_ve float
)
AS
BEGIN
    DECLARE @so_luong_ghe INT;
    DECLARE @i INT = 1; /*Biến đếm số thứ tự vé.*/
    DECLARE @ten_ve NVARCHAR(5);

    -- Lấy giá trị so_luong_ghe từ truy vấn SELECT và lưu vào biến @so_luong_ghe
    SELECT TOP 1 @so_luong_ghe = t.so_luong_ghe
    FROM tau t
    INNER JOIN tuyen ty ON t.id = ty.id_tau
    INNER JOIN chuyen ch ON ty.id = ch.id_tuyen
    WHERE ch.id = @id_chuyen;

    -- Kiểm tra xem @so_luong_ghe có giá trị không
    IF @so_luong_ghe IS NOT NULL
    BEGIN
        WHILE @i <= @so_luong_ghe
        BEGIN
           -- Sử dụng CONCAT để tạo chuỗi số thứ tự liền mạch từ "A01" đến "G34", bỏ đi 2 số sau chữ cái
            SET @ten_ve = CHAR(65 + FLOOR((@i - 1) / 4)) +  RIGHT('00' + CAST(@i AS VARCHAR(2)), 2);

            INSERT INTO ve (ten_ghe, id_chuyen, gia_ve, status, id_hoa_don)
            VALUES (@ten_ve, @id_chuyen, @gia_ve, 0, null);

            SET @i = @i + 1;
        END;
    END;
END;
GO
