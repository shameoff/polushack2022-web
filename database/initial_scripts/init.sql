CREATE TABLE AppUser(
    id bigserial NOT NULL primary key,
    role char NOT NULL,
    first_name varchar(70) NOT NULL,
    last_name varchar(70) NOT NULL,
    email varchar(370) NOT NULL,
    status_now varchar(70) NOT NULL
);
CREATE TABLE TransportType (
    id bigserial primary key,
    transport_type_name varchar(30) NOT NULL UNIQUE
);
CREATE TABLE AppUserTransportType (
    app_user_id bigint NOT NULL references AppUser(id),
    transport_type_id bigint NOT NULL references TransportType(id)
);
CREATE TABLE Transport (
    id bigserial primary key,
    transport_type varchar(30) NOT NULL references TransportType(transport_type_name),
    transport_name varchar(100),
    status_now varchar(20)
);
CREATE TABLE WorkHistory (
    id bigserial primary key,
    worker_id bigint NOT NULL references AppUser(id),
    transport_id bigint NOT NULL references Transport(id),
    when_start date,
    end_date date,
    location_where varchar,
    is_active boolean
);
CREATE TABLE Report (
    id bigserial primary key,
    worker_id bigint references AppUser(id),
    transport_id bigint references Transport(id),
    additional_info text
);

CREATE TABLE Request (
    id bigserial primary key,
    requster_id bigint NOT NULL references AppUser(id),
    worker_id bigint NOT NULL references AppUser(id),
    transport_type varchar NOT NULL references TransportType(transport_type_name),
    location_where text NOT NULL, -- ТУТ НАДО ПОМЕНЯТЬ ТИП НА СОСТАВНОЙ ИЛИ ДРУГОЙ
    time_frame date NOT NULL,
    status_now varchar(10)
);