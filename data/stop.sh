#!/bin/bash

java -jar ../lib/sqltool-2.7.2.jar --rcFile=sqlTool.rc --sql 'SHUTDOWN COMPACT;' database
