create table product (id bigint not null auto_increment, description varchar(255) not null, price double precision not null, stock integer not null, primary key (id)) ENGINE=InnoDB;
create table visitor (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) ENGINE=InnoDB;
