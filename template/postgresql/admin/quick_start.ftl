Create new admin user
---------------------
su -
su - postgres
psql
create user admin with encrypted password '*****';
\du
--drop user admin;
create database testdb with owner = 'admin';
\l
--drop database testdb;
\q

Create table using new admin account
-------------------------------------
psql -h localhost -U admin -d testdb
create table test00 (id INTEGER, name VARCHAR(20));
--select schemaname, tablename from pg_tables where schemaname = 'public';
