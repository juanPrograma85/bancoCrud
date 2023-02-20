USE [Banco]
GO
/****** Object:  Table [dbo].[cliente]    Script Date: 20/02/2023 12:46:30 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cliente](
	[clienteid] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[estado] [bit] NULL,
	[personaidentificacion] [numeric](19, 0) NOT NULL,
	[contraseña] [varchar](12) NULL,
 CONSTRAINT [PK__cliente__C2FE2075C32A887D] PRIMARY KEY CLUSTERED 
(
	[clienteid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cuenta]    Script Date: 20/02/2023 12:46:30 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cuenta](
	[numerocuenta] [numeric](19, 0) NOT NULL,
	[tipocuenta] [char](20) NULL,
	[clienteid] [numeric](19, 0) NULL,
	[saldoinicial] [decimal](18, 0) NULL,
	[estado] [bit] NULL,
 CONSTRAINT [PK_cuenta] PRIMARY KEY CLUSTERED 
(
	[numerocuenta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movimiento]    Script Date: 20/02/2023 12:46:30 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movimiento](
	[movimientoid] [numeric](19, 0) NOT NULL,
	[descripcion] [varchar](255) NULL,
	[fechamovimiento] [datetime] NULL,
	[numerocuenta] [numeric](19, 0) NULL,
	[saldo] [numeric](19, 2) NULL,
	[tipomovimiento] [varchar](255) NULL,
	[valor] [numeric](19, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[movimientoid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[persona]    Script Date: 20/02/2023 12:46:30 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[persona](
	[identificacion] [numeric](19, 0) NOT NULL,
	[direccion] [varchar](100) NULL,
	[edad] [int] NULL,
	[genero] [varchar](15) NULL,
	[nombre] [varchar](100) NULL,
	[telefono] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[identificacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[cliente] ON 

INSERT [dbo].[cliente] ([clienteid], [estado], [personaidentificacion], [contraseña]) VALUES (CAST(1 AS Numeric(19, 0)), 1, CAST(23330 AS Numeric(19, 0)), NULL)
SET IDENTITY_INSERT [dbo].[cliente] OFF
GO
INSERT [dbo].[cuenta] ([numerocuenta], [tipocuenta], [clienteid], [saldoinicial], [estado]) VALUES (CAST(3034 AS Numeric(19, 0)), N'ahorro              ', CAST(1 AS Numeric(19, 0)), CAST(0 AS Decimal(18, 0)), 1)
GO
INSERT [dbo].[movimiento] ([movimientoid], [descripcion], [fechamovimiento], [numerocuenta], [saldo], [tipomovimiento], [valor]) VALUES (CAST(2 AS Numeric(19, 0)), N'deposito realizado por juan', CAST(N'2023-02-18T00:00:00.000' AS DateTime), CAST(3034 AS Numeric(19, 0)), CAST(1000.00 AS Numeric(19, 2)), N'+', CAST(1000.00 AS Numeric(19, 2)))
INSERT [dbo].[movimiento] ([movimientoid], [descripcion], [fechamovimiento], [numerocuenta], [saldo], [tipomovimiento], [valor]) VALUES (CAST(3 AS Numeric(19, 0)), N'deposito realizado por juan', CAST(N'2023-02-18T00:00:00.000' AS DateTime), CAST(3034 AS Numeric(19, 0)), CAST(650.00 AS Numeric(19, 2)), N'-', CAST(350.00 AS Numeric(19, 2)))
GO
INSERT [dbo].[persona] ([identificacion], [direccion], [edad], [genero], [nombre], [telefono]) VALUES (CAST(23330 AS Numeric(19, 0)), N'call', 23, N'Masculino', N'fernando', N'3147852465')
GO
ALTER TABLE [dbo].[cliente]  WITH CHECK ADD  CONSTRAINT [FKqpkac6mpwxgy9eeo9phwhkfgi] FOREIGN KEY([personaidentificacion])
REFERENCES [dbo].[persona] ([identificacion])
GO
ALTER TABLE [dbo].[cliente] CHECK CONSTRAINT [FKqpkac6mpwxgy9eeo9phwhkfgi]
GO
ALTER TABLE [dbo].[cuenta]  WITH CHECK ADD  CONSTRAINT [FK_cuenta_Cliente] FOREIGN KEY([clienteid])
REFERENCES [dbo].[cliente] ([clienteid])
GO
ALTER TABLE [dbo].[cuenta] CHECK CONSTRAINT [FK_cuenta_Cliente]
GO
