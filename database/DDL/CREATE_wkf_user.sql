--
-- CREATE_wkf_user.sql
--
-- DROP TABLE wkf_user;

CREATE  TABLE wkf_user
 (
  id BIGSERIAL
 ,company_code VARCHAR (10)  NOT NULL
 ,user_code VARCHAR (10)  NULL
 ,user_name VARCHAR (30)  NULL
 ,create_date_time TIMESTAMP(0) NOT NULL
 ,create_user_id VARCHAR (18)  NOT NULL
 ,update_date_time TIMESTAMP(0)  default current_timestamp
 ,update_user_id VARCHAR (18)  NOT NULL

 ,CONSTRAINT wkf_user_pkey PRIMARY KEY (id)
)
 TABLESPACE pg_default;

COMMENT ON TABLE  wkf_user IS 'employee master';
COMMENT ON COLUMN wkf_user.id IS 'surrogate key';
COMMENT ON COLUMN wkf_user.company_code IS 'company code';
COMMENT ON COLUMN wkf_user.user_code IS 'employee number';
COMMENT ON COLUMN wkf_user.user_name IS 'employee Name';
COMMENT ON COLUMN wkf_user.create_date_time IS 'create date';
COMMENT ON COLUMN wkf_user.create_user_id IS 'owner of record';
COMMENT ON COLUMN wkf_user.update_date_time IS 'update date';
COMMENT ON COLUMN wkf_user.update_user_id IS 'record of the update';

CREATE UNIQUE INDEX wkf_user_unique_idx1 on wkf_user(company_code,user_code);
