create table tb_product
(
    id                bigint         not null,
    active            boolean,
    identify_code     varchar(255)   not null,
    name              varchar(255)   not null,
    registration_date timestamp,
    total_quantity    int            not null,
    update_date       timestamp,
    value             decimal(19, 2) not null,
    category_id       bigint         not null,
    primary key (id)
);
