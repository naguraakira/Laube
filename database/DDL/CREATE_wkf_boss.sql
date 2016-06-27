--
-- CREATE_wkf_boss.sql
--
-- DROP TABLE wkf_boss;

CREATE  TABLE wkf_boss
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,unit_code VARCHAR (10)  NOT NULL
 ,user_code VARCHAR (10)  NULL
 ,application_form_code VARCHAR (10)  NULL 
 ,boss_company_code VARCHAR (10)  NULL
 ,boss_unit_code VARCHAR (10)  NULL
 ,boss_user_code VARCHAR (10)  NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_boss_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_boss IS 'boss master';
COMMENT ON COLUMN wkf_boss.id IS 'sarrogate key';
COMMENT ON COLUMN wkf_boss.company_code IS 'company code';
COMMENT ON COLUMN wkf_boss.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_boss.user_code IS 'employee number';
COMMENT ON COLUMN wkf_boss.application_form_code IS 'application code';
COMMENT ON COLUMN wkf_boss.boss_company_code IS 'boss company code';
COMMENT ON COLUMN wkf_boss.boss_unit_code IS 'boss unit code';
COMMENT ON COLUMN wkf_boss.boss_user_code IS 'boss employee number';
COMMENT ON COLUMN wkf_boss.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_boss.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_boss.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_boss.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_boss_unique_idx1 on wkf_boss(company_code,unit_code,user_code);
