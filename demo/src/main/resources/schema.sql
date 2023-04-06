-- 스키마(my_db) 생성
-- create schema my_db;

-- 테이블 존재 시 삭제
drop table if exists event_attached_photo_ids;

drop table if exists event_attached_photo_ids_aud;

drop table if exists event_aud;

drop table if exists event_points;

drop table if exists event;

drop table if exists event_points_aud;

drop table if exists hibernate_sequence;

drop table if exists place;

drop table if exists point;

drop table if exists revinfo;

drop table if exists user;


-- 테이블 생성
create table my_db.hibernate_sequence
(
    next_val bigint null
);

create table my_db.place
(
    id      varchar(255) not null
        primary key,
    content varchar(255) null,
    title   varchar(255) null
) default character set UTF8;

create table my_db.point
(
    id         bigint       not null
        primary key,
    comment    varchar(255) null,
    score      bigint       null,
    score_type varchar(255) null
) default character set UTF8;

create table my_db.revinfo
(
    rev      int auto_increment
        primary key,
    revtstmp bigint null
) default character set UTF8;

create table my_db.event_attached_photo_ids_aud
(
    rev                int          not null,
    event_id           varchar(255) not null,
    attached_photo_ids varchar(255) not null,
    revtype            tinyint      null,
    primary key (rev, event_id, attached_photo_ids),
    constraint FKnvgp4kyjd2blx3yq49uc4iy3f
        foreign key (rev) references my_db.revinfo (rev)
) default character set UTF8;

create table my_db.event_aud
(
    id        varchar(255) not null,
    rev       int          not null,
    revtype   tinyint      null,
    action    varchar(255) null,
    content   varchar(255) null,
    review_id varchar(255) null,
    type      varchar(255) null,
    place_id  varchar(255) null,
    user_id   varchar(255) null,
    primary key (id, rev),
    constraint FK76r0s14ewob41mu1pe3qdbdke
        foreign key (rev) references my_db.revinfo (rev)
) default character set UTF8;

create table my_db.event_points_aud
(
    rev       int          not null,
    event_id  varchar(255) not null,
    points_id bigint       not null,
    revtype   tinyint      null,
    primary key (rev, event_id, points_id),
    constraint FKr2lul05k996s79k3p1awhqwmx
        foreign key (rev) references my_db.revinfo (rev)
) default character set UTF8;

create table my_db.user
(
    id          varchar(255) not null
        primary key,
    login_name  varchar(255) null,
    name        varchar(255) null,
    password    varchar(255) null,
    total_point bigint       null
) default character set UTF8;

create table my_db.event
(
    id          varchar(255) not null
        primary key,
    created_at  datetime(6)  not null,
    modified_at datetime(6)  not null,
    action      varchar(255) null,
    content     varchar(255) null,
    review_id   varchar(255) null,
    type        varchar(255) null,
    place_id    varchar(255) null,
    user_id     varchar(255) null,
    constraint FKi8bsvlthqr8lngsyshiqsodak
        foreign key (user_id) references my_db.user (id),
    constraint FKpuvix4lexrakgdlt8si1tbtxv
        foreign key (place_id) references my_db.place (id)
) default character set UTF8;

-- event의 review_id 인덱스 생성
create index event_review_id_index
    on my_db.event (review_id);

create table my_db.event_attached_photo_ids
(
    event_id           varchar(255) not null,
    attached_photo_ids varchar(255) null,
    constraint FK3l2nlw1ph4dwcak8m2gtwaxvv
        foreign key (event_id) references my_db.event (id)
) default character set UTF8;

create table my_db.event_points
(
    event_id  varchar(255) not null,
    points_id bigint       not null,
    constraint UK_lk97343vq8yd28lxdhjdkcuky
        unique (points_id),
    constraint FK9b2fcrv7arrtanf7n1n0bdi7e
        foreign key (points_id) references my_db.point (id),
    constraint FKp988hwodwiaoynluk6khk4s5t
        foreign key (event_id) references my_db.event (id)
) default character set UTF8;
