# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table genre (
  name                      varchar(255) not null,
  image                     varchar(255),
  constraint pk_genre primary key (name))
;

create table movie (
  id                        integer not null,
  title                     varchar(255),
  year                      integer,
  duration                  integer,
  description               varchar(500),
  storyline                 varchar(1000),
  director                  varchar(255),
  writers                   varchar(255),
  stars                     varchar(255),
  cover                     varchar(255),
  background                varchar(255),
  movie                     varchar(255),
  trailer                   varchar(255),
  constraint pk_movie primary key (id))
;

create table review (
  id                        integer not null,
  username                  varchar(255),
  movie_id                  integer,
  comment                   varchar(1500),
  rating                    double,
  constraint pk_review primary key (id))
;

create table subscription (
  months                    integer not null,
  name                      varchar(255),
  description               varchar(255),
  price                     double,
  constraint pk_subscription primary key (months))
;

create table user (
  username                  varchar(255) not null,
  email                     varchar(255),
  password                  varchar(255),
  expiration                bigint,
  constraint pk_user primary key (username))
;


create table genre_movie (
  genre_name                     varchar(255) not null,
  movie_id                       integer not null,
  constraint pk_genre_movie primary key (genre_name, movie_id))
;

create table movie_genre (
  movie_id                       integer not null,
  genre_name                     varchar(255) not null,
  constraint pk_movie_genre primary key (movie_id, genre_name))
;
create sequence genre_seq;

create sequence movie_seq;

create sequence review_seq;

create sequence subscription_seq;

create sequence user_seq;




alter table genre_movie add constraint fk_genre_movie_genre_01 foreign key (genre_name) references genre (name) on delete restrict on update restrict;

alter table genre_movie add constraint fk_genre_movie_movie_02 foreign key (movie_id) references movie (id) on delete restrict on update restrict;

alter table movie_genre add constraint fk_movie_genre_movie_01 foreign key (movie_id) references movie (id) on delete restrict on update restrict;

alter table movie_genre add constraint fk_movie_genre_genre_02 foreign key (genre_name) references genre (name) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists genre;

drop table if exists genre_movie;

drop table if exists movie;

drop table if exists movie_genre;

drop table if exists review;

drop table if exists subscription;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists genre_seq;

drop sequence if exists movie_seq;

drop sequence if exists review_seq;

drop sequence if exists subscription_seq;

drop sequence if exists user_seq;

