#!/bin/bash

#sed -f sedscr ../template/mariadb/*
find ../template/mariadb -name '*.ftl' -exec sed -f sedscr {} \;
