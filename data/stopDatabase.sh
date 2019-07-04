#!/bin/bash

java -jar ../lib/sqltool.jar --rcFile=sqlTool.rc --sql 'SHUTDOWN COMPACT;' database
