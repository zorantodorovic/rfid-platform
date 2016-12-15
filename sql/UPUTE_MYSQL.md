# mysql naredbe u terminalu:

> mysql -u root -p 
> toor
> create database iot_platform_records;
> create database iot_platform_logic;  // zasad se ne koristi


# mysql postavke za jdbc:
src/main/resources/application.properties:

jdbc.driverClassName = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://localhost:3306/iot_platform_logic?autoReconnect=true&useSSL=false
jdbc.username = root
jdbc.password = toor
