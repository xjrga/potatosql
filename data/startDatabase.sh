#!/bin/sh

java -cp ../lib/hsqldb-2.5.1.jar org.hsqldb.server.Server --database.0 file:database/potatosql --dbname.0 database

