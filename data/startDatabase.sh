#!/bin/sh

java -cp /home/jr/Projects/Libraries/hsqldb/hsqldb-2.4.1/hsqldb/lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:database/potatosql --dbname.0 database
