--
-- CREATE_wkf_unit.sql
--
-- DROP TABLE wkf_unit;

CREATE  TABLE wkf_unit
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,unit_code VARCHAR (10)  NULL
 ,unit_name VARCHAR (30)  NULL
 ,manager_code VARCHAR (10)  NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_unit_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_unit IS 'unit master';
COMMENT ON COLUMN wkf_unit.id IS 'surrogate key';
COMMENT ON COLUMN wkf_unit.company_code IS 'company code';
COMMENT ON COLUMN wkf_unit.unit_code IS 'unit code';
COMMENT ON COLUMN wkf_unit.unit_name IS 'unit name';
COMMENT ON COLUMN wkf_unit.manager_code IS 'responsible party';
COMMENT ON COLUMN wkf_unit.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_unit.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_unit.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_unit.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_unit_unique_idx1 on wkf_unit(company_code,unit_code);
