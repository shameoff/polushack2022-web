#!/bin/bash

psql --username=$POSTGRES_USER --dbname=polus_db <<- EOSQL
INSERT INTO transport_type("type") VALUES ('Парк погрузчиков');
INSERT INTO transport_type("type") VALUES ('Парк автовышек');
INSERT INTO transport_type("type") VALUES ('Парк кранов');
EOSQL