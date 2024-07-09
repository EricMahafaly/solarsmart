create table if not exists battery
(
    id              serial,
    relais_state_id bigint not null,
    module_id       bigint not null,
    marque          varchar(255),
    puissance       double precision,
    voltage         double precision
);

alter table battery add constraint pk_battery primary key (id);

create table if not exists battery_data
(
    id          serial,
    date        timestamp,
    tension     double precision,
    puissance   double precision,
    courant     double precision,
    energy      double precision,
    pourcentage double precision,
    battery_id  bigint not null
);

create index if not exists idx88ydrl97hegsrlq9cg4353cpx
    on battery_data (date);

create index if not exists battery_data_date_index
    on battery_data (date);

alter table battery_data
    add constraint pk_battery_data
        primary key (id);

alter table battery_data
    add constraint fk_battery_data_on_battery
        foreign key (battery_id) references battery;

create table if not exists customer
(
    id              serial,
    name            varchar(255),
    prenom          varchar(255),
    email           varchar(255),
    password        varchar(255),
    image           varchar(255),
    code_postal     varchar(255),
    address         varchar(255),
    module_id       bigint,
    subscription_id bigint
);

alter table customer
    add constraint pk_customer
        primary key (id);

alter table customer
    add constraint uk_lcfaj9qlnvws71vntm4inujxl
        unique (module_id);

create table if not exists module
(
    id           serial,
    reference    varchar(75),
    ssid         varchar(25),
    password     varchar(70),
    created_date timestamp(6)
);

alter table module
    add constraint pk_module
        primary key (id);

alter table battery
    add constraint fk_battery_on_module
        foreign key (module_id) references module;

alter table customer
    add constraint fkhqpypb5py7lbn7qmuhkmok5rm
        foreign key (module_id) references module;

create table if not exists notification
(
    id        serial,
    seen      boolean,
    message   varchar(255),
    date      timestamp,
    module_id bigint not null
);

alter table notification
    add constraint pk_notification
        primary key (id);

alter table notification
    add constraint fk_notification_on_module
        foreign key (module_id) references module;

create table if not exists panneau
(
    id              serial,
    relais_state_id bigint not null,
    module_id       bigint not null,
    marque          varchar(255),
    puissance       double precision,
    voltage         double precision
);

alter table panneau
    add constraint pk_panneau
        primary key (id);

alter table panneau
    add constraint fk_panneau_on_module
        foreign key (module_id) references module;

create table if not exists panneau_data
(
    id         serial,
    date       timestamp,
    tension    double precision,
    puissance  double precision,
    courant    double precision,
    production double precision,
    panneau_id bigint not null
);

create index if not exists panneau_data_date_index
    on panneau_data (date);

alter table panneau_data
    add constraint pk_panneau_data
        primary key (id);

alter table panneau_data
    add constraint fk_panneau_data_on_panneau
        foreign key (panneau_id) references panneau;

create table if not exists planning_battery
(
    id         serial,
    date_debut timestamp,
    date_fin   timestamp,
    done       boolean,
    energy     double precision,
    battery_id bigint not null
);

alter table planning_battery
    add constraint pk_planning_battery
        primary key (id);

alter table planning_battery
    add constraint fk_planning_battery_on_battery
        foreign key (battery_id) references battery;

create table if not exists planning_prise
(
    id           serial,
    date_debut   timestamp,
    date_fin     timestamp,
    done         boolean,
    consommation double precision,
    prise_id     bigint not null
);

alter table planning_prise
    add constraint pk_planning_prise
        primary key (id);

create table if not exists prise
(
    id              serial,
    relais_state_id bigint not null,
    module_id       bigint not null
);

alter table prise
    add constraint pk_prise
        primary key (id);

alter table planning_prise
    add constraint fk_planning_prise_on_prise
        foreign key (prise_id) references prise;

alter table prise
    add constraint fk_prise_on_module
        foreign key (module_id) references module;

create table if not exists prise_data
(
    id           serial,
    date         timestamp,
    tension      double precision,
    puissance    double precision,
    courant      double precision,
    consommation double precision,
    prise_id     bigint not null
);

create index if not exists prise_data_date_index
    on prise_data (date);

alter table prise_data
    add constraint pk_prise_data
        primary key (id);

alter table prise_data
    add constraint fk_prise_data_on_prise
        foreign key (prise_id) references prise;

create table if not exists relais_state
(
    id      serial,
    state   varchar(255),
    active  boolean,
    couleur varchar(255),
    valeur  integer
);

alter table relais_state
    add constraint pk_relais_state
        primary key (id);

alter table battery
    add constraint fk_battery_on_relais_state
        foreign key (relais_state_id) references relais_state;

