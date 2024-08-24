#!/bin/sh

java -cp ../lib/hsqldb-2.7.3.jar org.hsqldb.server.Server --port 9001 --database.0 file:database/potatosql --dbname.0 database

