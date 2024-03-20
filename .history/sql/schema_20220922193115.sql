CREATE TABLE empleado (
    id_empleado         MEDIUMINT NOT NULL AUTO_INCREMENT,
    nombre              VARCHAR(100) NOT NULL,
    apellidos           VARCHAR(100) NOT NULL,
    fecha_nacimiento    DATE NOT NULL,
    puesto              VARCHAR(100) NOT NULL,
    email               VARCHAR(320),
    PRIMARY KEY (id_empleado)
);