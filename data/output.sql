

#!/bin/bash

java -cp ../lib/hsqldb-2.7.1.jar org.hsqldb.util.DatabaseManagerSwing




urlid database
url jdbc:hsqldb:hsql://localhost:9001/database;shutdown=true;
username sa
password


#!/bin/sh

java -cp ../lib/hsqldb-2.7.1.jar org.hsqldb.server.Server --port 9001 --database.0 file:database/production/potatosql --dbname.0 database



#!/bin/bash

java -jar ../lib/sqltool-2.7.1.jar --rcFile=sqlTool.rc --sql 'SHUTDOWN COMPACT;' database


