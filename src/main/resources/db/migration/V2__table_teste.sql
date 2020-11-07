create table "bct"."tb_teste"
(
  id integer constraint tb_teste_pk primary key not null
, inclusao timestamp not null
, alteracao timestamp
, status integer not null
, nome varchar not null
);

create sequence "bct"."seq_tb_teste"  minvalue 1 maxvalue 999999999999999 increment by 1 start with 2;