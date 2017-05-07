#!/bin/bash

# make sure you run the script directly

# compile Java
# : is used to specify multiple jars
rm -rf bin/*
javac -d bin -cp src src/main/java/vanilla/Main.java
javac -d bin -cp src src/main/java/vanilla/Test.java
# exclude the R codes
rm -rf bin/report

# run Java
java -cp bin main.java.vanilla.Test
java -cp bin main.java.vanilla.Main

# compile R
R -e 'setwd("src/report"); source("build.R"); build()' > /dev/null 2>&1