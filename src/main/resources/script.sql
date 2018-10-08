CREATE DATABASE IF NOT EXITS smartfleet;
CREATE USER IF NOT EXISTS 'smartadmin'@'localhost' IDENTIFIED BY 'smartadmin';
GRANT ALL PRIVILEGES ON *.* TO 'smartadmin'@'localhost' WITH GRANT OPTION;
CREATE USER 'smartadmin'@'%' IDENTIFIED BY 'smartadmin';
GRANT ALL PRIVILEGES ON *.* TO 'smartadmin'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

USE smartfleet;

CREATE TABLE IF NOT EXISTS tipo_base(
	id varchar(20) not null,
	categoria varchar(60) not null,
	tipo varchar(60) not null,
	descripcion varchar(100),
	activo char(1) default 'Y',
	PRIMARY KEY (id)
)COMMENT 'registra las categorias y tipos de elementos';

INSERT INTO tipo_base VALUES ('TE_EQUI','TIPO_ESTADO','ESTADO_EQUIPO','Estados Equipo','Y');
INSERT INTO tipo_base VALUES ('TP_EQUI_CAM','TIPO_EQUIPO','CAMION', 'Equipo de tipo camion','Y');
INSERT INTO tipo_base VALUES ('TP_EQUI_WCAM','TIPO_EQUIPO','CAMION DE AGUA', 'Equipo de tipo camion de agua','Y');
INSERT INTO tipo_base VALUES ('TP_EQUI_PAL','TIPO_EQUIPO','PALA', 'Equipo de tipo pala','Y');
INSERT INTO tipo_base VALUES ('TP_EQUI_DZ','TIPO_EQUIPO','CUCHILLA', 'Equipo de tipo cuchilla(dozer)','Y');
INSERT INTO tipo_base VALUES ('TP_EQUI_PER','TIPO_EQUIPO','PERFORADORA', 'Equipo de tipo perforadora','Y');

CREATE TABLE IF NOT EXISTS estados(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	categoria varchar(20) not null,
	estado varchar(60) not null,
	PRIMARY KEY (id),
	CONSTRAINT fk_estado_tipo_base FOREIGN KEY (categoria) REFERENCES tipo_base(id)
)COMMENT 'registra todos los estado de una categoria';

INSERT INTO estados (categoria, estado) VALUES ('TE_EQUI','PRODUCCION');
INSERT INTO estados (categoria, estado) VALUES ('TE_EQUI','RESERVA');
INSERT INTO estados (categoria, estado) VALUES ('TE_EQUI','DEMORA');
INSERT INTO estados (categoria, estado) VALUES ('TE_EQUI','MALOGRADO');
alter table estados add column color integer default -16777216;

CREATE TABLE IF NOT EXISTS razones(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	estado_id bigint(20) not null,
	razon varchar(100) not null,
	PRIMARY KEY (id),
	CONSTRAINT fk_razon_estado FOREIGN KEY (estado_id) REFERENCES estados(id)
)COMMENT 'registra los razones de cada estado';

INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='PRODUCCION'),'PRODUCCION');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='PRODUCCION'),'REMANEJO');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='RESERVA'),'SIN PALA');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='RESERVA'),'FALTA OPERADOR');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='DEMORA'),'REFRIGERIO');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='DEMORA'),'CHARLA');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='MALOGRADO'),'LLANTAS');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='MALOGRADO'),'BUM');
INSERT INTO razones(estado_id, razon) VALUES ((SELECT id FROM estados WHERE estado='MALOGRADO'),'DIRRECCION');

CREATE TABLE IF NOT EXISTS equipos(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	categoria varchar(20) not null,
	cdg_equi varchar(60) not null,
	marca_equi varchar(60),
	modelo_equi varchar(60),
	cap_comb numeric(15,2),
	cap_carg numeric(15,2),
	activo char(1) default 'Y',
	PRIMARY KEY(id),
	CONSTRAINT fk_equipo_tipo FOREIGN KEY(categoria) REFERENCES tipo_base(id)
)COMMENT 'registra los detalles de los diferentes tipos de equipos';

