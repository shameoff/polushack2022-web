#!/bin/bash

psql --username=$POSTGRES_USER --dbname=polus_db <<- EOSQL
INSERT INTO TransportType("type") VALUES ('Парк погрузчиков');
INSERT INTO TransportType("type") VALUES ('Парк автовышек');
INSERT INTO TransportType("type") VALUES ('Парк кранов');
EOSQL