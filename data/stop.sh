#!/bin/bash

java -jar ../lib/sqltool-2.7.1.jar --rcFile=sqlTool.rc --sql 'SHUTDOWN COMPACT;' database
