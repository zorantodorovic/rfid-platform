CREATE TABLE Chip
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20)
);
CREATE UNIQUE INDEX Chip_id_uindex ON Chip (id);
CREATE TABLE Query
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT(11) NOT NULL,
    sensorId INT(11),
    fromDateTime DATETIME,
    toDateTime DATETIME,
    count INT(11),
    CONSTRAINT Query_User_id_fk FOREIGN KEY (userId) REFERENCES User (id),
    CONSTRAINT Query_Sensor_id_fk FOREIGN KEY (sensorId) REFERENCES Sensor (id)
);
CREATE UNIQUE INDEX Query_id_uindex ON Query (id);
CREATE INDEX Query_User_id_fk ON Query (userId);
CREATE INDEX Query_Sensor_id_fk ON Query (sensorId);
CREATE TABLE Record
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dateTime DATETIME NOT NULL,
    sensorId INT(11) NOT NULL,
    chipId INT(11) NOT NULL,
    CONSTRAINT Record_Sensor_id_fk FOREIGN KEY (sensorId) REFERENCES Sensor (id),
    CONSTRAINT Record_Chip_id_fk FOREIGN KEY (chipId) REFERENCES Chip (id)
);
CREATE UNIQUE INDEX Record_id_uindex ON Record (id);
CREATE INDEX Record_Chip_id_fk ON Record (chipId);
CREATE INDEX Record_Sensor_id_fk ON Record (sensorId);
CREATE TABLE Sensor
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    sensorType INT(11) NOT NULL,
    userId INT(11) NOT NULL,
    ipAddress VARCHAR(60) NOT NULL,
    CONSTRAINT Sensor_User_id_fk FOREIGN KEY (userId) REFERENCES User (id)
);
CREATE UNIQUE INDEX Sensor_id_uindex ON Sensor (id);
CREATE UNIQUE INDEX Sensor_ipAdress_uindex ON Sensor (ipAddress);
CREATE INDEX Sensor_User_id_fk ON Sensor (userId);
CREATE TABLE Sink
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    uri VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX Sink_id_uindex ON Sink (id);
CREATE TABLE User
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    password VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX User_id_uindex ON User (id);
CREATE UNIQUE INDEX User_username_uindex ON User (username);
CREATE TABLE UserSensorSink
(
    userId INT(11) NOT NULL,
    sinkId INT(11) NOT NULL COMMENT 'connection string to db',
    sensorId INT(11) NOT NULL COMMENT 'SensorId',
    CONSTRAINT `PRIMARY` PRIMARY KEY (userId, sinkId, sensorId),
    CONSTRAINT UserSensorSink_Sensor_id_fk FOREIGN KEY (sensorId) REFERENCES Sensor (id),
    CONSTRAINT UserSensorSink_User_id_fk FOREIGN KEY (userId) REFERENCES User (id),
    CONSTRAINT UserSensorSink_Sink_id_fk FOREIGN KEY (sinkId) REFERENCES Sink (id)
);
CREATE INDEX UserSensorSink_Sensor_id_fk ON UserSensorSink (sensorId);
CREATE INDEX UserSensorSink_Sink_id_fk ON UserSensorSink (sinkId);
