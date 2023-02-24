create table customer
(
    id           int          not null
        primary key,
    customer_key varchar(255) null,
    name         varchar(255) null,
    last_name    varchar(255) null,
    constraint UK_mgu9s2wqqgbjyg5nvnbjf8jhg
        unique (customer_key)
)
    collate = utf8mb4_0900_ai_ci;

create table customer_order
(
    id               bigint         not null
        primary key,
    crated_by        varchar(255)   null,
    created_date     datetime(6)    null,
    last_modify_date datetime(6)    null,
    modified_by      varchar(255)   null,
    price            decimal(38, 2) not null,
    version          int            not null,
    customer_id      int            null,
    constraint FKf9abd30bhiqvugayxlpq8ryq9
        foreign key (customer_id) references customer (id)
)
    collate = utf8mb4_0900_ai_ci;

create table customer_order_seq
(
    next_val bigint null
)
    collate = utf8mb4_0900_ai_ci;

create table customer_seq
(
    next_val bigint null
)
    collate = utf8mb4_0900_ai_ci;

