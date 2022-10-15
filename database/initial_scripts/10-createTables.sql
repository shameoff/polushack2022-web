CREATE TABLE "User"(
    id bigserial primary key,
    "role" char NOT NULL,
    first_name varchar(70) NOT NULL,
    last_name varchar(70) NOT NULL,
    email varchar(370) NOT NULL,
    "status" varchar(70) NOT NULL,
    password_hash varchar NOT NULL,
    work_start_time timestamp NOT NULL,
    work_end_time timestamp NOT NULL,
    rest_start_time timestamp NOT NULL,
    rest_end_time timestamp NOT NULL,
    latitude real,
    longitude real
);
CREATE TABLE TransportType (
    id bigserial primary key,
    "type" varchar(50) NOT NULL UNIQUE
);
CREATE TABLE UserTransportType (
    "user_id" bigint NOT NULL references "User"(id),
    transport_type_id bigint NOT NULL references TransportType(id),
    primary key ("user_id", transport_type_id)
);
CREATE TABLE Transport (
    "type" varchar(30) NOT NULL references TransportType("type"),
    characteristics varchar(200),
    "name" varchar(100) NOT NULL,
    car_number varchar(30) primary key,
    "status" varchar(20)
);
CREATE TABLE WorkHistory (
    id bigserial primary key,
    worker_id bigint NOT NULL references "User"(id),
    transport_id varchar(30) NOT NULL references Transport(car_number),
    "time_start" timestamp,
    "time_end" timestamp,
    "location" varchar,
    is_active boolean,
    latitude real,
    longitude real
);
CREATE TABLE Report (
    id bigserial primary key,
    worker_id bigint references "User"(id),
    transport_id varchar(30) references Transport(car_number),
    "description" text
);

CREATE TABLE Request (
    id bigserial primary key,
    requster_id bigint NOT NULL references "User"(id),
    worker_id bigint NOT NULL references "User"(id),
    transport_type varchar NOT NULL references TransportType("type"),
    characteristics varchar(200) NOT NULL references ,
    "time_start" timestamp,
    "time_end" timestamp,
    "status" varchar(10),
    latitude real,
    longitude real,
    "description" text
);
