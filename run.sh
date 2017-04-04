#!/bin/bash
echo 'Building project...'
mvn package -q -B

if [ $? -eq 0 ]; then
  echo 'Done, running project package...'
  clear
  java -jar target/tp1-1.0-SNAPSHOT.jar
fi