INSERT INTO equipos(categoria, cdg_equi, marca_equi, modelo_equi, cap_comb, cap_carg)
VALUES ((SELECT id from tipo_base where tipo='CAMION'), 'T01', 'MARCA_1','MODELO_1', 14, 25);

INSERT INTO equipos(categoria, cdg_equi, marca_equi, modelo_equi, cap_comb, cap_carg)
VALUES ((SELECT id from tipo_base where tipo='CAMION'), 'T02', 'MARCA_2','MODELO_2', 14, 25);

CREATE TABLE IF NOT EXISTS operadores(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	doc_ope VARCHAR(11),
	cdg_ope varchar(20) not null,
	nom_ope varchar(100) not null,
	ape_ope varchar(100) not null,
	PRIMARY KEY(id)
)COMMENT 'registra los detalles de cada operador';

insert into operadores(cdg_ope, nom_ope, ape_ope) values('47891603','4789','MARK','WINTER');

CREATE TABLE IF NOT EXISTS ope_equi(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	ope_id bigint(20) not null,
	equi_id bigint(20) not null,
	fecha_asig datetime default now(),
	tipo_asig varchar(60) not null COMMENT 'tipo de asignacion: actual o permanente',
	fecha_ini datetime,
	fecha_fin datetime,
	PRIMARY KEY (id),
	CONSTRAINT fk_ope_equi_operador FOREIGN KEY(ope_id) REFERENCES operadores(id),
	CONSTRAINT fk_ope_equi_equipo FOREIGN KEY(equi_id) REFERENCES equipos(id)
)COMMENT 'registra las asignaciones de operadores al equipo correspondiente';

CREATE TABLE IF NOT EXISTS dispositivos(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	ip_disp varchar(60),
	gate_disp varchar(60),
	mac_disp varchar(60),
	tip_disp varchar(60),
	PRIMARY KEY (id)
)COMMENT 'registra los datos de cada dispositivo';
insert into dispositivos (version, fecha_crea, crea_por, ip_disp, gate_disp, mac_disp, tip_disp)
values (0, now(), 'SISTEMA', '192.168.1.2','192.168.1.1','APANNHJNB','LOCAL');
insert into dispositivos (version, fecha_crea, crea_por, ip_disp, gate_disp, mac_disp, tip_disp)
values (0, now(), 'SISTEMA', '192.168.1.4','192.168.1.1','BNNLAJJ18','LOCAL');
alter table dispositivos add column port_disp varchar(60) default '8080';

CREATE TABLE IF NOT EXISTS gps_disp(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	latitud varchar(60),
	longitud varchar(60),
	altitud varchar(60),
	xutm varchar(60),
	yutm varchar(60),
	fecha_gps datetime default now(),
	velocidad varchar(60),
	disp_id bigint(20),
	PRIMARY KEY(id),
	CONSTRAINT fk_gps_disp_disp FOREIGN KEY(disp_id) REFERENCES dispositivos(id)
)COMMENT 'registra el historial de ubicaciones del dispositivo';

CREATE TABLE IF NOT EXISTS equi_disp(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	equi_id bigint(20),
	disp_id bigint(20),
	fecha_asig datetime default now(),
	PRIMARY KEY(id),
	CONSTRAINT fk_equi_disp_equi FOREIGN KEY(equi_id) REFERENCES equipos(id),
	CONSTRAINT fk_equi_disp_disp FOREIGN KEY(disp_id) REFERENCES dispositivos(id)
)COMMENT 'registra la relacion entre equipo y el dispositivo asignado';

ALTER TABLE equi_disp ADD COLUMN activo CHAR(1) DEFAULT 'Y';

