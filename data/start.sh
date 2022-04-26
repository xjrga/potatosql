#!/bin/sh

java -cp ../lib/hsqldb-2.6.1.jar org.hsqldb.server.Server --port 9001 --database.0 file:database/production/potatosql --dbname.0 database

