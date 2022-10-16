CREATE TYPE public.worker_status AS ENUM
    ('AVAILABLE', 'RESTING', 'NON_WORKING', 'VACATION', 'BUSY', 'FIRED');

CREATE TABLE IF NOT EXISTS public."user"(
    id bigserial primary key,
    "role" char NOT NULL,
    first_name varchar(70) NOT NULL,
    last_name varchar(70) NOT NULL,
    email varchar(370) NOT NULL,
    "status" worker_status NOT NULL,
    password_hash varchar NOT NULL,
    work_start_time time NOT NULL,
    work_end_time time NOT NULL,
    rest_start_time time NOT NULL,
    rest_end_time time NOT NULL,
    latitude real,
    longitude real
);
CREATE TABLE IF NOT EXISTS public.transport_type (
    id bigserial primary key,
    "type" varchar(50) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS public.user_transport_type (
    "user_id" bigint NOT NULL references "user"(id),
    transport_type_id bigint NOT NULL references transport_type(id),
    primary key ("user_id", transport_type_id)
);
CREATE TABLE IF NOT EXISTS public.transport (
    "type" varchar(30) NOT NULL references transport_type("type"),
    characteristics varchar(200),
    "name" varchar(100) NOT NULL,
    car_number varchar(30) primary key,
    "status" varchar(20)
);
CREATE TABLE IF NOT EXISTS public.work_history (
    id bigserial primary key,
    worker_id bigint NOT NULL references "user"(id),
    transport_id varchar(30) NOT NULL references transport(car_number),
    "time_start" timestamp,
    "time_end" timestamp,
    "location" varchar,
    is_active boolean,
    latitude real,
    longitude real
);
CREATE TABLE IF NOT EXISTS public.report (
    id bigserial primary key,
    worker_id bigint references "user"(id),
    transport_id varchar(30) references transport(car_number),
    "description" text
);

CREATE TABLE IF NOT EXISTS public.request (
    id bigserial primary key,
    requster_id bigint NOT NULL references "user"(id),
    worker_id bigint NOT NULL references "user"(id),
    transport_type varchar NOT NULL references transport_type("type"),
    characteristics varchar(200) NOT NULL,
    "time_start" timestamp,
    "time_end" timestamp,
    "status" varchar(10),
    latitude real,
    longitude real,
    "description" text
);