INSERT INTO equi_disp(equi_id, disp_id, fecha_asig) 
VALUES ((SELECT id from equipos WHERE cdg_equi='T01'), (SELECT id from dispositivos where mac_disp='APANNHJNB'), now());
INSERT INTO equi_disp(equi_id, disp_id, fecha_asig) 
VALUES ((SELECT id from equipos WHERE cdg_equi='T02'), (SELECT id from dispositivos where mac_disp='BNNLAJJ18'), now());

CREATE TABLE IF NOT EXISTS equi_state(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	equi_id bigint(20),
	state_id bigint(20),
	razon_id bigint(20),
	comentario varchar(100),
	fecha_ini datetime,
	fecha_fin datetime,
	PRIMARY KEY(id),
	CONSTRAINT fk_equi_state_equi FOREIGN KEY(equi_id) REFERENCES equipos(id),
	CONSTRAINT fk_equi_state_state FOREIGN KEY(state_id) REFERENCES estados(id),
	CONSTRAINT fk_equi_disp_razon FOREIGN KEY(razon_id) REFERENCES razones(id)
)COMMENT 'registra los detalles de los estados de cada uno de los equipos';


CREATE TABLE IF NOT EXISTS ubicaciones(
	id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	ubi_type varchar(60),
	ubi_name varchar(60),
	ubi_coord_x varchar(60),
	ubi_coord_y varchar(60),
	ubi_coord_z varchar(60),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS rutas(
id bigint(20) not null auto_increment,
	version integer default 0,
	fecha_crea datetime default now(),
	fecha_act datetime default null,
	crea_por varchar(60) default 'SISTEMA',
	act_por varchar(60) default null,
	ubi_ini bigint(20),
	ubi_final bigint(20),
	equi_id bigint(20),
	distancia bigint(20),
	time_ini datetime,
	time_fin datetime,
	time_estim bigint,
	PRIMARY KEY(id),
	CONSTRAINT fk_rutas_ubi_ini FOREIGN KEY(ubi_ini) REFERENCES ubicaciones(id),
	CONSTRAINT fk_rutas_ubi_fin FOREIGN KEY(ubi_fin) REFERENCES ubicaciones(id),
	CONSTRAINT fk_rutas_equi FOREIGN KEY(equi_id) REFERENCES equipos(id),
);


CREATE TABLE IF NOT EXISTS turnos(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	nom_turno varchar(60) not null,
	horas integer(20) default 8,
	hora_ini time not null,
	hora_fin time not null,
	PRIMARY KEY(id)
)COMMENT 'registra los datos de cada turno';

CREATE TABLE IF NOT EXISTS horarios(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
    fecha_asig datetime,
    turno_id bigint(20),
    ope_id bigint(20),
    isClosed char(1) default 'N',
    PRIMARY KEY(id),
    CONSTRAINT fk_horario_turno FOREIGN KEY(turno_id) REFERENCES turnos(id),
    CONSTRAINT fk_horario_operario FOREIGN KEY(ope_id) REFERENCES operadores(id)
)COMMENT 'registrar los horarios por cada operario en funcion de su turno'; 

alter table horarios add column hora_ini time after ope_id;
alter table horarios add column hora_fin time after hora_ini;


CREATE TABLE IF NOT EXISTS equi_util_hist(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
    horario_id bigint(20),
    equi_id bigint(20),
    fecha_abast datetime,
    cant_comb varchar(20),
    horometro varchar(20),
    hodometro varchar(20),
    kilometraje varchar(20),
    primary key(id),
    CONSTRAINT fk_equi_util_hist_horario FOREIGN KEY(horario_id) REFERENCES horarios(id),
    CONSTRAINT equi_util_hist_equipo FOREIGN KEY(equi_id) REFERENCES equipos(id)
);

----------------------------------------------------------------------------------------------------
CREATE TABLE applicationparameter (
   code varchar(15) NOT NULL,
   crea_por varchar(60) DEFAULT NULL,
   fecha_crea datetime DEFAULT NULL,
   description varchar(80) DEFAULT NULL,
   textValue varchar(250) DEFAULT NULL,
   intValue int(10) unsigned DEFAULT NULL,
   doubleValue decimal(20,6) DEFAULT NULL,
   startInterval varchar(30) DEFAULT NULL,
   endInterval varchar(30) DEFAULT NULL,
   activeIndicator char(1) NOT NULL DEFAULT 'Y',
   PRIMARY KEY (code)
 ) ;
  
insert into applicationparameter(code, crea_por, fecha_crea, description, intvalue) 
 value ('APP_PORT', 'SISTEMA', NOW(), 'Puerto de la aplicacion para recibir mensaje', 9091);
 
insert into applicationparameter(code, crea_por, fecha_crea, description, intvalue) 
 value ('GPS_TIMER', 'SISTEMA', NOW(), 'El intervalo de tiempo de demora en pintar el punto GPS en milisegundos', 1000);
 
 
 create table if not exists equi_act(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	equi_id bigint(20),
	nom_act varchar(60),
	fecha_asig datetime,
	fecha_fin datetime,
	
	primary key(id),
	constraint fk_equi_act_equi foreign key(equi_id) references equipos(id)
 );
 
 create table if not exists materiales(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	tipo_material varchar(60),
	ley1 integer,
	ley2 integer,
	ley3 integer,
	ley4 integer,
	ley5 integer,
	ley6 integer,
	ley7 integer,
	ley8 integer,
	primary key (id)
 );
 
 create table if not exists poligonos(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	ubi_coord_x varchar(60),
	ubi_coord_y varchar(60),
	ubi_coord_z varchar(60),
	mater_id bigint(20),
	nivel varchar(60),
	proyecto varchar(60),
	activo char(1) default 'Y',
	primary key(id),
	constraint fk_polig_mater foreign key(mater_id) references materiales(id)
 );
 
 create table if not exists equi_poly(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	equi_id bigint(20),
	poly_id bigint(20),
	fecha_asig datetime,
	primary key(id),
	constraint fk_equi_poly_equi foreign key(equi_id) references equipos(id),
	constraint fk_equi_poly_poly foreign key(poly_id) references poligonos(id)
 );
 
 create table if not exists usuarios(
 	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
    usu_nom varchar(60),
    usu_ape varchar(60),
    usu_doc varchar(20),
    usu_acc varchar(60),
    usu_pass varchar(60),
    activo char(1) default 'Y',
    primary key(id)
 );
 
 insert into usuarios(usu_nom, usu_ape, usu_doc, usu_acc, usu_pass)
 values('Marius','Grigoras','47891603','marius.grigoras','123456789');
 insert into usuarios(usu_nom, usu_ape, usu_doc, usu_acc, usu_pass)
 values('Mihai','Grigoras','79089284','mihai.grigoras','123456789');
 insert into usuarios(usu_nom, usu_ape, usu_doc, usu_acc, usu_pass)
 values('Marina','Grigoras','79704917','marina.grigoras','123456789');
 
 create table if not exists roles(
 	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
	rol_nom varchar(60),
	primary key (id)
);
insert into roles(rol_nom) values('admin');
insert into roles(rol_nom) values('invitado');
insert into roles(rol_nom) values('operario');


create table if not exists usu_rol(
	id bigint(20) not null auto_increment,
    version integer default 0,
    fecha_crea datetime default now(),
    fecha_act datetime default null,
    crea_por varchar(60) default 'SISTEMA',
    act_por varchar(60) default null,
    usu_id bigint(20) not null,
    rol_id bigint(20) not null,
    fecha_asig datetime,
    valid char(1) default 'Y',
    primary key (id),
    constraint fk_usu_rol_usuarios foreign key(usu_id) references usuarios(id),
    constraint fk_usu_rol_roles foreign key(rol_id) references roles(id)
);

insert into usu_rol(usu_id, rol_id, fecha_asig) values (1, 1, now());
insert into usu_rol(usu_id, rol_id, fecha_asig) values (2, 3, now());
insert into usu_rol(usu_id, rol_id, fecha_asig) values (3, 2, now());