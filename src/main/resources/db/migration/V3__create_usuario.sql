create table "bct"."tb_operacao"
(
  id integer constraint tb_operacao_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo varchar not null
, uri varchar not null
, descricao varchar not null
);


create sequence "bct"."seq_tb_operacao"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;


create table "bct"."tb_perfil"
(
  id integer constraint tb_perfil_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, nome varchar(25) null unique
);

create sequence "bct"."seq_tb_perfil"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

-- perfil operacao

create table "bct"."tb_perfil_ope"
(
  id integer constraint tb_perfil_ope_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, id_perfil integer not null
, id_operacao integer not null
);


create sequence "bct"."seq_tb_perfil_ope"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "bct"."tb_perfil_ope" add foreign key (id_perfil) references "bct".tb_perfil(id);
alter table "bct"."tb_perfil_ope" add foreign key (id_operacao) references "bct".tb_operacao(id);

-- USUARIO
create table "bct"."tb_usuario"
(
  id integer constraint tb_usuario_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo varchar null
, email varchar null
, nome varchar null
, senha varchar null
);

create sequence "bct"."seq_tb_usuario"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

-- PERFIL_USUARIO
create table "bct".tb_perfil_usuario
(
  id integer constraint tb_perfil_usuario_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, id_perfil integer not null
, id_usuario integer not null
);


create sequence "bct".seq_tb_perfil_usuario  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "bct".tb_perfil_usuario add foreign key (id_perfil) references "bct".tb_perfil(id);
alter table "bct".tb_perfil_usuario add foreign key (id_usuario) references "bct".tb_usuario(id);

-- RECUPERACAO DE CONTA
create table "bct".tb_recuperar_conta
(
  id integer constraint tb_recuperar_conta_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, codigo_recuperacao varchar(50) unique not null
, expiracao timestamp not null
, id_usuario integer not null
);

create sequence "bct".seq_tb_recuperar_conta  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;

alter table "bct".tb_recuperar_conta add foreign key (id_usuario) references "bct".tb_usuario(id);

create table "bct".oauth_client_details (
    id integer constraint tb_oauth_client_details_pk primary key not null
    , inclusao timestamp not null
    , alteracao timestamp
    , status integer not null ,
    client_id VARCHAR(256) UNIQUE ,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

create sequence "bct".seq_oauth_client_details  minvalue 1 maxvalue 999999999999999 increment by 1 start with 1000;