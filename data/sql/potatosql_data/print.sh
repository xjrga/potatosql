#!/bin/sh

if [ -f output.sql ] ; then
rm output.sql
fi

for i in `ls -v`; do
if [ $i != print.sh ] && [ $i != output.sql ] ; then
cat $i >> output.sql
echo "\n" >> output.sql
fi
done;
