#!/bin/bash

psql --username=$POSTGRES_USER --dbname=polus_db <<- EOSQL
COPY transport("type",characteristics,"name",car_number)
FROM '/src/Transport.csv'
DELIMITER ','
CSV HEADER;
EOSQL