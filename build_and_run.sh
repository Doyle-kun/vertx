#!/usr/bin/env bash
mvn package -DskipTests; java -jar target/olx-offer-app-1.0-SNAPSHOT-fat.jar
