CREATE TABLE Record
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dateTime DATETIME NOT NULL,
    sensorId INT(11) NOT NULL,
    chipId INT(11) NOT NULL
);
CREATE UNIQUE INDEX Record_id_uindex ON Record (id);
