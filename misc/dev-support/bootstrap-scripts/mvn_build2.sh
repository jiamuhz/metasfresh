#!/bin/bash
set -e

mvn --file ../../../backend/pom.xml -T2C -DskipTests --settings ../maven/settings.xml clean install
