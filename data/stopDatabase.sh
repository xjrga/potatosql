#!/bin/bash

java -jar ../lib/sqltool-2.7.3.jar --rcFile=sqlTool.rc --sql 'SHUTDOWN COMPACT;' database