alter table panneau
    add constraint fk_panneau_on_relais_state
        foreign key (relais_state_id) references relais_state;

alter table prise
    add constraint fk_prise_on_relais_state
        foreign key (relais_state_id) references relais_state;

create table if not exists reference_battery
(
    id            serial,
    checked_date  date             default CURRENT_DATE                       not null,
    checked_state bigint           default 0                                  not null,
    duration      time             default '08:00:00'::time without time zone not null,
    energy        double precision default 80.0                               not null,
    module_id     bigint
);

alter table reference_battery
    add primary key (id);

alter table reference_battery
    add constraint uk_699mi8f737blel1ugm3x5e5wl
        unique (module_id);

alter table reference_battery
    add constraint fkdnlgyhshl8o2396d4s2wysj76
        foreign key (module_id) references module;

create table if not exists reference_prise
(
    id            serial,
    checked_date  date             default CURRENT_DATE                       not null,
    checked_state bigint           default 0                                  not null,
    consommation  double precision default 80                                 not null,
    duration      time             default '08:00:00'::time without time zone not null,
    module_id     bigint
);

alter table reference_prise
    add primary key (id);

alter table reference_prise
    add constraint uk_1d098bjvn3uu5nm5vfkqji7ix
        unique (module_id);

alter table reference_prise
    add constraint fkbd9c93rohcwmo5lr8hajo0gf8
        foreign key (module_id) references module;

create table if not exists subscription
(
    id               serial,
    alert            boolean,
    assistance       integer,
    entretien        varchar(255),
    monitoring       varchar(255),
    name             varchar(255),
    planing          boolean,
    remote_control   varchar(255),
    stockage_mensuel double precision
);

alter table subscription
    add primary key (id);

create table if not exists subscription_price
(
    id                serial,
    price             numeric(10, 2),
    subscription_type varchar(255),
    subscription_id   bigint
);

alter table subscription_price
    add primary key (id);

alter table customer
    add constraint fk56xonhawfb33vylya6u2vm818
        foreign key (subscription_id) references subscription_price;

alter table subscription_price
    add constraint fkhysv1clxh58nvedyma9clqyt9
        foreign key (subscription_id) references subscription;

alter table subscription_price
    add constraint subscription_price_subscription_type_check
        check ((subscription_type)::text = ANY
               ((ARRAY ['MENSUEL'::character varying, 'SEMESTRIEL'::character varying, 'ANNUEL'::character varying])::text[]));

create table if not exists administrator
(
    id        serial,
    email     varchar(255),
    password  varchar(255),
    last_name varchar(255),
    name      varchar(255)
);

alter table administrator
    add primary key (id);

create table if not exists module_info
(
    id          serial,
    description text,
    name        varchar(20),
    module_id   integer not null
);

alter table module_info
    add constraint module_info_pk
        primary key (id);

alter table module_info
    add constraint module_info_module_id_fk
        foreign key (module_id) references module;

create table if not exists module_info_detail
(
    id             serial,
    module_info_id integer not null,
    description    text,
    key            varchar(30),
    value          varchar(50)
);

alter table module_info_detail
    add constraint module_info_detail_pk
        primary key (id);

alter table module_info_detail
    add constraint module_info_detail_module_info_id_fk
        foreign key (module_info_id) references module_info;

create table if not exists rating
(
    id          serial,
    score       integer,
    customer_id integer,
    comment     integer,
    date        timestamp default now()
);

alter table rating
    add constraint rating_pk
        primary key (id);

alter table rating
    add constraint rating_customer_id_fk
        foreign key (customer_id) references customer;

create table if not exists report_state
(
    id    serial,
    state varchar(20),
    value integer
);

alter table report_state
    add constraint report_state_pk
        primary key (id);

create table if not exists report
(
    id           serial,
    state_id     integer,
    created_date timestamp default now(),
    closed_date  timestamp,
    customer_id  integer,
    description  text,
    priority     integer,
    admin_id     integer
);

alter table report
    add constraint report_pk
        primary key (id);

alter table report
    add constraint report_report_state_id_fk
        foreign key (state_id) references report_state;

alter table report
    add constraint report_customer_id_fk
        foreign key (customer_id) references customer;

alter table report
    add constraint report_administrator_id_fk
        foreign key (admin_id) references administrator;

create table if not exists report_comment
(
    id          serial,
    comment     text,
    sender_type integer,
    date        timestamp default now() not null,
    report_id   integer
);

alter table report_comment
    add constraint report_comment_pk
        primary key (id);

alter table report_comment
    add constraint report_comment_report_id_fk
        foreign key (report_id) references report;

