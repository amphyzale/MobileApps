
create table user_data (
    id varchar(255) not null,
    name varchar(255) not null,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255) not null,
    status varchar(255) default 'ACTIVE',
    email varchar(255) not null,
    created timestamp,
    updated timestamp,
    user_pic varchar(255),
    gender varchar(255),
    locale varchar(255),
    last_visit timestamp,
    primary key (id)
);

create table roles (
    id int8 not null,
    name varchar(255) not null,
    created timestamp,
    updated timestamp,
    status varchar(255) default 'ACTIVE',
    primary key (id)
);

create table user_roles (
    user_id varchar(255),
    role_id int8
);

alter table if exists user_roles
    add constraint fk_user_roles_users
        foreign key (role_id) references roles;

alter table if exists user_roles
    add constraint fk_user_users_role
        foreign key (user_id) references user_data;
