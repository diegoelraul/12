-- >>>>> UBIGEO <<<<< --

DROP TABLE IF EXISTS `consultorio`.`ubigeo`;
CREATE TABLE  `consultorio`.`ubigeo` (
  `DEPARTAMENTO_ID` char(2) NOT NULL,
  `PROVINCIA_ID` char(2) NOT NULL,
  `DISTRITO_ID` char(2) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  PRIMARY KEY (`DEPARTAMENTO_ID`,`PROVINCIA_ID`,`DISTRITO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- >>>>> EMPRESA <<<<< --

DROP TABLE IF EXISTS `consultorio`.`empresa`;
CREATE TABLE  `consultorio`.`empresa` (
  `EMPRESA_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RUC` varchar(11) NOT NULL,
  `RAZON_SOCIAL` varchar(50) NOT NULL,
  `ACTIVO` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`EMPRESA_ID`),
  UNIQUE KEY `EMPRESA_INDEX_RUC` (`RUC`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- >>>>> SUCURSAL <<<<< --

DROP TABLE IF EXISTS `consultorio`.`sucursal`;
CREATE TABLE  `consultorio`.`sucursal` (
  `EMPRESA_ID` int(10) unsigned NOT NULL,
  `SUCURSAL_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  `DIRECCION` varchar(40) NOT NULL,
  `URBANIZACION` varchar(25) DEFAULT NULL,
  `DEPARTAMENTO_ID` char(2) NOT NULL,
  `PROVINCIA_ID` char(2) NOT NULL,
  `DISTRITO_ID` char(2) NOT NULL,
  `TELEFONOS` varchar(20) DEFAULT NULL,
  `PRINCIPAL` char(1) NOT NULL DEFAULT 'N',
  `ACTIVO` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`SUCURSAL_ID`) USING BTREE,
  UNIQUE KEY `IDX_SUCURSAL_EMPRESA_NOMBRE` (`EMPRESA_ID`,`NOMBRE`),
  KEY `FK_SUCURSAL_UBIGEO` (`DEPARTAMENTO_ID`,`PROVINCIA_ID`,`DISTRITO_ID`),
  CONSTRAINT `FK_SUCURSAL_EMPRESA` FOREIGN KEY (`EMPRESA_ID`) REFERENCES `empresa` (`EMPRESA_ID`),
  CONSTRAINT `FK_SUCURSAL_UBIGEO` FOREIGN KEY (`DEPARTAMENTO_ID`, `PROVINCIA_ID`, `DISTRITO_ID`) REFERENCES `ubigeo` (`DEPARTAMENTO_ID`, `PROVINCIA_ID`, `DISTRITO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- >>>>> VISTA: DEPARTAMENTO <<<<< --

DROP VIEW IF EXISTS `consultorio`.`departamento`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW  `consultorio`.`departamento` AS
select `ubigeo`.`DEPARTAMENTO_ID` AS `DEPARTAMENTO_ID`,`ubigeo`.`NOMBRE` AS `NOMBRE`
from `ubigeo`
where ((`ubigeo`.`PROVINCIA_ID` = '00') and (`ubigeo`.`DISTRITO_ID` = '00'));

-- >>>>> VISTA: PROVINCIA <<<<< --

DROP VIEW IF EXISTS `consultorio`.`provincia`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW  `consultorio`.`provincia` AS
select `ubigeo`.`DEPARTAMENTO_ID` AS `DEPARTAMENTO_ID`,`ubigeo`.`PROVINCIA_ID` AS `PROVINCIA_ID`,`ubigeo`.`NOMBRE` AS `NOMBRE`
from `ubigeo`
where ((`ubigeo`.`PROVINCIA_ID` <> '00') and (`ubigeo`.`DISTRITO_ID` = '00'));

-- >>>>> VISTA: DISTRITO <<<<< --

DROP VIEW IF EXISTS `consultorio`.`distrito`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW  `consultorio`.`distrito` AS
select `ubigeo`.`DEPARTAMENTO_ID` AS `DEPARTAMENTO_ID`,`ubigeo`.`PROVINCIA_ID` AS `PROVINCIA_ID`,`ubigeo`.`DISTRITO_ID` AS `DISTRITO_ID`,`ubigeo`.`NOMBRE` AS `NOMBRE`
from `ubigeo`
where ((`ubigeo`.`PROVINCIA_ID` <> '00') and (`ubigeo`.`DISTRITO_ID` <> '00'));

-- >>>>> VISTA: DEP_PROV_DIST <<<<< --

DROP VIEW IF EXISTS `consultorio`.`dep_prov_dist`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW  `consultorio`.`dep_prov_dist` AS
select `d`.`DEPARTAMENTO_ID` AS `DEPARTAMENTO_ID`,`d`.`NOMBRE` AS `DEPARTAMENTO`,`p`.`PROVINCIA_ID` AS `PROVINCIA_ID`,`p`.`NOMBRE` AS `PROVINCIA`,`t`.`DISTRITO_ID` AS `DISTRITO_ID`,`t`.`NOMBRE` AS `DISTRITO`
from (((`ubigeo` `u`
join `departamento` `d` on((`u`.`DEPARTAMENTO_ID` = `d`.`DEPARTAMENTO_ID`)))
join `provincia` `p` on(((`u`.`DEPARTAMENTO_ID` = `p`.`DEPARTAMENTO_ID`) and (`u`.`PROVINCIA_ID` = `p`.`PROVINCIA_ID`))))
join `distrito` `t` on(((`u`.`DEPARTAMENTO_ID` = `t`.`DEPARTAMENTO_ID`) and (`u`.`PROVINCIA_ID` = `t`.`PROVINCIA_ID`) and (`u`.`DISTRITO_ID` = `t`.`DISTRITO_ID`))));

