Create new admin user
---------------------
su -
mysql
CREATE USER 'admin02'@'localhost' IDENTIFIED BY '*****';
SELECT User, Host FROM mysql.user;
--DROP USER 'admin02'@'localhost';
CREATE DATABASE testdb;
SHOW databases
--DROP DATABASE testdb;
GRANT ALL ON testdb.* TO 'admin02'@'localhost';
SHOW GRANTS FOR 'admin02'@'localhost';
quit

Create table using new admin account
------------------------------------
mysql -h localhost -u admin02 -p
USE testdb;
CREATE TABLE test00 (id INT, name VARCHAR(20));
show tables