
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `apellido` varchar(500) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `direccion` varchar(500) NOT NULL,
  `razon_social` varchar(500) NOT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  `is_closed` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  `is_superuser` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` VALUES (1,'admin','admin','cbb_admin','cbb_password','2018-12-09 14:29:42',1,1),(2,'secretaria','cbb','cbb_permisos','cbb_permisos','2018-12-09 14:29:42',1,0),(3,'admin','cbb','cbb_root','cbb_root','2018-12-09 14:29:42',1,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_logged` tinyint(4) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tipo_permiso`;
CREATE TABLE `tipo_permiso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_permiso` varchar(250) NOT NULL,
  `precio` double NOT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=511 DEFAULT CHARSET=utf8;

LOCK TABLES `tipo_permiso` WRITE;
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ABACERIA, TIENDAS DE ABARROTES O FRUTERIAS", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ABACERIA, TIENDAS DE ABARROTES O FRUTERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ABACERIA, TIENDAS DE ABARROTES O FRUTERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ALQUILER DE VEHICULOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ALQUILER DE VEHICULOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ALQUILER DE VEHICULOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ANTENAS DE MEDIOS DE COMUNICACIÓN", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ANTENAS DE MEDIOS DE COMUNICACIÓN", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ANTENAS DE MEDIOS DE COMUNICACIÓN", 300);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASADEROS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASADEROS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASADEROS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASERRADEROS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASERRADEROS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASERRADEROS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASOCIACIONES, TRANSPORTE Y TRICICLOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASOCIACIONES, TRANSPORTE Y TRICICLOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ASOCIACIONES, TRANSPORTE Y TRICICLOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("AVICOLAS INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAHIA", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAHIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAHIA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAILE PUBLICOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAILE PUBLICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAILE PUBLICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BALANCEADOS, ABONOS Y/O FERETILIZANTES INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BANCOS", 65);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BANCOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BANCOS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAR ESCOLAR", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAR ESCOLAR", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAR ESCOLAR", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BARES Y/O CANTINAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BARES Y/O CANTINAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BARES Y/O CANTINAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAZAR Y ARTICULOS DE REGALO", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAZAR Y ARTICULOS DE REGALO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BAZAR Y ARTICULOS DE REGALO", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES  ", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES  ", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES  ", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BLOQUES INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BODEGAS EN GENERAL", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BODEGAS EN GENERAL", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BODEGAS EN GENERAL", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOTICAS, FARMACIAS Y/ PRODUCTOS NATURALES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOTICAS, FARMACIAS Y/ PRODUCTOS NATURALES", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOTICAS, FARMACIAS Y/ PRODUCTOS NATURALES", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOUTIQUE Y/ O PRENDAS DE VESTIR", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOUTIQUE Y/ O PRENDAS DE VESTIR", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("BOUTIQUE Y/ O PRENDAS DE VESTIR", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CABINAS TELEFONICAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CABINAS TELEFONICAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CABINAS TELEFONICAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CAFETERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CAFETERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CAFETERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CALZADO", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CALZADO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CALZADO", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANCHAS DEPORTIVAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANCHAS DEPORTIVAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANCHAS DEPORTIVAS", 45);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANTERAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANTERAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CANTERAS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARBONERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARBONERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARBONERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARPINTERIA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARPINTERIA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARPINTERIA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS INDUATRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS INDUATRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROCERIAS INDUATRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROS DE DIVERSION GUSANITOS", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROS DE DIVERSION GUSANITOS", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CARROS DE DIVERSION GUSANITOS", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIO DE LECHE", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIO DE LECHE", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIO DE LECHE", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIOS ( ARROZ, MAIZ, Y OTROS)", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIOS ( ARROZ, MAIZ, Y OTROS)", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTRO DE ACOPIOS ( ARROZ, MAIZ, Y OTROS)", 200);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS DE CAPACITACION", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS DE CAPACITACION", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS DE CAPACITACION", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS EDUCATIVOS O SIMILARES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS EDUCATIVOS O SIMILARES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CENTROS EDUCATIVOS O SIMILARES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CERRAJERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CERRAJERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CERRAJERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CINES Y TEATRO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CINES Y TEATRO", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CINES Y TEATRO", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLCHONES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLCHONES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLCHONES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLEGIOS", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLEGIOS", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COLEGIOS", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMEDORES, PICANTERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMEDORES, PICANTERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMEDORES, PICANTERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMISARIATOS Y SUPERMERCADOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMISARIATOS Y SUPERMERCADOS", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMISARIATOS Y SUPERMERCADOS", 320);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIA AGRICOLA", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIA AGRICOLA", 1);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIA AGRICOLA", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIAS DE SEGUROS EN GENERAL", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIAS DE SEGUROS EN GENERAL", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPAÑIAS DE SEGUROS EN GENERAL", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPETENCIAS DEPORTIVAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPETENCIAS DEPORTIVAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPETENCIAS DEPORTIVAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPLEJOS TURISTICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPLEJOS TURISTICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPLEJOS TURISTICOS", 200);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE GRANOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE GRANOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE GRANOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE VALORES", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE VALORES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COMPRA Y VENTA DE VALORES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONFECCION DE PRENDAS DE VESTIR INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONFECCION DE PRENDAS DE VESTIR INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONFECCION DE PRENDAS DE VESTIR INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION (REMODELACION)", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION (REMODELACION)", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION (REMODELACION)", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MAYOR", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MAYOR", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MAYOR", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MENOR", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MENOR", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSTRUCCION MENOR", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS MEDICOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS MEDICOS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS MEDICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS ODONTOLOGICOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS ODONTOLOGICOS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CONSULTORIOS ODONTOLOGICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COOPERATIVA DE AHORRO Y CREDITO", 65);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COOPERATIVA DE AHORRO Y CREDITO", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COOPERATIVA DE AHORRO Y CREDITO", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COPIADORAS Y/O CENTRO DE COMPUTO", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COPIADORAS Y/O CENTRO DE COMPUTO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COPIADORAS Y/O CENTRO DE COMPUTO", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COSTURA Y CONFECCIÓN", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COSTURA Y CONFECCIÓN", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("COSTURA Y CONFECCIÓN", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CYBER", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CYBER", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("CYBER", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DE PINTURAS, DILUYENTES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DE PINTURAS, DILUYENTES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DE PINTURAS, DILUYENTES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DEPOSITO DE COLAS Y CERVEZAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DEPOSITO DE COLAS Y CERVEZAS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DEPOSITO DE COLAS Y CERVEZAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DISCOTECAS Y PEÑAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DISCOTECAS Y PEÑAS", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("DISCOTECAS Y PEÑAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRICO, ELECTRONICO Y/O REPARACIÓN", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRICO, ELECTRONICO Y/O REPARACIÓN", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRICO, ELECTRONICO Y/O REPARACIÓN", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRODOMESTICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRODOMESTICOS", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ELECTRODOMESTICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EMPRESAS DE GUARDIANIA DE SEGURIDAD", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EMPRESAS DE GUARDIANIA DE SEGURIDAD", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EMPRESAS DE GUARDIANIA DE SEGURIDAD", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EQUIPOS DE SEGURIDAD", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EQUIPOS DE SEGURIDAD", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("EQUIPOS DE SEGURIDAD", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ESPECTACULOS PUBLICOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ESPECTACULOS PUBLICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ESPECTACULOS PUBLICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERIAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERIAS", 200);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERRETERIAS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERRETERIAS", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FERRETERIAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORICOLAS INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORICOLAS INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORICOLAS INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORISTERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORISTERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FLORISTERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FOTOGRAFICO", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FOTOGRAFICO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FOTOGRAFICO", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUENTE DE SODA, HELADERIAS, Y YOGURT", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUENTE DE SODA, HELADERIAS, Y YOGURT", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUENTE DE SODA, HELADERIAS, Y YOGURT", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNCIONES DE SERVICIOS COMUNITARIOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNCIONES DE SERVICIOS COMUNITARIOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNCIONES DE SERVICIOS COMUNITARIOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNERARIAS Y CREMATORIOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNERARIAS Y CREMATORIOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("FUNERARIAS Y CREMATORIOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALLERAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALLERAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GALLERAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GARAJES Y/O PARQUEADEROS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GARAJES Y/O PARQUEADEROS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GARAJES Y/O PARQUEADEROS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GASOLINERAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GASOLINERAS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GASOLINERAS", 200);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GIMNASIOS, CENTROS DE MASAJES, SAUNA - VAPOR", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GIMNASIOS, CENTROS DE MASAJES, SAUNA - VAPOR", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("GIMNASIOS, CENTROS DE MASAJES, SAUNA - VAPOR", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HACIENDAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HACIENDAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HACIENDAS", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSPITALES Y CLINICAS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSPITALES Y CLINICAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSPITALES Y CLINICAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSTERIAS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSTERIAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOSTERIAS", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOTELES", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOTELES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("HOTELES", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IGLESIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IGLESIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IGLESIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPORTACION, FABRICACION Y/O VENTA DE JUEGOS PIROTECNICOS", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPORTACION, FABRICACION Y/O VENTA DE JUEGOS PIROTECNICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPORTACION, FABRICACION Y/O VENTA DE JUEGOS PIROTECNICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPRENTAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPRENTAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("IMPRENTAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INCUBADORAS DE HUEVOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INCUBADORAS DE HUEVOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INCUBADORAS DE HUEVOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INSUMOS AGROPECUARIOS Y/O AGROQUIMICOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INSUMOS AGROPECUARIOS Y/O AGROQUIMICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("INSUMOS AGROPECUARIOS Y/O AGROQUIMICOS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JOYERIA Y RELOJERIA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JOYERIA Y RELOJERIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JOYERIA Y RELOJERIA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS ELECTRONICOS Y MANUALES", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS ELECTRONICOS Y MANUALES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS ELECTRONICOS Y MANUALES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS MECANICOS", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS MECANICOS", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("JUEGOS MECANICOS", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("KIOSKO CARRETILLAS Y OCASIONALES", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("KIOSKO CARRETILLAS Y OCASIONALES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("KIOSKO CARRETILLAS Y OCASIONALES", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LABORATORIOS CLINICOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LABORATORIOS CLINICOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LABORATORIOS CLINICOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LADRILLEAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LADRILLEAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LADRILLEAS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LATONERIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LATONERIA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LATONERIA", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVADORA Y LUBRICADORA DE VEHICULOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVADORA Y LUBRICADORA DE VEHICULOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVADORA Y LUBRICADORA DE VEHICULOS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVANDERIAS Y TINTORERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVANDERIAS Y TINTORERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LAVANDERIAS Y TINTORERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LIBRERIAS, PAPELERIAS Y/O REVISTAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LIBRERIAS, PAPELERIAS Y/O REVISTAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LIBRERIAS, PAPELERIAS Y/O REVISTAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERAS INDUSTRIAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERAS INDUSTRIAS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERAS INDUSTRIAS", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LICORERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LINEAS AEREAS", 500);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LINEAS AEREAS", 1000);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LINEAS AEREAS", 2000);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LLANTAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LLANTAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LLANTAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LUBRICANTES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LUBRICANTES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("LUBRICANTES", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MADERA INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MADERA INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MADERA INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MAQUINARIA AGRICOLA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MAQUINARIA AGRICOLA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MAQUINARIA AGRICOLA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MARISQUERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MARISQUERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MARISQUERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MATERIALES DE CONSTRUCCION", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MATERIALES DE CONSTRUCCION", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MATERIALES DE CONSTRUCCION", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MECANICA AUTOMOTRIZ Y/O ELECTROMECANICA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MECANICA AUTOMOTRIZ Y/O ELECTROMECANICA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MECANICA AUTOMOTRIZ Y/O ELECTROMECANICA", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNCIACIÓN (TELEFONICA, INTERNET)", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNCIACIÓN (TELEFONICA, INTERNET)", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNCIACIÓN (TELEFONICA, INTERNET)", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN (RADIO)", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN (RADIO)", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN (RADIO)", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN(IMPRESOS)", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN(IMPRESOS)", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MEDIOS DE COMUNICACIÓN(IMPRESOS)", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MERCADO CENTRAL", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MERCADO CENTRAL", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MERCADO CENTRAL", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("METAMECANICA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("METAMECANICA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("METAMECANICA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MINI MARKET", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MINI MARKET", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MINI MARKET", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOLINOS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOLINOS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOLINOS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOTELES", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOTELES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MOTELES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES DE OFICINA Y AFINES FABRICA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES DE OFICINA Y AFINES FABRICA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES DE OFICINA Y AFINES FABRICA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES Y/O EQUIPOS DE OFICINA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES Y/O EQUIPOS DE OFICINA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUEBLES Y/O EQUIPOS DE OFICINA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUSICALES Y DISCOS, VIDEOS", 10);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUSICALES Y DISCOS, VIDEOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("MUSICALES Y DISCOS, VIDEOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("NIGHT CLUB Y/O CASAS DE CITA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("NIGHT CLUB Y/O CASAS DE CITA", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("NIGHT CLUB Y/O CASAS DE CITA", 70);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS DE TRANSPORTE TERRESTRE", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS DE TRANSPORTE TERRESTRE", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS DE TRANSPORTE TERRESTRE", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS EN GENERAL", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS EN GENERAL", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OFICINAS EN GENERAL", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OPTICAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OPTICAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OPTICAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTRAS INDUSTRIAS Y/O FABRICAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTRAS INDUSTRIAS Y/O FABRICAS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTRAS INDUSTRIAS Y/O FABRICAS", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS ALMACENES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS ALMACENES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS ALMACENES", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS TALLERES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS TALLERES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("OTROS TALLERES", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PANADERIA Y PASTELERIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PANADERIA Y PASTELERIA", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PANADERIA Y PASTELERIA", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PAÑALERAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PAÑALERAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PAÑALERAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PILADORAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PILADORAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PILADORAS", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PIZZERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PIZZERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PIZZERIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLANTA PURIFICADORA Y EMBASADORA DE AGUA INDUSTRIA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLANTA PURIFICADORA Y EMBASADORA DE AGUA INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLANTA PURIFICADORA Y EMBASADORA DE AGUA INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLASTICOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLASTICOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PLASTICOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PROCESADORA DE ALIMENTOS, BEBIDAS Y REFRESCOS O SIMILARES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PROCESADORA DE ALIMENTOS, BEBIDAS Y REFRESCOS O SIMILARES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PROCESADORA DE ALIMENTOS, BEBIDAS Y REFRESCOS O SIMILARES", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCCION DE PLANTAS PARA LA AGRICULTURA (PILON-SEMILLERO)", 140);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCCION DE PLANTAS PARA LA AGRICULTURA (PILON-SEMILLERO)", 180);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCCION DE PLANTAS PARA LA AGRICULTURA (PILON-SEMILLERO)", 242);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS PARA MASCOTAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS PARA MASCOTAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS PARA MASCOTAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS QUIMICOS INDUSTRIA", 140);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS QUIMICOS INDUSTRIA", 180);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PRODUCTOS QUIMICOS INDUSTRIA", 242);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUBLICIDAD Y ROTULOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUBLICIDAD Y ROTULOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUBLICIDAD Y ROTULOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUESTOS INFORMALES", 5);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUESTOS INFORMALES", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("PUESTOS INFORMALES", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMA DE PIROTECNIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMA DE PIROTECNIA", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMA DE PIROTECNIA", 0);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMAS CONTROLADAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMAS CONTROLADAS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("QUEMAS CONTROLADAS", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECARGA Y MANTENIMIENTO DE EXTINTORES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECARGA Y MANTENIMIENTO DE EXTINTORES", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECARGA Y MANTENIMIENTO DE EXTINTORES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECICLADORAS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECICLADORAS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RECICLADORAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REENCAUCHADORA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REENCAUCHADORA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REENCAUCHADORA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REPUESTOS AUTOMOTRICES Y /O ACCESORIOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REPUESTOS AUTOMOTRICES Y /O ACCESORIOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("REPUESTOS AUTOMOTRICES Y /O ACCESORIOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESIDENCIALES Y PENSIONES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESIDENCIALES Y PENSIONES", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESIDENCIALES Y PENSIONES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESTAURANTES Y/O CHIFAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESTAURANTES Y/O CHIFAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("RESTAURANTES Y/O CHIFAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALAS DE BILLAS Y BILLARES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALAS DE BILLAS Y BILLARES", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALAS DE BILLAS Y BILLARES", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALON DE EVENTOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALON DE EVENTOS", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALON DE EVENTOS", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALONES DE BELLEZA Y PELUQUERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALONES DE BELLEZA Y PELUQUERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SALONES DE BELLEZA Y PELUQUERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SECADORAS DE ARROZ, MAIZ Y/O OTROS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SECADORAS DE ARROZ, MAIZ Y/O OTROS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SECADORAS DE ARROZ, MAIZ Y/O OTROS", 200);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SERVICIOS TURISTICOS Y DE DIVERSIÓN", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SERVICIOS TURISTICOS Y DE DIVERSIÓN", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SERVICIOS TURISTICOS Y DE DIVERSIÓN", 40);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SORTEO, RIFAS, JUEGOS DE AZAR Y DEMAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SORTEO, RIFAS, JUEGOS DE AZAR Y DEMAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("SORTEO, RIFAS, JUEGOS DE AZAR Y DEMAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TALLER DE EBANISTERIA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TALLER DE EBANISTERIA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TALLER DE EBANISTERIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TANQUES DE GLP", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TANQUES DE GLP", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TANQUES DE GLP", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TAPICERIA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TAPICERIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TAPICERIA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TERCENAS Y/O FRIGORIFICOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TERCENAS Y/O FRIGORIFICOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TERCENAS Y/O FRIGORIFICOS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TEXTIL INDUSTRIA", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TEXTIL INDUSTRIA", 150);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TEXTIL INDUSTRIA", 250);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TORNO Y PRECISION", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TORNO Y PRECISION", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TORNO Y PRECISION", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE CARBON", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE CARBON", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE CARBON", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE COMBUSTIBLE TANQUES O BIDONES", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE COMBUSTIBLE TANQUES O BIDONES", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE COMBUSTIBLE TANQUES O BIDONES", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE GAS CILINDROS", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE GAS CILINDROS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE GAS CILINDROS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE MADERA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE MADERA", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("TRANSPORTE DE MADERA", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VEHICULOS Y /O MOTOCICLETAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VEHICULOS Y /O MOTOCICLETAS", 35);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VEHICULOS Y /O MOTOCICLETAS", 50);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE ARROZ PILADO", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE ARROZ PILADO", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE ARROZ PILADO", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE GAS", 32);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE GAS", 60);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE GAS", 100);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE LACTEOS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE LACTEOS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA DE LACTEOS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA Y REPARACION DE CELULARES", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA Y REPARACION DE CELULARES", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VENTA Y REPARACION DE CELULARES", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VETERINARIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VETERINARIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VETERINARIAS", 30);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VIDRERIAS", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VIDRERIAS", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VIDRERIAS", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VULCANIZADORA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VULCANIZADORA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("VULCANIZADORA", 25);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ZAPATERIA", 15);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ZAPATERIA", 20);
INSERT INTO cbb_db.tipo_permiso (`tipo_permiso`, `precio`) VALUES("ZAPATERIA", 25);
UNLOCK TABLES;

DROP TABLE IF EXISTS `permisos`;
CREATE TABLE `permisos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(250) DEFAULT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_expiracion` date NOT NULL,
  `modo_permiso` varchar(45) NOT NULL,
  `numero_deposito` varchar(100) NOT NULL,
  `fecha_documento` date NOT NULL,
  `vehiculo_marca` varchar(100) DEFAULT NULL,
  `extintor` tinyint(4) DEFAULT NULL,
  `capacidad` varchar(100) DEFAULT NULL,
  `placa` varchar(100) DEFAULT NULL,
  `ruta_pdf` varchar(250) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_tipo_permiso` int(11) NOT NULL,
  `id_clientes` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_idx` (`id_usuario`),
  KEY `id_tipo_permiso_idx` (`id_tipo_permiso`),
  KEY `id_clientes_idx` (`id_clientes`),
  CONSTRAINT `id_clientes` FOREIGN KEY (`id_clientes`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_tipo_permiso` FOREIGN KEY (`id_tipo_permiso`) REFERENCES `tipo_permiso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;